import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginMenu {
    public static void run(Scanner scanner) {
        while (true) {
            String line = scanner.nextLine();
            if (line.equals("exit") || line.equals("back")) {
                break;
            }
            else if(line.equals("show menu name")){
                System.out.println("login menu");
            }
            else if(getcommandMatcher(line , "^register as user -u ([A-Za-z0-9]+) -p ([A-Za-z0-9]+)$").find()){
                Matcher matcher = getcommandMatcher(line , "^register as user -u ([A-Za-z0-9]+) -p ([A-Za-z0-9]+)$");
                userRegister(matcher);
            }
            else if(getcommandMatcher(line , "^register as artist -u ([A-Za-z0-9]+) -p ([A-Za-z0-9]+) -n ([A-Za-z0-9\\s]+)$").find()){
                Matcher matcher = getcommandMatcher(line , "register as artist -u (.+) -p (.+) -n (.+)");
                artistRegister(matcher);
            }
            else if(getcommandMatcher(line , "^login as user -u ([A-Za-z0-9]+) -p ([A-Za-z0-9]+)$").find()){
                Matcher matcher = getcommandMatcher(line , "login as user -u (.+) -p (.+)");
                userLogin(matcher , scanner);
            }
            else if(getcommandMatcher(line , "^login as artist -u ([A-Za-z0-9]+) -p ([A-Za-z0-9]+)$").find()){
                Matcher matcher = getcommandMatcher(line , "login as artist -u (.+) -p (.+)");
                artistLogin(matcher , scanner);
            }
            else{
                System.out.println("invalid command");
            }

        }
    }
    private static Matcher getcommandMatcher(String input , String regex){
        return Pattern.compile(regex).matcher(input);
    }
    private static void artistRegister(Matcher matcher){
        if(matcher.find()){
            String username = matcher.group(1);
            String password = matcher.group(2);
            String nickName = matcher.group(3);
            Matcher matcher1 = getcommandMatcher(username , "^[A-Za-z0-9]+$");
            if(!isUsernametrue(matcher1)){
                System.out.println("invalid command");
            }
            else{
                //chek tekraribodan namkarbari
                if(Artists.isthere(username)){
                    System.out.println("username already exists");
                }
                else {
                    Matcher matcher2 = getcommandMatcher(password, "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z0-9]{3,}$");
                    if (!isPasswordWeek(matcher2)) {
                        System.out.println("password is not strong enough");
                    } else {
                        //add shodan artist
                        Artists artists = new Artists(username, password, nickName);
                        System.out.println("artist registered successfully");
                    }
                }
            }
        }
    }
    private static void userRegister(Matcher matcher){
        if(matcher.find()){
            String username = matcher.group(1);
            String password = matcher.group(2);
            Matcher matcher1 = getcommandMatcher(username , "^[A-Za-z0-9]+$");

            if (!isUsernametrue(matcher1)) {
                System.out.println("invalid command");
            } else {
                //chek tekraribodan namkarbari
                if(User.isthat(username)){
                    System.out.println("username already exists");
                }
                else {
                    Matcher matcher2 = getcommandMatcher(password, "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z0-9]{3,}$");
                    if (!isPasswordWeek(matcher2)) {
                        System.out.println("password is not strong enough");
                    } else {
                        //add shodan karbar
                        User user = new User(username, password);
                        System.out.println("user registered successfully");
                    }
                }
            }
        }
    }
    private static void artistLogin(Matcher matcher , Scanner scanner){
        if(matcher.find()){
            String name = matcher.group(1);
            String password = matcher.group(2);
            Matcher matcher1 = getcommandMatcher(name , "^[A-Za-z0-9]+$");
            if(!isUsernametrue(matcher1)){
                System.out.println("invalid command");
            }else {
                int chek = Artists.isthereartist(name, password);
                if (chek == 0) {
                    System.out.println("username doesn't exist");
                } else if (chek == 1) {
                    System.out.println("password is wrong");
                } else {
                    System.out.println("artist logged in successfully");
                    ArtistMenu.run(scanner);
                }
            }
        }
    }
    private static void userLogin(Matcher matcher , Scanner scanner){
        if(matcher.find()){
            String usermane = matcher.group(1);
            String password = matcher.group(2);
            Matcher matcher1 = getcommandMatcher(usermane , "^[A-Za-z0-9]+$");
            if(!isUsernametrue(matcher1)){
                System.out.println("invalid command");
            }
            else {
                int chek = User.isthereuser(usermane, password);
                if (chek == 0) {
                    System.out.println("username doesn't exist");
                } else if (chek == 1) {
                    System.out.println("password is wrong");
                } else {
                    System.out.println("user logged in successfully");
                    UserManu.run(scanner);
                }
            }
        }
    }

    private static boolean isUsernametrue(Matcher matcher){
        if(matcher.find()){
            return true;
        }
        return false;
    }

    private static boolean isPasswordWeek(Matcher matcher){
        if(matcher.find()){
            return true;
        }
        return false;
    }

}
