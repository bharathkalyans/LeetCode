package contests;

import java.util.*;

public class Contest284 {
    public static void main(String[] args) {
        int[] a = new int[]{2, 2, 2, 2, 2};


        /**
         5
         [[3,1,4,1],[1,1,2,2],[1,0,2,0],[4,3,4,4],[0,3,1,4],[2,3,3,4]]
         [[0,0],[2,1],[2,0],[2,3],[4,3],[2,4],[4,1],[0,2],
         [4,0],[3,1],[1,2],[1,3],[3,2]]
         * */
        int[][] ar = new int[][]{{3, 1, 4, 1}, {1, 1, 2, 2},
                {1, 0, 2, 0}, {4, 3, 4, 4},
                {0, 3, 1, 4}, {2, 3, 3, 4}};

        int[][] dig = new int[][]{{0, 0}, {2, 1},
                {2, 0}, {2, 3},
                {4, 3}, {2, 4},
                {4, 1}, {0, 2},
                {4, 0}, {3, 1}, {0, 4}, {0, 3}, {1, 4},
                {1, 2}, {1, 3}, {3, 2}};
        System.out.println(new Contest284().digArtifacts(5, ar, dig));

    }

    public int digArtifacts(int n, int[][] artifacts, int[][] dig) {
        int numberOfArtifacts = 0;
        int colour = 1;
        int[][] grid = new int[n][n];
        for (int[] row : grid) Arrays.fill(row, 0);

        //Building the Location Grid.
        for (int[] positions : artifacts) {
            int count = 0;
            for (int p = positions[0]; p <= positions[2]; p++) {
                for (int q = positions[1]; q <= positions[3]; q++) {
                    grid[p][q] = colour;
                }
            }
            colour++;
        }
        //Remove the Mud.
        for (int[] position : dig) {
            grid[position[0]][position[1]] *= -1;
        }

        //Debugging Purpose.
        for (int[] row : grid) {
            for (int c : row) System.out.print(c + " ");
            System.out.println();
        }

        //Now Go through the Grid to check if we have discovered any artifact.
        for (int[] positions : artifacts) {
            int count = 0;
            boolean flag = true;
            for (int p = positions[0]; p <= positions[2]; p++) {
                for (int q = positions[1]; q <= positions[3]; q++) {
                    if (grid[p][q] > 0) {
                        flag = false;
                        break;
                    }
                }
            }
            if (flag) numberOfArtifacts++;
        }

        return numberOfArtifacts;
    }

    public List<Integer> findKDistantIndices(int[] nums, int key, int k) {
        int n = nums.length;

        List<Integer> possibleIndices = new ArrayList<>();
        HashSet<Integer> set = new LinkedHashSet<>();
        for (int i = 0; i < n; i++) {
            if (nums[i] == key) possibleIndices.add(i);
        }

        for (int i = 0; i < n; i++) {
            for (int index : possibleIndices) {
                if (Math.abs(i - index) <= k && nums[index] == key)
                    set.add(i);
            }
        }

        return new ArrayList<>(set);
    }


}
