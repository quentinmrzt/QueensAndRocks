package gameElements;

public class Eval1 implements Eval {

	// TP5 question 1
	public float getEval(Player player, Board b) {
		Player adversaire;
		Queen reineAdverse, reine;
		int nbAccessible=0, nbImprenable=0;
		
		if(player.number==0) {
			adversaire = b.getGame().getPlayer1();
			reineAdverse = b.getGame().getQueen1();
			reine = b.getGame().getQueen0();
		} else {
			adversaire = b.getGame().getPlayer0();
			reineAdverse = b.getGame().getQueen0();
			reine = b.getGame().getQueen1();
		}

		nbAccessible = b.numberOfAccessible2(player)-b.numberOfAccessible2(adversaire);
		
		boolean imprenable,imprenableEnnemi;
		// parcours du plateau 
		for (int y=0 ; y<b.getSize() ; y++) {
			for (int x=0 ; x<b.getSize() ; x++) {
				imprenable = false;
				imprenableEnnemi = false;
				// vecteur i et j
				for (int j=-1 ; j<=1 ; j++) {
					for (int i=-1 ; i<=1 ; i++) {
						boolean fin=false;
						int posX=x+i;
						int posY=y+j;
						if (posX<0 || posY<0 || posX>=b.getSize() || posY>=b.getSize() || (i==0 && j==0)) {
							//fin=true;
						} else {
							Square s = b.getPiece(posX,posY);
								
							// on regarde si la case qu'on a récupérer n'est pas occupé pas une reine ennemie
							if(s.toString().equals(reine.toString())) {
								imprenable=true;
							}
							if(s.toString().equals(reineAdverse.toString())) {
								imprenableEnnemi=true;
							}
						}
					}
				}
				if (imprenable) {
					nbImprenable++;
				}
				if (imprenableEnnemi) {
					nbImprenable--;
				}
			}
		}
		
		return nbAccessible+nbImprenable;
		
	}

}
