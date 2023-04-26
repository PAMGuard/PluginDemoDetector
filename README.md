# PluginDemoDetector
This is a really simple detector, which was written for a PAMGuard workshop held back right at the start of the project, in around 2007. 

It's main purpose is to show developers how to make processing modules for 
PAMGuard and how either to set them up as a plug-in, which can be added to an existing PAMGuard installation, or to build them into PAMGuard. 

There are 11 separate Java files which make up the plugin, though some of these are trivially small, mostly just wrapping one of the standard 
PAMGuard classes. 

## Root folder (workshopdemodetector)
The start of any PAMGuard module is a subclass of **PamControlledUnit**, in this case **WorskhopController**. This must have a constructor taking a 
single String name, though it calls into the super class constructor which takes two parameters, a "unitType" as well as the "unitName". The controller
manages module parameters, and uses the **PamSettingManager** to store these into the psfx file. It also generates items for the settings menu and contains 
one or more **PamProcess** objects, which will do the actual processing.

**WorkshopProcess** is the subclass of **PamProcess** which does the actual processing of incoming data. This simple demo will take FFT data from the 
PAMGuard FFT / spectrogram engine and run a simple in band energy detector. This is done on each channel separately, so there is an inner class
called **ChannelDetector** which does the work for each channel. 

Detection data are put into **WorkshopDataUnit**'s, the detector also saves regular measures of background noise into **BackgroundDataUnit**'s. These get added
to **PamDataBlocks**. These days, I'd always subclass **PamDataBlock**, but it seems that here, I didn't do thiat and just used the base class. 

**WorkshopPlugin** helps integrate the module into the rest of PAMGuard as a plugin module. These are jar files that you place in the /plugins folder of a standard PAMGuard installation. On startup, PAMGuard scans this folder for additional modules, and then uses the information in this class to generate menu items, help, etc. 

## io
Contains an interface to the PAMGuard database. Detections will automatically be saved if a database is present. It's easy to add additional 
columns for other data fields if you so wish, all without ever having to write a line of SQL code. 

## swing
Contains some classes which interract with the PAMGuard Graphical User Interface.

**WorkshopOverlayGraphics** handles drawing of data on top of the spectrogram display and the map. These days, all this functionality is standard in 
**PamDetectionOverlayGraphics** but this is easy to override with bespoke drawing. 

**WorkshopSymbolManager** handles which symbols are used for displaying detector data and what colour they should be. This does little but extend the 
**StandardSymbolManager**. you'll interract with this if you display the detections on the map or spectrogram and want to change the colours. There are plenty of other examples in other PAMGuard detectors. 

**WorkshopParametersDialog** is the main dialog launched from the "Settings" menu where you set detection thresholds, data sources, etc. 

**WorkshopPluginPanelProvider** implements **DisplayPanelProvider** makes available a display panel which can insert into the bottom of a spectrogram display to show how the detector is operating. On the spectrogram display, right click for "Settings", go to the "Plug ins" tab and select "Worskhop demo detector" for this to appear on any spectrogram display. 

