import java.util.*;

public class RecursiveDescentParsing {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter an input string: ");
        String input = "(int)";
        System.out.print("Enter a grammar (in BNF format): ");
        String grammar_in = "E->T|T+E;T->int|int*T|(E)";
        Map<String, List<String>> grammar = new HashMap<>();
        String[] productions = grammar_in.split(";");
        for (String production : productions) {
            String[] parts = production.split("->");
            String leftSide = parts[0].trim(); // grammar
            String rightSide = parts[1].trim(); // symbol
            String[] symbols = rightSide.split("\\|");
            List<String> list = new ArrayList<>();
            for (String s : symbols) {
                list.add(s);
            }
            grammar.put(leftSide, list);
        }

        System.out.print("Enter the start symbol: ");
        String startSymbol = "E";
        if (parse(input, grammar, startSymbol, 0)) {
            System.out.println("accepted");
        } else {
            System.out.println("not accepted");
        }
    }

    private static boolean parse(String s, Map<String, List<String>> grammar, String iterator, Integer index) {
        if (index == null) index = 0;
        if (iterator.compareTo(s) == 0) {
            return true;
        }

        if (iterator.length() <= index | s.length() <= index) {
            return false;
        }

        if (iterator.charAt(index) == s.charAt(index)) {
            boolean flag = parse(s, grammar, iterator, index + 1);
            if (flag) {
                return true;
            }
        }

        List<String> list = grammar.get(Character.toString(iterator.charAt(index)));
        if (list != null){
            for (String str : list) {
                String temp = iterator.substring(0, index) + str + iterator.substring(index+1);

                System.out.println(temp);
                boolean flag = parse(s, grammar, temp, index);
                if (flag) {
                    return true;
                }
            }
        }
        return false;
    }
}