
# The VC4 driver gets confused when root is blank. This seems to fix that
CMDLINE_ROOTFS = "root=ram0" 

# I was running into Out-Of-Memory errors and setting cma to 32M seems to help
CMDLINE_CMA = "cma=32M"
