package fr.esiea.chhuondaniere.jeu;

import java.util.ArrayList;
import java.util.Collections;

// Classe représentant le pot contenant les lettres de l'alphabet 

public class LetterPot {
	private ArrayList<Character> letterList;

	public LetterPot(){
		String sample = new String("azertyuiopqsdfghjklmwxcvbn");
	       this.letterList = new ArrayList<Character>();
	       for(int i = 0; i<sample.length(); i++){
	           this.letterList.add(sample.charAt(i));
	       }
	}
	
	public char getRandomLetter(){
		Collections.shuffle(this.letterList); //Random: Nous mélangeons les lettres de l'alphabet
		return this.letterList.get(1);        //Et nous prenons la première lettre de la liste mélangée
	}

	public ArrayList<Character> getLetterPot() {
		return letterList;
	}
	
}
