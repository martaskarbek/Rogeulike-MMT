import java.util.Random;
import java.util.Scanner;

public class InputProvider {

    private static Scanner sc = new Scanner(System.in);
    private static Random random = new Random();

    public static int RandomInt(){
        return random.nextInt(30);        
    }

    public static int getInt(String prompt) {
        int number;
        System.out.print(prompt);
        number = Integer.parseInt(sc.next());
        return number;
                
    }

    public static String getString(String prompt) {
        String string;
        System.out.print(prompt);
        string = sc.next();
        return string;   
        }
    

}