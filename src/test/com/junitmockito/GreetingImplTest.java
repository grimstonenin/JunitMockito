package com.junitmockito;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class GreetingImplTest {

   private Greeting greeting;

    @Before
    public void init(){
        System.out.println("Setup");
        greeting = new GreetingImpl();
    }

    @Test
    public void greetShouldReturnAValidOutput(){
        System.out.println("greetShouldReturnAValidOutput");

        String result = greeting.greeting("Junit");
        assertNotNull(result);
        assertEquals("Hello Junit",result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void greetShouldThrowExceptionForNameIsNull(){
        System.out.println("greetShouldThrowExceptionForNameIsNull");
        String result = greeting.greeting(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void greetShouldThrowExceptionForNameIsEmpty(){
        System.out.println("greetShouldThrowExceptionForNameIsEmpty");
        String result = greeting.greeting(null);
    }

    @After
    public void teardown(){
        System.out.println("teardown");
        greeting = null;
    }
}
