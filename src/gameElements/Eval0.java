package gameElements;

public class Eval0 implements Eval {

	// tp4 question 1.2
	public float getEval(Player player, Board b) {
		int scoreAdversaire=0;
		
		if(player.number==0) {
			scoreAdversaire = b.getScore(b.getGame().getPlayer1());
		} else {
			scoreAdversaire = b.getScore(b.getGame().getPlayer0());
		}
		
		return b.getScore(player)-scoreAdversaire;
	}

}
