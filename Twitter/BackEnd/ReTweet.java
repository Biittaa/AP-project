package com.example.demo43;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReTweet extends Tweet implements Serializable {
    User retweetedPerson;
    Tweet t;
    LocalDateTime tweetDate;

    public ReTweet(User retweetedPerson,Tweet t ) {
        this.retweetedPerson = retweetedPerson;
        this.setTweetMessage(t.getTweetMessage());
        this.setLikes(t.getLikes());
        this.setTweetDate(t.getTweetDate());
        this.setRetweets(t.getRetweets());
        this.setQuotes(t.getQuotes());
        this.setReplies(t.getReplies());
        this.setHashtags(t.getHashtags());
        this.setImages(t.getImages());
        this.setUser(t.getUser());
        this.t=t;
    }

    public User getRetweetedPerson() {
        return retweetedPerson;
    }

    public Tweet getT() {
        return t;
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
        else{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return tweetDate.format(formatter);
        }
    }

    @Override
    public void setTweetDate(LocalDateTime tweetDate) {
        this.tweetDate = tweetDate;
    }

    @Override
    public LocalDateTime getTweetDate() {
        return tweetDate;
    }
}

