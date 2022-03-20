package contests;

import java.util.Stack;

public class Contest285 {
    public static void main(String[] args) {
        int[] a = new int[]{6, 6, 5, 5, 4, 1};

    }

    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> s = new Stack<>();

        for (int asteroid : asteroids) {
            if (asteroid < 0) {
                while (!s.isEmpty() && s.peek() > 0 && s.peek() < -asteroid)
                    s.pop();
                if (!s.isEmpty() && s.peek() == -asteroid) s.pop();
                else if (!s.isEmpty() && s.peek() >= -asteroid) continue;
                else s.push(asteroid);
            } else s.push(asteroid);
        }

        //Filling the result.
        int size = s.size(), i = size - 1;
        int[] result = new int[size];
        while (!s.isEmpty()) result[i--] = s.pop();
        return result;
    }


    public int countCollisions(String d) {
        int collisions = 0, n = d.length();
        int left = 0, right = n - 1;
        char[] directions = d.toCharArray();
        while (left < n && directions[left] == 'L') left++;
        while (right >= 0 && directions[right] == 'R') right--;

        while (left < right) {
            if (directions[left] != 'S') collisions++;
            left++;
        }
        return collisions;
    }


    public int countHillValley(int[] nums) {
        int hills = 0, n = nums.length;

        for (int i = 1; i < n - 1; i++) {
            int left = i - 1, right = i + 1;
            if (nums[i] == nums[i - 1]) continue;
            while (left >= 0 && nums[left] == nums[i]) left--;
            while (right < n && nums[right] == nums[i]) right++;
            if (left >= 0 && right < n) {
                if (nums[left] < nums[i] && nums[right] < nums[i]) hills++;
                else if (nums[left] > nums[i] && nums[right] > nums[i]) hills++;
            }
        }
        return hills;
    }

}
