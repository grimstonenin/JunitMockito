package com.mockitoscrapbook;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class ATest {

    @Mock
    private B b;

    private A a;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        a = new A(b);
    }

    @Test
    public void usesVoidMethodShouldCalltheVoidMethod() throws Exception {
        assertEquals(1,a.usesVoidMethod());
        verify(b).voidmethod();
    }
}
