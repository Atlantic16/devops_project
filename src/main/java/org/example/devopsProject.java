package org.example;

public class devopsProject 
{
    public static void main( String[] args )
    {
        //DataFrame df = new DataFrame("data/data.csv");
        DFelement[] d = new DFelement[1];
        d[0] = new DFelement("a", new Integer[]{1, 2});
        DataFrame df = new DataFrame(d);
        System.out.println(df.mean("a"));

    }
}
