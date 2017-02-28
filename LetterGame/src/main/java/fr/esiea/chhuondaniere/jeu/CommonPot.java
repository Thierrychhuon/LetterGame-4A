package fr.esiea.chhuondaniere.jeu;

import java.util.ArrayList;

public class CommonPot {
	private ArrayList<Character> letterList;
	
	public CommonPot(){
		this.letterList = new ArrayList<Character>();
	}
	
	void displayPotLetter(){
		for(int i = 0; i<this.letterList.size(); i++){
	    	   System.out.print(this.letterList.get(i));
	    }
	}
	
	public void addLetter(char e){
		this.letterList.add(e);
	}
	
	public void removeLetter(char e){
		this.letterList.remove(e);
	}
	
}
