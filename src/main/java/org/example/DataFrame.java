package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataFrame {
    List<DFelement> map = new ArrayList<>();
    
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
     * @param key
     * @return Object
     */
    public Object pop(String key){
        Object res = null;
        for(DFelement obj: map){
            if(obj.getKey() == key){
                res = obj;
                break;
            }
        }

        return res;
    }

    /**
     * Auxiliary function, works with show() function
     * @param elem
     * @return void
     */
    public void print(DFelement elem){
        String key = elem.getKey();
        Object value = elem.getValue();
        
        System.out.print(key);
        System.out.println(Arrays.toString((Object[]) value));
    }

    /**
     * Show DataFrame's elements
     * @param void
     * @return void
     */
    public void show(){
        if(map.size() == 0) return;
        for(DFelement elem: map){
            print(elem);
        }
    }
}
