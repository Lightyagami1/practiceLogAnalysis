package com.cleartax.log;

import com.cleartax.log.models.data;

import java.util.*;

public class topK {
    private int k;
    private dataFrequency df;

    public topK(int k, ArrayList<ArrayList<String>> val, dataFrequency df) {
        this.k = k;
        this.df = df;
    }



    public Map<data, Integer> exec() {
        df.process();

        List<Map.Entry<data , Integer>> list = new LinkedList<>(df.freq.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<data, Integer>>() {
                    @Override
                    public int compare(Map.Entry<data, Integer> o1, Map.Entry<data, Integer> o2) {
                        return (o1.getValue()).compareTo(o2.getValue());
                    }
                }
                        .reversed()//to arrange it in descending order
        );
        Map<data, Integer> result = new LinkedHashMap<>();//maintains the order which the entries were put into the map

        int limit = k;
        for (Map.Entry<data, Integer> entry : list) {
            if (limit == 0) {
                break;
            }
            result.put(entry.getKey(), entry.getValue());
            limit--;
        }
        return result;

    }



}
