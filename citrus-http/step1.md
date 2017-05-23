This tutorial shows how to access Http REST APIs with Citrus.

First of all we need a demo web application that we would like to test. So lets build and start the [todo-app](https://github.com/citrusframework/todo-demo-app) sample web application.

## Building the sample app

The tutorial uses a small web application that we want to integrate with in our tests. The demo application is a SpringBoot web application
that is able to create and manage todo entries. The sample application has already been loaded for you. 

You can find the application in the following directory 

`cd ~/tutorial/app`{{execute}}

We can build and start the application with Maven by executing following command:

`mvn package`{{execute}}

`mvn spring-boot:start`{{execute}}

This commands may take some time (~1-2 minutes) as the process will compile, package and start the complete web application with all required dependencies. 

As soon as the build has finished you can visit the running todo demo web application: 

https://[[HOST_SUBDOMAIN]]-8080-[[KATACODA_HOST]].environments.katacoda.com

You can create new todo entries, check them as done and remove the entries from the list.
 
We are ready to move on! Please continue with the next step.