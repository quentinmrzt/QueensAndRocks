package gameElements;

public class Rock implements Square {
	protected Player player;

	public String toString() {
		if (player.getNumber()==0) {
			return "@";
		} else {
			return "#";
		}	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player p) {
		player = p;
	}
}
