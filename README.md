# RaidShadowLegendsV2.2
Clickey click time!

Uses java 1.8 to run.

* If X and Y are left empty, the user has 5 seconds to move the mouse to the location where the click will take place

* Smart Checkbox:
  - allows the pixel maps recognition (one map for button focused, one for button unfocused) around the location where the click will take place
  - allows the skipping of click if the pixel maps are not met
  - due to the fact that we can identify when we click on the button, we added the runs section where the clicks will stop after a certail amount of clicks

* Clicks - interval between clicks (default of 10 seconds)
* Duration - for how long do you want to click at the specific location


Aditional information:
You can SAVE and LOAD settings. Usually the game spawns the window with the same dimension and you can use one of you screen corners as anchors, this way you can save the settings and next time when you start the tool it will load the data from the Settings.json file.

The pixel maps and the Runs values are not stored while saving (yet).

This project is the first phase to learing some kind of UI creation in java. It's not something to be taken as example, but, serve its purpose :).

Hope you like it.

Pretty sure no1 will download it but, hey, just in case, let me know your thoughts about it.
