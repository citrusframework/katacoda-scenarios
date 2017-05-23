This quickstart shows how to create a new Citrus project with Maven.

Citrus as a framework integrates best with build tools like Maven or Gradle. These tools can help us to manage the project 
sources and all required dependencies in order to compile, package and execute the tests in our project.

## Maven archetype

Citrus provides several Maven archetypes that can be used as a starting point for creating a new Citrus project. The archetype
creates all required source files and directories for us.

Execute following command in the terminal window:
 
`mvn archetype:generate -Dfilter=com.consol.citrus.mvn:`{{execute}}

The command lists all available project archetypes for Citrus. Please choose the number `1`{{execute}} which is the default quickstart archetype.
Also choose the latest available version `2.7.1`.

Now you need to give some project information such as the groupId, artifactId and project version. Please use following data:

| Prompt     | Value                      |
|------------|----------------------------|
| groupId    | `com.company`{{execute}}   |
| artifactId | `citrus-sample`{{execute}} |
| version    | `1.0-SNAPSHOT`{{execute}}  |
| package    | `com.company`{{execute}}   |
| verify     | `Y`{{execute}}             |
|            |                            |

After that we should have a successful build and a new project directory **citrus-sample**. 

The project directory contains all source files. The Citrus test sources are located in **src/test** directory. Have a look at these files and explore
the project.

We are ready to move on! Please navigate to the project directory with `cd citrus-sample`{{execute}} and continue with the next step.