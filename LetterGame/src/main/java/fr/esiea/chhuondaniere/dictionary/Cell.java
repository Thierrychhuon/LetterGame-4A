package fr.esiea.unique.binome.name.dictionary;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Cell {
	
	private char c ;
	public static final Character EOW = '\0';
	private Map<Character,Cell> children = new HashMap<>();
	
	public Cell(char c) {
		this.c=c;
	}
	
	public Cell getOrCreate (char firstLetter){
			return children.computeIfAbsent(firstLetter, l-> new Cell(l));
	}
	
	public boolean isEndOfWorld(){
		return children.get(EOW) != null ;
	}
	
	public Optional<Cell> get(char currentLetter) {
		return Optional.ofNullable(children.get(currentLetter));
	}
}
