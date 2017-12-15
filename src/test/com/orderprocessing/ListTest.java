package com.orderprocessing;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;

public class ListTest {

    @Spy
    private List<String> list = new ArrayList<>();

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void test(){
        list.add("test");
        doReturn(3).when(list).size();

        assertEquals(3,list.size());
    }
}
