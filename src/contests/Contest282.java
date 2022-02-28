package contests;

import java.util.HashMap;

public class Contest282 {
    public static void main(String[] args) {

    }

    public long minimumTime(int[] time, int totalTrips) {

        int n = time.length;
        if (n == 1) return time[0] / totalTrips;

        long trips = 0L;
        long low = 1, high = (long) 1e5;

        while (low <= high) {
            long mid = low + (high - low) / 2;
            long currentTotalTrips = 0L;

            for (int times : time) {
                currentTotalTrips += mid / times;
            }
            if (currentTotalTrips >= totalTrips) {
                trips = currentTotalTrips;
                high = mid - 1;
            } else low = mid + 1;

        }

        return trips;
    }

    public int minSteps(String ss, String tt) {
        int minSteps = 0;

        HashMap<Character, Integer> map = new HashMap<>();
        for (char character : ss.toCharArray())
            map.put(character, map.getOrDefault(character, 0) + 1);

        for (char character : tt.toCharArray()) {
            if (!map.containsKey(character)) minSteps++;
            int frequency = map.getOrDefault(character, 0);
            map.remove(character);
            frequency -= 1;
            if (frequency > 0) map.put(character, frequency);
        }
        for (int frequencies : map.values())
            minSteps += frequencies;
        return minSteps;
    }

    public int prefixCount(String[] words, String pref) {
        int count = 0;

        for (String word : words)
            if (word.startsWith(pref)) count++;

        return count;
    }

}
