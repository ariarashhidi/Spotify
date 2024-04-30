import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlaylistMenu {
    public static void run(Scanner scanner){
        while (true){
            String line = scanner.nextLine();
            if(line.equals("back")){
                break;
            }
            else if(line.equals("show menu name")){
                System.out.println("playlist menu");
            }
            else if(line.equals("show tracks")){
                showTrack();
            }
            else if(line.equals("show duration")){
                showDuration();
            }
            else if(getCommandmatcher(line , "^add -t ([A-Za-z0-9\\s]+)$").find()){
                Matcher matcher = getCommandmatcher(line , "add -t (.+)");
                if(matcher.find()){
                    addTracks(matcher);
                }
            }
            else if(getCommandmatcher(line , "^remove -t ([A-Za-z0-9\\s]+)$").find()){
                Matcher matcher = getCommandmatcher(line , "remove -t (.+)");
                if(matcher.find()){
                    removeTracks(matcher);
                }
            }
            else{
                System.out.println("invalid command");
            }
        }
    }
    private static Matcher getCommandmatcher(String input , String regex){
        return Pattern.compile(regex).matcher(input);
    }
    private static void showTrack(){
        Playlist playlist = Playlist.getCurrentPlaylist();
        ArrayList<Track> tracks = new ArrayList<>();
        tracks = Artists.sortTracksByReleaseDate(playlist.gettrack());
        for(int i = 0 ; i < tracks.size() ; i++){
            System.out.println(tracks.get(i).getName());
        }
    }
    private static void showDuration(){
        Playlist playlist = Playlist.getCurrentPlaylist();
        System.out.println(Playlist.duration(playlist));
    }
    private static void addTracks(Matcher matcher){
        if(true){
            String trackName = matcher.group(1);
            Playlist playlist = Playlist.getCurrentPlaylist();
            User user = User.getLoggedinuser();
            if(user != playlist.getOwner()){
                System.out.println("user doesn't own this playlist");
            }
            else{
                if(!Track.istheresong(trackName)){
                    System.out.println("no such track");
                }
                else{
                    Track track = Track.getTrackbyName(trackName);
                    int g = 0 ;
                    for(int i = 0 ; i<playlist.gettrack().size() ; i++){
                        if(track == playlist.gettrack().get(i)){
                            System.out.println("track is already in the playlist");
                            g =1;
                            break;
                        }
                    }
                    if(g == 0){
                        playlist.addtrack(track);
                        System.out.println("track added to playlist successfully");
                    }
                }
            }
        }
    }
    private static void removeTracks(Matcher matcher){
        if(true){
            String trackname = matcher.group(1);
            Playlist playlist = Playlist.getCurrentPlaylist();
            int j = 0;
            for(int i = 0 ; i < playlist.gettrack().size() ; i++){
                if(playlist.gettrack().get(i).getName().equals(trackname)){
                    Track track = Track.getTrackbyName(trackname);
                    playlist.removetrack(track);
                    j ++ ;
                    System.out.println("track removed from playlist successfully");
                    break;
                }
            }
            if(j == 0){
                System.out.println("no such track in playlist");
            }
        }
    }
}
