package com.example.demo43;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Vote extends Tweet {
    private String question;
    private HashMap<String, Integer> choices;
 
    private int voteType;
    private HashMap<String,ArrayList<User>> voter;
    
    public Vote(String question, int voteType) {
        this.question = question;
        this.voteType = voteType;
        choices = new HashMap<>();
    }
    
    public Vote(String question, int voteType, HashMap<String, Integer> choices, HashMap<String, ArrayList<User>> voter) {
    	this.question = question;
    	this.voteType = voteType;
    	this.choices = choices;
    	this.voter = voter;
    }
    
    public void addChoice(String c) {
        choices.put(c, 0);
    }
    
    public void vote(ArrayList<String> choice, User u) {
        for(String c : choice) {
            int l = choices.get(c) + 1;
            choices.replace(c, l);
            voter.get(c).add(u);
        }
    }
    
    public int getChoiceVoteNum(String choice) {
        return choices.get(choice);
    }
    
    public String FindResult() {
        int max = 0;
        String res = null;
        for(String c : choices.keySet()) {
            if(max<choices.get(c)){
                max = choices.get(c);
                res = c;
            }
        }
        return res;
    }
    
    public boolean voteTypeM() {
        if(voteType == 0){
            return false;
        }
        return true;
    }
    
    public void showInProfile() {
    	System.out.println("   =>   \"" + question + "\"");
    	for (String choice : choices.keySet()) {
    		System.out.println("   - " + choice + ": (" + choices.get(choice) + ")");
    	}
    }
    
    public void showInTimeLine(User u) {
    	Scanner s = new Scanner(System.in);
    	System.out.println("   => " + super.getUser().getID() + "   \"" + question + "\"");
    	for (String choice : choices.keySet()) {
    		System.out.println("   - " + choice + ": " + choices.get(choice) + " (" + choices.get(choice) + ")");
    		System.out.println("   (Enter + if you want to vote to this choice, else -): ");
    		if (s.nextLine().equals("+")) {
    			voter.get(choice).add(u);
    			int numOfVotes = choices.get(choice) + 1;
    			choices.put(choice, numOfVotes);
    		}
    	}
    }
    
    public String toString() {
    	String vote = question + " " + voteType + "  " + "choices ";
    	for (String s : choices.keySet()) {
    		vote += (s + ", " + choices.get(s) + " ");
    	}
    	vote += "voters";
    	for (String s : voter.keySet()) {
    		vote += (s);
    		for (User u : voter.get(s)) {
    			vote += (", " + u.getID());
    		}
    		vote += " ";
    	}
    	return vote;
    }
}

