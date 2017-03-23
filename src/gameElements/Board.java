package gameElements;

import java.util.ArrayList;
import java.util.Collections;
import java.util.NoSuchElementException;


public class Board {
	
	//ATTENTION
	//Ceci est un squelette incomplet contenant uniquement le profil de quelques méthodes, dans le but de compiler la classe GameUI sans erreurs
	//Il manque les getters et les setters ainsi que les classes externes telles que Square, Eval, Game, Player,...
	
	
	
	//---------------TP1------------------------
	private Game game;
	private int size, numberOfPieces;
	private Square[][] board;
	
	
	public Board() {
		game = new Game();
		numberOfPieces = 0;
		size = 8; // taille par défaut
		board = new Square[size][size];
		/* On ini à empty */
		for (int y=0 ; y<size ; y++) {
			for (int x=0 ; x<size ; x++) {
				board[y][x] = game.getEmpty();
			}
		}
		rocksPlayer0 = size;
		rocksPlayer1 = size;
	}
	
	public Board(int s) {
		game = new Game();
		numberOfPieces = 0;
		size = s;
		board = new Square[size][size];
		/* On ini à empty */
		for (int y=0 ; y<size ; y++) {
			for (int x=0 ; x<size ; x++) {
				board[y][x] = game.getEmpty();
			}
		}
		rocksPlayer0 = size;
		rocksPlayer1 = size;
	}
	
	public Board(Board b) {
		game = b.getGame();
		numberOfPieces = b.getNumberOfPieces();
		size = b.getSize();
		board = new Square[size][size];
		
		for (int y=0 ; y<size ; y++) {
			for (int x=0 ; x<size ; x++) {
				board[y][x] = b.getBoard()[y][x];
			}
		}
		rocksPlayer0 = b.getRocksPlayer0();
		rocksPlayer1 = b.getRocksPlayer1();
	}
	
	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getNumberOfPieces() {
		return numberOfPieces;
	}

	public void setNumberOfPieces(int numberOfPieces) {
		this.numberOfPieces = numberOfPieces;
	}

	public Square[][] getBoard() {
		return board;
	}

	public void setBoard(Square[][] board) {
		this.board = board;
	}

	public Square getPiece(int i, int j) {
		return board[j][i];
	}
	
	public void setPiece(int i, int j, Square piece) {
		board[j][i] = piece;
		numberOfPieces = numberOfPieces+1;
	}

	public void removePiece(int i, int j) {
		board[j][i] = game.getEmpty();
		numberOfPieces = numberOfPieces-1;
	}
	
	public boolean isEmpty(int i, int j) {
		return board[j][i]==game.getEmpty();
	}
	
	public String toString() {
		StringBuilder table = new StringBuilder();
		table.append("D0: Dame 0   D1: Dame 1\nR0: Rochet 0   R1: Rochet 1\n");
		
		for (int y=0 ; y<size ; y++) {
			table.append("| ");
			for (int x=0 ; x<size ; x++) {
				if (isEmpty(x,y)) {
					table.append(board[y][x].toString()+"   | ");
				} else {
					table.append(board[y][x].toString()+"  | ");
				}
			}
			table.append("\n");
		}
		return table.toString();
	}
	
	public Board clone() {
		return new Board(this);
	}

	public boolean isAccessible(int i, int j) {		
		if (!this.isEmpty(i, j)) {
			return false;
		}
		
		boolean accessible = true;
		for (int y=0 ; y<size ; y++) {
			for (int x=0 ; x<size ; x++) {
				/* les collones */
				if (i==x && !isEmpty(x,y)) {
					accessible=false;
				}
				
				/* les lignes */
				if (j==y && !isEmpty(x,y)) {
					accessible=false;
				}
				
				/* les diagonales */
				if (Math.abs(i-x)==Math.abs(j-y) && !isEmpty(x,y)) {
					accessible=false;
				}
				
			}
		}
		
		return accessible;
	}
	
	public String toStringAccess() {
		StringBuilder table = new StringBuilder("");
		
		for (int y=0 ; y<size ; y++) {
			table.append("| ");
			for (int x=0 ; x<size ; x++) {
				table.append(board[y][x].toString());
				if (!isAccessible(x,y) && isEmpty(x,y)) {
					table.append("X ");
				}else if(isEmpty(x,y)){
					table.append("  ");
				}else {
					table.append(" ");
				}
				table.append(" | ");
			}
			table.append("\n");
		}
		return table.toString();
	}

	public int numberOfAccessible() {
		int nb=0;
		
		for (int y=0 ; y<size ; y++) {
			for (int x=0 ; x<size ; x++) {
				if (isAccessible(x,y)) {
					nb++;
				}
			}	
		}
		
		return nb;
	}

	public int numberOfQueens() {
		int nb=0;
		
		for (int y=0 ; y<size ; y++) {
			for (int x=0 ; x<size ; x++) {
				if (!isEmpty(x,y)) {
					nb++;
				}
			}	
		}
		
		return nb;
	}
	
	public boolean placeQueen(int i, int j) {
		if (isAccessible(i,j)) {
			setPiece(i,j,game.getQueen0());
			return true;
		}
		return false;
	}
	
	//----------TP2-----------------------
	// question 1.1
		public boolean isSolution() {
			if (this.numberOfQueens()==this.getSize()) {
				return true;
			}
			return false;
		}
		
		// question 1.2
		public ArrayList<Board> getSuccessors() {
			ArrayList<Board> lb = new ArrayList<Board>();
			
			for (int y=0 ; y<size ; y++) {
				for (int x=0 ; x<size ; x++) {
					if (isAccessible(x,y)) {
						Board b = this.clone();
						b.placeQueen(x, y);
						lb.add(b);
					}
				}	
			}
			
			return lb;
		}
		
		// question 1.3
		public ArrayList<Board> depthFirstSearch(Board initialState) {
			ArrayList<Board> sol = new ArrayList<Board>();
			
			if(initialState.isSolution()) {
				sol.add(initialState);
				return sol;
			}

			for(Board s: initialState.getSuccessors()) {
					sol.addAll(depthFirstSearch(s));
					if(!sol.isEmpty() && sol.get(0).isSolution() ){
						sol.add(initialState);
						return sol;
					}
						


			}
			if(sol.isEmpty() && initialState.isEmpty()) {
				throw new NoSuchElementException();
			}
			return sol;
			
			
		}
		
		// question 1.4
		public ArrayList<Board> depthFirstSearch() {
			return depthFirstSearch(new Board(size));
		}
		
		public boolean isEmpty() {
			boolean empty = true;
			
			for (int y=0 ; y<size ; y++) {
				for (int x=0 ; x<size ; x++) {
					if(!this.isEmpty(x,y)) {
						empty = false;
					}
				}	
			}

			
			return empty;
		}
		
		// question 1.5
		public String solutionSteps(Board b) {
			StringBuilder s = new StringBuilder();
			
			ArrayList<Board> lb = b.depthFirstSearch();
			for(Board bo:lb) {
				s.append(bo.toString());
			}
			
			return s.toString();
		}
		
		// question 3.1
		public ArrayList<Board> getNewSuccessors() {
			ArrayList<Board> lb = new ArrayList<Board>();
			int n = this.numberOfQueens()+1;
			
			for(int i=0; i<this.size ; i++) {
				if (isAccessible(n,i)) {
					Board b = this.clone();
					b.placeQueen(n, i);
					lb.add(b);
				}
			}
			return lb;
		}
		
		// question 3.2
		public ArrayList<Board> depthFirstSearch2(Board initialState) {
			ArrayList<Board> sol = new ArrayList<Board>();
			
			if(initialState.isSolution()) {
				sol.add(initialState);
				return sol;
			}

			for(Board s: initialState.getNewSuccessors()) {
					sol.addAll(depthFirstSearch(s));
					if(!sol.isEmpty() && sol.get(0).isSolution() ){
						sol.add(initialState);
						return sol;
					}
						


			}
			if(sol.isEmpty() && initialState.isEmpty()) {
				throw new NoSuchElementException();
			}
			return sol;
			
			
		}
		
		// question 3.2
		public ArrayList<Board> depthFirstSearch2() {
			return depthFirstSearch2(new Board(size));
		}
		
		// question 4.1
		public int[] boardToArray() {
			int[] tab = new int[size];
			
			for (int x=0 ; x<size ; x++) {
				boolean vide=true;
				for (int y=0 ; y<size ; y++) {
					if(!this.isEmpty(x, y) && vide) {
						tab[x]=y;
						vide=false;
					}
				}
				if(vide) {
					tab[x]=-1;
				}
			}

			return tab;
		}
		
		// question 4.1
		public Board arrayToBoard(int[] array) {
			Board b = new Board(array.length);
			
			for (int x=0 ; x<size ; x++) {
				for (int y=0 ; y<size ; y++) {
					if(array[x]==y){
						b.placeQueen(x, y);
					}
				}
			}
			
			return b;
		}
	
		// question 4.2
		public ArrayList<int[]> getArraySuccessors(int[] array) {
			ArrayList<int[]> lb = new ArrayList<int[]>();
			Board b = arrayToBoard(array);
			
			for (int x=0 ; x<array.length ; x++) {
				if(array[x]==-1) {
					for(int y=0 ; y<array.length ; y++) {
						if(b.isAccessible(x, y)){
							int[] tmp = array.clone();
							tmp[x]=y;
							lb.add(tmp);
						}
					}
				}
				
			}
			
			return lb;
		}
		
		// question 4.3
		public boolean isSolutionArray(int[] array) {
			for (int i: array) {
				if (i==-1) {
					return false;
				}
			}
			
			return true;
		}
		
		public boolean isEmptyArray(int[] array) {
			for (int i: array) {
				if (i!=-1) {
					return false;
				}
			}
			
			return true;
		}
		
		// question 4.4
		public ArrayList<int[]> depthFirstSearchArray(int[] initialState) {
			ArrayList<int[]> sol = new ArrayList<int[]>();
			
			if(isSolutionArray(initialState)) {
				sol.add(initialState);
				return sol;
			}

			for(int[] i: getArraySuccessors(initialState)) {
					sol.addAll(depthFirstSearchArray(i));
					if(!sol.isEmpty() && isSolutionArray(sol.get(0)) ){
						sol.add(initialState);
						return sol;
					}
						


			}
			if(sol.isEmpty() && isEmptyArray(initialState)) {
				throw new NoSuchElementException();
			}
			return sol;
		}
	
		public ArrayList<int[]> depthFirstSearchArray() {
			int[] tab = new int[size];
			for(int i = 0; i <size; i++){
				tab[i] = -1;
			}
			return depthFirstSearchArray(tab);
		}
		
		
	
	//------------TP3----------------------
	public int rocksPlayer0;
	public int rocksPlayer1;
	
	public static int queenValue = 5;
	public static int rockValue = 2;
	
	// question 1.1
	public int getRocksPlayer0() {
		return rocksPlayer0;
	}
	public void setRocksPlayer0(int rocksPlayer0) {
		this.rocksPlayer0 = rocksPlayer0;
	}
	public int getRocksPlayer1() {
		return rocksPlayer1;
	}
	public void setRocksPlayer1(int rocksPlayer1) {
		this.rocksPlayer1 = rocksPlayer1;
	}

	// question 1.2
	public int getNumberOfRocksLeft(Player player) {
		if (player.getNumber()==0) {
			return rocksPlayer0;
		} else {
			return rocksPlayer1;
		}
	}
	
	public void useRock(Player player) {
		if (player.getNumber()==0) {
			rocksPlayer0--;
		} else {
			rocksPlayer1--;
		}
	}
	
	// question 1.3
	public boolean isAccessible2(int i, int j, Player currentPlayer) {
		if (!this.isEmpty(i, j)) {
			return false;
		}
		
		boolean resultat=true;
		// on determine la reine adverse
		Square reineEnnemie;
		if(currentPlayer.getNumber()==0) {
			reineEnnemie = this.game.getQueen1();
		} else {
			reineEnnemie = this.game.getQueen0();
		}
		
		// tous les vecteurs
		for (int y=-1 ; y<=1 ; y++) {
			for (int x=-1 ; x<=1 ; x++) {
				boolean fin=false;
				int posX=i;
				int posY=j;
				while (!fin && resultat) {
					posX = posX+x;
					posY = posY+y;
					if (posX<0 || posY<0 || posX>=size || posY>=size || (x==0 && y==0)) {
						fin=true;
					} else {
						Square s = this.getPiece(posX,posY);
						
						// on regarde si la case qu'on a récupérer n'est pas occupé pas une reine ennemie
						if(s.toString().equals(reineEnnemie.toString())) {
							resultat=false;
							fin=true;
						} else if(!this.isEmpty(posX, posY)) {
							fin=true;
						}
					}
				}
			}
		}
		
		return resultat;
	}
	
	// question 1.4
	public int numberOfAccessible2(Player player) {
		int nb=0;
		
		for (int y=0 ; y<size ; y++) {
			for (int x=0 ; x<size ; x++) {
				if (isAccessible2(x,y,player)) {
					nb++;
				}
			}	
		}
		
		return nb;
	}
	
	// question 1.5
	public String toStringAccess2(Player player) {
		StringBuilder table = new StringBuilder("");
		
		for (int y=0 ; y<size ; y++) {
			table.append("| ");
			for (int x=0 ; x<size ; x++) {
				table.append(board[y][x].toString());
				if (!isAccessible2(x,y,player) && isEmpty(x,y)) {
					table.append("X ");
				} else if(isEmpty(x,y)){
					table.append("  ");
				}else{
					table.append(" ");
				}
				table.append(" | ");
			}
			table.append("\n");
		}
		return table.toString();
	}
	
	// question 1.6
	public int numberOfQueens2(Player player) {
		int nb = 0;
		Square reine;
		
		if(player.getNumber()==0) {
			reine = this.game.getQueen0();
		} else {
			reine = this.game.getQueen1();
		}
		
		for (int y=0 ; y<size ; y++) {
			for (int x=0 ; x<size ; x++) {
				Square s = this.getPiece(x,y);

				if(s.toString().equals(reine.toString())) {
					nb++;
				}
			}
		}
		
		return nb;
	}
	
	public int numberOfRocks2(Player player) {
		int nb = 0;
		Square rock;
		
		if(player.getNumber()==0) {
			rock = this.game.getRock0();
		} else {
			rock = this.game.getRock1();
		}
		
		for (int y=0 ; y<size ; y++) {
			for (int x=0 ; x<size ; x++) {
				Square s = this.getPiece(x,y);

				if(s.toString().equals(rock.toString())) {
					nb++;
				}
			}
		}
		
		return nb;
	}
	
	// question 1.7
	public int getScore(Player player){
		return queenValue*this.numberOfQueens2(player)+rockValue*this.numberOfRocks2(player);
	}
	
	public boolean placeQueen2(int i, int j, Player player) {
		Square reine;

		if(player.getNumber()==0) {
			reine = this.game.getQueen0();
		} else {
			reine = this.game.getQueen1();
		}
		
		if (isAccessible2(i,j,player)) {
			setPiece(i,j,reine);
			return true;
		}
		
		return false;
	}

	public boolean placeRock2(int i, int j, Player player) {
		Square rocher;

		if(player.getNumber()==0) {
			rocher = this.game.getRock0();
		} else {
			rocher = this.game.getRock1();
		}
		
		if (this.isEmpty(i, j) && this.getNumberOfRocksLeft(player)>0) {
			setPiece(i,j,rocher);
			useRock(player);
			return true;
		}
		
		return false;
	}
	
	//----------------------TP4&5--------------------------
	
	// question 1.3
	public boolean isFinal() {
		int nombreRocher0 = this.getNumberOfRocksLeft(this.getGame().getPlayer0());
		int nombreRocher1 = this.getNumberOfRocksLeft(this.getGame().getPlayer1());
		int nombreAccesible0 = this.numberOfAccessible2(this.getGame().getPlayer0());
		int nombreAccesible1 =this.numberOfAccessible2(this.getGame().getPlayer1());
		
		return (nombreRocher0==0 && nombreAccesible0==0) || (nombreRocher1==0 && nombreAccesible1==0);
	}
	
	
	// question 1.4
	public ArrayList<Board> getSuccessors2(Player player) {
		ArrayList<Board> lb = new ArrayList<Board>();
				
		for (int y=0 ; y<size ; y++) {
			for (int x=0 ; x<size ; x++) {	
				// place une reine
				if (isAccessible2(x,y,player)) {
					Board b = this.clone();
					b.placeQueen2(x, y, player);
					lb.add(b);
				}
				
				// place un rocher
				if (this.isEmpty(x, y) && getNumberOfRocksLeft(player)>0) {
					Board b = this.clone();
					b.placeRock2(x, y, player);
					lb.add(b);
				}
			}
		}
				
		return lb;
	}

	// question 1.5
	public Board minimax(Board b,Player currentPlayer, int minimaxDepth, Eval evaluation) {
		ArrayList<Board> lb = getSuccessors2(currentPlayer);

		float score;
		float scoreMax = Float.NEGATIVE_INFINITY;
		// plateau vide si plus successeur
		Board sortie = new Board(size);
		
		if(b.isFinal()){
			return b;
		}
		
		for(Board s: lb) {
			score = evaluation(s,currentPlayer, minimaxDepth,evaluation,currentPlayer);
			if(score >= scoreMax){
				sortie = s;
				scoreMax = score;
			}
		}
		
		return sortie;
	}


	public float evaluation(Board b, Player player, int c, Eval e, Player playing) {
		float resultat = e.getEval(player,b);
		float score_min,score_max;
		
		// on cherche l'adversaire
		Player adversaire;
		if (playing.getNumber()==0) {
			adversaire = b.getGame().getPlayer1();
		} else {
			adversaire = b.getGame().getPlayer0();
		}
		
		if(b.isFinal()){
			if(resultat==0) {
				return 0;
			} else if (resultat>0) {
				return Float.POSITIVE_INFINITY;
			} else {
				return Float.NEGATIVE_INFINITY;
			}
		}
		
		if(c==0) {
			return resultat; 
		}
		
		ArrayList<Board> lb = b.getSuccessors2(playing);
		if(player.getNumber()==playing.getNumber()){
			score_max = Float.NEGATIVE_INFINITY;
			
			for(Board s : lb){
				score_max = Math.max(score_max,evaluation(s,player,c-1,e,adversaire));
			}
			
			return score_max;
		} else {
			score_min = Float.POSITIVE_INFINITY;
			for(Board s : lb){
				score_min = Math.min(score_min,evaluation(s,player,c-1,e,adversaire));
			}
			return score_min;
		}
	}
}
