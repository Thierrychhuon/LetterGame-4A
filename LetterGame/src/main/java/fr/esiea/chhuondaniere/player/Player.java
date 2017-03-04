package fr.esiea.chhuondaniere.player;

import java.util.ArrayList;

public class Player {

	private String name;
	private boolean isIA;
	private ArrayList<String> wordlist;
	
	
	public Player(String name){
		this.name = name;
		this.wordlist = new ArrayList<String>();
		this.isIA = false;
	}
	
	
	// Methods, Getters and Setters
	public void  addWordToPlayer(String word){
		this.wordlist.add(word);
	}
	
	public void removeWordToPlayer(String word){
		this.wordlist.remove(word);
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public  ArrayList<String> getwordlist() {
		return wordlist;
	}
	
	public boolean isIA() {
		return isIA;
	}

	public void setIA(boolean isIA) {
		this.isIA = isIA;
	}
}
