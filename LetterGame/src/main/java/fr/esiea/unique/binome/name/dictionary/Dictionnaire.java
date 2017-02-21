package fr.esiea.unique.binome.name.dictionary;

import java.util.Optional;

public class Dictionnaire {
	private Cell origin = new Cell('0');
	
	public void addWord(String w){
		Cell currentCell= origin ;
		for(char currentLetter : w.toCharArray()){
			currentCell = currentCell.getOrCreate(currentLetter);
		}
		currentCell.getOrCreate(Cell.EOW);
	}
	public boolean isWordValid(String w){
		Cell currentCell = origin;
		for (char currentLetter:w.toCharArray()) {
			Optional<Cell> optional = currentCell.get(currentLetter);
			if(!optional.isPresent()) {
				return false;
			}
			currentCell = optional.get();
		}
		return currentCell.isEndOfWorld();
	}
}
