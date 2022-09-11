import java.util.Scanner;
import java.util.Stack;

public class ConvertInfixToRPN {
    private static final char[] opt = new char[]{'+', '-', '*', '/', '(', ')'};
    private static final Stack<Character> stack = new Stack<>();

    private static String refine(String s) {
        for (int i = s.length() - 1; i >= 0; --i) {
            if (checkOpt(s.charAt(i)) || checkOpt(s.charAt(i + 1))) {
                s = s.substring(0, i + 1) + ' ' + s.substring(i + 1);
            }
        }
        return s.replaceAll(" \\s+", " ");
    }

    private static void process(String t) {
        char c = t.charAt(0);
        char x;

        if (!checkOpt(c)) {
            System.out.print(t + ' ');
        } else {
            switch (c) {
                case '(':
                    stack.push(c);
                    break;
                case ')':
                    do {
                        x = stack.pop();
                        if (x != '(') {
                            System.out.print(String.valueOf(x) + ' ');
                        }
                    } while (x != '(');
                    break;
                case '+':
                case '-':
                case '*':
                case '/':
                    while (!stack.isEmpty() && priority(c) <= priority(stack.peek())) {
                        System.out.print(stack.pop().toString() + ' ');
                    }
                    stack.push(c);
                    break;
            }
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

    private static int priority(char c) {
        if (c == '*' || c == '/') return 2;
        if (c == '+' || c == '-') return 1;
        if (c == '(') return 0;
        return 0;
    }

    public static void main(String[] args) {
        System.out.print("Infix = ");
        Scanner scanner = new Scanner(System.in);
        String infix = scanner.nextLine();
        scanner.close();

        infix = refine(infix);
        System.out.println("Refined: " + infix);
        System.out.print("RPN: ");

        StringBuilder t = new StringBuilder();

        for (int i = 0; i < infix.length(); ++i) {
            if (infix.charAt(i) != ' ') {
                t.append(infix.charAt(i));
            } else {
                process(t.toString());
                t = new StringBuilder();
            }
        }

        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + ' ');
        }
    }
}
