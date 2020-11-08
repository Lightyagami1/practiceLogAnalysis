package com.cleartax.log;

import com.cleartax.log.models.REST_METHOD;
import com.cleartax.log.models.data;
import com.cleartax.log.models.temporalData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class temporalAnalytics{
    private HashMap<data, Integer> minTime, maxTime, totTime;
    private dataFrequency df;
    private ArrayList<ArrayList<String>> val;

    public temporalAnalytics(ArrayList<ArrayList<String>> val, dataFrequency df) {
        this.df = df;
        this.val = val;
    }

    private void populate() {
        for(ArrayList<String> v : val) {
            Integer responseTime = Integer.valueOf(v.get(3));
            data cur = new data();
            cur.setUrl(v.get(1));
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
        for (Map.Entry mapElement : df.freq.entrySet()) {
            data key = (data)mapElement.getKey();
            Integer value = (Integer)mapElement.getValue();

            temporalData cur = new temporalData(key.getRm(),
                                                key.getUrl(),
                                                minTime.get(key),
                                                maxTime.get(key),
                                                (double) (totTime.get(key)/value)
            );

            res.add(cur);
        }
        return res;
    }
}
