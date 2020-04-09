import java.util.Scanner;

public class Input {
    static Scanner scanner = new Scanner(System.in);

    public int getIntInput() {
        return scanner.nextInt();
    }

    public String getStrInput(String message) {
        System.out.println(message);
        return scanner.next();
    }

    public static int getIntInput(String message) {
        System.out.println(message);
        return scanner.nextInt();
    }
}