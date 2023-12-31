# TuneTracker

A prefilled database with songs and fake users. Interacting with CLI can allow users to sign up/ login and perform core functions

## Description

Tunetracker allows users to sign up/ login to access core features of the application such as following/ unfollowing other users, create a playlist, add songs to it, view profile statistics, and recommend songs based on listening history.

## Getting Started

### Dependencies

* Download MySQL Server: https://dev.mysql.com/downloads/mysql/
* Download MySQL Java Connector and add to classpath: https://dev.mysql.com/downloads/connector/j/5.1.html
* Download either MySQL workbench or DataGrip to see tables in database update: https://dev.mysql.com/downloads/workbench/ | https://www.jetbrains.com/datagrip/download/#section=mac

### Installing

* Git clone this repository in your local machine anywhere you like
* DbConnection.java file may need to be modified (field names such as: url, username, password) based on port number hosting MySQL server, what name you decide to name the database, and what username, password you make for your database.
* MySQL Workbench: To keep consistency you may make a New Connection using MySQL workbench using the port number: **3306**, using **root** as the username/ password, and then creating a creating a schema called **tunetracker**.
* DataGrip: I believe in DataGrip you may click on the DataSources category, then enter all the necessary information such as port number (**3306**), **root** as the username/ password, and creating a database called **tunetracker**.

### Executing program

Run Main.java File and follow instructions in terminal 

## Help

For help as to what commands are available simply enter 'help' in the terminal

```
help
```

## Authors

Thomas Garcia thomasakulla@gmail.com

## Version History

* 0.2
  *  Functionality added to create a new user, view a profile (limited), login, logout, and added 'help' command to
  offer help on current version of application.

* 0.1
    * Able to connect to database, no functionality yet besides connecting to database. No JUnit testing yet.
 
## Future/ Goals

* Add a follow command so a user can follow another user.
* Add a playlist/ collection table where users can add their favorite songs to.
* Cover edge cases such as duplicate usernames, and make sure empty entries aren't valid for creating an account.
* Clean up code in Loader.java.
* Add more documentation.
* Fully test out application to make sure edge cases are covered and no major bugs using JUnit.
* Creating a frontend to connect to the backend using Javascript, and Javascript frameworks such as React or Vue and Bootstrap.
