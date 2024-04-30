
import java.util.ArrayList;
import java.util.Date;

public class Artists {
    private String Username ;
    private String Password ;
    private String Nickname ;
    private int followers ;
    private ArrayList<Track> tracks = new ArrayList<>();

    private static Artists loggedinasartist ;
    private static ArrayList<Artists> artists = new ArrayList<>();
    public Artists (String username , String password , String nickname){
        this.Username = username ;
        this.Password = password ;
        this.Nickname = nickname ;
        artists.add(this);
    }
    public static int isthereartist(String username , String password){
        for(int i = 0 ; i<artists.size() ; i++){
            if(artists.get(i).Username.equals(username)){
                if(artists.get(i).Password.equals(password)){
                    setLoggedinasartist(artists.get(i));
                    return 2;
                }
                return 1;
            }
        }
        return 0;
    }
    public static Artists getArtistbyusername(String username){
        for(int i = 0 ; i < artists.size() ; i++){
            if(artists.get(i).Username.equals(username)){
                return artists.get(i);
            }
        }
        return artists.get(0);
    }
    public String getUsername(){
        return this.Username;
    }
    public String getPassword(){
        return this.Password;
    }
    public String getNickname(){
        return this.Nickname;
    }
    public int getFollowers(){
        return this.followers;
    }
    public int getRank(){
        int rank = 1 ;
        for(int i = 0 ; i < artists.size() ; i++){
            if(artists.get(i) != this){
                if(artists.get(i).followers > this.followers){
                    rank += 1;
                }
            }
        }
        return rank;
    }
    public void setFollowers(int followers){
        this.followers = followers;
    }
    public ArrayList<Track> getTracks(){
        return this.tracks;
    }
    public void addTOTracks(Track track){
        this.tracks.add(track);
    }

    public static ArrayList<Track> sortTracksByReleaseDate(ArrayList<Track> tracks) {
        int n = tracks.size();
        for (int i = 1; i < n; i++) {
            int j = i;
            while (j > 0) {
                Date d1 = tracks.get(j - 1).getRealeaDate(), d2 = tracks.get(j).getRealeaDate();
                if (d1.compareTo(d2) < 0 || (d1.compareTo(d2) == 0 && tracks.get(j - 1).getName().compareTo(tracks.get(j).getName()) > 0)) {
                    Track temp = tracks.get(j);
                    tracks.set(j, tracks.get(j - 1));
                    tracks.set(j - 1, temp);
                    j--;
                } else break;
            }
        }
        return tracks;
    }

    public static Artists getLoggedinasartist(){
        return loggedinasartist ;
    }
    public static void setLoggedinasartist(Artists artists){
        loggedinasartist = artists ;
    }
    public static boolean isthere(String name){
        for(int i = 0 ; i < artists.size() ; i++){
            if(artists.get(i).Username.equals(name)){
                return true;
            }
        }
        return false;
    }
}
