package org.example;

import java.util.LinkedHashMap;
import java.util.Map;

public class DFelement {
    private Map<String, Object> col;
    private String key;

    public DFelement(String key, Object value){
        //accept only object array data type
        if(!value.getClass().isArray() || value.getClass().getComponentType().isPrimitive())
            throw new IllegalArgumentException("Primitive type are not allowed");

        col = new LinkedHashMap<>();
        col.put(key, value);
        this.key = key;
    }

    public String getKey(){
        return key;
    }

    public Object getValue(){
        return col.get(key);
    }



}
