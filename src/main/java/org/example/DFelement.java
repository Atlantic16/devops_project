package org.example;

public class DFelement implements Cloneable{
    private String label;
    private Object value;
    private int size;

    public DFelement(String label, Object value){
        //accept only object array data type
        if(!value.getClass().isArray() || value.getClass().getComponentType().isPrimitive())
            throw new IllegalArgumentException("Primitive type are not allowed");

        this.value = value;
        this.label = label;
        this.size = ((Object[]) value).length;
    }

    public String getLabel(){
        return label;
    }

    public Object getValue(){
        return value;
    }

    public int getSize(){
        return size;
    }

    public void setKey(String label){
        this.label = label;
    }

    public void setValue(Object value){
        this.value = value;
    }

    public Object geti(int index){
        return index < size && index >= 0 ? ((Object[])value)[index] : null;
    }
}
