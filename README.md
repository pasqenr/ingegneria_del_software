# Ingegneria del software

Final project for the course _Ingegneria del software_ of _Università degli studi di Verona_. The application is a warehouse 
manager written in Java 8.

The chosen database is SQLite to simplify testing and replicability but should be simple port the DatabaseWrapper and the queries 
to other RDBMS.

There are some tests written using JUnit and the project uses i18n internationalization to provide the Swing user interface in 
english and italian.

The code was (and it is) originally hosted on GitLab and uses the integrated CI to run the tests when code changes are commited.
The build tool is Gradle, so it's required compile and run the code. There are included files that should make the integration
in Intellij IDEA and Eclipse easy.
