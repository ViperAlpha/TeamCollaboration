# TeamCollaboration
> Project developed at University of Wisconsin-Whitewater, for the course Software Engineering

# Pre-requisits

To run this project you must ensure:
- Java JDK 8 is installed and in your path.
- Mysql is installed and its address is `mysql://localhost:3306/`
- You have created a database with name `TeamCollaboration`
    - Can be done through mysql command prompt 
```sql
CREATE DATABASE TeamCollaboration;
```
- You have a username and password in the mysql database with username `root` and with password `root`
- You have Apache Tomcat for your operating system. Recommended version: **apache-tomcat-7.0.67**
    - Note that the Linux version is included in the `apache/linux/*` folder
- You have a valid IDE for Spring projects: Intellij IDEA or STS Bundle. **Intellij IDEA is highly recommended**

*Note that many of these properties can be changed within the application.properties file in this spring project. 
Obviously any changes to that file shouldn't be committed to break others project.*

# Instructions

After ensuring that you have all the above done here is a step by step process:
1. Navigate to the `sql` folder and run the `TeamCollaboration.sql` file in mysql command prompt:
```
mysql> USE TeamCollaboration;           # Selecting the proper database to run this project in
mysql> source TeamCollaboration.sql;    # Assuming you are in the sql/create-database directory
mysql> SHOW TABLES;                     # Ensure that the tables were created
```
2. Open up this project in Intellij IDEA or STS Bundle
    - For Intellij:
        - Run -> Edit Configurations -> + -> Tomcat Server -> Fill in information and press run button. [Link with help](https://www.jetbrains.com/idea/help/run-debug-configuration-tomcat-server.html)
    - For STS Bundle:
        - TODO: Documentation. I don't use eclipse so I don't know

# Testing

These are Test Users available in the default `sql/create-database/TeamCollaboration.sql` file:

```
username: joe@gmail.com
password: joe@gmail.com

username: dan@gmail.com
password: dan@gmail.com

username: thao@uww.edu
password: thao@uww.edu
```

These are Test Users available in the default `sql/create-database/TeamCollaboration.sql` file:

```
username: admin@gmail.com
password: admin@gmail.com

```
You will have to change `resources/application.properties` file's `messaging.user.download.directory` constant to
an already created directory on your box if you want messages to be sent with `links/attachments`

# Colaborators

- [Giulianno Sbrugnera](https://github.com/giuliannosbrugnera)
- [Reinaldo Moreira](https://github.com/ViperAlpha)
- [Steven Horvatin](https://github.com/horvste)