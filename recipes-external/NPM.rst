In order to create these 2 recipes nodejs recipes, I souced the OE build environment, removed
the existing worksapce (rm -rf build/workspace) and ran the following devtool
commands::

        devtool add "npm://registry.npmjs.org/;package=pug;version=3.0.2" 
        devtool add "npm://registry.npmjs.org/;package=express;version=4.18.1"


I then copied the workspace/recipes folder into this folder and add "js" to all
of the package names
