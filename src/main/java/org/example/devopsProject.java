package org.example;

public class devopsProject 
{
    public static void print(String s){
        System.out.println("\n");
        System.out.println(s);
        System.out.println("===========================");
    }

    public static void main( String[] args )
    {
        print("=======DataFrame Demonstration (Using a csv )=======");
        print("*DataFrame creation");
        DataFrame df = new DataFrame("data/data.csv");
        print("*Show DataFrame");
        df.show();
        print("*Show the first 2 columns");
        df.head(2);
        print("*Show the last 2 columns");
        df.tail(3);

    }
}
