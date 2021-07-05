
# NCURSES does not like when i try to run with a terminal that uses
# rxvt-unicode-256color.  This hack forces xterm which is a simpler The proper
# fix would be to update the ncurses terminfo to add rxvt, but this hack works
# for now

export TERM="xterm-256color"


