package org.example;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
//import static org.junit.Assert.assertNotNull;


public class DFelementTest {
    private DFelement integer;
    private DFelement str;
    private DFelement array;
    @Before
    public void setUp(){
        integer = new DFelement("Integer", 3);
        str = new DFelement("String", "hello element");
    }

    @Test(expected= IllegalArgumentException.class)
    public void constructorTest(){
        array = new DFelement("Array", List.of(1,2,3));
        assertNotNull(integer);
        assertNotNull(str);
    }
    @Test
    public void getLabelTest(){
        String expectedLabel = "Integer";
        String expectedLabelStr = "String";
        assertEquals(expectedLabel, integer.getLabel());
        assertEquals(expectedLabelStr, str.getLabel());
        assertNotEquals("hello", str.getLabel());
    }

    @Test
    public void getValueTest(){
        Object expected = 3;
        Object expectedStrValue = "hello";
        assertEquals(expected,integer.getValue());
        assertEquals(expectedStrValue, str.getValue());
        assertNotEquals(7, integer.getValue());
    }

    @Test
    public void setLabelTest(){
        String expected = "int";
        integer.setLabel(expected);
        assertEquals(expected, integer.getLabel());
    }

    @Test
    public void setValueTest(){
        Object expected = 7;
        Object notExpected = 8;
        integer.setValue(expected);
        assertEquals(expected, integer.getValue());
        assertNotEquals(notExpected, integer.getValue());
    }
}
