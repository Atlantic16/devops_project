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
        print("Calculate the mean of the age column");
        System.out.println(df.mean("age"));
        print("Find the min value of val column (Double)");
        System.out.println(df.minDouble("val"));
        print("Find the min value of code column (Integer)");
        System.out.println(df.minInt("code"));
        print("Select subset of DataFrame (selecting name and city columns)");
        DataFrame df2 = df.getColumnSubset(new String[]{"name", "city"});
        df2.show();
        print("Select subset of DataFrame (selecting 2 and 3 rows)");
        DataFrame df3 = df.getRowSubset(new int[]{2,3});
        df3.show();

    }
}
