As the sample application is up and running we can now run some integration tests that will call the Http REST API as a client and
validate the server response messages.

## Todo app tests

The basic Citrus project structure is already prepared. Navigate to the project with

`cd ~/tutorial/app-tests`{{execute}}

You can see the Citrus project structure which is a usual Maven project with:

| File/Directory     | Description                      |
|--------------------|----------------------------------|
| pom.xml            | Maven project configuration      |
| src/test/java      | Directory holding all Citrus Java tests |
| src/test/resources | Directory holding the Citrus configuration files  |
| citrus-context.xml | Spring bean application context configuration   |
|                    |                                  |

Lets start adding some Http integration testing.

## Http client component

Citrus uses ready-to-use endpoints for exchanging messages over various transports. Our todo sample application provides a Http REST API that we
now want to call as a client. So we add a new Http client endpoint component to the Citrus configuration. Open the file `src/test/resources/citrus-context.xml` in the editor.

The file shows a Spring XML bean configuration. You can add and manage Citrus components here by adding new Spring beans. If you want you can find out more
about the [Spring framework](https://spring.io/) on the official website.

We add the new Http client component as follows:

<pre class="file" data-filename="src/test/resourcescitrus-context.xml" data-target="replace">&lt;citrus-http:client id="todoClient"
            request-url="http://localhost:8080"/>
</pre>

This client component is ready to send Http requests to the todo app web application. We use the new endpoint component in the Citrus test. Open the file
`src/test/java/org/citrus/samples/TodoAppIT.java` and you will see two usual TestNG unit test methods. The test class extends from `TestNGCitrusTestRunner` which 
adds automatic Citrus preparations to the test. Also the test runner base class is our entrance to the Citrus Java fluent API that helps us to write integration tests in Java.
 
We use the previously defined Http client component as local member variable in the test.
 
<pre class="file" data-filename="src/test/java/org/citrus/samples/TodoAppIT.java" data-target="replace">public class TodoAppIT extends TestNGCitrusTestRunner {

    @Autowired
    private HttpClient todoClient;
    
    @Test
    @CitrusTest
    public void testGet() {
        // TODO code the test
    }

    @Test
    @CitrusTest
    public void testPost() {
        // TODO code the test
    }
}
</pre>
 
The Http client endpoint component is automatically injected to the test using Spring's autowired dependency injection mechanism. We can use the client directly in the test methods now.
 
## Writing integration tests
 
As already mentioned Citrus provides a Java fluent API for writing integration tests. This API is automatically available as we extended our class from `TestNGCitrusTestRunner`. Of course
you can also use `JUnitCitrusTestRunner` in case you prefer to use JUnit as basic test framework. The Citrus Java fluent API is available for both famous unit test frameworks. 

We can use the Java fluent API in the test method body as follows:
 
<pre class="file" data-filename="src/test/java/org/citrus/samples/TodoAppIT.java" data-target="replace">public class TodoAppIT extends TestNGCitrusTestRunner {

    @Autowired
    private HttpClient todoClient;
    
    @Test
    @CitrusTest
    public void testGet() {
        http(action -> action.client(todoClient)
            .send()
            .get("/todolist")
            .accept("text/html"));

        http(action -> action.client(todoClient)
            .receive()
            .response(HttpStatus.OK));    
    }
}
</pre>

The test now send a first Http client request to the todo application. This basic Http GET request is answered with the pure HTML **index.html**. The test uses
a receive operation to receive that response data. The test expects a **Http 200 OK** response from the server. Every time Citrus is doing a receive operation we can 
specify the exepcted message content such as message headers and payload data.

Lets add some more response validation by adding an Xpath expression and some payload validation:

<pre class="file" data-filename="src/test/java/org/citrus/samples/TodoAppIT.java" data-target="replace">public class TodoAppIT extends TestNGCitrusTestRunner {

    @Autowired
    private HttpClient todoClient;
    
    @Test
    @CitrusTest
    public void testGet() {
        http(action -> action.client(todoClient)
            .send()
            .get("/todolist")
            .accept("text/html"));

        http(action -> action.client(todoClient)
            .receive()
            .response(HttpStatus.OK)
            .messageType(MessageType.XHTML)
            .xpath("//xh:h1", "TODO list")
            .namespace("xh", "http://www.w3.org/1999/xhtml")
            .payload("&lt;!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\"\n" +
                    "\"org/w3/xhtml/xhtml1-transitional.dtd\">" +
                    "&lt;html xmlns=\"http://www.w3.org/1999/xhtml\">" +
                        "&lt;head>@ignore@&lt;/head>" +
                        "&lt;body>@ignore@&lt;/body>" +
                    "&lt;/html>"));    
    }
}
</pre>

Now we are using the full Citrus message validation power. We expect some HTML message payload that is additionally validated with the Xpath expression `//xh:h1.

## Run the test
You can run all Citrus tests with Maven by calling
 
`mvn integration-test`{{execute}}

This executes all test cases. You will see some log out put in all terminal windows marking the incoming request processing. Good news
is that you can execute the tests multiple times without having to restart the wep application.

You can also start a single test by adding its name to the command:

`mvn integration-test -Dit.test=TodoAppIT#testGet`{{execute}}

This should only execute the **testGet** testing method in a single test run.

Visit the web application once again and see the additional voting entries that were created by the Citrus integration tests.

https://[[HOST_SUBDOMAIN]]-8080-[[KATACODA_HOST]].environments.katacoda.com/todolist