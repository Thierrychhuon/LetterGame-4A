package fr.esiea.chhuondaniere.dictionary;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Optional;
import java.util.Scanner;
// Classe dictionnaire (qui sera sous forme d'arbre)

public class Dictionnaire {
	private Cell origin = new Cell('0');
	
	public Dictionnaire(){
		try{
			File file = new File( "src/main/resources/dico.txt");
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(new FileReader(file)); 
			while (scanner.hasNextLine()) {
					String next = scanner.nextLine();
					this.addWord(next);
					System.out.println(next);
			}
		}catch(FileNotFoundException e){
			System.out.println("File not found");
		}
	}
	
	public void addWord(String w){
		Cell currentCell= origin ;
		for(char currentLetter : w.toCharArray()){
			currentCell = currentCell.getOrCreate(currentLetter);
		}
		currentCell.getOrCreate(Cell.EOW);
	}
	
	public boolean isWordValid(String w){
		w=w.toLowerCase();
		Cell currentCell = origin;
		for (char currentLetter:w.toCharArray()) {
			Optional<Cell> optional = currentCell.get(currentLetter);
			if(!optional.isPresent()) {
				return false;
			}
			currentCell = optional.get();
		}
		return currentCell.isEndOfWord();
	}
	
}
