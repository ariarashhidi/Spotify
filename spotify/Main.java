import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        LoginMenu loginMenu = new LoginMenu();
        loginMenu.run(scan);
    }
}