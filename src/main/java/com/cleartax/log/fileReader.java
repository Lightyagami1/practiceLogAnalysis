package com.cleartax.log;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class fileReader {
    private String path;

    public fileReader(String path) {
        this.path = path;
    }

    public ArrayList<ArrayList<String >> Parse() {
        String line = "";
        String splitBy = ",";
        ArrayList<ArrayList<String>> cols = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            while ((line = br.readLine()) != null)   //returns a Boolean value
            {
                String[] curLine = line.split(splitBy);
                ArrayList<String> cur = new ArrayList<>();
                cur.add(curLine[0]);
                cur.add(normalize(curLine[1]));
                cur.add(curLine[2]);
                cur.add(curLine[3]);
                cur.add(curLine[4]);
                cols.add(cur);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cols;
    }

    private String normalize(String v) { // IMPLEMENT ME
        return v;
    }
}