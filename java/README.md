### Add lib to maven
```bash
mvn install:install-file -Dfile=lib/algs4.jar -DgroupId=edu.princeton.cs \
    -DartifactId=algs4 -Dversion=1.0.0 -Dpackaging=jar
```