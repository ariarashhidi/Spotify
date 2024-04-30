
import java.util.ArrayList;

public class Playlist {
    private String name ;
    private User owner ;
    private ArrayList<Track> tracks = new ArrayList<>();
    private static ArrayList<Playlist> playlists = new ArrayList<>();
    private static Playlist currentPlaylist ;
    public Playlist(String name , User owner){
        this.name = name ;
        this.owner = owner ;
        playlists.add(this);

    }
    public ArrayList<Track> gettrack(){
        return tracks;
    }
    public void addtrack(Track track){
        tracks.add(track);
    }
    public void removetrack(Track track){
        ArrayList<Track> newone = new ArrayList<>();
        for(int i = 0 ; i < this.gettrack().size() ; i++){
            if(this.gettrack().get(i) == track){
                continue;
            }
            newone.add(this.gettrack().get(i));
        }
        this.gettrack().clear();
        for(int i = 0 ; i< newone.size() ; i++){
            this.gettrack().add(newone.get(i));
        }
    }
    public User getOwner(){
        return this.owner;
    }
    public String getName(){
        return this.name;
    }
    public static void addPlaylist(Playlist playlist){

    }
    public static void removePlayList(Playlist playlist){
        ArrayList<Playlist> newone = new ArrayList<>();
        for(int i = 0 ; i < playlists.size() ; i++){
            if(playlists.get(i) == playlist){
                continue;
            }
            newone.add(playlists.get(i));
        }
        playlists.clear();
        for(int i = 0 ; i<newone.size() ; i++){
            playlists.add(newone.get(i));
        }
    }
    public static Playlist getPlaylistbyname(String name){
        for(int i = 0 ; i<playlists.size() ; i++){
            if(playlists.get(i).getName().equals(name)){
                return playlists.get(i);
            }
        }
        return playlists.get(0);
    }
    public static Playlist getCurrentPlaylist(){
        return currentPlaylist;
    }
    public static void setCurrentPlaylist(Playlist playlist){
        currentPlaylist = playlist;
    }
    public static boolean isplaylistexist(String name){
        for(int i = 0 ; i < playlists.size() ; i++){
            if(playlists.get(i).name.equals(name)){
                return false;
            }
        }
        return true;
    }
    public static int duration(Playlist playlist){
        int h = 0 ;
        for(int i = 0 ; i < playlist.tracks.size() ; i++){
            h += playlist.tracks.get(i).getDuration();
        }
        return h;
    }
}
