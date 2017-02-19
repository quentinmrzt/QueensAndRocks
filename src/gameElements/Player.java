package gameElements;

public class Player {
	protected int number;
	protected String colorMode;

	public Player(int nb) {
		super();
		this.number = nb;
		this.colorMode = "bw";
	}
	
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	
	public String getColorMode() {
		return colorMode;
	}
	public void setColorMode(String colorMode) {
		this.colorMode = colorMode;
	}

	public String toString() {
		if (number==0) {
			if(colorMode.equals("bw")) {
				return "white";
			} else {
				return "green";
			}
		} else {
			if(colorMode.equals("bw")) {
				return "black";
			} else {
				return "orange";
			}
		}
	}
	
	
}
