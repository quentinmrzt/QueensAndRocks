package gameElements;

public class Queen implements Square {
	protected Player player;
	
	public String toString() {
		if (player.getNumber()==0) {
			return "D0";
		} else {
			return "D1";
		}
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player p) {
		player=p;		
	}
}
