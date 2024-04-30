import java.util.ArrayList;
import java.util.Date;

public class Track {
    private String name ;
    private Artists artists ;
    private int duration ;
    private Date realeaDate ;
    private boolean type ;
    private static ArrayList<Track> tracks = new ArrayList<>();
    public Track (Date realeaDate , Artists artists , boolean type , int duration , String name){
        this.artists = artists ;
        this.duration = duration ;
        this.name = name ;
        this.type = type ;
        this.realeaDate = realeaDate ;
        tracks.add(this);
    }
    public Date getRealeaDate(){
        return this.realeaDate ;
    }
    public Artists getArtists(){
        return this.artists;
    }
    public boolean gettype(){
        return this.type;
    }
    public int getDuration(){
        return this.duration;
    }
    public String getName(){
        return this.name;
    }
    public static void addTrack(Track track){

    }
//    public static ArrayList<Track> getTracks(){
//
//    }
    public static Track getTrackbyName(String name){
        for(int i = 0 ; i<tracks.size() ; i++){
            if(tracks.get(i).name.equals(name)){
                return tracks.get(i);
            }
        }
        return tracks.get(0);
    }
    public static boolean istheresong(String name){
        for(int i = 0 ; i < tracks.size() ; i++){
            if(tracks.get(i).name.equals(name)){
                return true;
            }
        }
        return false;
    }
}

