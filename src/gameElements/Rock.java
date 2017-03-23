package gameElements;

public class Rock implements Square {
	protected Player player;

	public String toString() {
		if (player.getNumber()==0) {
			return "R0";
		} else {
			return "R1";
		}	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player p) {
		player = p;
	}
}
