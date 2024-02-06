package com.example.demo43;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Quote extends Tweet implements Serializable {
    String quote;
    Tweet tweet;
    public Quote(){

    }
    public Quote(String message, int likes, int retweets, ArrayList<Tweet> replies, User u, LocalDateTime ld){
        super(message,likes, retweets,  replies,  u, ld);
    }

    public void setTweet(Tweet tweet) {
        this.tweet = tweet;
    }

    public Tweet getTweetOn() {
        return tweet;
    }
}

