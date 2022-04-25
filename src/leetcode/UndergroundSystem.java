package leetcode;

import java.util.HashMap;

class Details {

    int id, time;
    String stationName;

    Details(String s, int t, int id) {
        this.id = id;
        this.time = t;
        this.stationName = s;
    }
}

class UndergroundSystem {

    HashMap<Integer, Details> checkIn;
    HashMap<Object, double[]> totalTime;

    public UndergroundSystem() {
        checkIn = new HashMap<>();
        totalTime = new HashMap<>();
    }

    public void checkIn(int id, String stationName, int t) {
        System.out.println("Successfully checked IN");
        checkIn.put(id, new Details(stationName, t, id));
    }

    public void checkOut(int id, String stationName, int t) {

        Details details = checkIn.get(id);
        String key = details.stationName + "+" + stationName;
        double[] info = totalTime.getOrDefault(key, new double[2]);
        int time = (t - details.time);
        info[0] += time;
        info[1]++;
        totalTime.put(key, info);
    }

    public double getAverageTime(String startStation, String endStation) {
        String key = startStation + "+" + endStation;
        double[] info = totalTime.get(key);
        return info[0] / info[1];
    }
}





















