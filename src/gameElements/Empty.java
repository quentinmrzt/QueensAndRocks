package gameElements;

public class Empty implements Square {
	protected Player player;

	public String toString() {
		return " ";
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player p) {
		player = p;
	}
}
