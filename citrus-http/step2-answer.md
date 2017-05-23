**citrus-context.xml**

<pre class="file" data-filename="src/test/resourcescitrus-context.xml" data-target="replace">&lt;?xml version="1.0" encoding="UTF-8"?>
&lt;beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:citrus="http://www.citrusframework.org/schema/config"
       xmlns:citrus-http="http://www.citrusframework.org/schema/http/config"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd
       http://www.citrusframework.org/schema/config http://www.citrusframework.org/schema/config/citrus-config.xsd
       http://www.citrusframework.org/schema/http/config http://www.citrusframework.org/schema/http/config/citrus-http-config.xsd">
	
    &lt;citrus:global-variables>
        &lt;citrus:variable name="project.name" value="Citrus Integration Tests"/>
    &lt;/citrus:global-variables>
    
    &lt;citrus-http:client id="todoClient"
                request-url="http://localhost:8080"/>
    	
&lt;/beans>
</pre>

**TodoAppIT.java**

<pre class="file" data-filename="src/test/java/org/citrus/samples/TodoAppIT.java" data-target="replace">package org.citrus.samples;

import org.testng.annotations.Test;

import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.dsl.testng.TestNGCitrusTestRunner;
import com.consol.citrus.http.client.HttpClient;
import com.consol.citrus.message.MessageType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

public class TodoAppIT extends TestNGCitrusTestRunner {

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

    @Test
    @CitrusTest
    public void testPost() {
        // TODO code the test
    }
}
</pre>