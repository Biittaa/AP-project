package com.example.demo43;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Enter {
    public static ArrayList<User> users = new ArrayList<>();
    
    public static void readUsers() {
        users = ReadingFile.readingUsers();
        if(Client.controller != null && Client.controller.getUser() != null) {
            for (User u : Enter.users) {
                if (u.getID().equals(Client.controller.getUser().getID())) {
                    Controller.setCurrentUser(u);
                }
            }
        }
        WritingFile.writingUsers();
    }
    
    public static boolean checkID(String id) {
        for (User u : users) {
            if (u.getID().equals(id)) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkExistedEmail(String email) {
        for (User u : users) {
            if (u.getEmail() != null && u.getEmail().equals(email)) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkExistedPhoneNumber(String phoneNumber) {
        for (User u : users) {
            if (u.getPhoneNumber() != null && u.getPhoneNumber().equals(phoneNumber)) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkEmailPattern(String Email) {
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(Email);
        if (!matcher.matches()) {
            return false;
        }
        return true;
    }

    public static boolean checkPasswordLength(String password) {
        if (password.length() < 8) {
            return false;
        }
        return true;
    }

    public static boolean checkPasswordAlphabet(String password) {
        int flag = 0, f = 0;
        for (int i = 0; i < password.length(); i++) {
            if (Character.isLowerCase(password.charAt(i))) {
                f = 1;
            }
            if (Character.isLowerCase(password.charAt(i))) {
                flag = 1;
            }
        }
        if (flag == 0 || f == 0) {
            return false;
        }
        return true;
    }
    public static int signIn(String id, String pass) {
        readUsers();
        for (User u : users) {
            if (u.getID().equals(id)) {
                if (u.getPass().equals(pass)) {
                    return 0;
                }
                return -1;
            }
        }
        return -2;
    }
    public static User signInResult(String id, String pass) {
        readUsers();
        for (User u : users) {
            if (u.getID().equals(id)) {
                if (u.getPass().equals(pass)) {
                    return u;
                }
            }
        }
        return null;
    }

    public static int addUser(ArrayList<String> info) {
        if (checkID(info.get(0))) {
            if (checkPasswordLength(info.get(5))) {
                if (checkPasswordAlphabet(info.get(5))) {
                    if (info.get(3) != null) {
                        if (checkExistedEmail(info.get(3))) {
                            if (checkEmailPattern(info.get(3))) {
                                User u = new User(info.get(0), info.get(1), info.get(2), info.get(3), info.get(4), info.get(5), info.get(6), info.get(7), LocalDate.now());
                                users.add(u);
                                WritingFile.writingUsers();
                                return 0;
                            }
                            return -1;
                        }
                        return -2;
                    } else if (info.get(4) != null) {
                        if (checkExistedPhoneNumber(info.get(4))) {
                            User u = new User(info.get(0), info.get(1), info.get(2), info.get(3), info.get(4), info.get(5), info.get(6), info.get(7), LocalDate.now());
                            users.add(u);
                            WritingFile.writingUsers();
                            return 0;
                        }
                        return -3;
                    }
                    return -4;
                }
                return -5;
            }
            return -6;
        }
        return -7;
    }
    public static boolean checkPhoneNumberPattern(String phoneNumber) {
        int numCounter = 0;
        for (int i = 0; i < phoneNumber.length(); i++) {
            if (phoneNumber.charAt(i) > 46 && phoneNumber.charAt(i) < 58)
                numCounter++;
        }
        if (numCounter == phoneNumber.length())
            return true;
        else
            return false;
    }
    /*************************************************/
    public static void addUser(User u){
        users.add(u);
    }


}

