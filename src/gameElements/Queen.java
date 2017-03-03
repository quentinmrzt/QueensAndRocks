package gameElements;

public class Queen implements Square {
	protected Player player;
	
	public String toString() {
		if (player.getNumber()==0) {
			return "W";
		} else {
			return "B";
		}
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player p) {
		player=p;		
	}
}
