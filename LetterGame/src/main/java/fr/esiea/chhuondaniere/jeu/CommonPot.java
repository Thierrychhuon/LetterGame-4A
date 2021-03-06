package fr.esiea.chhuondaniere.jeu;

import java.util.ArrayList;

// Pot commun contenant les lettres tirées

public class CommonPot {
	private ArrayList<Character> letterList;
	
	public CommonPot(){
		this.letterList = new ArrayList<Character>();
	}
	
	void displayPotLetter(){ // Affiche les lettres du pot dans la console
		for(int i = 0; i<this.letterList.size(); i++){
	    	   System.out.print(this.letterList.get(i));
	    }
	}
	
	public ArrayList<Character> getLetterList() {
		return letterList;
	}

	public void addLetters(String e){ // Ajoute une lettre au pot commun
		e = e.toLowerCase();         
		for(char currentLetter: e.toCharArray()){
			this.letterList.add(currentLetter);
		}
	}
	
	public void removeLetter(char e){ // Retire une lettre du pot commun
		this.letterList.remove(Character.valueOf(e));
	}
	
	public void removeLetters(String word){ // Retire plusieurs lettres du pot commun
		for(char currentLetter: word.toCharArray()){
			this.letterList.remove(Character.valueOf(currentLetter));
		}
	}
	
	public void setLetterList(ArrayList<Character> letterList) {
		this.letterList = letterList;
	}

	public boolean isPotContains(String word){ // Vérifie si le mot en argument peut être créer avec les lettres
		/*char tab[]=null;                     // du pot commun
		for(int i=0;i<this.letterList.size();i++){
			tab[i]=this.letterList.get(i);
		}*/
		ArrayList<Character> list = new ArrayList<Character>(this.letterList);
		for (char currentLetter: word.toCharArray()){
			if(!list.contains(currentLetter)){
				return false;
			}
			list.remove(Character.valueOf(currentLetter));
		}
		return true;
	}
	
}
