import java.util.Scanner;

public class main {

    public static int index = 0;
    public static String input_globalString;

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

    public static boolean match(Character chk, Character correct) {
        if (chk == correct) {
            return true;
        }
        return false;
    }

    public static boolean grammer_E() {

        if(input_globalString.charAt(index) == '+'){ // call +TE
            System.out.println(input_globalString.charAt(index) + " : E -> T+E");
            grammer_T();
            grammer_E();
        }else{ // call T
            System.out.println(input_globalString.charAt(index) + " : E -> T");
            grammer_T();
        }
        return false;
    }

    public static boolean grammer_T() {
        if(input_globalString.charAt(index) == '('){ // call (T)
            System.out.println(input_globalString.charAt(index) + " : T -> (E)");
            index++;
            grammer_E();
            if(input_globalString.charAt(index) == ')'){
                System.out.println(input_globalString.charAt(index) + " : T -> (E)");
                index++;
            }else{
                return false;
            }
        }else if(input_globalString.charAt(index) == '*'){ // call T -> *intT
            System.out.println(input_globalString.charAt(index) + " : T -> int*T");
            index++;
            if(Character.isDigit(input_globalString.charAt(index))) {
                grammer_T();
                return true;
            }
        }else if(Character.isDigit(input_globalString.charAt(index))){ // call T -> int
            System.out.println(input_globalString.charAt(index) + " : T -> int");
            index++;
            return true;
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
            grammer_E();
            if (index == input.length()) {
                System.out.println("String is successfully parsed");
            }
        } else {
            System.out.println("Invalid parsing String");
        }
    }
}