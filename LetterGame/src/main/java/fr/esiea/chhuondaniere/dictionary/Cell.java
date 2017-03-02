package fr.esiea.chhuondaniere.dictionary;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
// Classe représentant les feuilles de l'arbre dictionnaire
public class Cell {
	
	private char letter ;
	public static final Character EOW = '\0';
	private Map<Character,Cell> children = new HashMap<>();
	
	public Cell(char c) {
		this.setLetter(c);
	}
	
	public Cell getOrCreate (char firstLetter){
			return children.computeIfAbsent(firstLetter, l-> new Cell(l));
	}
	
	public boolean isEndOfWord(){
		return children.get(EOW) != null ;
	}
	
	public Optional<Cell> get(char currentLetter) {
		return Optional.ofNullable(children.get(currentLetter));
	}

	public char getLetter() {
		return letter;
	}

	public void setLetter(char letter) {
		this.letter = letter;
	}
}
