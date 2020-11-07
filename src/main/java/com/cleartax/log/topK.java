package com.cleartax.log;

import java.util.*;

public class topK extends dataFrequency{
    private int k;

    public topK(int k, ArrayList<ArrayList<String>> val) {
        super(val);
        this.k = k;
    }



    public Map<data, Integer> exec() {
        process();

        List<Map.Entry<data , Integer>> list = new LinkedList<>(freq.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<data, Integer>>() {
                    @Override
                    public int compare(Map.Entry<data, Integer> o1, Map.Entry<data, Integer> o2) {
                        return (o1.getValue()).compareTo(o2.getValue());
                    }
                }
                        .reversed()//to arrange it in decending order
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
