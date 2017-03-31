package gameElements;

import java.util.ArrayList;
import java.util.Random;

public class Eval2 implements Eval {

	// TP5 question 3.1
	public float getEval(Player player, Board b) {
		int n=0;
		Random r = new Random();
		int valeur;
		Player playerCurrent = player;
		ArrayList<Board> lb = new ArrayList<Board>();
		Board btmp; 
		
		// nos 100 parties
		for (int i=0 ; i<100 ; i++) {
			btmp = b.clone();
			while(!btmp.isFinal()) {
				// On prend tout les coups jouables
				lb = btmp.getSuccessors2(playerCurrent);
				
				// On joue un coup au hasard
				if(lb.size() > 1){
					valeur = r.nextInt(lb.size()-1);
				}else{
					valeur = 0;
				}
				btmp = lb.get(valeur).clone();
				
				// On inverse le joueur
				if (playerCurrent.getNumber()==0) {
					playerCurrent = btmp.getGame().getPlayer1();
				} else {
					playerCurrent = btmp.getGame().getPlayer0();
				}
				lb.clear();
			}
			
			// On récupère le gagnant 
			Player playerGagnant;
			if (btmp.getScore(btmp.getGame().getPlayer0())>btmp.getScore(btmp.getGame().getPlayer1())) {
				playerGagnant=btmp.getGame().getPlayer0();
			} else if(btmp.getScore(btmp.getGame().getPlayer0())<btmp.getScore(btmp.getGame().getPlayer1())){
				playerGagnant=btmp.getGame().getPlayer1();
			} else {
				playerGagnant=null;
			}
			
			if (playerGagnant != null && playerGagnant.getNumber()==player.getNumber()) {
				n++;
			}
		}
		
		return 2*n/100-1;
	}

}
