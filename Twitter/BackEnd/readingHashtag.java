package com.example.demo43;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class readingHashtag {
    public static void read() {
        try (ObjectInputStream oIn = new ObjectInputStream(new FileInputStream("Hashtag.bin"))) {
            while (true) {
                Hashtag u = (Hashtag) oIn.readObject();
                if (u == null)
                    break;

                Hashtag.getHashtags().add(u);
            }
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }

    }

}
