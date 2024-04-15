package org.example;

public class DFelement {
    private String label;
    private Object value;

    public DFelement(String label, Object value){
        //accept only object array data type
        if(!value.getClass().isArray() || value.getClass().getComponentType().isPrimitive())
            throw new IllegalArgumentException("Primitive type are not allowed");

        this.value = value;
        this.label = label;
    }

    public String getLabel(){
        return label;
    }

    public Object getValue(){
        return value;
    }

    public void setKey(String label){
        this.label = label;
    }

    public void setValue(Object value){
        this.value = value;
    }
}
