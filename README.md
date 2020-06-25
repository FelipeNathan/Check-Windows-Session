# Check Windows Session

This project check if have someone connected in your server via RDP (mstsc) and list these users

## Prerequisite for build
 - Maven

## Prerequisite for run
 - JRE 1.8+

## Build
 - Run command `mvn clean package`

## Running
 - Navigate to the target folder and run `javar -jar (jar-name).jar` where jar-name is the generated jar file
 - Open your browser in `localhost:3080`

## Environment Variable
 - server.port: default 3080 
    - you can pass it like `java -jar -Dserver.port=8080 (jar-name).jar`
