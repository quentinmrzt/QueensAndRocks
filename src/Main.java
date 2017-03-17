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
	
	// tp3 question 1.9 
	public static void test3() {
		Scanner sc = new Scanner(System.in);
		int i, j, s;
		String p = "";
		System.out.print("Choisissez une taille du tableau : ");
		s = sc.nextInt();
		Board b = new Board(s);
		int numJoueur=0;
		boolean fin=false;
		Player joueur;
		Player adversaire;
		
		while(!fin) {
			if(numJoueur==0) {
				joueur = b.getGame().getPlayer0();
				adversaire = b.getGame().getPlayer1();
			} else {
				joueur = b.getGame().getPlayer1();
				adversaire = b.getGame().getPlayer0();
			}
			
			if(b.getNumberOfRocksLeft(joueur)>0 || b.numberOfAccessible2(joueur)>0) {
				System.out.println("Joueur n°"+numJoueur+", à votre tour:");
				System.out.println("Nombre de pierre restante: "+b.getNumberOfRocksLeft(joueur)+"\n");
				System.out.println("Votre score: "+b.getScore(joueur)+", adversaire: "+b.getScore(adversaire));
				
				System.out.println(b.toStringAccess2(joueur)+"\n");
				p="";
				while(!p.equals("d") && !p.equals("r")){
					
					System.out.println("Quelle pièce voulez-vous placer ?");
					System.out.print("Pièce (d/r): ");
					p = sc.next();
					
					Square piece;
					if (p.equals("d")) {
						if(b.numberOfAccessible2(joueur)==0) {
							System.out.println("Il n'y a plus de case accessible !");
							p="";
						}
						if(numJoueur==0) {
							piece = b.getGame().getQueen0();
						} else {
							piece = b.getGame().getQueen1();
						}
					} else if (p.equals("r")){
						if(b.getNumberOfRocksLeft(joueur)==0) {
							System.out.println("Tu n'as pas de rocher !");
							p="";
						}
						if(numJoueur==0) {
							piece = b.getGame().getRock0();
						} else {
							piece = b.getGame().getRock1();
						}
					} else {
						System.out.println("Caractère invalide, recommence.");
					}
					
				}
				
				boolean place=false;
				while(!place) {
					System.out.println("Ou voulez vous placez votre pièce ?");
					System.out.print("Rentrez x: ");
					i = sc.nextInt();
					System.out.print("Rentrez y: ");
					j = sc.nextInt();
					
					if(p.equals("d")){
						if(!b.placeQueen2(i,j,joueur)) {
							System.err.println("Cases inacessible !");
						} else {
							System.out.println("La reine a bien été placée.\n");
							place=true;
						}
					} else {
						if(!b.placeRock2(i,j,joueur)) {
							System.err.println("Cases inacessible !");
						} else {
							System.out.println("Le rocher a bien été placé\n");
							place=true;
						}
					}
				}
			}
			
			if(numJoueur==0) {
				numJoueur=1;
			} else {
				numJoueur=0;
			}
			
			if (b.getNumberOfRocksLeft(b.getGame().getPlayer0())<=0 && b.numberOfAccessible2(b.getGame().getPlayer0())<=0 
			 && b.getNumberOfRocksLeft(b.getGame().getPlayer1())<=0 && b.numberOfAccessible2(b.getGame().getPlayer1())<=0) {
				fin=true;
			}
		}	
		int score0=b.getScore(b.getGame().getPlayer0());
		int score1=b.getScore(b.getGame().getPlayer1());
		System.out.println("Score joueur0: "+score0+" et score joueur1: "+score1);
		if(score0>score1) {
			System.out.println("Bravo au joueur 0 !");
		} else if (score0<score1){
			System.out.println("Bravo au joueur 1 !");
		} else {
			System.out.println("Bravo aux 2 joueurs, ex aequo !");
		}
	}
	
	public static void main(String[] args) {
		
		Board b = new Board();
		/*b.setPiece(1, 4, b.getGame().getQueen0());
		b.setPiece(0, 7, b.getGame().getQueen1());*/
		GameUI gUI = new GameUI(b);
		gUI.launch();
	
		//test();
		//test2();
		
		
		// Test collone
		/*b.setPiece(4, 0, b.getGame().getQueen0());
		b.setPiece(4, 3, b.getGame().getRock0());
		b.setPiece(4, 7, b.getGame().getQueen0());
		b.setPiece(4, 5, b.getGame().getRock0());*/

		// Test diagonale
		/*b.setPiece(0, 0, b.getGame().getQueen0());
		b.setPiece(3, 3, b.getGame().getRock0());
		b.setPiece(7, 7, b.getGame().getQueen0());
		b.setPiece(5, 5, b.getGame().getRock0());*/
		
		// Test ligne
		/*b.setPiece(0, 4, b.getGame().getQueen0());
		b.setPiece(2, 4, b.getGame().getRock1());
		b.setPiece(7, 4, b.getGame().getQueen1());
		b.setPiece(5, 4, b.getGame().getRock0());*/
		
		test3();
	}
}
