package fr.esiea.chhuondaniere.jeu;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

import fr.esiea.chhuondaniere.dictionary.Dictionnaire;
import fr.esiea.chhuondaniere.player.Player;

public class Jeu {
	private ArrayList<Player> playerList;
	private ArrayList<String> foundWordList;;
	private CommonPot commonPot;
	private LetterPot letterPot;
	private Dictionnaire dico;
	boolean end = false ;
	
	public Jeu(){
		this.playerList = new ArrayList<Player>();
		this.foundWordList = new ArrayList<String>();
		this.commonPot= new CommonPot();
		this.letterPot= new LetterPot();
		this.dico = new Dictionnaire();
		@SuppressWarnings("unused")
		boolean end = false ;
	}
	
	public void beginning(){

		//Player 1
		System.out.println("Bienvenue sur Letter game");
		System.out.println("***********************************************************************");
		Scanner scan = new Scanner(System.in);
		System.out.println("Salut le nouveau, comment tu t'appelles ? ");
		String name1 =scan.next();	
		System.out.println("Bienvenue "+name1+" !");
		Player joueur1 = new Player(name1);
		this.playerList.add(joueur1);
		
		//Player2
		String ia="";
		System.out.println("Ah tu es venu avec une personne ! Comment s'appelle-t-elle ?");
		String name2 =scan.next();	
		System.out.println("Bienvenue "+name2+" !");
		while(yesOrNo(ia)==false){
			System.out.print("Attends ! Est-ce que "+name2+" serait un bot ? Réponds rapidement par yes ou no ");
			ia=scan.next();
		}
		//IA
		Player joueur2 = new Player(name2);
		this.playerList.add(joueur2);
		
		if(ia.equalsIgnoreCase("yes")){
		joueur2.setIA(true);
		}
		
		
		System.out.println("Fini !");
	}
	
	public void whoFirst(Player joueur1, Player joueur2){
		char joueur1Letter=letterPot.getRandomLetter();
		char joueur2Letter=letterPot.getRandomLetter();
		System.out.println("Tirons des lettres au hasard pour déterminer l'ordre de jeu:");
		System.out.println(joueur1.getName()+" a tiré un "+joueur1Letter);
		System.out.println(joueur2.getName()+" a tiré un "+joueur2Letter);
		this.commonPot.addLetters(Character.toString(joueur1Letter));
		this.commonPot.addLetters(Character.toString(joueur2Letter));
		if(joueur1Letter<joueur2Letter){
			this.playerList.add(joueur1);
			this.playerList.add(joueur2);
			System.out.println("C'est donc "+joueur1.getName()+" qui commence !");
		}else{
			this.playerList.add(joueur2);
			this.playerList.add(joueur1);
			System.out.println("C'est donc "+joueur2.getName()+" qui commence !");
		}
	}
	public ArrayList<String> possiblelist(String word){
		ArrayList<String> player1list = this.playerList.get(0).getwordlist();
		ArrayList<String> player2list = this.playerList.get(1).getwordlist();
		ArrayList<String> possibleSteal = new ArrayList<String>();
		String suggestedWord = word;
		
		//Check Player1's words
		for(String currentWord: player1list){
			suggestedWord=word;
			boolean isWordContainsCurrent = true;
			for(char currentLetter: currentWord.toCharArray()){
				if(!(suggestedWord.contains(String.valueOf(currentLetter)))){
					isWordContainsCurrent = false;
					break;
				}
				suggestedWord.replace(Character.toString(currentLetter),"");
			}
			if(isWordContainsCurrent == true){
				possibleSteal.add(currentWord);
			}
		}
		
		//Check Player2's words
		for(String currentWord: player2list){
			suggestedWord=word;
			boolean isWordContainsCurrent = true;
			for(char currentLetter: currentWord.toCharArray()){ //Attention aux removes
				if(!(currentWord.contains(String.valueOf(currentLetter)))){
					isWordContainsCurrent = false;
					break;
				}
				suggestedWord.replace(Character.toString(currentLetter),"");
			}
			if(isWordContainsCurrent == true){
				possibleSteal.add(currentWord);
			}
		}
		
		//Liste des mots ayant pu être volé
		possibleSteal.sort(Comparator.comparing(String::length).reversed());
		return possibleSteal;
	}
	
	//Soustrait firstWordOfTheList à suggestedWord et renvoie le mot restant
	public String remainLetter(String firstWordOfTheList, String suggestedWord){ 
		String remainWord=suggestedWord;
		for (char currentLetter: firstWordOfTheList.toCharArray()){
			remainWord=remainWord.replace(Character.toString(currentLetter),"");
		}
		return remainWord;
	}
	
	public boolean playOrPass(Player currentPlayer, Player otherPlayer){
    	@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		String suggestedWord="";
		ArrayList<String> possiblelist=  new ArrayList<String>();
		
		//Gain d'une lettre
		this.commonPot.addLetters(Character.toString(letterPot.getRandomLetter()));
		
		displayInformation();
		
		System.out.println("\nJoueur "+currentPlayer.getName()+":");
		//Vérifions que le mot est dans le dictionnaire
		while(!this.dico.isWordValid(suggestedWord) || suggestedWord.length()==1){
			System.out.print("Peux-tu proposer un mot ? Un mot qui existe dans le dictionnaire bien évidemment ! ");
			suggestedWord =scan.nextLine().toLowerCase();
			suggestedWord= suggestedWord.replaceAll(" ", "");
			if(dejaVuWord(suggestedWord)){
				System.out.println("Mais j'ai déjà vu ce mot quelque part !");
				suggestedWord="untrucquinexistepas";
			}
			if(suggestedWord.isEmpty()){
				System.out.println("Tu viens de passer ton tour, profites en pour réfléchir !");
				return false;
			}
			if(suggestedWord.equalsIgnoreCase("q")){
				this.end=true;
				return false;
			}
		}
		//Vérifions si le mot peut être créer avec les mots du commonPot
		if(this.commonPot.isPotContains(suggestedWord)){
			System.out.println("Bien joué, tu as trouvé un mot !");
			this.commonPot.removeLetters(suggestedWord);
			currentPlayer.addWordToPlayer(suggestedWord);
			addToFoundWordList(suggestedWord);
			return true;
		}else{
			possiblelist=possiblelist(suggestedWord);
			return checkSteal(possiblelist,suggestedWord,currentPlayer,otherPlayer);
		}
	}

	public void addToFoundWordList(String word) {
		this.foundWordList.add(word);
	}

	public boolean checkSteal(ArrayList<String> possibleList, String suggestedWord, Player currentPlayer, Player otherPlayer){
		ArrayList<String> possibleList2= new ArrayList<String> (possibleList);
		String remainWord= remainLetter(possibleList.get(0), suggestedWord);
		if(!remainWord.isEmpty()){
			System.out.println("Remain: "+remainWord);
		}
		//Cas de l'anagramme
		if(remainWord.isEmpty()) {
		    System.out.println("Intéressant, vous venez de trouver un anagramme !");
		    currentPlayer.addWordToPlayer(suggestedWord);
		    currentPlayer.removeWordToPlayer(suggestedWord);
		    otherPlayer.removeWordToPlayer(suggestedWord);
		    addToFoundWordList(suggestedWord);		    
		    return true; 
			}
		
		for(int i=0; i<possibleList2.size();i++){
			/*if(possibleList.isEmpty()){
				System.out.println("Pas de vol pour aujourd'hui");
				return false;
			}*/
			remainWord= remainLetter(possibleList.get(i), suggestedWord);
			System.out.println(remainWord);
			
			if(this.commonPot.isPotContains(remainWord)){
				System.out.println("Subtil, vous venez de former un mot à l'aide d'un autre mot !");
				currentPlayer.addWordToPlayer(suggestedWord);
				currentPlayer.removeWordToPlayer(possibleList.get(0));
			    otherPlayer.removeWordToPlayer(possibleList.get(0));
			    addToFoundWordList(suggestedWord);
			    return true;
				}else{
					possibleList2.remove(0);
			}
		}
		System.out.println("Bien tenté mais vous ne pouvez pas faire ce mot !");
		return true;
	}
	
	/*public void checkSteal(ArrayList<String> possiblelist){
		String remainWord="";
		//for(int i=0; i<possiblelist.size();i++){
			remainWord=possiblelist.get(0);
			remainWord=remainLetter()
			if(this.commonPot.isPotContains(remainWord)){
				
			}
		//}
	}*/
	
	public boolean dejaVuWord(String suggestedWord){
		for(String foundWord: this.foundWordList){
			if(foundWord.equalsIgnoreCase(suggestedWord)){
				return true;
			}
		}
		return false;
	}
	
	public void displayInformation(){
		Player joueur1=this.playerList.get(0);
		Player joueur2=this.playerList.get(1);
		System.out.println("\nEtat du jeu:");
		System.out.println("-----------------------------------------------------");
		System.out.print("Pot commun: ");
		for(char currentLetter: this.commonPot.getLetterList()){
			System.out.print(currentLetter+" ");
		}
		System.out.print("\n"+joueur1.getName()+": ");
		for(String currentWord: joueur1.getwordlist()){
			System.out.print(currentWord+" ");
		}
		System.out.print("\n"+joueur2.getName()+": ");
		for(String currentWord: joueur2.getwordlist()){
			System.out.print(currentWord+" ");
		}
		System.out.println("\n-----------------------------------------------------\n");
	}
	
	public ArrayList<Player> getPlayerList() {
		return playerList;
	}

	public void setPlayerList(ArrayList<Player> playerList) {
		this.playerList = playerList;
	}

	public CommonPot getCommonPot() {
		return commonPot;
	}

	public void setCommonPot(CommonPot commonPot) {
		this.commonPot = commonPot;
	}

	public boolean playerWin(Player currentPlayer) {
		if(currentPlayer.getwordlist().size()>1){
			return true;
		}else{
			return false;
		}
	}

	public void setEnd() {
		this.end = true;
	}

	public boolean isEnd() {
		return end;
	}

	public boolean yesOrNo(String answer){
		String yes="yes";
		String no= "no";
		if(answer.equalsIgnoreCase(yes)||answer.equalsIgnoreCase(no)){
			return true; 
		}else{
			return false;
		}
	}
	
}
