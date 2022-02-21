import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;


public class LeetCode {
    public static void main(String[] args) {

        int[][] a = new int[][]{{1, 4}, {3, 6}, {2, 8}};

        System.out.println(new LeetCode().removeCoveredIntervals(a));
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
