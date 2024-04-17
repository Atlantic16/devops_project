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
    private Integer[] integers = {1};
    private String[] strings = {"Hello"};
    @Before
    public void setUp(){
        integer = new DFelement("Integer", integers);
        str = new DFelement("String", strings);
    }

    @Test(expected= IllegalArgumentException.class)
    public void constructorTest(){
        DFelement element = new DFelement("Label", 3);
        array = new DFelement("Key", new Integer[]{1,2});
        assertNotNull(array);
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
        Integer[] expected = integers;
        String[] expectedStrValue = strings;
        assertEquals(expected,integer.getValue());
        assertEquals(expectedStrValue, str.getValue());
        assertNotEquals(7, integer.getValue());
    }

    @Test
    public void setKeyTest(){
        String expected = "int";
        integer.setKey(expected);
        assertEquals(expected, integer.getLabel());
    }

    @Test
    public void setValueTest(){
        Integer[] expected = {7};
        Integer[] notExpected = {8,2};
        integer.setValue(expected);
        assertEquals(expected, integer.getValue());
        assertNotEquals(notExpected, integer.getValue());
    }

    @Test
    public void getiTest(){
        DFelement elem = new DFelement("Element", new Integer[]{1,2,3});
        assertEquals(1, (int) elem.geti(0));
        assertEquals(3, (int) elem.geti(2));
        assertEquals("Hello",str.geti(0));
        assertTrue(elem.geti(4) == null);
        assertTrue(elem.geti(-1) == null);
    }

    @Test
    public void cloneTest(){
        DFelement clone = str.clone();
        assertEquals(str.getLabel(), clone.getLabel());
        assertEquals(str.getValue(), clone.getValue());
        clone = integer.clone();
        assertEquals(integer.getLabel(), clone.getLabel());
        assertEquals(integer.getValue(), clone.getValue());
    }
}
