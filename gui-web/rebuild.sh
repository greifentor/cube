cd ..
mvn clean install -Pproduction -Dmaven.test.skip
if [ $? -eq 0 ]; then
	cd gui-web/
	source start-test.sh
else
	cd gui-web/
fi
