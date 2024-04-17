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
    private Double[] doubles = {1.0,2.0};
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
        assertNotEquals(5, dataframe.mean("Integer"),0.001);
        assertEquals(1.5, dataframe.mean("Double"),0.001);
    }
    @Test
    public void minTest(){
        dataframe = new DataFrame(elements);
        assertEquals(1, dataframe.minInt("Integer"));
    }

    @Test
    public void maxTest(){
        dataframe = new DataFrame(elements);
        assertEquals(3, dataframe.maxInt("Integer"));
    }

}
