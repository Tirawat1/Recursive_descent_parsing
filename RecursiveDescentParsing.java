import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class RecursiveDescentParsing {

    private static String input;
    private static int pos;
    private static String grammar;
    private static Stack<Character> temporary;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter an input string: ");
        input = "(int)";
        System.out.print("Enter a grammar (in BNF format): ");
        grammar = "E->T|T+E;T->int|int*T|(E)";
        System.out.print("Enter the start symbol: ");
        String startSymbol = "E";
        pos = 0;

        parse(startSymbol);
        if (pos == input.length()) {
            System.out.println("Parsing successful.");
        } else {
            System.out.println("Parsing failed.");
        }
    }

    private static void parse(String nonTerminal) {
        int n = 0;
        boolean check = true;
        String[] productions = grammar.split(";");
        for (String production : productions) {
            String[] parts = production.split("->");
            String leftSide = parts[0].trim(); // grammar
            String rightSide = parts[1].trim(); // symbol
            if (leftSide.equals(nonTerminal)) {
                String[] symbols = rightSide.split("\\|");
                System.out.println("Grammar: " + leftSide);
                for (String symbol : symbols) {
                    n++;
                    System.out.println(n);
                    if (check) {
                        System.out.println("Symbol: " + symbol);
//                    if (symbol.equals("ε")) {
//                        return;
//                    }
                        if (pos < input.length()) {
                            if (isNonTerminal(symbol)) {
                                System.out.println("is non-terminal: " + symbol);
                                System.out.println("parse");
                                parse(symbol);
                            } else if (containNonTerminal(symbol)) {
                                Stack<Character> next = nextParse(symbol);
                                if (next.size() >= 1) {
                                    System.out.println("contain parse " + symbol);
                                    parse(next.pop().toString());
                                    if (pos < input.length()) {
                                        check = reCheck(next, symbol);
                                    }
                                }
                            } else if (pos < input.length() && input.charAt(pos) == symbol.charAt(0)) {
                                nextParse(symbol);
                                check = false;
                            }
                        }
                    }
                }
            }
        }
    }
    private static boolean isNonTerminal(String symbol) {
        return symbol.length() == 1 && Character.isUpperCase(symbol.charAt(0));
    }
    private static boolean containNonTerminal(String symbol) {
        for (int i = 0; i < symbol.length(); i++) {
            if (isNonTerminal(String.valueOf(symbol.charAt(i)))) {
                return true;
            }
        }
        return false;
    }
    private static Stack<Character> nextParse(String symbol) {
        Stack<Character> temp = new Stack<>();
        for (int i = 0; i < symbol.length(); i++) {
            if (temp.size() == 0) {
                if (isNonTerminal(String.valueOf(symbol.charAt(i))) && i != symbol.length() - 1) {
                    System.out.println("push Non");
                    temp.push(symbol.charAt(i));
                } else if (pos < input.length()) {
                    if (input.charAt(pos) == symbol.charAt(i)) {
                        pos++;
                        System.out.println("pos++ nextParse");
                    } else {
                        System.out.println(input.charAt(pos));
                        pos -= temp.size();
                        temp.clear();
                        return temp;
                    }
                }
            } else {
                temp.push(symbol.charAt(i));
            }
        }
        return temp;
    }
    private static boolean reCheck(Stack<Character> temp, String symbol) {
        System.out.println("kuy");
        while (temp.size() > 0) {
            if (temp.peek() == input.charAt(pos) && pos < input.length()) {
                temp.pop();
                pos++;
                System.out.println("pos check");
            } else if (isNonTerminal(temp.peek().toString())) {
                parse(temp.pop().toString());
            } else {
                pos -= (symbol.length() + 1 - temp.size());
                return false;
            }
        }
        return true;
    }
}