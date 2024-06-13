package com.example.demo43;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;

public class UserAbility implements Serializable {
    private HashSet<User> followers;
    private HashSet<User> followings;
    private HashSet<User> blackList;
    private HashSet<User> inBlackList;
    private HashSet<ReTweet> retweets;
    private HashSet<Tweet> allTweets;
    private HashSet<Vote> votes;

    public UserAbility() {
        followers = new HashSet<>();
        followings = new HashSet<>();
        blackList = new HashSet<>();
        inBlackList = new HashSet<>();
        retweets = new HashSet<>();
        allTweets = new HashSet<>();
        votes = new HashSet<>();
    }

    public UserAbility(HashSet<User> followers, HashSet<User> followings, HashSet<User> black, HashSet<User> inBlack, HashSet<Tweet> tweets, HashSet<Vote> votes) {
        this.followers = followers;
        this.followings = followings;
        blackList = black;
        inBlackList = inBlack;
        allTweets = tweets;
        this.votes = votes;
    }

    public HashSet<User> getFollowers() {
        return followers;
    }

    public HashSet<User> getFollowings() {
        return followings;
    }

    public HashSet<User> getInBlackList() {
        return inBlackList;
    }

    public HashSet<Tweet> getTweets() {
        return allTweets;
    }

    public HashSet<ReTweet> getRetweets() {
        return retweets;
    }

    public void addTweet(Tweet t) {
        allTweets.add(t);
        System.out.println(this.getTweets().size() + "*&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
        WritingFile.writingUsers();
    }

    public String toString() {
        String ability = "followers  ";
        for (User u : followers) {
            ability += (u.getID() + "   ");
        }
        ability += "followings  ";
        for (User u : followings) {
            ability += (u.getID() + "   ");
        }
        ability += "blackList  ";
        for (User u : blackList) {
            ability += (u.getID() + "    ");
        }
        ability += "inBlackList";
        for (User u : inBlackList) {
            ability += (u.getID() + "   ");
        }
        ability += "allTweets  ";
        for (Tweet t : allTweets) {
            ability += (t + "   ");
        }
        ability += "votes ";
        for (Tweet t : votes) {
            ability += (t + "  ");
        }
        ability += "done";
        return ability;
    }

    /*********** follow **********/
    public void Follow(User u, User currentUser) {
        followings.add(u);
        u.getAbility().followers.add(currentUser);
        for (int i = 0; i < Enter.users.size(); i++) {
            if (Enter.users.get(i).getID().equals(u.getID())) {
                Enter.users.remove(Enter.users.get(i));
                Enter.users.add(u);
                break;
            }
        }
        WritingFile.writingUsers();
    }

    public void unFollow(User u, User currentUser) {
        followings.remove(u);
        u.getAbility().followers.remove(currentUser);
        for (int i = 0; i < Enter.users.size(); i++) {
            if (Enter.users.get(i).getID().equals(u.getID())) {
                Enter.users.remove(Enter.users.get(i));
                Enter.users.add(u);
                break;
            }
        }
        WritingFile.writingUsers();
    }

    public boolean checkIfFollowing(User u) {
        for (User p : followings) {
            if (u.getID().equals(p.getID())) {
                return true;
            }
        }
        return false;
    }

    public boolean checkIfReTweeted(Tweet t) {
        for (Tweet tweet : allTweets) {
            if (tweet instanceof ReTweet) {
                if (((ReTweet) tweet).getT().getTweetMessage().equals(t.getTweetMessage())) {
                    return true;
                }
            }
        }
        return false;
    }
    public void block(User user) {
        User blocked = user;
        User blocker = Client.controller.getUser();
        for(User p : followers) {
            if(p.getID().equals(user.getID())) {
                this.unFollow(blocked, blocker);
                break;
            }
        }
        for(User p : followings) {
            if(p.getID().equals(user.getID())) {
                this.unFollow(blocked, blocker);
                break;
            }
        }
        blocked.getAbility().getInBlackList().add(blocker);
        blackList.add(user);
        for(User u : Enter.users){
            if(u.getID().equals(user.getID())){
                Enter.users.remove(u);
                Enter.users.add(user);
                break;
            }
        }
        WritingFile.writingUsers();
    }

    public void unblock(User user) {
        User unblocker = null;
        User unblocked = user;
        for (User u : Enter.users) {
            if (u.getID().equals(user.getID())) {
                unblocker = u;
                break;
            }
        }
        blackList.remove(unblocked);
        unblocked.getAbility().getInBlackList().remove(unblocker);
        for(User u : Enter.users){
            if(u.getID().equals(user.getID())){
                Enter.users.remove(u);
                Enter.users.add(user);
                break;
            }
        }
        WritingFile.writingUsers();
    }

    public boolean checkIfBlocked(User u){
        for(User b : blackList){
            if(b.getID().equals(u.getID())){
                return true;
            }
        }
        return false;
    }
    public HashSet<User> getBlackList() {
        return blackList;
    }
    
}
