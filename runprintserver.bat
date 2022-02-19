dir *.java /s /b /o:gn > sources.txt
javac @sources.txt
#javac -sourcepath . .\src\printserver\Main.java

jar cvf printserver.jar -C .\src .

java -classpath printserver.jar -Djava.security.manager -Djava.security.policy==printserver.policy -Djava.security.auth.login.config==printserver_jaas.config printserver.Main