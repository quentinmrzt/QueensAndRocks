import java.util.Scanner;

import gameElements.*;
import graphics.*;

public class Main {
	public static void test() {
		Scanner sc = new Scanner(System.in);
		int i, j, s;
		System.out.print("Choisissez une taille du tableau : ");
		s = sc.nextInt();
		Board b = new Board(s);
		while(b.numberOfAccessible()!=0 && b.numberOfQueens()!=b.getSize()) {
			System.out.println(b.numberOfAccessible());
			System.out.println("Ou voulez vous placez votre dame ?");
			System.out.print("Rentrez x: ");
			i = sc.nextInt();
			System.out.print("Rentrez y: ");
			j = sc.nextInt();
			
			
			if(!b.placeQueen(i,j)) {
				System.err.println("Cases inacessible !");
			}
			
			System.out.println(b.toString());
			System.out.println(b.toStringAccess());
		}
		if (b.numberOfQueens()==b.getSize()) {
			System.out.println("Bravo ! Vous avez gagnez !");
		} else {
			System.out.println("Bouh ! Recommence ;)");
		}
	}
	
	public static void main(String[] args) {
		Board b = new Board(3);
		//b.setPiece(1,0,b.getGame().getQueen0());
		
		
		//b.setPiece(0,2,b.getGame().getRock0());
		//b.setPiece(7,4,b.getGame().getRock1());
		
		System.out.println(b.solutionSteps(b));
		GameUI gUI = new GameUI(b);
		gUI.launch();
		
		//test();
		
	}
}
