package leetcode;

import java.util.*;

public class LeetCode {
    public static void main(String[] args) {
        int[] arr = new int[]{10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println(new LeetCode().lengthOfLIS(arr));
    }


    //LIS Binary Search Approach
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        ArrayList<Integer> al = new ArrayList<>();
        al.add(nums[0]);

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > al.get(al.size() - 1)) al.add(nums[i]);
            else {
                int index = Collections.binarySearch(al, nums[i]);
                if (index < 0) {
                    index = Math.abs(index) - 1;
                }
                al.set(index, nums[i]);
            }
        }

        return al.size();
    }

    public int minimumOperations(int[] nums) {
        int minimumOperations = 0, n = nums.length;
        Arrays.sort(nums);

        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) continue;
            int valueToSubtract = nums[i];
            boolean isSubtracted = false;
            for (int j = i; j < n; j++) {
                if (nums[j] >= valueToSubtract) {
                    nums[j] -= valueToSubtract;
                    isSubtracted = true;
                }
            }
            Arrays.stream(nums).forEach(System.out::print);
            System.out.println();
            if (isSubtracted) minimumOperations++;
        }
        return minimumOperations;
    }

    public int shortestPath(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        int[][] visited = new int[m][n];
        for (int[] row : visited) Arrays.fill(row, Integer.MAX_VALUE);
        Integer[] initialValues = new Integer[]{0, 0, 0, 0};
        Queue<Integer[]> q = new LinkedList<>();
        q.add(initialValues);
        visited[0][0] = 0;

        //DIRECTIONS
        int[] X = new int[]{-1, 1, 0, 0};
        int[] Y = new int[]{0, 0, -1, 1};

        while (!q.isEmpty()) {
            Integer[] currentValues = q.remove();
            if (currentValues[0] == m - 1 && currentValues[1] == n - 1) return currentValues[3];
            for (int i = 0; i < X.length; i++) {
                int newX = currentValues[0] + X[i];
                int newY = currentValues[1] + Y[i];

                if (!isInBound(newX, newY, m, n)) continue;

                int newK = currentValues[2] + grid[newX][newY];
                if (newK > k) continue;

                if (visited[newX][newY] <= newK) continue;

                visited[newX][newY] = newK;
                q.add(new Integer[]{newX, newY, newK, currentValues[3] + 1});

            }
        }

        return -1;
    }

    private boolean isInBound(int x, int y, int R, int C) {
        return (x >= 0 && x < R && y >= 0 && y < C);
    }

    //https://leetcode.com/problems/word-subsets/
    public List<String> wordSubsets(String[] words1, String[] words2) {
        List<String> result = new ArrayList<>();
        int[] highestFrequencyOfCharacters = buildHighestFrequency(words2);

        for (String word : words1) {
            boolean isSubSet = true;
            int[] currentFrequency = getFrequencyOfCharacters(word);
            for (int i = 0; i < 26; i++) {
                if (currentFrequency[i] < highestFrequencyOfCharacters[i]) {
                    isSubSet = false;
                    break;
                }
            }
            if (isSubSet) result.add(word);
        }

        return result;
    }

    private int[] buildHighestFrequency(String[] words2) {
        int[] frequencyOfCharacters = new int[26];
        for (String word : words2) {
            int[] currentFrequencyOfWord = getFrequencyOfCharacters(word);
            for (int i = 0; i < 26; i++)
                frequencyOfCharacters[i] = Math.max(frequencyOfCharacters[i], currentFrequencyOfWord[i]);
        }
        return frequencyOfCharacters;
    }

    private int[] getFrequencyOfCharacters(String word1) {
        int[] frequencyOfCharacters = new int[26];
        for (char c : word1.toCharArray()) frequencyOfCharacters[c - 'a']++;
        return frequencyOfCharacters;
    }

    public List<String> findAndReplacePattern(String[] words, String pattern) {
        List<String> result = new ArrayList<>();

        int[] indexesOfPattern = new int[pattern.length()];
        for (int i = 0; i < pattern.length(); i++)
            indexesOfPattern[i] = pattern.indexOf(pattern.charAt(i));

        for (String word : words)
            if (isOfSamePattern(word, indexesOfPattern)) result.add(word);

        return result;
    }

    private boolean isOfSamePattern(String word, int[] indexesOfPattern) {

        int n = word.length();
        if (n != indexesOfPattern.length) return false;

        int[] indexesOfWord = new int[n];
        for (int i = 0; i < word.length(); i++) {
            indexesOfWord[i] = word.indexOf(word.charAt(i));
            if (indexesOfPattern[i] != indexesOfWord[i]) return false;
        }
        return true;
    }


    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        int m = nums1.length, n = nums2.length;
        if (m == 1 && n == 1) return (double) (nums1[0] + nums2[0]) / 2;
        ArrayList<Integer> al = new ArrayList<>();
        for (int element : nums1) al.add(element);
        for (int element : nums2) al.add(element);

        Collections.sort(al);
        int size = al.size();

        if (size % 2 == 0) {
            int middleIndex = size / 2;
            return (double) (al.get(middleIndex) + al.get(middleIndex - 1)) / 2;
        } else {
            return (double) al.get(size / 2);
        }

    }

    public int numMatchingSubsequence(String superString, String[] words) {
        int matchingSubSequences = 0, m = superString.length();
        HashMap<String, Integer> frequencyOfWords = new HashMap<>();
        for (String word : words)
            frequencyOfWords.put(word, frequencyOfWords.getOrDefault(word, 0) + 1);

        //Now use the same method used in the question isSubSequence?

        for (String subSequence : frequencyOfWords.keySet()) {
            int i = 0, j = 0, n = subSequence.length();
            while (i < m && j < n) {
                if (superString.charAt(i) == subSequence.charAt(j)) {
                    j++;
                }
                i++;
            }
            if (j == n) matchingSubSequences += frequencyOfWords.get(subSequence);
        }

        return matchingSubSequences;
    }


    public static int minCost(int[][] costs) {
        if (costs == null || costs.length == 0) return 0;
        int n = costs.length;
        int[][] dp = new int[n + 1][4];
        for (int[] row : dp) Arrays.fill(row, -1);
        return solve(costs, costs.length - 1, -1, dp);
    }

    private static int solve(int[][] cost, int index, int previousHouse, int[][] dp) {
        if (index == 0) {
            int minCost = Integer.MAX_VALUE;
            for (int i = 0; i < cost[0].length; i++) {
                if (i != previousHouse) minCost = Math.min(cost[0][i], minCost);
            }
            return minCost;
        }

        if (dp[index][previousHouse + 1] != -1) return dp[index][previousHouse + 1];


        int currentCost = Integer.MAX_VALUE;
        for (int i = 0; i < cost[0].length; i++) {
            if (i != previousHouse) currentCost = Math.min(currentCost, solve(cost, index - 1, i, dp) + cost[index][i]);
        }

        return dp[index][previousHouse + 1] = currentCost;
    }

    //https://leetcode.com/problems/maximum-area-of-a-piece-of-cake-after-horizontal-and-vertical-cuts/submissions/
    public int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {

        int MOD = (int) Math.pow(10, 9) + 7;
        int m = horizontalCuts.length, n = verticalCuts.length;

        Arrays.sort(horizontalCuts);
        Arrays.sort(verticalCuts);

        long maximumHorizontal = horizontalCuts[0], maximumVertical = verticalCuts[0];

        //FOR HORIZONTAL
        for (int i = 0; i < m - 1; i++)
            maximumHorizontal = Math.max(maximumHorizontal, horizontalCuts[i + 1] - horizontalCuts[i]);


        maximumHorizontal = Math.max(maximumHorizontal, h - horizontalCuts[m - 1]);


        //FOR VERTICAL
        for (int i = 0; i < n - 1; i++)
            maximumVertical = Math.max(maximumVertical, verticalCuts[i + 1] - verticalCuts[i]);


        maximumVertical = Math.max(maximumVertical, w - verticalCuts[n - 1]);


        return (int) (maximumHorizontal * maximumVertical % MOD);
    }


    public int[][] reconstructQueue(int[][] people) {

        Arrays.sort(people, (o1, o2) -> {
            if (o1[0] == o2[0]) return o1[1] - o2[1];
            else return o2[0] - o1[0];
        });

        List<int[]> tempList = new ArrayList<>();
        for (int[] unit : people) {
            tempList.add(unit[1], unit);
        }
        return tempList.toArray(new int[people.length][2]);
    }

    //https://leetcode.com/problems/furthest-building-you-can-reach/
    public int furthestBuilding(int[] heights, int bricks, int ladders) {
        int n = heights.length, building;
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (building = 1; building < n; building++) {
            int heightDifference = heights[building] - heights[building - 1];
            if (heightDifference > 0) {
                if (pq.size() < ladders) pq.add(heightDifference);
                else {
                    int bricksToBeUsed = heightDifference;
                    if (!pq.isEmpty() && pq.peek() < heightDifference) {
                        bricksToBeUsed = pq.poll();
                        pq.add(heightDifference);
                    }

                    if (bricks - bricksToBeUsed >= 0) bricks -= bricksToBeUsed;
                    else return building - 1;
                }
            }

        }

        return n - 1;
    }

    public static String greatestLetter(String s) {

        System.out.println((char) (97));
        System.out.println((char) (65));

        HashSet<Character> set = new HashSet<>();
        for (char c : s.toCharArray()) set.add(c);

        for (int i = 96; i >= 65; i--) {
            char upperCase = (char) i;
            char lowerCase = Character.toLowerCase(upperCase);
            if (set.contains(lowerCase) && set.contains(upperCase)) return String.valueOf(upperCase);
        }

        return "";
    }

    public long countSubarrays(int[] nums, long k) {
        int count = 0, n = nums.length;

        for (int i = 0; i < n; i++) {
            long sum = 0;
            for (int j = i; j < n; j++) {
                sum += nums[j];
                System.out.println("Current Sum is :: " + sum);
                if (sum * (j - i + 1) < k) count++;
            }
        }

        return count;
    }

    public int[] successfulPairs(int[] spells, int[] potions, long success) {

        int m = spells.length, n = potions.length;
        int[] result = new int[m];

        Arrays.sort(potions);
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = m - 1; i >= 0; i--) {
            if (map.containsKey(spells[i])) {
                result[i] = map.get(spells[i]);
                continue;
            }

            int low = 0, high = n - 1;
            while (low <= high) {
                int mid = (low + high) / 2;
                if ((long) spells[i] * potions[mid] >= success) high = mid - 1;
                else low = mid + 1;
            }
            int successfulPairs = n - low;
            map.put(spells[i], successfulPairs);
            result[i] = successfulPairs;
        }

        return result;
    }


    public boolean strongPasswordCheckerII(String password) {

        int n = password.length();
        boolean hasEightCharacters = n >= 8;
        boolean hasLowerCase = false;
        boolean hasUpperCase = false;
        boolean hasADigit = false;
        boolean hasSpecialCharacter = false;

        String specialCharacters = "!@#$%^&*()-+";

        for (int i = 0; i < n; i++) {
            char character = password.charAt(i);
            if (Character.isDigit(character)) hasADigit = true;
            else if (Character.isUpperCase(character)) hasUpperCase = true;
            else if (Character.isLowerCase(character)) hasLowerCase = true;
            else if (character == '!') hasSpecialCharacter = true;
            else if (character == '@') hasSpecialCharacter = true;
            else if (character == '#') hasSpecialCharacter = true;
            else if (character == '$') hasSpecialCharacter = true;
            else if (character == '%') hasSpecialCharacter = true;
            else if (character == '^') hasSpecialCharacter = true;
            else if (character == '&') hasSpecialCharacter = true;
            else if (character == '*') hasSpecialCharacter = true;
            else if (character == '(') hasSpecialCharacter = true;
            else if (character == ')') hasSpecialCharacter = true;
            else if (character == '-') hasSpecialCharacter = true;
            else if (character == '+') hasSpecialCharacter = true;

            if (i > 0 && password.charAt(i - 1) == password.charAt(i)) return false;
        }

        return hasEightCharacters && hasLowerCase && hasUpperCase && hasADigit && hasSpecialCharacter;
    }

    public int[] arrayChange(int[] nums, int[][] operations) {

        int n = nums.length, m = operations.length;
        HashMap<Integer, Integer> allNums = new HashMap<>();

        for (int i = 0; i < n; i++) allNums.put(nums[i], i);

        for (int[] operation : operations) {
            int key = operation[0];
            int valueToBeSubstituted = operation[1];
            if (allNums.containsKey(key)) {
                int index = allNums.get(operation[0]);
                allNums.remove(key);
                allNums.put(valueToBeSubstituted, index);
            }
        }

        for (Map.Entry<Integer, Integer> mm : allNums.entrySet())
            nums[mm.getValue()] = mm.getKey();

        return nums;
    }

    //https://leetcode.com/problems/check-if-a-string-contains-all-binary-codes-of-size-k/
    public boolean hasAllCodes(String s, int k) {
        if (s.length() < k) return false;
        HashSet<String> set = generateSubStrings(s, k);
        return hasAllCodes("", s, k, set);
    }

    private HashSet<String> generateSubStrings(String s, int k) {
        HashSet<String> set = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < k; i++) sb.append(s.charAt(i));
        set.add(sb.toString());

        for (int i = k; i < s.length() - k + 1; i++) {
            sb.deleteCharAt(0);
            sb.append(s.charAt(i));
            set.add(sb.toString());
        }
        return set;
    }

    private boolean hasAllCodes(String str, String s, int k, HashSet<String> set) {
        if (str.length() == k) {
            System.out.println(str);
            return set.contains(str);
        }

        boolean addingZero = hasAllCodes(str + '0', s, k, set);
        boolean addingOne = hasAllCodes(str + '1', s, k, set);

        return addingZero && addingOne;
    }

    //https://leetcode.com/problems/palindromic-substrings/
    public static int countSubstrings(String s) {
        int n = s.length(), count = 0;
        for (int i = 0; i < n; i++) {
            count += countSubstrings(s, i, i);
            count += countSubstrings(s, i, i + 1);
        }
        return count;
    }

    private static int countSubstrings(String s, int pivotLeft, int pivotRight) {
        int countOfPalindromes = 0, n = s.length();

        while (pivotLeft >= 0 && pivotRight < n && s.charAt(pivotLeft) == s.charAt(pivotRight)) {
            countOfPalindromes++;
            pivotLeft--;
            pivotRight++;
        }

        return countOfPalindromes;
    }

    public static String removeDigit(String number, char digit) {
        List<String> l = new ArrayList<>();

        for (int i = 0; i < number.length(); i++) {
            if (number.charAt(i) == digit) {
                String tempNumber = number.substring(0, i) + number.substring(i + 1);
                l.add(tempNumber);
            }
        }
        Collections.sort(l);
        return l.get(l.size() - 1);
    }

    //https://leetcode.com/problems/backspace-string-compare/discuss/?currentPage=1&orderBy=most_votes&query=
    public boolean backspaceCompare(String s, String t) {
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();

        for (char c : s.toCharArray()) {
            if (c == '#') {
                if (sb1.length() == 0) continue;
                sb1.deleteCharAt(sb1.length() - 1);
            } else sb1.append(c);
        }

        for (char c : t.toCharArray()) {
            if (c == '#') {
                if (sb2.length() == 0) continue;
                sb2.deleteCharAt(sb2.length() - 1);
            } else sb2.append(c);
        }

        return sb1.toString().equals(sb2.toString());
    }

    public static List<Long> maxSubSetSum(List<Integer> k) {
        int n = k.size();
        List<Long> l = new ArrayList<Long>(n);
        for (Integer integer : k) {
            l.add(getFactorsSum(integer));
        }
        return l;
    }

    private static Long getFactorsSum(int integer) {
        long sum = 0;
        for (int i = 1; i <= integer; i++) {
            if (integer % i == 0) sum += i;
        }
        return sum;
    }

    public static int getTriangleArea(List<Integer> x, List<Integer> y) {

        int aX = x.get(0);
        int aY = y.get(0);
        int bX = x.get(1);
        int bY = y.get(1);
        int cX = x.get(2);
        int cY = y.get(2);

        return Math.abs((aX * (bY - cY) + bX * (cY - aY) + cX * (aY - bY))) / 2;
    }

    public int minimumRounds(int[] tasks) {
        int rounds = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int x : tasks) map.put(x, map.getOrDefault(x, 0) + 1);

        for (Map.Entry<Integer, Integer> mm : map.entrySet()) {
            System.out.println("Key and Value are :: " + mm.getKey() + " :: " + mm.getValue());
            int value = mm.getValue();

            while (value % 3 > 3) {
                rounds++;
                value /= 3;
            }
            while (value % 2 > 2) {
                rounds++;
                value /= 2;
            }
            if (value == 1) return -1;
        }

        return rounds;
    }

    public String digitSum(String s, int k) {
        if (s == null || s.length() < k) return s;

        char[] string = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        int j = 0, sum = 0;
        for (int i = 0; i < string.length; i++) {
            sum += string[i] - '0';
            j++;
            if (j == k) {
                sb.append(sum);
                sum = 0;
                j = 0;
            }
        }
        if (j != 0) sb.append(sum);
        return digitSum(sb.toString(), k);
    }

    //https://leetcode.com/problems/shift-2d-grid/submissions/
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        List<List<Integer>> result = new ArrayList<>(m);
        ArrayList<Integer> al = new ArrayList<>();
        for (int[] row : grid)
            for (int element : row)
                al.add(element);

        while (k-- > 0) {
            al.add(0, al.remove(al.size() - 1));
        }

        int index = 0;
        for (int i = 0; i < m; i++) {
            ArrayList<Integer> temp = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                temp.add(al.get(index++));
            }
            result.add(temp);
        }
        return result;
    }

    public int calPoints(String[] ops) {
        Stack<Integer> stack = new Stack<>();
        for (String s : ops) {
            if (Character.isLetter(s.charAt(0))) {
                if (s.charAt(0) == 'D') {
                    int peek = stack.peek() * 2;
                    stack.push(peek);
                } else if (s.charAt(0) == 'C') {
                    stack.pop();
                }
            } else if (s.charAt(0) == '+') {
                int first = stack.pop();
                int second = stack.pop();
                int sum = first + second;
                stack.push(second);
                stack.push(first);
                stack.push(sum);
            } else {
                int number = Integer.parseInt(s);
                stack.push(number);
            }
            System.out.println(stack);
        }
        int sum = 0;
        while (!stack.isEmpty()) sum += stack.pop();
        return sum;
    }

    //https://leetcode.com/problems/two-city-scheduling/submissions/
    public int twoCitySchedCost(int[][] costs) {
        int minimumCost = 0, n = costs.length;
        int maxPeopleInACity = n / 2, peopleInCityA = 0, peopleInCityB = 0;

        Arrays.sort(costs, (a, b) -> (a[0] - a[1]) - (b[0] - b[1]));

        /* DEBUGGING */
        for (int[] cost : costs) {
            for (int c : cost) System.out.print(c + " ");
            System.out.println();
        }

        for (int i = n - 1; i >= 0; i--) {
            int costToA = costs[i][0], costToB = costs[i][1];
            if (peopleInCityA == maxPeopleInACity) {
                minimumCost += costToB;
            } else if (peopleInCityB == maxPeopleInACity) {
                minimumCost += costToA;
            } else {
                if (costToA < costToB) {
                    minimumCost += costToA;
                    peopleInCityA++;
                } else {
                    minimumCost += costToB;
                    peopleInCityB++;
                }
            }

        }
        return minimumCost;
    }

    //https://leetcode.com/problems/broken-calculator/
    public int brokenCalc(int startValue, int target) {
        int operations = 0;

        while (startValue != target) {
            if (target % 2 == 0) target /= 2;
            else target += 1;
            operations++;
        }
        //We are subtracting target from start because we can get minimum steps in this way.
        // As you are done dividing by 2!
        return operations + startValue - target;
    }


    //https://leetcode.com/problems/smallest-string-with-a-given-numeric-value/
    public String getSmallestString(int n, int k) {
        char[] answer = new char[n];
        Arrays.fill(answer, 'a');
        k = k - n;
        while (n > 0 && k > 0) {
            int min = Math.min(25, k);
            answer[--n] = (char) (min + 'a');
            k -= min;
        }
        return String.valueOf(answer);
    }

    //https://leetcode.com/problems/partition-labels/
    public List<Integer> partitionLabels(String s) {
        int n = s.length();
        List<Integer> l = new ArrayList<>();
        HashMap<Character, Integer> map = new HashMap<>();

        //Store the last indexes of the characters.
        for (int i = 0; i < n; i++) map.put(s.charAt(i), i);

        int outerLoop = 0;
        while (outerLoop < n) {
            int maxEnd = map.get(s.charAt(outerLoop)), innerLoop = outerLoop + 1;
            while (innerLoop < maxEnd) {
                int curr = map.get(s.charAt(innerLoop));
                if (curr > maxEnd) {
                    maxEnd = curr;
                }
                innerLoop++;
            }
            l.add(maxEnd - outerLoop + 1);
            outerLoop = maxEnd + 1;
        }

        return l;
    }

    //https://leetcode.com/problems/minimum-domino-rotations-for-equal-row/
    public int minDominoRotations(int[] tops, int[] bottoms) {
        int minFlips = Integer.MAX_VALUE;

        for (int i = 1; i <= 6; i++) {
            minFlips = Math.min(minFlips, generateAllSubSequences(tops, bottoms, i));
            minFlips = Math.min(minFlips, generateAllSubSequences(bottoms, tops, i));
        }

        return minFlips == Integer.MAX_VALUE ? -1 : minFlips;
    }

    private int generateAllSubSequences(int[] tops, int[] bottoms, int target) {
        int n = tops.length;
        int minFlips = 0;

        for (int i = 0; i < n; i++) {
            if (tops[i] != target && bottoms[i] != target) return Integer.MAX_VALUE;
            if (tops[i] != target) minFlips++;
        }
        return minFlips;
    }

    //https://leetcode.com/problems/remove-stones-to-minimize-the-total/
    public int minStoneSum(int[] piles, int k) {
        int stoneWeight = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int pile : piles) pq.add(pile);

        while (k-- > 0) {
            int stones = pq.poll();
            stones -= (int) Math.floor(stones / 2);
            pq.add(Math.round(stones));
        }

        while (pq.size() > 0) stoneWeight += pq.poll();
        return stoneWeight;
    }

    public int halveArray(int[] nums) {
        int minimumOperation = 0;
        double sum = 0;
        PriorityQueue<Double> pq = new PriorityQueue<>(Collections.reverseOrder());

        for (int x : nums) {
            sum += x;
            pq.add((double) x);
        }
        System.out.println("Sum is :: " + sum);

        double tempSum = sum;
        double requiredSum = sum / 2;
        while (tempSum > requiredSum) {

            double peekElement = pq.poll();
            System.out.println("Temp Sum is :: " + tempSum + " and peek element is  :: " + peekElement);
            peekElement /= 2;
            tempSum -= peekElement;
            pq.offer(peekElement);
            minimumOperation++;
        }

        return minimumOperation;
    }

    public boolean divideArray(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int x : nums) map.put(x, map.getOrDefault(x, 0) + 1);


        for (int count : map.values()) {
            if (count % 2 != 0) return false;
        }

        return true;
    }

    //https://leetcode.com/problems/validate-stack-sequences/submissions/
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> s = new Stack<>();
        int poppedPointer = 0;
        for (int j : pushed) {
            s.push(j);
            while (!s.isEmpty() && s.peek() == popped[poppedPointer]) {
                s.pop();
                poppedPointer++;
            }
        }
        return s.isEmpty();
    }

    //https://leetcode.com/problems/minimum-add-to-make-parentheses-valid/submissions/
    public int minAddToMakeValid(String str) {
        Stack<Character> s = new Stack<>();
        for (char c : str.toCharArray()) {
            if (c == '(') s.push(c);
            else {
                if (!s.isEmpty() && s.peek() == '(') s.pop();
                else s.push(c);
            }
        }
        return s.size();
    }

    //https://leetcode.com/problems/minimum-number-of-swaps-to-make-the-string-balanced/submissions/
    public int minSwaps(String s) {
        int open = 0, unBalanced = 0;
        for (char character : s.toCharArray()) {
            if (character == '[') open++;
            else {
                if (open > 0) open--;
                else {
                    unBalanced++;
                }
            }
        }

        unBalanced += open;
        unBalanced /= 2;
        return (unBalanced + 1) / 2;
    }

    //https://leetcode.com/problems/minimum-number-of-swaps-to-make-the-string-balanced/
    public String minRemoveToMakeValid(String string) {
        Stack<Integer> s = new Stack<>();
        StringBuilder sb = new StringBuilder();
        int index = 0;
        for (char c : string.toCharArray()) {
            if (c == '(') s.push(index);
            else if (c == ')') {
                if (!s.isEmpty() && string.charAt(s.peek()) == '(') s.pop();
                else s.push(index);
            }
            index++;
        }

        HashSet<Integer> set = new HashSet<>(s);
        index = 0;
        for (char character : string.toCharArray()) {
            if (!set.contains(index)) sb.append(character);
            index++;
        }

        return sb.toString();
    }

    //https://leetcode.com/problems/spiral-matrix-ii/submissions/
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int first = 1, last = n * n;

        int top = 0, left = 0, bottom = n - 1, right = n - 1;

        //Traverse from 1 to n^2;
        while (first <= last) {
            for (int i = left; i <= right && first <= last; i++) {
                matrix[top][i] = first++;
            }
            top++;
            for (int i = top; i <= bottom && first <= last; i++) {
                matrix[i][right] = first++;
            }
            right--;
            for (int i = right; i >= left && first <= last; i--) {
                matrix[bottom][i] = first++;
            }
            bottom--;
            for (int i = bottom; i >= top && first < last; i--) {
                matrix[i][left] = first++;
            }
            left++;
        }
        return matrix;
    }


    //https://leetcode.com/problems/spiral-matrix/submissions/
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        if (matrix == null || matrix.length == 0) return result;

        int m = matrix.length, n = matrix[0].length;
        int top = 0, left = 0, bottom = m - 1, right = n - 1;
        int size = m * n;

        while (result.size() < size) {
            for (int i = left; i <= right && result.size() < size; i++) {
                result.add(matrix[top][i]);
            }
            top++;
            for (int i = top; i <= bottom && result.size() < size; i++) {
                result.add(matrix[i][right]);
            }
            right--;

            for (int i = right; i >= left && result.size() < size; i--) {
                result.add(matrix[bottom][i]);
            }
            bottom--;
            for (int i = bottom; i >= top && result.size() < size; i--) {
                result.add(matrix[i][left]);
            }
            left++;
        }
        return result;
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
                if (!s.isEmpty()) s.pop();
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
