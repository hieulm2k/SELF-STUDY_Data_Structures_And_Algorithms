import java.util.Scanner;
import java.util.Stack;

public class CalcRPNExpression {
    private static final char[] opt = new char[]{'+', '-', '*', '/'};
    private static final Stack<Integer> stack = new Stack<>();

    private static String refine(String s) {
        for (int i = s.length() - 1; i >= 0; --i) {
            if (checkOpt(s.charAt(i)) || checkOpt(s.charAt(i + 1))) {
                s = s.substring(0, i + 1) + ' ' + s.substring(i + 1);
            }
        }
        return s.replaceAll(" \\s+", " ");
    }

    private static void process(String t) {
        if (!checkOpt(t.charAt(0))) {
            stack.push(Integer.parseInt(t));
        } else {
            int y = stack.pop();
            int x = stack.pop();
            switch (t.charAt(0)) {
                case '+':
                    x += y;
                    break;
                case '-':
                    x -= y;
                    break;
                case '*':
                    x *= y;
                    break;
                case '/':
                    x /= y;
                    break;
            }
            stack.push(x);
        }
    }

    private static boolean checkOpt(char s) {
        for (char c : opt) {
            if (c == s) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        System.out.print("Enter RPN Expression: ");
        Scanner scanner = new Scanner(System.in);
        String rpn = scanner.nextLine();
        scanner.close();
        rpn = refine(rpn);
        StringBuilder t = new StringBuilder();

        for (int i = 0; i < rpn.length(); ++i) {
            if (rpn.charAt(i) != ' ') {
                t.append(rpn.charAt(i));
            } else {
                process(t.toString());
                t = new StringBuilder();
            }
        }

        System.out.println(rpn + " = " + stack.pop());
    }
}