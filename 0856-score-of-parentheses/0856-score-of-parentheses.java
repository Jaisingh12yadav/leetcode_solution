import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public int scoreOfParentheses(String s) {

        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(0);

        for (char ch : s.toCharArray()) {

            if (ch == '(') {
                stack.push(0);
            } 
            else {
                int inside = stack.pop();

                int score;

                if (inside == 0) {
                    score = 1;
                } else {
                    score = 2 * inside;
                }

                stack.push(stack.pop() + score);
            }
        }

        return stack.pop();
    }
}