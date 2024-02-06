package com.example.demo43;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;

public class Controller implements Serializable {
    private static User currentUser;
    public static User getCurrentUser() {
        return currentUser;
    }
    public User getUser(){
        return currentUser;
    }


    public static void setCurrentUser(User currentUser) {
        Controller.currentUser = currentUser;
    }

    public Controller() {

    }

    public void setUser(String id) {
        for (User u : Enter.users) {
            if (u.getID().equals(id)) {
                currentUser = u;
                break;
            }
        }
    }

    public int checkTextTweet(String message){
        if(message.length()>280){
            return -1;
        }
        return 1;
    }
    public ArrayList<Tweet> searchedHashtag(String text){
        ArrayList<Tweet> tweets = TimeLine.getAllTweets();
        ArrayList<Tweet> resultTweets = new ArrayList<>();
        for(Tweet t : tweets){
            if(t.checkHashtag(text)){
                resultTweets.add(t);
            }
        }
        return resultTweets;
    }
    public ArrayList<String> getImages(Tweet t){
        return t.getImages();
    }
    public ArrayList<User> getAllUsers(){
        return Enter.users;
    }
    public void follow(User u){
        currentUser.getAbility().Follow(u,currentUser);
    }
    public void unfollow(User u){
        currentUser.getAbility().unFollow(u,currentUser);
    }
    public int signUp(String id, String first, String last, String eOrP, String pass) {
        ArrayList<String> info = new ArrayList<>();
        info.add(id);
        info.add(first);
        info.add(last);
        String email = null;
        String phone = null;
        if (eOrP.charAt(0) > 47 && eOrP.charAt(0) < 58) {
            phone = eOrP;
        } else {
            email = eOrP;
        }
        info.add(email);
        info.add(phone);
        info.add(pass);
        info.add("");
        info.add("");
        int returning = Enter.addUser(info);
        if (returning == 0)
            this.setUser(id);
        return Enter.addUser(info);
    }
    public ArrayList<Tweet> showTimeLine() {
        return TimeLine.setTimeLine(currentUser);
    }

    public void addNewTweet(String message,ArrayList<String> imageAddress){
        Tweet t = new Tweet();
        t.setTweet(currentUser,message);
        for(String address : imageAddress){
            t.addImage(address);
        }
        TimeLine.addAllTweets(t);
        WritingFile.writingUsers();
        currentUser.getAbility().addTweet(t);
        WritingFile.writingUsers();
        String[] h = message.split(" ");
        for(int i=0;i<h.length;i++){
            if(h[i].charAt(0) == '#'){
                t.addHashtag(h[i]);
                Hashtag.addHashtag(h[i],t);
            }
        }
        writingHashtags.write();
        WritingFile.writingUsers();
    }
    public void addNewReTweet(Tweet t){
        Enter.readUsers();
        ReTweet r = new ReTweet(currentUser,t);
        t.retweet();
        r.setTweetDate(LocalDateTime.now());
        TimeLine.addAllTweets(r);
        HomePage.resetTweets(t);
        HomePage.resetTweets(r);
        WritingFile.writingUsers();
        currentUser.getAbility().addTweet(r);
        writingHashtags.write();
        WritingFile.writingUsers();

    }

    public void addNewQuote(String message,Tweet l){
        Enter.readUsers();
        Tweet t = new Quote();
        t.setTweet(currentUser,message);
        TimeLine.addAllTweets(t);
        currentUser.getAbility().addTweet(t);
        ((Quote)t).setTweet(l);
        String[] h = message.split(" ");
        for(int i=0;i<h.length;i++){
            if(h[i].charAt(0) == '#'){
                t.addHashtag(h[i]);
                Hashtag.addHashtag(h[i],t);
            }
        }
        writingHashtags.write();
        WritingFile.writingUsers();

    }
    public Tweet addComment(Tweet t,String message){
        Enter.readUsers();
        Tweet comment = new Tweet();
        comment.setTweet(currentUser,message);
        t.addReplies(comment);
        HomePage.resetTweets(t);
        TimeLine.addAllTweets(comment);
        currentUser.getAbility().addTweet(comment);
        String[] h = message.split(" ");
        for(int i=0;i<h.length;i++){
            if(h[i].charAt(0) == '#'){
                comment.addHashtag(h[i]);
                Hashtag.addHashtag(h[i],comment);
            }
        }
        writingHashtags.write();
        WritingFile.writingUsers();
        return comment;

    }
    public HashSet<Tweet> getAllTweetsOfCurrentUser(){
         return currentUser.getAbility().getTweets();
    }
    public HashSet<Tweet> getTweets(User u){
        return u.getAbility().getTweets();
    }
    public boolean checkIfReTweeted(Tweet t){
        return currentUser.getAbility().checkIfReTweeted(t);
    }
    public boolean checkIfLiked(Tweet t){
        return t.checkIfLiked(currentUser);
    }
    public HashSet<User> getCurrentUserFollowings(){
          return currentUser.getAbility().getFollowings();
    }
    public HashSet<User> getCurrentUserFollowers(){
        return currentUser.getAbility().getFollowers();
    }
    public ArrayList<Message> getAllMessages(User u){
        return  currentUser.getDirect().getMessage(u);
    }
    public void sendChat(String message , User u){
        currentUser.getDirect().sendChat(message,u);
        u.getDirect().receiveChat(currentUser,message);
        for(User t : Enter.users){
            if(t.getID().equals(u.getID())){
                Enter.users.remove(t);
                Enter.users.add(u);
                break;
            }
        }
        WritingFile.writingUsers();
    }
    public User searchUserByID(String id) {
        ArrayList<User> users = Enter.users;
        for (User u : users) {
            if (u.getID().equals(id))
                return u;
        }
        return null;
    }
    public HashSet<User> getFollowers(User u){
        return u.getAbility().getFollowers();
    }
    public HashSet<User> getFollowings(User u){
        return u.getAbility().getFollowings();
    }


    public void block(User u) {
        currentUser.getAbility().block(u);
    }

    public void unblock(User u) {
        currentUser.getAbility().unblock(u);
    }
    public HashSet<User> getCurrentUserBlackList(){
        return currentUser.getAbility().getBlackList();
    }

}
