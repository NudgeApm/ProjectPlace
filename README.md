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
	./run.sh <fichier-de-scenario>

Des exemples de scénarios sont fournis dans le répertoire "scenarios".

# installation & configuration de la sonde

1. créer l'application dans l'interface de nudge
2. télécharger et décompresser la sonde depuis l'interface de nudge dans le répertoire "probe"
3. lancer l'application avec les commandes suivantes :


	cd webapp
	mvn jetty:run-forked -Pnudge


