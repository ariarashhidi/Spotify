import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArtistMenu {
    public static void run(Scanner scanner){
        while (true){
            String line = scanner.nextLine();
            if(line.equals("back")){
                break;
            }
            else if(line.equals("show menu name")){
                System.out.println("artist menu");
            }
            else if(line.equals("show tracks")){
                showtracks();
            }
            else if(line.equals("show songs")){
                showSongs();
            }
            else if(line.equals("show podcasts")){
                showPodcasts();
            }
            else if(getCommandmatcher(line , "^release -n ([A-Za-z0-9\\s]+) -t (.+) -d (\\d+) -r (\\d+)\\/(\\d+)\\/(\\d+)$").find()){
                Matcher matcher = getCommandmatcher(line , "release -n (.+) -t (.+) -d (\\d+) -r (\\d+)\\/(\\d+)\\/(\\d+)");
                if(matcher.find()){
                    releastracks(matcher);
                }
            }
            else if(line.equals("num of followers")){
                getNumberOfFollowers();
            }
            else if(line.equals("get rank")){
                getRank();
            }
            else{
                System.out.println("invalid command");
            }
        }
    }
    private static Matcher getCommandmatcher(String input , String regex){
        return Pattern.compile(regex).matcher(input);
    }
    private static void showtracks(){
        Artists artists = Artists.getLoggedinasartist();
        ArrayList<Track> trackssoertbydate = new ArrayList<>();
        trackssoertbydate = Artists.sortTracksByReleaseDate(artists.getTracks());
        for(int i = 0 ; i < trackssoertbydate.size() ; i++){
            System.out.println(trackssoertbydate.get(i).getName());
        }

    }
    private static void showSongs(){
        Artists artists = Artists.getLoggedinasartist();
        ArrayList<Track> trackssoertbydate = new ArrayList<>();
        trackssoertbydate = Artists.sortTracksByReleaseDate(artists.getTracks());
        for(int i = 0 ; i < trackssoertbydate.size() ; i++){
            if(trackssoertbydate.get(i).gettype()){
                System.out.println(trackssoertbydate.get(i).getName());
            }
        }
    }
    private static void showPodcasts(){
        Artists artists = Artists.getLoggedinasartist();
        ArrayList<Track> trackssoertbydate = new ArrayList<>();
        trackssoertbydate = Artists.sortTracksByReleaseDate(artists.getTracks());
        for(int i = 0 ; i < trackssoertbydate.size() ; i++){
            if(!trackssoertbydate.get(i).gettype()){
                System.out.println(trackssoertbydate.get(i).getName());
            }
        }

    }
    private static void releastracks(Matcher matcher){
        if(true){
            String nameofsong = matcher.group(1);
            if(Track.istheresong(nameofsong)){
                System.out.println("track name already exists");
            }
            else {
                String type = matcher.group(2);
                if (type.equals("song") || type.equals("podcast")) {
                    boolean type1;
                    if (type.equals("song")) {
                        type1 = true;
                    } else {
                        type1 = false;
                    }
                    int duration = Integer.parseInt(matcher.group(3));
                    if(matcher.group(4).length() != 4 || matcher.group(5).length()!=2 || matcher.group(6).length() != 2){
                        System.out.println("invalid command");
                    }
                    else {
                        String date = matcher.group(4) + "/" + matcher.group(5) + "/" + matcher.group(6);
                        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");//////////////////////
                        Date date1 = null;
                        try {
                            date1 = format.parse(date);
                        } catch (ParseException e) {
                            throw new RuntimeException(e);
                        }
                        Artists artists = Artists.getLoggedinasartist();
                        Track track = new Track(date1, artists, type1, duration, nameofsong);
                        artists.addTOTracks(track);
                        System.out.println("track released successfully");
                    }
                }
            }

        }
    }
    private static void getRank(){
        Artists artists = Artists.getLoggedinasartist();
        System.out.println(artists.getRank());
    }
    private static void getNumberOfFollowers(){
        Artists artists = Artists.getLoggedinasartist();
        System.out.println(artists.getFollowers());
    }
}
