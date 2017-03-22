# Ursa
Current Version: **Ursa.Minor.1**

Unified Research Support Assistant 

Ursa is an educational research tool developed to aid undergraduate / graduate researchers in automating certain tasks, as well as providing a unified environment for research, notes, and related tasks.

# Building Ursa
Ursa is built using the [Play Framework](https://www.playframework.com/) with Java. The project is currently set up in [Intelij](https://www.jetbrains.com/idea/) however it can be built and edited in most environments. Base requirements to build Ursa are:

* [Java 8 (JDK/JRE)](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
* [SBT](http://www.scala-sbt.org/download.html)

To install SBT from the command line (Ubuntu):

```
echo "deb https://dl.bintray.com/sbt/debian /" | sudo tee -a /etc/apt/sources.list.d/sbt.list
sudo apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv 2EE0EA64E40A89B84B2DF73499E82A75642AC823
sudo apt-get update
sudo apt-get install sbt
```

To start the serber & launch Ursa:

```
sbt run
```

All dependencies should be automatically downloaded and integrated into the program. A testing DB will need to be set up.
Ursa is currently set up to utilize a MySQL server. In the **application.conf** file find and edit the following:

```
db.default.driver=com.mysql.jdbc.Driver
db.default.url="jdbc:mysql://TEST DB URL/DB NAME"
db.default.user= TEST DB USERNAME
db.default.password= TEST DB PASSWORD
```

The current DB schema used is as follows:
```
id  VARCHAR(1000) PRIMARY KEY
first_name VARCHAR(500) NOT NULL
last_name VARCHAR(500) NOT NULL
email VARCHAR(255) NOT NULL
locale VARCHAR(5) NOT NULL
imageurl VARCHAR(1000) NOT NULL
```


