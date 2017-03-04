package fr.esiea.chhuondaniere.main;

import java.util.ArrayList;

import fr.esiea.chhuondaniere.jeu.Jeu;
import fr.esiea.chhuondaniere.player.Player;

public class Main {

	public static void main(String[] args) {
		Jeu jeu = new Jeu();
		jeu.beginning();
		ArrayList<Player> playerList = new ArrayList<Player>(jeu.getPlayerList());
		jeu.whoFirst(playerList.get(0), playerList.get(1));
		Player joueur1 = playerList.get(0);
		Player joueur2 = playerList.get(1);
		jeu.displayInformation();

		while(true){
			
			while(jeu.playOrPass(joueur1, joueur2)){
				if(jeu.playerWin(joueur1)||jeu.playerWin(joueur2))break;
			}
			if(jeu.playerWin(joueur1)||jeu.playerWin(joueur2))break;
			while(jeu.playOrPass(joueur2, joueur1)){
				if(jeu.playerWin(joueur1)||jeu.playerWin(joueur2))break;
			}
			if(jeu.playerWin(joueur1)||jeu.playerWin(joueur2))break;
		}
		if(jeu.playerWin(joueur1)){
			System.out.println("Bravo "+joueur1.getName()+", tu remportes ce jeu !");
		}else if(jeu.playerWin(joueur2)){
			System.out.println("Bravo "+joueur2.getName()+", tu remportes ce jeu !");
		}else{
			System.out.println("Vous avez quitté, revenez nous voir vite !");
		}
		
	}
}
