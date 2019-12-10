#!/bin/sh

java -cp ..\hsqldb-2.5.0\hsqldb\lib\hsqldb.jar org.hsqldb.util.DatabaseManagerSwing --url jdbc:hsqldb:hsql://localhost/workdb
