package chess;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class GameController {
	Board board;
	boolean gameOver;
	int turn;
	Scanner scanner;
	
	GameController(Board board) {
		this.board = board;
	}
	
	public void startGame() {
		// Initialize game variables
		this.gameOver = false;
		this.turn = 1;
		this.board.printBoard();
		this.scanner = new Scanner(System.in);
		
		// continue game until its over
		while(!this.gameOver && this.turn < 10) {
			
			// Declare who's turn it is
			String colorTurn = (this.turn & 1) == 0 ? "Black's" : "White's";
			System.out.println("\n" + colorTurn + "turn to move");
			colorTurn = colorTurn == "White's" ? "whites" : "blacks";
			
			String selectedPiece = "";
			String n_position = "";
			boolean approved_piece	= false;
			boolean approved_move	= false;
			boolean treasonous_move = false;
			int s_piece_idx = 0;
			
			
			// Get Piece to be moved
			System.out.print("Enter desired Piece to move: ");
			// Make sure its a playable Piece by iterating through the current player's available
			// Pieces discriminated by color
			while(!approved_piece) {
				selectedPiece = this.scanner.nextLine();
				if(selectedPiece.charAt(1) == 'q' || selectedPiece.charAt(1) == 'K') {
					selectedPiece = " " + selectedPiece;
				}
				if(colorTurn.equals("whites")) {
					for(int i = 0; i < this.board.getWhites().size(); i++) {
						if(selectedPiece.equals(this.board.getWhites().get(i).getName())) {
							approved_piece = true;
							s_piece_idx = i;
						}
					}
				} else {
					for(int i = 0; i < this.board.getBlacks().size(); i++) {
						if(selectedPiece.equals(this.board.getBlacks().get(i).getName())) {
							approved_piece = true;
							s_piece_idx = i;
						}
					}
				}
				// allow surrender
				if(selectedPiece.equals("surrender")) {
					approved_piece = true;
					
				}
				if(!approved_piece) {
					System.out.print("Piece not on board! Choose playable Piece: ");
				}
			}
			// exit if surrendered
			if(approved_piece && selectedPiece.equals("surrender")) {
				this.gameOver = true;
				break;
				
			}
			// Get position to move selected Piece to
			System.out.print("Enter position to move to: ");
			while(!approved_move) {
				boolean inMap = false;;
				n_position = this.scanner.nextLine();
				
				// Make sure position given is inside the board map
				if(n_position.charAt(0) >= 'a' && n_position.charAt(0) <= 'h') {
					if(n_position.charAt(1) >= '1' && n_position.charAt(1) <= '8') {
						inMap = true;
						// More stuff to check
						if(colorTurn.equals("whites")) {
							for(int i = 0; i < this.board.getWhites().size(); i++) {
								if(this.board.getWhites().get(i).getPosition().equals(n_position)) {
									// not really, just lets me know if n_position corresponds to ally position
									treasonous_move = true;
								}
							}
							if(!approved_move && !treasonous_move) {
								// if legal to move there, the move is approved
								approved_move = this.board.getWhites().get(s_piece_idx).move(n_position);
							}
							
							
						} else {
							for(int i = 0; i < this.board.getBlacks().size(); i++) {
								if(this.board.getBlacks().get(i).getPosition().equals(n_position)) {
									// not really, just lets me know if n_position corresponds to ally position
									treasonous_move = true;
								}
							}
							// if n_position is an empty or enemy-occupied space, check if legal to move there
							if(!approved_move && !treasonous_move) {
								// if legal to move there, the move is approved
								approved_move = this.board.getBlacks().get(s_piece_idx).move(n_position);
							}
						}
					}
				}
				// if desired position is outside the map, try 'n whoop some player's butt
				if(!inMap) {
					System.out.println("Enter a position INSIDE the board. i.e. 'g6'");
				}
				
				if(n_position.equals("surrender")) {
					approved_move = true;
				}
				
				if(!approved_move && !treasonous_move) {
					String invalid_move = treasonous_move ? "Cannot move to ally-occupied space!" : "Invalid move!";
					System.out.println(invalid_move);
					System.out.println("Enter position to move to: ");
				}
			}
			if(approved_move = true && n_position.equals("surrender")) {
				this.gameOver = true;
				break;
				
			}
			// Update game board and print
			System.out.println("\n\n---------------------\n");
			this.board.updateBoard(colorTurn);
			this.board.printBoard();
			turn++;
			approved_piece = false;
			approved_move = false;
		}
		
		// Establish winner and exit
		String winner = (this.turn & 1) == 0 ? "Whites" : "Blacks";
		System.out.println("\n---------------------\n" + winner + " wins!");
		
		this.scanner.close();
	}
}