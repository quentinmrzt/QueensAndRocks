package gameElements;

public class Game {
	protected Player player0, player1;
	protected Queen queen0, queen1;
	protected Rock rock0, rock1;
	protected Empty empty;
	
	public Game() {
		player0 = new Player(0);
		queen0 = new Queen();
		queen0.setPlayer(player0);
		rock0 = new Rock();
		rock0.setPlayer(player0);
		
		player1 = new Player(1);
		queen1 = new Queen();
		queen1.setPlayer(player1);
		rock1 = new Rock();
		rock1.setPlayer(player1);
		
		empty = new Empty();
		empty.setPlayer(player0);
	}

	public Player getPlayer0() {
		return player0;
	}

	public Player getPlayer1() {
		return player1;
	}

	public Queen getQueen0() {
		return queen0;
	}

	public Queen getQueen1() {
		return queen1;
	}

	public Rock getRock0() {
		return rock0;
	}

	public Rock getRock1() {
		return rock1;
	}

	public Empty getEmpty() {
		return empty;
	}
	
	public void setColorMode() {
		if (player0.getColorMode().equals("bw") && player1.getColorMode().equals("bw")) {
			player0.setColorMode("og");
			player1.setColorMode("og");
		} else if(player0.getColorMode().equals("bw") && player1.getColorMode().equals("og")){
			player0.setColorMode("og");
			player1.setColorMode("bw");
		} else if (player0.getColorMode().equals("og") && player1.getColorMode().equals("bw")) {
			player0.setColorMode("bw");
			player1.setColorMode("og");
		} else {
			player0.setColorMode("bw");
			player1.setColorMode("bw");
		}
	}
	
	public Player otherPlayer(Player player) {
		if (player.getNumber()==0) {
			return player1;
		} else {
			return player0;
		}
	}
}
