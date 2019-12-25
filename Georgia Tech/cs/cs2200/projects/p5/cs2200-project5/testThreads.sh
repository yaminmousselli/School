#!/bin/sh
#Literally just runs the simulation in two terminals. "sleep" is the time between starting the server and starting the client
#Written by Grace Harper 23/04/2018
x-terminal-emulator  -e 'bash -c "make clean; make; python2 rtp-server.py -p 8080 -c .99; bash"'
sleep 2
x-terminal-emulator  -e 'bash -c "make clean; make; ./rtp-client 127.0.0.1 8080; bash"'
