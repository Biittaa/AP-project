package com.example.demo43;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class TimeLine {
	public static ArrayList<Tweet> allTweets = new ArrayList<>();
    public static ArrayList<Tweet> setTimeLine(User u) {
        Enter.readUsers();
        ArrayList<Tweet> timeLineTweets = new ArrayList<>();
        HashSet<Tweet> followingTweets = followingTweets(u);
        ArrayList<Tweet> starTweets = favStar(u);
        timeLineTweets.addAll(followingTweets);
        timeLineTweets.addAll(starTweets);
        HashSet<Tweet> resTweets = new HashSet<>();
        for(Tweet t:timeLineTweets){
            resTweets.add(t);
        }
        for(int i=timeLineTweets.size()-1;i>=0;i--){
            timeLineTweets.remove(timeLineTweets.get(i));
        }
        for(Tweet t:resTweets){
            timeLineTweets.add(t);
        }
        timeLineTweets = sortTweets(timeLineTweets);
        return timeLineTweets;
    }

    public static HashSet<Tweet> followingTweets(User u) {
        HashSet<User> following = u.getAbility().getFollowings();
        HashSet<Tweet> tweets = new HashSet<>();
        for (User e : following) {
            for(User j : Enter.users){
                if(j.getID().equals(e.getID())){
                    e = j;
                }
            }
            tweets.addAll(e.getAbility().getTweets());
        }
        for (User e : following){
            tweets.addAll(e.getAbility().getRetweets());
        }
        tweets.addAll(u.getAbility().getTweets());
        return tweets;
    }

    public static ArrayList<Tweet> sortTweets(ArrayList<Tweet> tweet) {
        for (int i = 0; i < tweet.size() - 1; i++) {
            for (int j = i + 1; j < tweet.size(); j++) {
                if (tweet.get(i).getTweetDate().isBefore(tweet.get(j).getTweetDate())) {
                    Collections.swap(tweet, i, j);
                }
            }
        }
        return tweet;
    }

    public static ArrayList<Tweet> favStar(User u) {
        ArrayList<Tweet> StarTweets = new ArrayList<>();
        HashSet<Tweet> tweets = new HashSet<>();
        for(User p : Enter.users){
            tweets.addAll(p.getAbility().getTweets());
        }
        for (Tweet t : tweets) {
            //&& !u.getAbility().checkIfBlocked(t.getUser())
            if (t.getLikes() >= 10 && !u.getAbility().checkIfBlocked(t.getUser())) {
                StarTweets.add(t);
            }
        }
        return StarTweets;
    }

    public static ArrayList<Tweet> getAllTweets() {
        HashSet<Tweet> tweets = new HashSet<>();
        ArrayList<Tweet> tweetS = new ArrayList<>();
        for(User p : Enter.users){
            tweets.addAll(p.getAbility().getTweets());
        }
        for(Tweet t : tweets){
            tweetS.add(t);
        }
        return tweetS;
    }

    public static void addAllTweets(Tweet t){
        allTweets.add(t);
    }
}

