### Introduction to Service Design and Engineering: 2nd assignment (Alan Ramponi, 179850)

TODO!

The project consists in developing a RESTful web service. An ant `build.xml` file allows to automate the compilation, cleaning and execution; in addition to that, dependencies are managed with ivy. The whole code is well documented and commented.

The project has been developed individually and the server has been deployed on heroku at the following base url:
https://murmuring-citadel-91696.herokuapp.com/rest/

**Extra tasks**: the health profile is built dinamically (according to measure types).

### Project architecture
The project is structured in several packages in order to organize all the classes in a reasonable way. Inside the main common package (`sde.ehealth`) it is possible to find:
* `server`: a package that contains all the things necessary to expose the service through a RESTful API:
  * **Application.java**: the main class useful to start the server;
  * **Configuration.java**: the configuration class to expose the service.
  * `dao`: a package that contains the DAO:
    * **EHealthDao.java**: the data access object class.
  * `model`: a package that contains the model objects of the application (annotated):
    * **Person.java**: the class of the "person" table;
    * **Measure.java**: the class of the "measure" table;
    * **Measurement.java**: the class of the "measurement" table;
    * **MeasurementHistory.java**: the class of the "measurement_history" table.
  * `resources`: a package that contains classes to expose REST operations to the client:
    * **PersonResource.java**: the class that exposes REST operations that handle Person objects;
    * **PersonCollectionResource.java**: the class that exposes REST operations that handle collections of Person objects;
    * **MeasurementResource.java**: the class that exposes REST operations that handle Measurement objects;
    * **MeasurementHistoryResporce.java**: the class that exposes REST operations that handle MeasurementHistory objects.
* `client`: a package that contains what we need in order to compute the requests:
  * **Application.java**: the main class of the client that sends all the requests of the assignment;
  * `responses`: a package that contains the classes to handle responses:
    * **Person.java**: the class relative to Person;
    * **Measure.java**: the class relative to Measure;
    * **Measurement.java**: the class relative to Measurement;
    * **MeasurementHistory.java**: the class relative to MeasurementHistory;
  * `parser`: a package that contains useful methods to parse XML and JSON strings:
    * **Parser.java**: the class that provides those methods.

### Tasks
The `build.xml` file contains some targets in order to execute various operations. In particular, using ant (`ant execute.client`) we accomplish all the requests of the assignment (that can be found here: https://sites.google.com/a/unitn.it/introsde_2016-17/lab-sessions/assignments/assignment-2).
Among the other things, it is possible to clean the project using the **clean** target.
An additional task was performed, i.e. the extra request related to the dynamical health profile.

### How to run it
In order to run the code, you need to follow the steps below:
* **Clone** the repo: `git clone https://github.com/alanramponi/introsde-2016-assignment-2.git`;
* **Navigate** into the project folder: `cd introsde-2016-assignment-2`;
* **Execute** the client requests using ant: `ant execute.client`.
