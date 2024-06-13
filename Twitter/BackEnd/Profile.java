package com.example.demo43;


import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Profile implements Serializable {
    private String avatar;
    private String header;
    private String bio;
    private String location;
    private String website;
    private LocalDate lastChange;

    public Profile() {
        bio = "";
        location = "";
        website = "";
        setLastChange();
    }

    public Profile(String avatar, String header, String bio, String location, String website, LocalDate ld) {
        this.avatar = avatar;
        this.header = header;
        this.bio = bio;
        this.location = location;
        this.website = website;
        this.lastChange = ld;
    }

    public String getHeader() {
        return header;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getBio() {
        return bio;
    }

    public String getLocation() {
        return location;
    }

    public String getWebsite() {
        return website;
    }

    public void setLastChange() {
        lastChange = LocalDate.now();
    }

    public void setAvatar(String avatarFile) {
        avatar = avatarFile;
        WritingFile.writingUsers();
    }

    public void setHeader(String headerFile) {
        header = headerFile;
        WritingFile.writingUsers();
        try {
            if (Files.size(headerPath) / 2048 > 2) {
                return -1;
            } else {
                header = headerFile;
                setLastChange();
                return 0;
            }
        } catch (IOException e) {
            return -2;
        }
    }

    public int setBio(String bio) {
        if (bio.length() > 160) {
            return -1;
        } else {
            this.bio = bio;
            setLastChange();
            WritingFile.writingUsers();
            return 0;
        }
    }

    public void setLocation(String location) {
        this.location = location;
        WritingFile.writingUsers();
        setLastChange();
    }

    public void setWebsite(String website) {
        this.website = website;
        WritingFile.writingUsers();
        setLastChange();
    }

    public String toString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        String profile = lastChange + "\n" + avatar + "\n" + header + "\nbio*" + bio + "*\n" + location + "\n" + website;
        return profile;
    }
}

