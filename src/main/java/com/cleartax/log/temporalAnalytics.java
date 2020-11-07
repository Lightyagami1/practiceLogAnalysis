package com.cleartax.log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class temporalAnalytics extends dataFrequency{
    private HashMap<data, Integer> minTime, maxTime, totTime;

    temporalAnalytics(ArrayList<ArrayList<String>> val) {
        super(val);
    }

    private void populate() {
        for(ArrayList<String> v : val) {
            Integer responseTime = Integer.valueOf(v.get(3));
            data cur = new data();
            cur.setURL(v.get(1));
            cur.setRm(REST_METHOD.valueOf(v.get(1)));

            minTime.putIfAbsent(cur, responseTime);
            minTime.computeIfPresent(cur, (key, value) -> Math.min(value, responseTime));

            maxTime.putIfAbsent(cur, responseTime);
            minTime.computeIfPresent(cur, (key, value) -> Math.max(value, responseTime));

            totTime.putIfAbsent(cur, responseTime);
            minTime.computeIfPresent(cur, (key, value) -> value + responseTime);
        }
    }

    public ArrayList<temporalData> execute() {
        populate();
        ArrayList<temporalData> res = new ArrayList<>();
        for (Map.Entry mapElement : freq.entrySet()) {
            data key = (data)mapElement.getKey();
            Integer value = (Integer)mapElement.getValue();

            temporalData cur = new temporalData(key.getRm(),
                                                key.getURL(),
                                                minTime.get(key),
                                                maxTime.get(key),
                                                (double) (totTime.get(key)/value)
            );

            res.add(cur);
        }
        return res;
    }
}
