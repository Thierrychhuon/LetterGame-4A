package fr.esiea.chhuondaniere.main;

import java.util.ArrayList;
import java.util.Collections;

//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.util.Scanner;

//import fr.esiea.chhuondaniere.dictionary.Dictionnaire;
import fr.esiea.chhuondaniere.jeu.LetterPot;

public class Main {

	public static void main(String[] args) {
		/*int i =0;
		int a=1;
		Dictionnaire dico= new Dictionnaire(); 
		
		while (a==1){
			Scanner scan = new Scanner(System.in);
			System.out.print("Hello new player, what's your name : ");
			String input = scan.nextLine();
			if(dico.isWordValid(input)){
				System.out.println("trouvé");				
			}
		}*/
		LetterPot pot = new LetterPot();
		int i=0;
		while(i<1){
		/*String sample = new String("azertyuiopqsdfghjklmwxcvbn");
	       ArrayList<Character> alphabet = new ArrayList<Character>();
	       for(int i = 0; i<sample.length(); i++){
	           alphabet.add(sample.charAt(i));
	       }
	       for(int i = 0; i<sample.length(); i++){
	    	   System.out.print(alphabet.get(i));
	       }
	       System.out.print("\n");
	       System.out.print(alphabet.get(0));
	       System.out.print("\n");
	       Collections.shuffle(alphabet);
	       for(int i = 0; i<sample.length(); i++){
	    	   System.out.print(alphabet.get(i));
	       }
	       System.out.print("\n");
	       System.out.print(alphabet.get(0));*/
			//System.out.print(pot.getRandomLetter()+"\n");
			System.out.print((char)65); // technique debut
			i=i+1;
		
		}
	}
}
