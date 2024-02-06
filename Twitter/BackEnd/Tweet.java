package com.example.demo43;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

public class Tweet implements Serializable {
    private String tweetMessage;
    private LocalDateTime tweetDate;
    private int likes;
    private ArrayList<User> likers = new ArrayList<>();
    private int retweets;
    private HashMap<User, String> quotes;
    private ArrayList<Tweet> replies;
    private ArrayList<Hashtag> hashtags;
    private ArrayList<String> hashtag = new ArrayList<>();

    private ArrayList<String> images;
    private User user;

    public Tweet() {
    }

    public Tweet(String message, int likes, int retweets, ArrayList<Tweet> replies, User u, LocalDateTime ld) {
        tweetMessage = message;
        tweetDate = ld;
        this.likes = likes;
        this.retweets = retweets;
        this.replies = replies;
        hashtags = new ArrayList<>();
        user = u;
        images = new ArrayList<>();
    }

    public boolean checkHashtag(String text) {
        for (String h : hashtag) {
            if (h.contains(text)) {
                return true;
            }
        }
        return false;
    }
    public void addHashtag(String h){
        hashtag.add(h);
    }
    public void addImage(String imageAddress) {
        images.add(imageAddress);
    }

    public void setTweet(User u, String message) {
        tweetMessage = message;
        user = u;
        tweetDate = LocalDateTime.now();
        likes = 0;
        retweets = 0;
        quotes = new HashMap<>();
        replies = new ArrayList<>();
        images = new ArrayList<>();
    }

    public void like(User u) {
        likes++;
        likers.add(u);
    }

    public void dislike(User u) {
        likes--;
        likers.remove(u);
    }
    public boolean checkIfLiked(User u){
        for(User p : likers){
            if(p.getID().equals(u.getID())){
                return true;
            }
        }
        return false;
    }

    public void retweet() {
        retweets++;
    }

    public void addQuote(User quoter, String quote) {
        quotes.put(quoter, quote);
    }

    public void addReplies(Tweet t) {
        replies.add(t);
    }

    public LocalDateTime getTweetDate() {
        return tweetDate;
    }

    public int getLikes() {
        return likes;
    }




    public User getUser() {
        return user;
    }
    public String PassedTime(){
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(tweetDate,now);
        long minutes = duration.toMinutes();
        if(duration.toMinutes() < 60){
            return String.format("%dm",minutes);
        }
        else if(minutes>60&&minutes<1440){
            return (minutes/60) +"h ";
        }
        else if(tweetDate.isBefore(now.plusYears(1))){
            DateTimeFormatter f = DateTimeFormatter.ofPattern("dd MMMM");
            return f.format(tweetDate);
        }
        else{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return tweetDate.format(formatter);
        }
    }

    public String toString() {
        String tweet = tweetDate + "  " + tweetMessage + "  " + likes + "  " + retweets + "  ";
        tweet += "replies";
       
        tweet += "quotes";
        for (User u : quotes.keySet()) {
            tweet += (u.getID() + ", " + quotes.get(u) + "");
        }
        return tweet;
    }

    public String getTweetMessage() {
        return tweetMessage;
    }

    public int getReplyCount() {
        return replies.size();
    }

    public int getRetweets() {
        return retweets;
    }

    public ArrayList<Tweet> getReplies() {
        return replies;
    }

    public ArrayList<String> getImages() {
        return images;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setTweetMessage(String tweetMessage) {
        this.tweetMessage = tweetMessage;
    }

    public void setTweetDate(LocalDateTime tweetDate) {
        this.tweetDate = tweetDate;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public void setRetweets(int retweets) {
        this.retweets = retweets;
    }

    public void setQuotes(HashMap<User, String> quotes) {
        this.quotes = quotes;
    }

    public void setReplies(ArrayList<Tweet> replies) {
        this.replies = replies;
    }

    public void setHashtags(ArrayList<Hashtag> hashtags) {
        this.hashtags = hashtags;
    }

    public void setImages(ArrayList<String> images) {
        this.images = images;
    }

    public HashMap<User, String> getQuotes() {
        return quotes;
    }

    public ArrayList<Hashtag> getHashtags() {
        return hashtags;
    }
}

