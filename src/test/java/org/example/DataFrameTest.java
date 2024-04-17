package org.example;

import org.junit.Before;
import org.junit.Test;

import javax.xml.crypto.Data;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.Assert.*;
public class DataFrameTest {

    final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private DataFrame dataframe;
    private DFelement[] elements;
    private DFelement integer;
    private DFelement str;
    private DFelement db;
    private DFelement badCase;
    private Integer[] integers = {1,2,3};
    private String[] strings = {"Hello","Hi","salam"};
    private Double[] doubles = {1.0,2.0,3.0};
    @Before
    public void setUp(){
        integer = new DFelement("Integer", integers);
        str = new DFelement("String", strings);
        db = new DFelement("Double", doubles);
        badCase = new DFelement("bad",new Object[]{null});

        elements = new DFelement[2];
        elements[0] = integer;
        elements[1] = str;
    }

    @Test
    public void constructorTest(){
        dataframe = new DataFrame(elements);
        assertNotNull(dataframe);
        int i = 0;
        while (i < elements.length){
            assertEquals(dataframe.getMap().get(i),elements[i]);
            i++;
        }
    }

    @Test
    public void addTest(){
        dataframe = new DataFrame(elements);

        dataframe.add(db);
        assertEquals(db,dataframe.getMap().get(dataframe.getMap().size() - 1));
        //dataframe.add(badCase); //method should throw NullPointerArgument
    }

    @Test
    public void popTest(){
        dataframe = new DataFrame(elements);
        assertEquals(str, dataframe.pop("String"));
        assertEquals(integer, dataframe.pop("Integer"));
    }

    @Test
    public void showTest(){
        dataframe = new DataFrame(elements);
        String expected = "class java.lang.Integer\nInteger[1, 2, 3]\nclass java.lang.String\nString[Hello, Hi, salam]";

        System.setOut(new PrintStream(outputStreamCaptor));
        dataframe.show();
        String printedOutput = outputStreamCaptor.toString().trim();
        assertEquals(expected, printedOutput);
    }
    @Test
    public void headTest(){
        dataframe = new DataFrame(elements);
        String[] expected = {"[1, Hello]", "[2, Hi]", "[3, salam]"};

        System.setOut(new PrintStream(outputStreamCaptor));
        int i = 0;
        while (i < dataframe.getMap().size() - 1){
            dataframe.head(i + 1 );
            String printedOutput = outputStreamCaptor.toString().trim();
            String expectedOutput = expected[i];
            assertEquals(expectedOutput, printedOutput);
            i++;
        }

        dataframe.head(3);
        String printedOutput = outputStreamCaptor.toString().trim();
        String expectedOutput = "[1, salam]";
        assertNotEquals(expectedOutput, printedOutput);

    }
    @Test
    public void tailTest(){
        dataframe = new DataFrame(elements);
        String[] expected = {"[1, Hello]", "[2, Hi]", "[3, salam]"};

        System.setOut(new PrintStream(outputStreamCaptor));
        dataframe.tail(1);
        String printedOutput = outputStreamCaptor.toString().trim();
        String expectedOutput = expected[2];
        assertEquals(expectedOutput, printedOutput);

    }
    @Test
    public void meanTest(){
        dataframe = new DataFrame(elements);
        dataframe.add(db);
        assertEquals(2, dataframe.mean("Integer"),0.001);
        assertNotEquals(5, dataframe.mean("Integer"));
        assertEquals(2.0, dataframe.mean("Double"),0.001);
    }
    @Test
    public void minIntTest(){
        dataframe = new DataFrame(elements);
        assertEquals(1, dataframe.minInt("Integer"));
    }

    @Test
    public void maxIntTest(){
        dataframe = new DataFrame(elements);
        assertEquals(3, dataframe.maxInt("Integer"));
    }

    @Test
    public void minDoubleTest(){
        dataframe = new DataFrame(elements);
        dataframe.add(db);
        assertEquals(1.0, dataframe.minDouble("Double"),0.001);
    }
    @Test
    public void maxDoubleTest(){
        dataframe = new DataFrame(elements);
        dataframe.add(db);
        assertEquals(3.0, dataframe.maxDouble("Double"),0.001);
    }

    @Test
    public void getColumnSubsetTest(){
        dataframe = new DataFrame(elements);
        dataframe.add(db);

        DFelement[] expectedElements = new DFelement[2];
        expectedElements[0] = integer;
        expectedElements[1] = db;
        DataFrame expected = new DataFrame(expectedElements);
        DataFrame result = dataframe.getColumnSubset(new String[]{"Integer","Double"});

        for (int elem=0; elem < expected.getMap().size(); elem++){
            assertEquals(expected.getMap().get(elem).getLabel(), result.getMap().get(elem).getLabel());
            Object[] expectedValues = (Object []) expected.getMap().get(elem).getValue();
            Object[] resultValues = (Object []) result.getMap().get(elem).getValue();
            for (int value=0; value < expectedValues.length; value++) {
                assertEquals(expectedValues[value], resultValues[value]);
            }
        }

        expectedElements = new DFelement[1];
        expectedElements[0] = str;
        expected = new DataFrame(expectedElements);
        result = dataframe.getColumnSubset(new String[]{"String"});

        for (int elem=0; elem < expected.getMap().size(); elem++){
            assertEquals(expected.getMap().get(elem).getLabel(), result.getMap().get(elem).getLabel());
            Object[] expectedValues = (Object []) expected.getMap().get(elem).getValue();
            Object[] resultValues = (Object []) result.getMap().get(elem).getValue();
            for (int value=0; value < expectedValues.length; value++) {
                assertEquals(expectedValues[value], resultValues[value]);
            }
        }
    }

    @Test
    public void getRowSubset(){
        dataframe = new DataFrame(elements);
        dataframe.add(db);

        int[] indices = new int[]{0,2};

        DFelement[] expectedElements = new DFelement[3];
        expectedElements[0] = new DFelement("Integer", new Integer[]{1,3});
        expectedElements[1] = new DFelement("String", new String[]{"Hello","salam"});
        expectedElements[2] = new DFelement("Double", new Double[]{1.0,3.0});

        DataFrame expected = new DataFrame(expectedElements);

        DataFrame result = dataframe.getRowSubset(indices);

        for (int elem=0; elem < expected.getMap().size(); elem++){
            assertEquals(expected.getMap().get(elem).getLabel(), result.getMap().get(elem).getLabel());
            Object[] expectedValues = (Object []) expected.getMap().get(elem).getValue();
            Object[] resultValues = (Object []) result.getMap().get(elem).getValue();
            for (int value=0; value < expectedValues.length; value++) {
                assertEquals(expectedValues[value], resultValues[value]);
            }
        }
    }
}
