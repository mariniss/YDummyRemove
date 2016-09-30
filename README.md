YDummyRemove
============

"Yet another Dummy Remote" is a really simple web app to control the Raspberry GPIOs via web.
It provides a single web page with a list of buttons to send the high or low signal to the Rapsberry GPIOs

You can use YDummyRemove to creates a simple project with the Raspberry, for instance, you can buy a Relay module, connect it with all "Home Theater" devices (Tv, Speakers, Decoder, Chrome Cast, Lights, etc) and turn on/off them based on what you need; I do this at my home to save energy. 
Or you can use it as base for a complete DIY Smart Home project.

I have implemented it with Java 8, Java Spark (http://sparkjava.com), Maven and Pi4j ([http://pi4j.com/](http://pi4j.com/)), it is just a single Main class of ~200 lines.


Compiling
---------

The required software to compile the project are Java 8, Git and Maven.

1. Download the sources by `` git clone https://github.com/mariniss/YDummyRemove.git `` (or download the zip)
2. `` mvn package `` from the sources main folder

And then will we have the executable jar in the target folders.

Installation
-------

The installation is really easy, you can put the jar into the Rapsberry via scp/ssh and then execute it with the command:

You can move the file with the command:
 `` scp ./target/YDummyRemove-jar-with-dependencies.jar pi@<raspberry ip>:/home/pi ``

and then execute the jar from the home:
 `` sudo java -jar YDummyRemove-jar-with-dependencies.jar `` 


If you want something more stable and the start of YDummyRemove at Raspberry startup you have to create the scripts file to define the service.

1. Create the service script:

`` sudo nano /etc/init.d/YDummyRemove ``
with the content:

```
#!/bin/bash
# YDummyRemove
#
# description: Yet another Dummy Remote

case $1 in
    start)
        /bin/bash /usr/local/bin/YDummyRemove-start.sh
    ;;
    stop)
        /bin/bash /usr/local/bin/YDummyRemove-stop.sh
    ;;
    restart)
        /bin/bash /usr/local/bin/YDummyRemove-stop.sh
        /bin/bash /usr/local/bin/YDummyRemove-start.sh
    ;;
esac
exit 0
```

2. Create the start script:
`` sudo nano /usr/local/bin/YDummyRemove-start.sh ``

with the content:
```
#!/bin/bash

java -jar /home/pi/YDummyRemove-jar-with-dependencies.jar
```

3. Create the stop sript
`` sudo nano /usr/local/bin/YDummyRemove-stop.sh ``

with the content:
```
#!/bin/bash

pid=`ps aux | grep YDummyRemove | awk '{print $2}'`
kill -9 $pid
```
 
4. Set them for the startup:
```
 sudo chmod +x /etc/init.d/YDummyRemove
 sudo update-rc.d YDummyRemove defaults
```

Now you can start and stop it with the command

```
sudo service YDummyRemove start
sudo service YDummyRemove stop
```

and YDummyRemove will be launched at Raspberry startup

Usage
-----

Once you have YDummyRemove up and running in your raspberry you can just open a browser and goto at URL: 

`` http://<raspberry ip>:4567 ``


Final notes
-----------
You can use YDummyRemove as is for your small IoT projects but if you want use it as base of big projects please make a refactoring in order to make it testable add then add a complete test suite for each usage case.
It could be useful also add a configuration file to put the GPIOs mapping. 
