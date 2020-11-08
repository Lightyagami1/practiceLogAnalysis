package com.cleartax.log;

import com.cleartax.log.file.logFileReader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Optional;

@SpringBootApplication
public class LogAnalyticsApplication {

    public static void main(String[] args) {
        SpringApplication.run(LogAnalyticsApplication.class, args);

        String path  = "/Users/sauravk/Downloads/LogParser.csv";
        Optional<logFileReader> f = Optional.ofNullable(new logFileReader(path, ",", 2));
        Optional<ArrayList<ArrayList<String>>> res = Optional.ofNullable(f.get().read());

        res.get().forEach(v -> {
            v.forEach(s -> System.out.printf("%s ", s));
            System.out.printf("\n");
        });

        //topK(res);


        /*

        read file > topK > result : print
        read file > timetaken > result : print
         */


    }

}
