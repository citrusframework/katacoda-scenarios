package org.citrus.samples;

import org.testng.annotations.Test;

import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.dsl.testng.TestNGCitrusTestRunner;
import com.consol.citrus.http.client.HttpClient;
import com.consol.citrus.message.MessageType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

public class TodoAppIT extends TestNGCitrusTestRunner {

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
