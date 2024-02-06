package com.example.demo43;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;


public class Hashtag implements Serializable {
    private static ArrayList<Hashtag> hashtags = new ArrayList<>();

    private HashMap<LocalDateTime,Integer> dateCount = new HashMap<>();
    private int count=0;

    private String name;
    
    public Hashtag(String name){
        this.name = name;
    }


    public HashMap<LocalDateTime, Integer> getDateCount() {
        return dateCount;
    }

    public void addCount(){
        count++;
    }
    public static ArrayList<Hashtag> chart(){
            ArrayList<Hashtag> tags = new ArrayList<>();
            for(Hashtag h : hashtags){
                tags.add(h);
            }
            ArrayList<Hashtag> h = new ArrayList<>();
            for(int i=0;i<5;i++){
                if(tags.size() == 0){
                    break;
                }
                int f = findTrend(tags);
                h.add(tags.get(f));
                tags.remove(tags.get(f));
            }
            return h;
    }
    public static int findTrend(ArrayList<Hashtag> tags){
        int f=0;
        for(int i=0;i<tags.size();i++){
            if(tags.get(i).count>f){
                f = i;
            }
        }
        return f;
    }
    public  int percent(LocalDateTime d){
        for(LocalDateTime h : dateCount.keySet()){
            if(h.toLocalDate().equals(d.toLocalDate())){
                return dateCount.get(h);
            }
        }
        return 0;
    }



    public String getName() {
        return name;
    }



    public static void addHashtag(String hashtag, Tweet t){
        for(Hashtag hashTag : hashtags){
            if(hashTag.getName().equals(hashtag)){
                HashMap<LocalDateTime,Integer> o = hashTag.getDateCount();
                for(LocalDateTime d : o.keySet()){
                    if(d.toLocalDate().equals(LocalDate.now())){
                        hashTag.addCount();
                        int c = o.get(d) + 1;
                        o.replace(d,c);
                        return;
                    }
                }
                o.put(LocalDateTime.now(),1);
                return;
            }
        }
        Hashtag h = new Hashtag(hashtag);
        h.getDateCount().put(LocalDateTime.now(),1);
        h.addCount();
        hashtags.add(h);
        writingHashtags.write();
    }



    public static ArrayList<Hashtag> getHashtags() {
        return hashtags;
    }
}

