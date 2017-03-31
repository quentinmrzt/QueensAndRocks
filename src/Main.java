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
				System.out.println("Nombre de rocher restante: "+b.getNumberOfRocksLeft(joueur)+"\n");
				System.out.println("Votre score: "+b.getScore(joueur)+", adversaire: "+b.getScore(adversaire));
				
				System.out.println(b.toStringAccess2(joueur)+"\n");
				p="";
				while(!p.equals("d") && !p.equals("r")){
					
					System.out.println("Quelle pièce voulez-vous placer ?");
					System.out.print("Pièce (d/r): ");
					p = sc.next();
					
					if (p.equals("d")) {
						if(b.numberOfAccessible2(joueur)==0) {
							System.out.println("Il n'y a plus de case accessible !");
							p="";
						}
					} else if (p.equals("r")){
						if(b.getNumberOfRocksLeft(joueur)==0) {
							System.out.println("Tu n'as pas de rocher !");
							p="";
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
	
	// tp3 question 1.10 
	public static void test3rocher() {
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
				System.out.println("Nombre de rocher restante: "+b.getNumberOfRocksLeft(joueur)+"\n");
				System.out.println("Votre score: "+b.getScore(joueur)+", adversaire: "+b.getScore(adversaire));
				
				System.out.println(b.toStringAccess2(joueur)+"\n");
				p="";
				
				if(joueur.getNumber()==0 && b.getNumberOfRocksLeft(joueur)==b.getSize()) {
					System.out.println("Vous devez obligatoirement placer un rocher en tant que premier joueur.");
					p = "r";
				}
				
				while(!p.equals("d") && !p.equals("r")){
					
					System.out.println("Quelle pièce voulez-vous placer ?");
					System.out.print("Pièce (d/r): ");
					p = sc.next();
					
					if (p.equals("d")) {
						if(b.numberOfAccessible2(joueur)==0) {
							System.out.println("Il n'y a plus de case accessible !");
							p="";
						}
					} else if (p.equals("r")){
						if(b.getNumberOfRocksLeft(joueur)==0) {
							System.out.println("Tu n'as pas de rocher !");
							p="";
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
	
	public static void test4joueurVSordi(int profondeur, Eval e) {
		Scanner sc = new Scanner(System.in);
		int i, j, s;
		String p = "";
		System.out.print("Choisissez une taille du tableau : ");
		s = sc.nextInt();
		Board b = new Board(s);
		boolean fin=false;
		Player joueur;
		Player ordinateur;
		int numJoueur=0;
		
		while(!fin) {

			joueur = b.getGame().getPlayer0();
			ordinateur = b.getGame().getPlayer1();

			if(numJoueur==0) {
				if(b.getNumberOfRocksLeft(joueur)>0 || b.numberOfAccessible2(joueur)>0) {
					System.out.println("A votre tour:");
					System.out.println("Nombre de rocher restante: "+b.getNumberOfRocksLeft(joueur)+"\n");
					System.out.println("Votre score: "+b.getScore(joueur)+", Ordinateur: "+b.getScore(ordinateur));
					
					System.out.println(b.toStringAccess2(joueur)+"\n");
					p="";
					
					if(joueur.getNumber()==0 && b.getNumberOfRocksLeft(joueur)==b.getSize()) {
						System.out.println("Vous devez obligatoirement placer un rocher en tant que premier joueur.");
						p = "r";
					}
					
					while(!p.equals("d") && !p.equals("r")){
						
						System.out.println("Quelle pièce voulez-vous placer ?");
						System.out.print("Pièce (d/r): ");
						p = sc.next();
						
						if (p.equals("d")) {
							if(b.numberOfAccessible2(joueur)==0) {
								System.out.println("Il n'y a plus de case accessible !");
								p="";
							}
						} else if (p.equals("r")){
							if(b.getNumberOfRocksLeft(joueur)==0) {
								System.out.println("Tu n'as pas de rocher !");
								p="";
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
				
				numJoueur=1;
			} else {
				Eval eval = e;
				b = new Board(b.minimax(b, ordinateur, profondeur, eval));
				numJoueur=0;
			}
			
			if ((b.getNumberOfRocksLeft(joueur)<=0 && b.numberOfAccessible2(joueur)<=0)
			 || (b.getNumberOfRocksLeft(ordinateur)<=0 && b.numberOfAccessible2(ordinateur)<=0)) {
				fin=true;
			}
		}
		
		System.out.println(b.toString());
		int score0=b.getScore(b.getGame().getPlayer0());
		int score1=b.getScore(b.getGame().getPlayer1());
		System.out.println("Score joueur: "+score0+" et score ordinateur: "+score1);
		if(score0>score1) {
			System.out.println("Bravo tu as gagné !");
		} else if (score0<score1){
			System.out.println("Dommage tu as perdu !");
		} else {
			System.out.println("ex aequo !");
		}
	}
	
	public static int test4ordiVSordi(int s, int profondeur0, int profondeur1, boolean rocher, Eval e0, Eval e1) {
		Board b = new Board(s);
		boolean fin=false;
		Player ordinateur0;
		Player ordinateur1;
		int numJoueur=0;

		Date date = new Date();

		
		b.setRegleRocher(rocher);
		
		while(!fin) {
			ordinateur0 = b.getGame().getPlayer0();
			ordinateur1 = b.getGame().getPlayer1();

			if (numJoueur==0) {
				System.out.println("Ordinateur0:");
				System.out.println(b.toStringAccess2(ordinateur0));;
				b = b.minimax(b, ordinateur0, profondeur0, e0);
				numJoueur=1;
			} else {
				System.out.println("Ordinateur1:");
				System.out.println(b.toStringAccess2(ordinateur1));
				b = b.minimax(b, ordinateur1, profondeur1, e1);
				numJoueur=0;
			}
			
			if (b.isFinal()) {
				fin=true;
			}
		}
		
		Date date2 = new Date();
		
		System.out.println(b.toString());
		float diff = date2.getTime()-date.getTime();
		System.out.println("Temps: "+diff+" ms");
		
		int score0=b.getScore(b.getGame().getPlayer0());
		int score1=b.getScore(b.getGame().getPlayer1());
		System.out.println("Score ordinateur0: "+score0+" et score ordinateur1: "+score1);
		if(score0>score1) {
			System.out.println("Victoire ordinateur0 !");
			return 0;
		} else if (score0<score1){
			System.out.println("Victoire ordinateur1 !");
			return 1;
		} else {
			System.out.println("Ex aequo !");
			return 2;
		}
	}
	
	public static void test4multiple(int nb, Eval e) {
		int nbOrdi0=0, nbOrdi1=0, nbEgalite=0;
		for(int i=0 ; i<nb ; i++) {
			int resultat = test4ordiVSordi(4,2,2,true,e,e);
			if(resultat==0) {
				nbOrdi0++;
			} else if(resultat==1) {
				nbOrdi1++;
			} else {
				nbEgalite++;
			}
		}
		System.out.println("\nFin, resultat sur "+nb+" partie(s):\nOrdi0: "+nbOrdi0+"\nOrdi1: "+nbOrdi1+"\nEgalite: "+nbEgalite);
	}
	
	public static float optimisation() {
		EvalLambda evalLambda = new EvalLambda();
		
		int gg;
		
		EvalLambda evalLambdaPrime = new EvalLambda();
		evalLambdaPrime.setLambda(evalLambda.getLambda()+ 0.01f);
		
		while((evalLambdaPrime.getLambda() - evalLambda.getLambda()) <= 0.1f && evalLambdaPrime.getLambda()<1){
			System.out.println("Lambda: "+evalLambda.getLambda()+" et lambda prime: "+evalLambdaPrime.getLambda());
			gg = test4ordiVSordi(4, 2, 2, true, evalLambda, evalLambdaPrime);
			
			if (gg==1) {
				evalLambda.setLambda(evalLambdaPrime.getLambda());
			}

			evalLambdaPrime.setLambda(evalLambdaPrime.getLambda()+0.01f);
		}
		
		return evalLambda.getLambda();
	}
	
	
	
	public static void main(String[] args) {
		//System.out.println(optimisation());
		Eval eval = new Eval1();
		
		/*
		GameUI gUI = new GameUI(b);
		gUI.setMinimaxDepth(2);
		gUI.setEvaluation(eval);
		gUI.launch();
		*/
		//test();
		//test2();
		//test3();
		//test3rocher();
		//test4joueurVSordi(2, eval);
		test4ordiVSordi(4, 2, 2, true, eval, eval);
		//test4multiple(1, eval);
		//test4ordiVSordiRocher(4,2);
		//optimisation();
		
		
		// pour 100 parties, le temps est extremement long
		//Eval eval = new Eval2();
	}
}
