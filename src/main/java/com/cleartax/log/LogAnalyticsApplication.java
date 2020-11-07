package com.cleartax.log;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;

@SpringBootApplication
public class LogAnalyticsApplication {

    public static void main(String[] args) {
        SpringApplication.run(LogAnalyticsApplication.class, args);

        String path  = "";
        fileReader f = new fileReader(path);
        ArrayList<ArrayList<String >> res = f.Parse();

        //topK(res);


        /*

        read file > topK > result : print
        read file > timetaken > result : print
         */


    }

}
