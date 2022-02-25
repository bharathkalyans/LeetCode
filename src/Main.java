import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') stack.push('(');
            else if (s.charAt(i) == ')') {
                if (stack.isEmpty()) {
                    System.out.println(1);
                    break;
                }
                if (stack.peek() == '(') stack.pop();
            }
        }
        if (stack.isEmpty()) System.out.println(0);
        else System.out.println(1);
    }
}
