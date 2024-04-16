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
    List<DFelement> map;

    /**
     * Constructor that take a DFelement array an populate the DataFrame with it.
     * @param element
     */
    public DataFrame(DFelement[] element){
        map = new ArrayList<>();

        for(DFelement e: element){
            if(e != null)
                add(e);
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

    /**
     * Auxiliary function, works with show() function
     * @param elem
     */
    public void print(DFelement elem){
        String label = elem.getLabel();
        Object value = elem.getValue();
        
        System.out.print(label);
        System.out.println(Arrays.toString((Object[]) value));
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
            print(map.get(i));
        }
    }

    /**
     * shows the last n rows
     * @param n the number of rows to print
     */
    public void tail(int n){
        if(map.size() == 0) return;
        n = map.size() - n;
        for(int i = map.size() - 1; i >= n && i >= 0; i--){
            print(map.get(i));
        }
    }
}
