package leetcode;

import java.util.*;


public class LeetCode {
    public static void main(String[] args) {
        int[] a = {2, 2, 3, 3, 3, 4};
        System.out.println(new LeetCode().simplifyPath("/../"));
    }


    //https://www.hackerrank.com/challenges/journey-to-the-moon/problem
    private static ArrayList<ArrayList<Integer>> buildGraph(List<List<Integer>> astronaut, int n) {
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++)
            graph.add(new ArrayList<>());
        for (List<Integer> pair : astronaut) {
            graph.get(pair.get(0)).add(pair.get(1));
            graph.get(pair.get(1)).add(pair.get(0));
        }
        return graph;
    }

    public static int journeyToMoon(int n, List<List<Integer>> astronaut) {
        ArrayList<ArrayList<Integer>> graph = buildGraph(astronaut, n);
        boolean isVisited[] = new boolean[n];
        ArrayList<Integer> people = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!isVisited[i]) {
                people.add(doDFS(i, graph, isVisited));
            }
        }

        int sum = 0;
        int result = 0;
        for (int size : people) {
            result += sum * size;
            sum += size;
        }
        return result;
    }

    private static Integer doDFS(int vertice, ArrayList<ArrayList<Integer>> graph, boolean[] isVisited) {
        isVisited[vertice] = true;
        int nodes = 1;

        for (int adjacentNode : graph.get(vertice)) {
            if (!isVisited[adjacentNode]) {
                nodes += doDFS(adjacentNode, graph, isVisited);
            }
        }
        return nodes;
    }


    //https://leetcode.com/problems/simplify-path/submissions/
    public String simplifyPath(String path) {
        StringBuilder sb = new StringBuilder();
        String[] files = path.split("/");
        Stack<String> s = new Stack<>();

        for (String file : files) System.out.println(file + " aa");

        for (String file : files) {
            if (file.equals("")) continue;
            if (file.equals("..")) {
                if (!s.isEmpty())
                    s.pop();
                continue;
            }
            if (file.equals(".")) continue;
            s.push(file);
        }

        if (s.isEmpty()) sb.append("/");
        while (!s.isEmpty()) {
            System.out.println(sb);
            sb.insert(0, s.pop()).insert(0, "/");
        }

        return sb.toString();
    }

    //https://leetcode.com/problems/snakes-and-ladders/
    public int snakesAndLadders(int[][] board) {
        int minSteps = 0;
        int rows = board.length;
        int start = 1, end = rows * rows;
        boolean[][] isVisited = new boolean[rows][rows];
        LinkedList<Integer> queue = new LinkedList<>();

        isVisited[rows - 1][0] = true;
        queue.add(start);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int currentPosition = queue.pop();
                if (currentPosition == end) return minSteps;
                //Rolling Dice upto 6 and adding those possible destinations to Queue.
                for (int dice = 1; dice <= 6; dice++) {
                    if (dice + currentPosition > end) break;

                    int[] nextPosition = findCoordinates(currentPosition + dice, rows);
                    int nextRow = nextPosition[0], nextColumn = nextPosition[1];

                    if (!isVisited[nextRow][nextColumn]) {
                        isVisited[nextRow][nextColumn] = true;
                        if (board[nextRow][nextColumn] != -1) queue.add(board[nextRow][nextColumn]);
                        else queue.add(currentPosition + dice);
                    }
                }
            }
            minSteps++;
        }
        return -1;
    }

    private int[] findCoordinates(int currentPosition, int n) {
        int r = n - (currentPosition - 1) / n - 1;
        int c = (currentPosition - 1) % n;
        if (r % 2 == n % 2) return new int[]{r, n - 1 - c};
        else return new int[]{r, c};
    }

    //https://leetcode.com/problems/delete-and-earn/
    public int deleteAndEarn(int[] nums) {
        int[] frequencyMap = new int[1001];
        int maxElement = 0, minElement = 10001;

        for (int number : nums) {
            frequencyMap[number] += number;
            maxElement = Math.max(maxElement, number);
            minElement = Math.min(minElement, number);
        }
        int[] DP = new int[maxElement + 1];
        Arrays.fill(DP, -1);
        return helperForDeleteAndEarn(minElement, maxElement, frequencyMap, DP);
    }

    private int helperForDeleteAndEarn(int minElement, int maxElement, int[] frequencyMap, int[] DP) {
        if (minElement > maxElement) return 0;

        if (DP[maxElement] == -1) {
            int includeMaxElement = helperForDeleteAndEarn(minElement, maxElement - 2, frequencyMap, DP) + frequencyMap[maxElement];
            int excludeMaxElement = helperForDeleteAndEarn(minElement, maxElement - 1, frequencyMap, DP);
            DP[maxElement] = Math.max(includeMaxElement, excludeMaxElement);
        }
        return DP[maxElement];
    }


    //https://leetcode.com/problems/champagne-tower/submissions/
    public double champagneTower(int poured, int query_row, int query_glass) {
        if (poured == 0) return 0;
        int rows = 100;
        double[][] champagneTower = new double[rows + 1][rows + 1];

        champagneTower[0][0] = poured;

        for (int row = 0; row <= query_row; row++) {
            for (int column = 0; column <= row; column++) {
                double splittingWine = (champagneTower[row][column] - 1.0) / 2.0;
                if (splittingWine > 0) {
                    champagneTower[row + 1][column] += splittingWine;
                    champagneTower[row + 1][column + 1] += splittingWine;
                }
            }
        }
        return Math.min(1, champagneTower[query_row][query_glass]);
    }

    //https://leetcode.com/problems/arithmetic-slices/submissions/
    public int numberOfArithmeticSlices(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        int arithmeticSlices = 0;
        int[] dp = new int[n];

        for (int i = 2; i < n; i++) {
            if (nums[i] - nums[i - 1] == nums[i - 1] - nums[i - 2]) dp[i] = dp[i - 1] + 1;
            arithmeticSlices += dp[i];
        }

        return arithmeticSlices;
    }

    //https://leetcode.com/problems/verifying-an-alien-dictionary/submissions/
    public boolean isAlienSorted(String[] words, String order) {
        int n = words.length;
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < order.length(); i++) map.put(order.charAt(i), i);
        for (int i = 0; i < n - 1; i++)
            if (!sortedAlienly(words[i], words[i + 1], map)) return false;
        return true;
    }

    private boolean sortedAlienly(String s, String t, HashMap<Character, Integer> map) {
        int m = s.length(), n = t.length();
        int i = 0, j = 0;

        while (i < m && j < n) {
            char ss = s.charAt(i);
            char tt = t.charAt(j);
            if (ss != tt) return map.get(ss) <= map.get(tt);

            i++;
            j++;
        }
        if (i < m) return false;
        return true;
    }

    //https://leetcode.com/problems/boats-to-save-people/solution/
    public int numRescueBoats(int[] people, int limit) {

        int trips = 0, n = people.length;
        Arrays.sort(people);
        int low = 0, high = n - 1;

        while (low <= high) {
            if (people[low] + people[high] <= limit) low++;
            high--;
            trips++;
        }
        return trips;
    }

    //https://www.lintcode.com/problem/641/solution/17645
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> result = new ArrayList<>();
        List<Long> number = new ArrayList<>();

        number.add((long) lower - 1);
        for (int i : nums) {
            if (i >= lower && i <= upper) number.add((long) i);
        }
        number.add((long) upper + 1);

        for (int i = 1; i < number.size(); i++) {
            String range = getRange(number.get(i - 1), number.get(i));
            if (range.length() > 0) result.add(range);
        }

        return result;
    }

    private String getRange(long i, long j) {
        if (i >= j - 1) return "";
        if (i == j - 2) return (j - 1) + "";
        return (i + 1) + "->" + (j - 1);
    }

    //https://leetcode.com/problems/summary-ranges/submissions/
    public List<String> summaryRanges(int[] nums) {
        List<String> result = new ArrayList<>();
        int n = nums.length;
        if (n == 0) return result;
        StringBuilder sb = new StringBuilder();
        int index = 0;
        while (index < n) {
            int temp = nums[index];
            sb.append(nums[index++]);

            while (index < n && nums[index] - nums[index - 1] == 1) index++;
            if (temp != nums[index - 1]) sb.append("->").append(nums[index - 1]);

            result.add(sb.toString());
            sb.setLength(0);
        }
        return result;
    }


    //https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string-ii/
    public String removeDuplicates(String s, int k) {

        Stack<CharacterFrequency> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char currentCharacter = s.charAt(i);
            int currentCharacterFrequency = 1;
            if (!stack.isEmpty() && stack.peek().c == currentCharacter) {
                int topElementFrequency = stack.peek().freq;
                //System.out.println("Popped character ::" + stack.pop().c);
                if (topElementFrequency + 1 == k) continue;
                else currentCharacterFrequency = topElementFrequency + 1;
            }
            stack.push(new CharacterFrequency(currentCharacter, currentCharacterFrequency));
            //System.out.println("Character at top is :: " + stack.peek().c + " with frequency :: " + stack.peek().freq);
        }

        while (stack.size() > 0) {
            CharacterFrequency temp = stack.pop();
            int frequency = temp.freq;
            char toAppendCharacter = temp.c;
            while (frequency-- > 0) sb.append(toAppendCharacter);
        }

        return sb.reverse().toString();
    }

    //https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string/submissions/
    public String removeDuplicates(String s) {
        int n = s.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (sb.length() != 0 && sb.charAt(sb.length() - 1) == s.charAt(i)) {
                sb.deleteCharAt(sb.length() - 1);
                continue;
            }
            sb.append(s.charAt(i));
        }
        return sb.toString();
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
            if (interval.isEmpty()) interval.add(ints);
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
