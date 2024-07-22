#!/usr/bin/env python

import os
import argparse

import pywebio
from pywebio import start_server
import pywebio
from pywebio.output import *
from pywebio.pin import *

import datetime

import sqlite3

DATABASE_FILENAME = "todos.db"

STORE_REGISTRY =[   {"label":"Groceries","value":"groceries","color":"primary"},
                    {"label":"Whole Foods","value":"whole_foods","color":"success"},
                    {"label":"Target","value":"target","color":"danger"},
                    {"label":"Home Depot","value":"home_depot","color":"warning"}
                 ]

CATEGORY_REGISTRY = {"Dairy":     {"color":"info"},
                     "Freezer":   {"color":"primary"},
                     "Meat":      {"color":"danger"},
                     "Produce":   {"color":"success"},
                     "Packaged":  {"color":"secondary"},
                     "Supplies":  {"color":"warning"},
                     "Toiletries":{"color":"secondary"},
                     "Other":     {"color":"secondary"}}


def init_db():
    conn = sqlite3.connect(DATABASE_FILENAME)
    cursor = conn.cursor()
    cursor.execute('''CREATE TABLE IF NOT EXISTS todos (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        name TEXT NOT NULL,
                        category TEXT NOT NULL,
                        store TEXT NOT NULL,
                        complete INTEGER DEFAULT 0,
                        modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
                    )''')

    cursor.execute('''CREATE TABLE IF NOT EXISTS history (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        name TEXT NOT NULL,
                        category TEXT NOT NULL,
                        counter INTEGER DEFAULT 0
                    )''')
    conn.commit()
    conn.close()


def time_ago(timestamp_str):
    # Parse the ISO formatted timestamp string
    timestamp = datetime.datetime.fromisoformat(timestamp_str)
    
    # Calculate the time difference
    now = datetime.datetime.now()
    diff = now - timestamp

    # Use timedelta attributes directly
    days = diff.days
    hours = diff.seconds // 3600
    minutes = (diff.seconds % 3600) // 60
    seconds = diff.seconds % 60

    # Return the appropriate string based on the largest non-zero unit
    if days > 0:
        return f"{days} day{'s' if days != 1 else ''} ago"
    elif hours > 0:
        return f"{hours} hour{'s' if hours != 1 else ''} ago"
    elif minutes > 0:
        return f"{minutes} minute{'s' if minutes != 1 else ''} ago"
    elif seconds > 0:
        return f"{seconds} second{'s' if seconds != 1 else ''} ago"
    else:
        return "Just now"

def render_table(arg=None):
    with use_scope("table", clear=True):
        if not pin.store:
            put_text("No Store Selected")
            return

        store_label = next((item['label'] for item in STORE_REGISTRY if item['value'] == pin.store))
        color_class = next((item['color'] for item in STORE_REGISTRY if item['value'] == pin.store))
        html_content = f'<h3 class="text-{color_class}">{store_label}</span>' 
        put_row([
            put_html(html_content),
            put_button("\u21bb",onclick=render_table,outline=True).style("text-align: right;") 
            ])
        conn = sqlite3.connect(DATABASE_FILENAME)
        cursor = conn.cursor()
        cursor.execute("SELECT id, name, category, store, complete, modified_at FROM todos WHERE store = ? ORDER BY complete, category", 
                       (pin.store,))
        items = cursor.fetchall()
        conn.close()

        size = "3em 50% auto" 
        for uid,name,category,store,complete,modified_at in items:
            if complete == 0:
                put_row([
                    put_button("\u2610", color="light", onclick=lambda id=uid: complete_item(id)),
                    put_text(name), 
                    put_button(category,
                               outline=True,color=CATEGORY_REGISTRY[category],
                               onclick=lambda id=uid: edit_item(id)
                               ).style("text-align: right;"), 
                ],size=size)
            else:
                put_row([
                    put_button("\u2611", color="dark", onclick=lambda id=uid: complete_item(id)),
                    put_markdown("~"+name+"~"), 
                    put_text(time_ago(modified_at)).style("font-size: 10px"), 
                    put_button(category,
                               outline=True,color="dark",
                               onclick=lambda id=uid: edit_item(id)
                               ).style("text-align: right;"), 
                ],size=size+" auto")
            # Add Seperator for each row
            put_html('<hr style="height:1px; background-color:grey; margin-top:0.5em; margin-bottom:0.5em;" />')

def clean_up():
    # Read all entries in the todo table from the database and deletes it if (a) the item is
    # complete and (b) the item was modified more than 4 hours ago
    #
    # Before the item is permanently deleted, the history should be updated by
    # passing the deleted item name and category to the update_history() function
    
    conn = sqlite3.connect(DATABASE_FILENAME)
    cursor = conn.cursor()
    
    # Get the current time and calculate the cutoff time (4 hours ago)
    current_time = datetime.datetime.now()
    cutoff_time = current_time - datetime.timedelta(hours=4)
    
    # Fetch completed items modified more than 4 hours ago
    cursor.execute("""
        SELECT id, name, category FROM todos 
        WHERE complete = 1 AND modified_at < ?
    """, (cutoff_time.isoformat(),))
    
    items_to_delete = cursor.fetchall()
    
    # Update history and delete items
    for item_id, name, category in items_to_delete:
        update_history(name, category, cursor)
        cursor.execute("DELETE FROM todos WHERE id = ?", (item_id,))
    
    conn.commit()
    conn.close()

def update_history(name, category, cursor):
    # Update the `history` table from the database.
    # If `name` does not exist, insert it into the table with the counter
    # defaulting to 0
    # If `name` already exists, update the category field with the provided
    # category and increment the counter
   
    # Check if the item already exists in the history
    cursor.execute("SELECT * FROM history WHERE name = ?", (name,))
    existing_item = cursor.fetchone()
    
    if existing_item:
        # Update existing item
        cursor.execute("""
            UPDATE history 
            SET category = ?, counter = counter + 1 
            WHERE name = ?
        """, (category, name))
    else:
        # Insert new item
        cursor.execute("""
            INSERT INTO history (name, category, counter) 
            VALUES (?, ?, 1)
        """, (name, category))
    

def add_item():
    name = pin.name.strip()
    category = pin.category
    store = pin.store
    current_time = datetime.datetime.now().isoformat()

    if store == None:
        toast("Select a store list to add to", color="error")
        return

    if not name or not category or not store:
        toast("All fields are required", color="error")
        return

    conn = sqlite3.connect(DATABASE_FILENAME)
    cursor = conn.cursor()
    cursor.execute("INSERT INTO todos (name, category, store, modified_at) VALUES (?, ?, ?, ?)",
                   (name, category, store, current_time))
    conn.commit()
    conn.close()
    toast(f"Item '{name}' added successfully!", color="success")
    render_table()
    clear_add_scope()

def delete_item(item_id):
    conn = sqlite3.connect(DATABASE_FILENAME)
    cursor = conn.cursor()
    cursor.execute("DELETE FROM todos WHERE id=?", (item_id,))
    conn.commit()
    conn.close()
    toast(f"Item deleted successfully!", color="success")
    render_table()
    close_popup()

def edit_item(item_id):
    conn = sqlite3.connect(DATABASE_FILENAME)
    cursor = conn.cursor()
    cursor.execute("SELECT id, name, category FROM todos WHERE id = ? ", 
                   (item_id,))
    item = cursor.fetchall()[0]
    conn.close()

    popup("Edit Item",[
            put_input("edit_name",value=item[1]),
            put_select("edit_category",options=CATEGORY_REGISTRY.keys(),value=item[2]),
            put_row([
                put_button("Save Changes",onclick=lambda id=item_id: save_item(id)),
                put_button("Delete",color="danger",
                           onclick=lambda id=item_id: delete_item(id)
                           ).style("text-align: right;"), 
            ])
        ])

def save_item(item_id):
    current_time = datetime.datetime.now().isoformat()

    conn = sqlite3.connect(DATABASE_FILENAME)
    cursor = conn.cursor()
    cursor.execute('''
            UPDATE todos
            SET name = ?, category = ?, modified_at = ? 
            WHERE id = ?
        ''', (pin.edit_name.strip(), pin.edit_category, current_time, item_id,))
    conn.commit()
    conn.close()

    render_table()
    close_popup()

def complete_item(item_id):
    """
    Toggles the "complete" value between 0 and 1
    """
    current_time = datetime.datetime.now().isoformat()

    conn = sqlite3.connect(DATABASE_FILENAME)
    cursor = conn.cursor()
    cursor.execute('''
            UPDATE todos
            SET complete = CASE
                WHEN complete = 0 THEN 1
                ELSE 0
            END,
            modified_at = ?
            WHERE id = ?
        ''', (current_time, item_id,))
    conn.commit()
    conn.close()
    render_table()

def clear_add_scope():
    pin.name = ""

def auto_category_selection(value):
    if value in pywebio.session.local.history:
        pin.category = pywebio.session.local.history[value]

def populate_history():
    # Read the history table from the database and populate the 
    # pywebio.session.local.history dictionary with a set of key-value pairs
    # where the key is the `name` and the value is the `category`
    pywebio.session.local.history = {}

    conn = sqlite3.connect(DATABASE_FILENAME)
    cursor = conn.cursor()

    # Fetch all entries from the history table, ordered by counter in descending order
    cursor.execute("SELECT name, category, counter FROM history ORDER BY counter DESC")
    history_items = cursor.fetchall()

    # Populate the dictionary
    for name, category, _ in history_items:
        pywebio.session.local.history[name] = category

    conn.close()


def main():
    """
    Nilles Todo List
    """

    """
    Define the UI structure of the page
    """

    init_db()


    
    put_markdown("# Nilles ToDos")
    put_actions("store",buttons=STORE_REGISTRY)


    populate_history()

    put_row([
        put_input("name",placeholder="Add New Item...", 
                  datalist=list(pywebio.session.local.history.keys())),
        put_select("category",options=CATEGORY_REGISTRY.keys()),
        put_button("Add",onclick=add_item)
    ],size="auto 6em 3em")


    pin_on_change("name",onchange=auto_category_selection)
    
    put_scope("table")

    pin_on_change("store",onchange=render_table,init_run=True)


if __name__ == '__main__':
    parser = argparse.ArgumentParser(description="Nilles Todo List Server")
    parser.add_argument('-c', '--cleanup', action='store_true', help='Run the clean_up function')
    args = parser.parse_args()

    if args.cleanup:
        print("Running clean_up function...")
        clean_up()
        print("Clean-up completed.")
    else:
        print("Starting the server...")
        pywebio.config(css_style=".container { background-color: beige; }")
        start_server(main, port=8088, debug=True, reconnect_timeout=0)

