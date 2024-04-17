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

    public List<DFelement> getMap(){
        return map;
    }

    /**
     * Empty constructor DataFrame
     */
    public DataFrame(){
        map = new ArrayList<>();
    }


    /**
     * Constructor that take a DFelement array an populate the DataFrame with it.
     * @param element element to init
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
        String label, type;
        List<Object> elems;
        Iterator<?> it;

        for(CSVRecord record: records){ //iterate over each record
            it = record.iterator();
            label = it.next().toString();
            type = it.next().toString();
            elems = new ArrayList<>();
            switch (type){
                case "String": 
                    while(it.hasNext()){ //iterate over each element of a record{
                        elems.add(it.next());
                    }
                break;

                case "Integer": 
                    while(it.hasNext()){ //iterate over each element of a record{
                        elems.add(Integer.parseInt(it.next().toString()));
                    }
                break;

                case "Double":
                    while(it.hasNext()){ //iterate over each element of a record{
                        elems.add(Double.parseDouble(it.next().toString()));
                    }
            }
             
            DFelement elem = new DFelement(label, elems.toArray()); //create an object DFelement
            map.add(elem);
        }
    }
    
    /**
     * Add an element to DataFrame instance.
     * @param element element to add
     * @return void
     */
    public void add(DFelement element){
        if(element.getValue() != null)
            map.add(element);
    }

    /**
     * Return the object of the key key, and drop it.
     * @param label the label of the column to pop
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

        index = longestSize - index; //index get the last index of the largerst row
        for(DFelement e: map){ 
            elements.add(e.geti(index - 1));
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
        if(obj instanceof Integer[] || obj instanceof Integer)
            return "Integer";
            
        else if(obj instanceof Double[] || obj instanceof Double)
            return "Double";

        return "String";
    }

    /**
     * Auxiliary function, works with show() function
     * @param elem the Object to print
     */
    private void print(DFelement elem){
        String label = elem.getLabel();
        Object value = elem.getValue();
        
        System.out. println(elem.geti(0).getClass());
        System.out.print(label);
        System.out.println(Arrays.toString((Object[]) value));
    }

    private void printRow(Object row){
        System.out.println(Arrays.toString((Object[]) row));
    }

    /**
     * Show DataFrame's elements
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
        String type = getColType(obj[0]);

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

    /**
     * Return the min of a column with label @label.
     * @param label label of the column to extract
     * @return return the minimum value of type integer
     */
    public int minInt(String label){
        Object[] obj = (Object[])getColumn(label);
        String type = getColType(obj[0]);
        int min = 0;

        if(type.equals("Integer")){
           min = (Integer)obj[0];
           for(int i = 0; i < obj.length; i++){
                if(min > (Integer)obj[i]) min = (Integer)obj[i];
           }
        }

        return min;
    }

    /**
     * Return the max of a column with label @label
     * @param label label of the column to extract
     * @return return the maximum value of type integer
     */
    public int maxInt(String label){
        Object[] obj = (Object[])getColumn(label);
        String type = getColType(obj[0]);
        int max = 0;

        if(type.equals("Integer")){
           max = (Integer)obj[0];
           for(int i = 0; i < obj.length; i++){
                if(max < (Integer)obj[i]) max = (Integer)obj[i];
           }
        }

        return max;
    }

    /**
     * Return the max of a column with label @label
     * @param label label of the column to extract
     * @return return the minimum value of type double
     */
    public double minDouble(String label){
        Object[] obj = (Object[])getColumn(label);
        String type = getColType(obj[0]);
        double min = 0.0;

        if(type.equals("Double")){
           min = (Double)obj[0];
           for(int i = 0; i < obj.length; i++){
                if(min > (Double)obj[i]) min = (Double)obj[i];
           }
        }

        return min;
    }

    /**
     * Return the max of a column with label @label
     * @param label label of the column to extract
     * @return return the maximum value of type double
     */
    public double maxDouble(String label){
        Object[] obj = (Object[])getColumn(label);
        String type = getColType(obj[0]);
        double max = 0;

        if(type.equals("Double")){
           max = (Double)obj[0];
           for(int i = 0; i < obj.length; i++){
                if(max < (Double)obj[i]) max = (Double)obj[i];
           }
        }

        return max;
    }

    /**
     * Function that return a subset of a DataFrame (column based)
     * @param labels labels of columns
     * @return new DataFrame
     */
    public DataFrame getColumnSubset(String[] labels){
        DataFrame df = new DataFrame();

        for(String s: labels){
            for(DFelement e:map){
                if(e.getLabel().equals(s)){
                    df.add(e.clone());
                }
            }
        }

        return df;
    }

    /**
     * Create and return a new DataFrame with selected items based on rows
     * @param indices array of selected indices
     * @return a new DataFrame
     */
    public DataFrame getRowSubset(int[] indices){
        DataFrame df = new DataFrame();
        List<Object> elements;

        for(DFelement e: map){
            elements = new ArrayList<>();
            for(int i: indices){
                elements.add(e.geti(i)); //Adding selected items
            }
            DFelement dfel = new DFelement(e.getLabel(), elements.toArray());
            df.add(dfel);
        }
        
        return df;
    }
}