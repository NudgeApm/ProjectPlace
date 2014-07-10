ProjectPlace
============
l'application de test pour Nudge APM

# Build

	mvn clean install

# Exécution - Application web

	cd webapp
	mvn jetty:run

l'application est accessible via [http://localhost:8080/]

# Exécution - Application de charge

	cd load
	java -jar ./target/*-jar-with-dependencies.jar
