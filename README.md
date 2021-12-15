java --version\
openjdk 11.0.11 2021-04-20\
OpenJDK Runtime Environment AdoptOpenJDK-11.0.11+9 (build 11.0.11+9)\
OpenJDK 64-Bit Server VM AdoptOpenJDK-11.0.11+9 (build 11.0.11+9, mixed mode)

npm --version\
6.14.13

Before building, frontend dependencies should be installed.
To do that, run these commands:
```
cd src/main/frontend
npm install dependencies
```
To build the application, run
```
./gradlew build
```
The app requires a running instance of mongodb on port 27017. If using docker, run
```
docker run --name mongodb -d -p 27017:27017 mongo:latest
```
The app can be started by running\
java -jar mybusinessapp-0.0.1-SNAPSHOT.jar

Original index.html and database objects are included in /assigment folder
