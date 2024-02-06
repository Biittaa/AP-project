package com.example.demo43;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class writingHashtags {
    public static void write(){
        try (ObjectOutputStream oOut = new ObjectOutputStream(new FileOutputStream("Hashtag.bin"));) {
            ArrayList<Hashtag> hashtags = Hashtag.getHashtags();
            for (Hashtag h : hashtags){
                oOut.writeObject(h);
                oOut.flush();
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
