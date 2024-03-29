# TuneTracker

A prefilled database with songs and fake users. Interacting with CLI can allow users to sign up/ login and perform other core functions.
Future implementations are at the bottom.
## Description

Tunetracker allows users to sign up/ login to access core features of the application such as following/ unfollowing other users, create a playlist, add songs to it, view profile statistics, etc.

## Getting Started

### Dependencies

* I'm using Java 21 so you may use a similar version or the same version.
* Installation setup for mysql for different platforms: https://dev.mysql.com/doc/mysql-getting-started/en/ (REMEMBER TO DOWNLOAD THE MySQL Server, I'm using an archived version (8.0.35).
* Download MySQL Java Connector and add to classpath: https://dev.mysql.com/downloads/connector/j/5.1.html
* Download either MySQL workbench or DataGrip to see tables in database update: https://dev.mysql.com/downloads/workbench/ | https://www.jetbrains.com/datagrip/download/#section=mac

### Installing

* Git clone this repository in your local machine anywhere you like.
* REMEMBER TO ADD THE DOWNLOADED JAVA CONNECTOR TO CLASSPATH (I had issues not doing so).
* Start up the MySQL Server.
* DbConnection.java file may need to be modified (field names such as: url, username, password) based on port number hosting MySQL server, what name you decide to name the database, and what username, password you make for your database.
* You may create the database with the appropriate port number and username/password (SEE NEXT BULLET POINT FOR THE PORT # & USERNAME/PASSWORD) via the CLI or using a GUI like the MySQL Workbench or DataGrip (I like DataGrip).
* MySQL Workbench: To keep consistency you may make a New Connection using MySQL workbench using the port number: **3306**, using **root** as the username/ password, and then creating a creating a schema called **tunetracker**.
* DataGrip: Click on the DataSources category, then enter all the necessary information such as port number (**3306**), **root** as the username/ password, and creating a database called **tunetracker**.

### Executing program
* Once you start up the Mysql Server and have everything configured, you can access the terminal side of this project
``Run Main.java File and follow instructions in terminal``

* STILL WORKING ON FRONTEND PART 

## Help

* For help as to what commands are available simply enter `help` in the terminal


* **It's recommended to use the dummy login! It's also suggested in the terminal when the program is ran, but the credentials are also below this text!**
* dummy email: `dummy@gmail.com`
* dummy password: `password`

## Authors

Thomas Garcia tmg2102@rit.edu or tmg2102dev@gmail.com

## Version History

* 1.0.0
  *  Functionality added to: create a new user, view one's own profile, view followers/ followees, login, logout, make a playlist,
  add a song to your playlist, view 5 random songs, follow a user, added 'help' command to
  offer help on current version of application, etc.

* 0.1
    * Able to connect to database, no functionality yet besides connecting to database. No JUnit testing yet.
 
## Future/ Goals

* ~~Add a playlist/ collection table where users can add their favorite songs to.~~
* ~~Cover edge cases such as duplicate usernames~~, and make sure empty entries aren't valid for creating an account.
* Clean up code in Loader.java.
* ~~Add more documentation.~~
* Recommend songs.
* Fully test out application to make sure edge cases are covered and no major bugs using JUnit.
* Creating a frontend to connect to the backend using Javascript, and Javascript frameworks such as React or Vue and Bootstrap.
