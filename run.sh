#!/bin/bash
export LD_LIBRARY_PATH=/usr/local/lib
java -jar -Djava.library.path=/usr/local/lib -Drequest.bind=5557 -Dresponse.bind=5558 -Dworker.timeout.millis=30000 birthday-paradox-assembly-1.0.jar
