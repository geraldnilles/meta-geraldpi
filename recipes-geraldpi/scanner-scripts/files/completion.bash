
_scan_comp(){
	COMPREPLY=( $(
		cd /media/documents
		compgen -f $2
	) )
}

complete -F _scan_comp -o nospace scan

