import java.util.Scanner;
import java.util.*;

public class main {

    public static int index = 0;
    public static String input_globalString;
    public static Map<vertex , List<vertex>> tree;

    public static boolean isGrammer(String input) {
        Character[] checkGrammer = { 'E', 'T', '(', ')','+','*'};
        for (int i = 0; i < input.length(); i++) {
            int flag = 0;
            Character chk = input.charAt(i);

            for (int j = 0; j < checkGrammer.length; j++) {
                if (match(chk, checkGrammer[j]) || Character.isDigit(chk)){
                    flag = 1;
                    break;
                }
            }
            if (flag == 0) {
                return false;
            }
        }
        return true;
    }


    public static boolean match(Character chk, Character grammer) {
        if (chk == grammer) {
            return true;
        }
        return false;
    }

    public static void parse(){
        vertex start = new vertex('E');
        tree.put(start, null);
        graph treeGraph = new graph(tree);
        E();
    }

    public static boolean E(){
        while(true){
            if()

            if(match()){
                index++;
                break;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        // System.out.println("Hello World");
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the string to parse: ");
        String input = sc.nextLine();
        input_globalString = input;
        if (isGrammer(input)) {
            // System.out.println("Valid Grammer");
            parse();
            if (index == input.length()) {
                System.out.println("String is successfully parsed");
            }
            else{
                System.out.println("String is failed parsed");
            }
        } else {
            System.out.println("Invalid parsing String");
        }
    }
}