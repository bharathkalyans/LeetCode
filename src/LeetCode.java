import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;


public class LeetCode {
    public static void main(String[] args) {

        int[][] a = new int[][]{{1, 4}, {3, 6}, {2, 8}};

    }


    //https://leetcode.com/problems/compare-version-numbers/
    public int compareVersion(String v1, String v2) {

        String[] l = v1.split("\\.");
        String[] r = v2.split("\\.");

        int n = l.length;
        int m = r.length;
        int i = 0, j = 0;

        while (i < n && j < m) {

            int a = Integer.parseInt(l[i]);
            int b = Integer.parseInt(r[j]);

            if (a == b) {
                i++;
                j++;
            } else if (a > b) return 1;
            else return -1;
        }

        while (i < n) {
            int val = Integer.parseInt(l[i]);
            if (val > 0) return 1;
            i++;
        }

        while (j < m) {
            int val = Integer.parseInt(r[j]);
            if (val > 0) return -1;
            j++;
        }
        return 0;
    }

    //https://leetcode.com/problems/remove-covered-intervals/
    public int removeCoveredIntervals(int[][] intervals) {
        int m = intervals.length;
        if (m == 1) return 1;

        Arrays.sort(intervals, (a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);
        ArrayList<int[]> interval = new ArrayList<>();

        for (int[] ints : intervals) {
            if (interval.isEmpty())
                interval.add(ints);
            else {
                int[] beforeInterval = interval.get(interval.size() - 1);
                if (beforeInterval[0] <= ints[0] && beforeInterval[1] >= ints[1]) continue;
                interval.add(ints);
            }
        }
        return interval.size();
    }


    //https://leetcode.com/problems/remove-k-digits/
    public static String removeKDigits(String str, int k) {

        char[] nums = str.toCharArray();
        int n = nums.length;

        Stack<Character> st = new Stack<>();

        //14399800
        // 1 3 9 9 8 0 0
        for (char num : nums) {
            // remove strictly decreasing sequence
            while (!st.isEmpty() && st.peek() > num && k > 0) {
                st.pop();
                k--;
            }

            // prevent leading zeros
            if (st.isEmpty() && num == '0') continue;
            st.push(num);
        }

        // k is not zero then remove last digits from the sequence
        while (!st.isEmpty() && k-- > 0) {
            st.pop();
        }

        if (st.isEmpty()) return "0";

        StringBuilder sb = new StringBuilder();

        while (!st.isEmpty()) sb.append(st.pop());

        return sb.reverse().toString();

    }

}
