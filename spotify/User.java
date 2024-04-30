import java.util.ArrayList;

public class User {
    private String username;
    private String password;
    private int followers;
    private int following;
    private ArrayList<Track> queue = new ArrayList<>();
    private ArrayList<Track> likedtracks = new ArrayList<>();
    private ArrayList<Playlist> playlist = new ArrayList<>();
    private static User loggedinuser ;
    private static ArrayList<User> users = new ArrayList<>();
    public User (String username , String password){
        this.username = username ;
        this.password = password ;
        users.add(this);
    }
    public int queutedad(){
        return queue.size();
    }
    public void addplaylist(Playlist playlist){
        this.playlist.add(playlist);

    }
    public void removePlaylist(Playlist plaaylist){
        ArrayList<Playlist> newone = new ArrayList<>();
        for(int i = 0 ; i < playlist.size() ; i++){
            if(playlist.get(i) == plaaylist){
                continue;
            }
            newone.add(playlist.get(i));
        }
        playlist.clear();
        for(int i = 0 ; i<newone.size() ; i++){
            playlist.add(newone.get(i));
        }
        Playlist.removePlayList(plaaylist);
    }
    public boolean isthereplaylist(String name){
        for(int i = 0 ; i < playlist.size() ; i++){
            if(playlist.get(i).getName().equals(name)){
                return true;
            }
        }
        return false;
    }
    public void addToQueue(Track track){
        queue.add(track);
    }
    public void removefromQueue(Track track){
        ArrayList<Track> newQueue = new ArrayList<>();
        for(int i = 0 ; i<queue.size() ; i++){
            if(queue.get(i) == track){
                continue;
            }
            newQueue.add(queue.get(i));
        }
        setQueue(newQueue);
    }
    public boolean issongq(String name){
        for(int i = 0 ; i < queue.size() ; i++){
            if(queue.get(i).getName().equals(name)){
                return true;
            }
        }
        return false;
    }
    public void addlikedtrack(Track track){
        likedtracks.add(track);
    }
    public boolean isthisliked(Track track){
        for(int i = 0 ; i< likedtracks.size() ; i++){
            if(likedtracks.get(i) == track){
                return false;
            }
        }
        return true;
    }
    public void removefromlikedtrack(Track track){
        ArrayList<Track> newone = new ArrayList<>();
        for(int i = 0 ; i < likedtracks.size() ; i++){
            if(likedtracks.get(i) == track){
                continue;
            }
            newone.add(likedtracks.get(i));
        }
        likedtracks.clear();
        for(int i = 0 ; i<newone.size() ; i++){
            likedtracks.add(newone.get(i));
        }

    }
    public static int isthereuser(String username , String password){
        for(int i = 0 ; i<users.size() ; i++){
            if(users.get(i).username.equals(username)){
                if(users.get(i).password.equals(password)){
                    setLoggedinuser(users.get(i));
                    return 2;
                }
                return 1;
            }
        }
        return 0;
    }
    public static User getUserbyusername(String username){
        for(int i = 0 ; i < users.size() ; i++){
            if(users.get(i).username.equals(username)){
                return users.get(i);
            }
        }
        return users.get(0);
    }
    public ArrayList<Track> getQueue(){
        return queue;
    }
    public void reverseQueue(int start , int finish){
        ArrayList<Track> newone = new ArrayList<>();
        int h = 0;
        for(int i = start ; i <= (finish+start)/2 ; i++){
            Track track1 = queue.get(i-1);
            Track track2 = queue.get(finish-h-1);
            queue.set(i-1 , track2);
            queue.set(finish-h-1 , track1);
            h++;
        }
    }
    public void setQueue(ArrayList<Track> tracks){
        queue.clear();
        for(int i = 0 ; i < tracks.size() ; i++){
            queue.add(tracks.get(i));
        }
    }
    public String getUsername(){
        return this.username;
    }
    public ArrayList<Playlist> getPlaylist(){
        return playlist;
    }
    public int getFollowers(){
        return this.followers;
    }
    public int getFollowing(){
        return this.following;
    }
    public void setFollowers(int followers){
        this.followers = followers;
    }
    public void setFollowing(int following){
        this.following = following;
    }
    public ArrayList<Track> getLikedtracks(){
        return likedtracks;
    }
    public String getPassword(){
        return this.password;
    }
    public static User getLoggedinuser(){
        return loggedinuser;
    }
    public static void setLoggedinuser(User user){
        loggedinuser = user ;
    }
    public static boolean isthat(String name){
        for(int i = 0 ; i < users.size() ; i++){
            if(users.get(i).username.equals(name)){
                return true;
            }
        }
        return false;
    }

}
