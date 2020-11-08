package com.cleartax.log.file;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;


public class logFileReader implements fileRead {
    private String path;
    private String splitBy;
    private int normalizingIndex;
    private ArrayList<ArrayList<String>> cols = null;

    public logFileReader(String path, String splitBy, int normalizingIndex) {
        this.path = path;
        this.splitBy = splitBy;
        this.normalizingIndex = normalizingIndex-1;
    }

    private void parse() {
        String line = "";
        cols = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            while ((line = br.readLine()) != null)   //returns a Boolean value
            {
                String[] curLine = line.split(splitBy);
                //System.out.printf("len : %d", curLine.length);
                ArrayList<String> cur = new ArrayList<>();
                for(int i = 0; i < curLine.length; ++i) {
                    if(i != normalizingIndex)
                        cur.add(curLine[i]);
                    else
                        cur.add(prune(curLine[i]));
                }
                cols.add(cur);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String prune(String v) {
        String[] tokens = v.split("/");
        //System.out.printf("token len : %d %s", tokens.length, tokens);

        for(int i = 0; i < tokens.length; ++i) {
            try {
                Integer.parseInt(tokens[i]);
                tokens[i] = "{id}";
            } catch (NumberFormatException e) {
                continue;
            }
        }
        String res = Arrays.stream(tokens)
                .map(Object::toString)
                .collect(Collectors.joining("/"));

        Optional<String> res2 = Arrays.stream(tokens)
                .reduce((str1, str2)
                        -> str1 + "/" + str2);
        System.out.printf("%s\n", res);
        ///System.out.printf("%s\n", res2.get());
        return res;
    }

    @Override
    public ArrayList<ArrayList<String>> read() {
        if (cols == null) {
            parse();
        }
        return cols;
    }

    @Override
    public void show() {

    }
}