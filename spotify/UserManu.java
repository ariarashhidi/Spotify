
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserManu {
    public static void run(Scanner scanner){
        while (true){
            String line = scanner.nextLine();
            if(line.equals("back")){
                break;
            }
            else if(line.equals("show menu name")){
                System.out.println("user menu");
            }
            else if(line.equals("show playlists")){
                UserManu.showPlayList();
            }
            else if(line.equals("show liked tracks")){
                UserManu.showLikedTracks();
            }
            else if(line.equals("show queue")){
                UserManu.showQueue();
            }
            else if(getCommandmatcher(line , "^add -t ([A-Za-z0-9\\s]+) to queue$").find()){
                Matcher matcher = getCommandmatcher(line , "add -t (.+) to queue");
                if(matcher.find()){
                    addtracktoQueue(matcher);
                }
            }
            else if(getCommandmatcher(line , "^like track -t ([A-Za-z0-9\\s]+)$").find()){
                Matcher matcher = getCommandmatcher(line , "like track -t (.+)");
                if(matcher.find()){
                    addtrackToLikedTracked(matcher);
                }
            }
            else if(getCommandmatcher(line , "^remove -t ([A-Za-z0-9\\s]+) from queue$").find()){
                Matcher matcher = getCommandmatcher(line , "remove -t (.+) from queue");
                if(matcher.find()){
                    removeFromQueue(matcher);
                }
            }
            else if(getCommandmatcher(line , "^remove -t ([A-Za-z0-9\\s]+) from liked tracks$").find()){
                Matcher matcher = getCommandmatcher(line , "remove -t (.+) from liked tracks");
                if(matcher.find()){
                    removefromLiked(matcher);
                }
            }
            else if(getCommandmatcher(line , "^reverse order of queue from (-?\\d+) to (-?\\d+)$").find()){
                Matcher matcher = getCommandmatcher(line , "reverse order of queue from (-?\\d+) to (-?\\d+)");
                if(matcher.find()){
                    reverseorderOfQoue(scanner , matcher);
                }
            }
            else if(getCommandmatcher(line , "^create -p ([A-Za-z0-9\\s]+)$").find()){
                Matcher matcher = getCommandmatcher(line , "create -p (.+)");
                if(matcher.find()){
                    createPlaylist(matcher);
                }
            }
            else if(getCommandmatcher(line , "^delete -p ([A-Za-z0-9\\s]+)$").find()){
                Matcher matcher = getCommandmatcher(line , "delete -p (.+)");
                if(matcher.find()){
                    deletplaylist(matcher);
                }
            }
            else if(getCommandmatcher(line , "^follow user -u ([A-Za-z0-9]+)$").find()){
                Matcher matcher = getCommandmatcher(line , "follow user -u (.+)");
                if(matcher.find()){
                    followuser(matcher);
                }
            }
            else if(getCommandmatcher(line , "^follow artist -u ([A-Za-z0-9]+)$").find()){
                Matcher matcher = getCommandmatcher(line , "follow artist -u (.+)");
                if(matcher.find()){
                    folloeArtist(matcher);
                }
            }
            else if(getCommandmatcher(line , "^unfollow user -u ([A-Za-z0-9]+)$").find()){
                Matcher matcher = getCommandmatcher(line , "unfollow user -u (.+)");
                if(matcher.find()){
                    unfollowuser(matcher);
                }
            }
            else if(getCommandmatcher(line , "^unfollow artist -u ([A-Za-z0-9]+)$").find()){
                Matcher matcher = getCommandmatcher(line , "unfollow artist -u (.+)");
                if(matcher.find()){
                    unfollowArtist(matcher);
                }
            }
            else if(getCommandmatcher(line , "^go to playlist menu -p ([A-Za-z0-9\\s]+)$").find()){
                Matcher matcher = getCommandmatcher(line , "go to playlist menu -p (.+)");
                if(matcher.find()){
                    gotoPlaylistmenu(scanner , matcher);
                }
            }
            else if(getCommandmatcher(line , "^show track info -t ([A-Za-z0-9\\s]+)$").find()){
                Matcher matcher = getCommandmatcher(line , "show track info -t (.+)");
                if(matcher.find()){
                    showTrackinfo(matcher);
                }
            }
            else if(getCommandmatcher(line , "^show playlist info -p ([A-Za-z0-9\\s]+)$").find()){
                Matcher matcher = getCommandmatcher(line , "show playlist info -p (.+)");
                if(matcher.find()){
                    playlistInfo(matcher);
                }
            }
            else if(getCommandmatcher(line , "^show artist info -u ([A-Za-z0-9]+)$").find()){
                Matcher matcher = getCommandmatcher(line , "show artist info -u (.+)");
                if(matcher.find()){
                    artistInfo(matcher);
                }
            }
            else if(getCommandmatcher(line , "^show user info -u ([A-Za-z0-9]+)$").find()){
                Matcher matcher = getCommandmatcher(line , "show user info -u (.+)");
                if(matcher.find()){
                    showUserinfo(matcher);
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
    private static void addtracktoQueue(Matcher matcher){
        if(true){
            String songname = matcher.group(1);
            if(!Track.istheresong(songname)){
                System.out.println("no such track");
            }
            else{
                User user = User.getLoggedinuser();
                Track track = Track.getTrackbyName(songname);
                user.addToQueue(track);
                System.out.println("track added to queue successfully");
            }
        }
    }
    private static void addtrackToLikedTracked(Matcher matcher){
        if(true){
            String songname = matcher.group(1);
            if(!Track.istheresong(songname)){
                System.out.println("no such track");
            }
            else{
                User user = User.getLoggedinuser();
                Track track = Track.getTrackbyName(songname);
                if(!user.isthisliked(track)){
                    System.out.println("track is already liked");
                }
                else {
                    user.addlikedtrack(track);
                    System.out.println("liked track successfully");
                }
            }
        }
    }
    private static void removeFromQueue(Matcher matcher){
        if(true){
            String songname = matcher.group(1);
            User user = User.getLoggedinuser();
            if(!user.issongq(songname)){
                System.out.println("no such track in queue");
            }
            else {
                Track track = Track.getTrackbyName(songname);
                user.removefromQueue(track);
                System.out.println("track removed from queue successfully");
            }
        }
    }
    private static void removefromLiked(Matcher matcher){
        if(true){
            String songname = matcher.group(1);
            Track track = Track.getTrackbyName(songname);
            User user = User.getLoggedinuser();
            if(user.isthisliked(track)){
                System.out.println("no such track in liked tracks");
            }
            else{
                user.removefromlikedtrack(track);
                System.out.println("track removed from liked tracks successfully");
            }
        }
    }
    private static void showPlayList(){
        ArrayList<Playlist> playlists = User.getLoggedinuser().getPlaylist();
        ArrayList<String> name = new ArrayList<>();
        for(int i = 0 ; i < playlists.size() ; i++){
            name.add(playlists.get(i).getName());
        }
        Collections.sort(name);
        for(int i = 0 ; i<name.size() ; i++){
            System.out.println(name.get(i));
        }
    }
    private static void showLikedTracks(){
        ArrayList<Track> likedtrackes = User.getLoggedinuser().getLikedtracks();
        ArrayList<String> name = new ArrayList<>();
        for(int i = 0 ; i < likedtrackes.size() ; i++){
            name.add(likedtrackes.get(i).getName());
        }
        Collections.sort(name);
        for(int i = 0 ; i< name.size() ; i++){
            System.out.println(name.get(i));
        }

    }
    private static void showUserinfo(Matcher matcher){
        if(true){
            String username = matcher.group(1);
            if(!User.isthat(username)){
                System.out.println("no such user");
            }
            else{
                User user = User.getUserbyusername(username);
                System.out.println(user.getUsername() + " " + user.getFollowers() + " " + user.getFollowing() + " " + user.getPlaylist().size());
            }
        }
    }
    private static void showTrackinfo(Matcher matcher){
        if(true){
            String trackName = matcher.group(1);
            if(!Track.istheresong(trackName)){
                System.out.println("no such track");
            }
            else{
                Track track = Track.getTrackbyName(trackName);
                String type = "";
                if(track.gettype()){
                    type = "song";
                }
                else{
                    type = "podcast";
                }
                Date real = track.getRealeaDate();
                SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
                System.out.println(track.getName() +" "+ type +" "+ track.getDuration() + " " + format.format(real) +" " + track.getArtists().getNickname());
            }
        }

    }
    private static void showQueue(){
        ArrayList<Track> showq = User.getLoggedinuser().getQueue();
        for(int i = 0 ; i <showq.size() ; i++){
            System.out.println(showq.get(i).getName());
        }
    }
    private static void artistInfo(Matcher matcher){
        if(true){
            String Artistname = matcher.group(1);
            if(!Artists.isthere(Artistname)){
                System.out.println("no such artist ");
            }
            else{
                Artists artist = Artists.getArtistbyusername(Artistname);
                System.out.println(artist.getUsername() + " " + artist.getNickname() + " " + artist.getFollowers() + " " + artist.getRank());
            }
        }
    }
    private static void playlistInfo(Matcher matcher){
        if(true){
            String playListName = matcher.group(1);
            if(Playlist.isplaylistexist(playListName)){
                System.out.println("no such playlist");
            }
            else{
                Playlist playlist = Playlist.getPlaylistbyname(playListName);
                int playlistduration = Playlist.duration(playlist);
                System.out.println(playlist.getName() + " " + playlist.getOwner().getUsername() + " " + playlistduration);
            }
        }
    }
    private static void followuser(Matcher matcher){
        if(true){
            User user = User.getLoggedinuser();
            String Username = matcher.group(1);
            if(!User.isthat(Username)){
                System.out.println("no such user");
            }
            else{
                if(User.getUserbyusername(Username) == user){
                    System.out.println("you can't follow yourself");
                }
                else{
                    user.setFollowing(user.getFollowing()+1);
                    User user1 = User.getUserbyusername(Username);
                    user1.setFollowers(user1.getFollowers()+1);
                    System.out.println("added user to followings");
                }
            }
        }
    }
    private static void unfollowuser(Matcher matcher){
        if(true){
            User user = User.getLoggedinuser();
            String nameUser = matcher.group(1);
            if(!User.isthat(nameUser)){
                System.out.println("no such user");
            }
            else{
                if(User.getUserbyusername(nameUser) == user){
                    System.out.println("you can't unfollow yourself");
                }
                else{
                    user.setFollowing(user.getFollowing()-1);
                    User user1 = User.getUserbyusername(nameUser);
                    user1.setFollowers(user1.getFollowers()-1);
                    System.out.println("user unfollowed successfully");
                }
            }
        }

    }
    private static void folloeArtist(Matcher matcher){
        if(true){
            String artistname = matcher.group(1);
            User user = User.getLoggedinuser();
            if(!Artists.isthere(artistname)){
                System.out.println("no such artist");
            }
            else{
                user.setFollowing(user.getFollowing()+1);
                Artists artists = Artists.getArtistbyusername(artistname);
                artists.setFollowers(artists.getFollowers()+1);
                System.out.println("added artist to followings");
            }

        }
    }
    private static void unfollowArtist(Matcher matcher){
        if(true){
            User user = User.getLoggedinuser();
            String Artistname = matcher.group(1);
            if(!Artists.isthere(Artistname)){
                System.out.println("no such artist");
            }
            else{
                user.setFollowing(user.getFollowing()-1);
                Artists artists = Artists.getArtistbyusername(Artistname);
                artists.setFollowers(artists.getFollowers()-1);
                System.out.println("artist unfollowed successfully");
            }
        }

    }
    private static void createPlaylist(Matcher matcher){
        if(true){
            User user = User.getLoggedinuser();
            String namePlaylist = matcher.group(1);
            if(!Playlist.isplaylistexist(namePlaylist)){
                System.out.println("playlist name already exists");
            }
            else{
                Playlist playlist = new Playlist(namePlaylist , user);
                user.addplaylist(playlist);
                System.out.println("playlist created successfully");
            }
        }
    }
    private static void deletplaylist(Matcher matcher){
        if(true){
            String nameofPlaylist = matcher.group(1);
            User user = User.getLoggedinuser();
            if(!user.isthereplaylist(nameofPlaylist)){
                System.out.println("user doesn't own such playlist");
            }
            else{
                Playlist playlist = Playlist.getPlaylistbyname(nameofPlaylist);
                user.removePlaylist(playlist);
                System.out.println("playlist deleted successfully");
            }
        }
    }
    private static void gotoPlaylistmenu(Scanner scanner , Matcher matcher){
        if(true){
            String playListname = matcher.group(1);
            if(Playlist.isplaylistexist(playListname)){
                System.out.println("no such playlist");
            }
            else {
                Playlist playlist = Playlist.getPlaylistbyname(playListname);
                Playlist.setCurrentPlaylist(playlist);
                //ssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss
                System.out.println("entered playlist menu successfully");
                PlaylistMenu.run(scanner);
            }
        }
    }
    private static void reverseorderOfQoue(Scanner scanner , Matcher matcher){
        if(true){
            int started = Integer.parseInt(matcher.group(1));
            int finished = Integer.parseInt(matcher.group(2));
            User user = User.getLoggedinuser();
            int te = user.queutedad();
            if(te == 0 ){
                System.out.println("queue is empty");
            }
            else if(started<1 || finished>te || started>=finished){
                System.out.println("invalid bounds");
            }
            else{
                user.reverseQueue(started , finished);
                System.out.println("order of queue reversed successfully");
            }
        }
    }

}
