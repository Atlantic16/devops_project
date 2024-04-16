package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Iterator;

import java.io.FileReader;
import java.io.Reader;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

public class DataFrame {
    private List<DFelement> map;


    /**
     * Constructor that take a DFelement array an populate the DataFrame with it.
     * @param element
     */
    public DataFrame(DFelement[] element){
        map = new ArrayList<>();

        for(DFelement e: element){
            if(e != null){
                add(e);
            }
        }
    }

    /**
     * Constructor that populate the DataFrame from a CSV file
     * @param fileName a CSV file
     */
    public DataFrame(String fileName){
        map = new ArrayList<>();
        Reader reader;
        CSVParser parser = null;

        try{ //try parse file
            reader = new FileReader(fileName);
            parser = CSVFormat.DEFAULT.parse(reader);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }

        //get all records
        List<CSVRecord> records = parser.getRecords();
        String label;
        List<Object> elems;
        Iterator<?> it;

        for(CSVRecord record: records){ //iterate over each record
            it = record.iterator();
            label = it.next().toString();
            elems = new ArrayList<>();
            while(it.hasNext()) //iterate over each element of a record
                elems.add(it.next());
            DFelement elem = new DFelement(label, elems.toArray()); //create an object DFelement
            map.add(elem);
        }

    }
    
    /**
     * Add an element to DataFrame instance.
     * @param element
     * @return void
     */
    public void add(DFelement element){
        if(element.getValue() != null)
            map.add(element);
    }

    /**
     * Return the object of the key key, and drop it.
     * @param label 
     * @return res
     */
    public Object pop(String label){
        Object res = null;
        for(DFelement obj: map){
            if(obj.getLabel() == label){
                res = obj;
                break;
            }
        }

        return res;
    }

    private Object getRow(int index){
        List<Object> elements = new ArrayList<>();
        for(DFelement e: map){
            elements.add(e.geti(index));
        }

        return elements.toArray();
    }

    private Object getRowRev(int index){
        List<Object> elements = new ArrayList<>();
        int longestSize = 0;

        for(DFelement e: map){//fetching the longest size
            longestSize = e.getSize() > longestSize ? e.getSize() : longestSize;
        }

        index = longestSize - 1; //index get the last index of the largerst row
        for(DFelement e: map){ 
            elements.add(e.geti(index));
        }
        return elements.toArray();
    }

    private Object getColumn(String label){
        for(DFelement e: map){
            if(e.getLabel().equals(label)){
                return e.getValue();
            }
        }
        return null;
    }

    private String getColType(Object obj){
        if(obj instanceof Integer[])
            return "Integer";
            
        else if(obj instanceof Double[])
            return "Double";

        return null;
    }

    /**
     * Auxiliary function, works with show() function
     * @param elem
     */
    private void print(DFelement elem){
        String label = elem.getLabel();
        Object value = elem.getValue();
        
        System.out.print(label);
        System.out.println(Arrays.toString((Object[]) value));
    }

    private void printRow(Object row){
        System.out.println(Arrays.toString((Object[]) row));
    }

    /**
     * Show DataFrame's elements
     * @param void
     */
    public void show(){
        if(map.size() == 0) return;
        for(DFelement elem: map){
            print(elem);
        }
    }

    /**
     * Shows the first n rows
     * @param n the number of rows to print 
     */
    public void head(int n){
        for(int i = 0; i < n && i < map.size(); i++){   
            printRow(getRow(i));
        }
    }

    /**
     * shows the last n rows
     * @param n the number of rows to print
     */
    public void tail(int n){
        for(int i = 0; i < n && i < map.size(); i++){   
            printRow(getRowRev(i));
        }
    }

    /**
     * Calculate the mean of values of a given column
     * </br>
     * The method is implemented only for Double and Integer data types
     * @param label label of the column to extract
     * @return the mean value of column:label
     */
    public double mean(String label){
        double m = 0;
        Object[] obj = (Object[])getColumn(label);
        String type = getColType(obj);

        if(type.equals("Integer")){
            for(int i = 0; i < obj.length; i++)
                m += Double.valueOf((Integer)obj[i]);
        }
        else if(type.equals("Double")){
            for(int i = 0; i < obj.length; i++)
                m += (Double)obj[i];
        }

        return m / obj.length;
    }
}