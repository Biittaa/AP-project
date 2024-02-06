package com.example.demo43;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class WritingFile {
    public static void writingUsers() {

        try ( ObjectOutputStream oOut = new ObjectOutputStream(new FileOutputStream("Use.bin"));) {
             if(Client.controller != null && Client.controller.getUser()!=null) {
                for (User y : Enter.users) {
                    for (User i : y.getAbility().getFollowers()) {
                        if (Client.controller.getUser().getID().equals(i.getID())) {
                            y.getAbility().getFollowers().remove(i);
                            y.getAbility().getFollowers().add(Client.controller.getUser());
                            break;
                        }
                    }
                }
            }
            if(Client.controller != null && Client.controller.getUser()!=null) {
                for (User y : Enter.users) {
                    for (User i : y.getAbility().getFollowings()) {
                        if (Client.controller.getUser().getID().equals(i.getID())) {
                            y.getAbility().getFollowings().remove(i);
                            y.getAbility().getFollowings().add(Client.controller.getUser());
                            break;
                        }
                    }
                }
            }
            for (User u : Enter.users) {
                if(Client.controller != null&&Client.controller.getUser()!=null) {
                    if (u.getID().equals(Client.controller.getUser().getID())) {
                        oOut.writeObject(Client.controller.getUser());
                        oOut.flush();
                        continue;
                    }
                }
                oOut.writeObject(u);
                oOut.flush();
            }
            oOut.close();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("something went wrong in writing files.");
        }
    }
}
