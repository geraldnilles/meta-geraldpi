#!/usr/bin/env python3

import os

from pywebio import start_server
import pywebio
from pywebio.output import *
from pywebio.pin import *

import sqlite3

def init_db():
    conn = sqlite3.connect('garage_inventory.db')
    cursor = conn.cursor()
    cursor.execute('''CREATE TABLE IF NOT EXISTS inventory (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        name TEXT NOT NULL,
                        quantity INTEGER NOT NULL,
                        section TEXT NOT NULL,
                        grid TEXT NOT NULL
                    )''')
    conn.commit()
    conn.close()

def render_table(arg=None):
    with use_scope("table", clear=True):
        conn = sqlite3.connect('garage_inventory.db')
        cursor = conn.cursor()
        cursor.execute("SELECT id, name, quantity, section, grid FROM inventory WHERE lower(name) LIKE ?", 
                       ('%' + pin.filter + '%',))
        items = cursor.fetchall()
        conn.close()
        table_data = [["Name", "Quantity", "Section", "Coordinates","Actions"]] + [
                [item[1], item[2], item[3], item[4], put_button("Delete", onclick=lambda id=item[0]: delete_item(id))] for item in items
            ]
        
        put_table(table_data)

def add_item():
    name = pin.name
    quantity = pin.quantity
    section = pin.section
    grid = pin.grid

    if not name or not quantity or not section or not grid:
        toast("All fields are required", color="error")
        return

    conn = sqlite3.connect('garage_inventory.db')
    cursor = conn.cursor()
    cursor.execute("INSERT INTO inventory (name, quantity, section, grid) VALUES (?, ?, ?, ?)",
                   (name, quantity, section, grid))
    conn.commit()
    conn.close()
    toast(f"Item '{name}' added successfully!", color="success")
    render_table()
    clear_add_scope()

def delete_item(item_id):
    conn = sqlite3.connect('garage_inventory.db')
    cursor = conn.cursor()
    cursor.execute("DELETE FROM inventory WHERE id=?", (item_id,))
    conn.commit()
    conn.close()
    toast(f"Item deleted successfully!", color="success")
    render_table()

def clear_add_scope():
    pin.name = ""
    pin.quantity = 1
    pin.section = "Work Bench"
    pin.grid = ""

def main():
    """
    Garage Inventory
    """

    """
    Define the UI structure of the page
    """

    init_db()
    
    put_markdown("# Garage Inventory")

    put_tabs([{"title":"View","content":put_scope("view_scope")},
              {"title":"Add","content":put_scope("add_scope")}])

    with use_scope("view_scope") as scope:
        put_input("filter",placeholder="Filter database")
        put_scope("table")
        pin_on_change("filter",onchange=render_table,init_run = True)

    with use_scope("add_scope") as scope:
        put_input("name",label="Name",placeholder="New Item Name")
        put_input("quantity",type="number",label="Quantity",value=1)
        put_select("section",label="Section",options=["Work Bench","Blue Bins","Red Bins","Yellow Bins","Attic"])
        put_input("grid",label="Coordinates",placeholder="i.e. B3")
        put_button("Add",onclick=add_item)


if __name__ == '__main__':
    pywebio.config(theme="dark",css_style=".container { max-width: 1200px; }")
    start_server(main, port=8088, debug=True)

