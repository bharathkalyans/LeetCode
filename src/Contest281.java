import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;


class Pair {
    char c;
    int freq;

    Pair(char c, int freq) {
        this.c = c;
        this.freq = freq;
    }
}

//https://leetcode.com/contest/weekly-contest-281/
public class Contest281 {
    public static void main(String[] args) {
        System.out.println(new Contest281()
                .repeatLimitedString("pdprlxqryxdirdr", 10));
    }


    public long coutPairs(int[] nums, int k) {
        long count = 0;
        HashMap<Integer, Integer> map = new HashMap<>();


        return count;

    }

    public String repeatLimitedString(String s, int repeatLimit) {

        char[] string = s.toCharArray();
//        Arrays.sort(string);
        int[] ch = new int[26];

        for (char cc : string)
            ch[cc - 'a']++;

        Deque<Pair> q = new ArrayDeque<>();

        for (int i = 25; i >= 0; i--) {
            if (ch[i] != 0) {
                q.add(new Pair((char) (i + 'a'), ch[i]));
                System.out.println("Character :: " + (char) (i + 'a') + " with Frequency :: " + ch[i]);
            }
        }

        StringBuilder sb = new StringBuilder();

        while (q.size() > 1) {
            Pair temp = q.poll();
            if (!sb.isEmpty() && temp.c == sb.charAt(sb.length() - 1)) {
                Pair temp2 = q.poll();
                assert temp2 != null;
                sb.append(temp2.c);
                if (temp2.freq - 1 != 0)
                    q.addFirst(new Pair(temp2.c, temp2.freq - 1));
            }
            int ff = Math.min(temp.freq, repeatLimit);
            while (ff-- > 0) sb.append(temp.c);

            if (temp.freq - repeatLimit > 0)
                q.addFirst(new Pair(temp.c, temp.freq - repeatLimit));

        }

        System.out.println(sb.toString());

        if (q.size() == 1) {
            Pair tt = q.poll();
            if (!sb.isEmpty() && tt.c != sb.charAt(sb.length() - 1)) {
                System.out.println("Element and Frequency is  :: " + tt.c + " :: " + tt.freq);
                int gg = Math.min(tt.freq, repeatLimit);
                sb.append(String.valueOf(tt.c).repeat(repeatLimit));
            }
        }

        return sb.toString();
    }

    public ListNode mergeNodes(ListNode head) {


        if (head == null || head.next == null) return head;

        ListNode dummyHead = new ListNode(-1);
        ListNode tail = dummyHead;
        ListNode temp = head.next;
        int curr_sum = 0;

        while (temp != null) {
            if (temp.val == 0) {
                ListNode tempNode = new ListNode(curr_sum);
                curr_sum = 0;
                tail.next = tempNode;
                tail = tempNode;

            } else {
                curr_sum += temp.val;
            }
            temp = temp.next;
        }

        return dummyHead.next;
    }

    public int countEven(int num) {

        int count = 0;

        for (int i = 2; i <= num; i++) {
            if (isSumOFDigitsEven(i))
                count++;
        }

        return count;
    }

    private boolean isSumOFDigitsEven(int i) {
        int temp = i;
        int sum = 0;
        while (temp > 0) {
            int digit = temp % 10;
            sum += digit;
            temp /= 10;
        }
        return sum % 2 == 0;
    }

}
