import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;

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
	
	public static void test2() {
		
		Scanner sc = new Scanner(System.in);
		int s;
		System.out.print("Choisissez une taille du tableau : ");
		s = sc.nextInt();
		Board b = new Board(s);
		
		System.out.println("Taille : "+b.getSize()+"\n");
		System.out.print("Board 1 : ");
		Date date = new Date();
		b.depthFirstSearch();
		Date date2 = new Date();
		System.out.println(date2.getTime()-date.getTime()+" ms");
		
		System.out.print("Board 2 : ");
		date = new Date();
		b.depthFirstSearch2();
		date2 = new Date();
		System.out.println(date2.getTime()-date.getTime()+" ms");
		
		System.out.print("Array : ");
		date = new Date();
		b.depthFirstSearchArray();
		date2 = new Date();
		System.out.println(date2.getTime()-date.getTime()+" ms");
	}
	
	
	public static void main(String[] args) {
		
		
		//GameUI gUI = new GameUI(b);
		//gUI.launch();
	
		//test();
		test2();
		
	}
}
