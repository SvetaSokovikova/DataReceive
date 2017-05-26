package com.mycompany.datareceive;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

public class Main {
    
    public static void main(String[] args){
        String database_name = "C:/Films";  //Database name parameter
        
        GraphDatabaseFactory dbFactory = new GraphDatabaseFactory();
        File location = new File(database_name);
        GraphDatabaseService graphDb = 
                dbFactory.newEmbeddedDatabase(location);
        
        String data_name = "C:/Users/User/Desktop/FilmsKmeans.csv"; //CsvFile name parameter
        
        String line = "";
        String csvSplitBy = ",";
        int w = 0; //Only to show progress
        String[] splittedLine = new String[2];
        
        try (BufferedReader br = new BufferedReader(new FileReader(data_name))){
            while ((line = br.readLine()) != null){
                splittedLine = line.split(csvSplitBy);
                graphDb.execute("match (n) where id(n)="+splittedLine[0]+" set n.type="+splittedLine[1]);
                w++;
                System.out.println(w);
            }
            
        } catch (IOException e){
            e.printStackTrace();
        }
        
    }
    
}
