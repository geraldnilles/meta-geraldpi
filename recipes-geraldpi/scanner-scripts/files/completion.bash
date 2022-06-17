
_scan_comp(){
	
	(
		cd /media/documents
		COMPREPLY=($( compgen -f $2 ))
	)
}

complete -F _scan_comp -o nospace scan


