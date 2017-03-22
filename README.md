# Ursa
Current Version: **Ursa.Minor.1**

Unified Research Support Assistant 

Ursa is an educational tool developed to improve the quality of research, and make the process more manageable. By unifying various components of the research process, and automating standard tasks, we can provide researchers with a structure and streamlined experience. Ursa will expand over time to continually provide researchers with new and innovative tools.


# Building Ursa
Ursa is built using the [Play Framework](https://www.playframework.com/) with Java. The project is currently set up in [Intelij](https://www.jetbrains.com/idea/) however it can be built and edited in most environments. Base requirements to build Ursa are:

* [Java 8 (JDK/JRE)](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
* [SBT](http://www.scala-sbt.org/download.html)

To install SBT from the command line (Ubuntu):

```
> echo "deb https://dl.bintray.com/sbt/debian /" | sudo tee -a /etc/apt/sources.list.d/sbt.list
> sudo apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv 2EE0EA64E40A89B84B2DF73499E82A75642AC823
> sudo apt-get update
> sudo apt-get install sbt
```

To start the server & launch Ursa:

```
> sbt run
```

By default Play will run on port 9000. To change this, you can run:

```
> sbt
> run PORT
```
Where PORT is the port number desired (80 will allow you to enter `localhost` in your browser to test).

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

In order for Google's Oauth2 to work correctly you'll need to [register an application](https://console.developers.google.com/) and its Client ID. Once you have this, place it in the Applicaiton class variable:

```
    private static String googleClientId = "CLIENT ID";
```

This will allow all components dependent on auth2 to access the Client ID. NOTE: When registering the application in Google's API Manager, make sure to include http://localhost:PORT in Authorized JavaScript origins.
