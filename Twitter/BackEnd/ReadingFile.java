package com.example.demo43;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class ReadingFile {
    public static ArrayList<User> readingUsers() {
        ArrayList<User> users = new ArrayList<>();
        try ( ObjectInputStream oIn =new ObjectInputStream( new FileInputStream("Use.bin"))) {
            while (true) {
                User u = (User) oIn.readObject();
                if (u == null)
                    break;
                users.add(u);
            }
             oIn.close();
        } catch (ClassNotFoundException e) {
            System.out.println("somethings went wrong in casting in reading files.");
        } catch (IOException e) {
            System.out.println("end of file.");
        }
        return users;
    }
}
