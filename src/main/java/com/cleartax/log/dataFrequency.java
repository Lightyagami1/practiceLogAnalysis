package com.cleartax.log;

import com.cleartax.log.models.REST_METHOD;
import com.cleartax.log.models.data;

import java.util.ArrayList;
import java.util.HashMap;

public class dataFrequency {
    protected HashMap<data, Integer> freq;
    protected ArrayList<ArrayList<String>> val;

    dataFrequency(ArrayList<ArrayList<String>> val) {
        this.val = val;
    }


    void process() {
        for(ArrayList<String> v : val) {
            data key = new data();
            key.setRm(REST_METHOD.valueOf(v.get(1)));
            key.setUrl(v.get(2));
            freq.putIfAbsent(key, 1);

            if(freq.containsKey(key)) {
                Integer cur = freq.get(key);
                freq.put(key, cur + 1);
            }
        }
    }
}
