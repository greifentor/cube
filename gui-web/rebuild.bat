cd ..
call mvn clean install -Pproduction -Dmaven.test.skip
cd gui-web/
java -jar target\carp-maps-ws-gui-0.0.1.jar
