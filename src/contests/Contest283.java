package contests;

import leetcode.TreeNode;

import java.util.*;

public class Contest283 {
    public static void main(String[] args) {

    }


    public TreeNode createBinaryTree(int[][] descriptions) {
        HashMap<Integer, TreeNode> map = new HashMap<>();
        HashSet<Integer> children = new HashSet<>();
        for (int[] arr : descriptions) {
            int parent = arr[0], child = arr[1], isLeft = arr[2];
            TreeNode parentNode = map.getOrDefault(parent, new TreeNode(parent));
            TreeNode childNode = map.getOrDefault(child, new TreeNode(child));

            if (isLeft == 1) parentNode.left = childNode;
            else parentNode.right = childNode;


            map.put(parent, parentNode);
            map.put(child, childNode);
            children.add(child);
        }

        for (int[] arr : descriptions) if (!children.contains(arr[0])) return map.get(arr[0]);
        return null;
    }

    public long minimalKSum(int[] nums, int K) {

        long k = (long) K;
        Long sum = 0L;
        int count = K;
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            // !set.contains(num) handles duplicates
            if (!set.contains(num) && 1 <= num && num <= k) {
                sum += (long) num;
                count -= 1;
            }
            set.add(num);
        }
        long ans = k * (k + 1) / 2 - sum;
        for (int i = K + 1; count < k; i++) {
            if (!set.contains(i)) {
                ans += i;
                count++;
            }
        }
        return ans;
    }

    public List<String> cellsInRange(String s) {
        List<String> l = new ArrayList<>();

        //A1 : K2
        char first = s.charAt(0);
        char second = s.charAt(3);
        int firstNumber = s.charAt(1) - '0';
        int secondNumber = s.charAt(4) - '0';

        for (int i = firstNumber; i <= secondNumber; i++) {
            for (char j = first; j <= second; j++) {
                String ss = j + "" + i;
                l.add(ss);
            }
        }
        Collections.sort(l);
        l.forEach(System.out::println);

        return l;
    }


}
