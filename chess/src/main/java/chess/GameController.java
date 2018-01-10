package chess;

import java.util.Scanner;

public class GameController {
	Board board;
	boolean gameOver;
	int turn;
	Scanner scanner;
	
	GameController(Board board) {
		this.board = board;
	}
	
	public void startGame() {
		this.board.printBoard();
		// Initialize game variables
		this.gameOver 			= false;
		this.turn 				= 1;
		this.scanner 			= new Scanner(System.in);
		String selectedPiece 	= "";
		String n_position 		= "";
		int o_position_X		= 0;
		int o_position_Y		= 0;
		boolean approved_piece	= false;
		boolean approved_move	= false;
		boolean treasonous_move = false;
		boolean inMap 			= false;
		boolean capture 		= false;
		int s_piece_idx			= 0;
		int c_piece_idx			= 0;
		
		// continue game until its over
		while(!this.gameOver && this.turn < 10) {
			
			// Declare who's turn it is
			String colorTurn = (this.turn & 1) == 0 ? "Black's" : "White's";
			System.out.println("\n" + colorTurn + "turn to move");
			colorTurn = colorTurn == "White's" ? "whites" : "blacks";
			
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
							o_position_X = this.board.getWhites().get(i).getX();
						}
					}
				} else {
					for(int i = 0; i < this.board.getBlacks().size(); i++) {
						if(selectedPiece.equals(this.board.getBlacks().get(i).getName())) {
							approved_piece = true;
							s_piece_idx = i;
							o_position_Y = this.board.getBlacks().get(i).getY();
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
				n_position = this.scanner.nextLine();
				
				// Make sure position given is inside the board map
				if(n_position.length() != 2) {
					System.out.print("Enter a valid coordinate: ");
					continue;
				}
				if(n_position.charAt(0) >= 'a' && n_position.charAt(0) <= 'h') {
					if(n_position.charAt(1) >= '1' && n_position.charAt(1) <= '8') {
						inMap = true;
						// More stuff to check. Check by who's turn it is
						if(colorTurn.equals("whites")) {
							for(int i = 0; i < this.board.getWhites().size(); i++) {
								if(this.board.getWhites().get(i).getPosition().equals(n_position)) {
									// not really, just lets me know if n_position corresponds to ally position
									System.out.println(this.board.getWhites().get(i));
									treasonous_move = true;
								}
							}
							// check if its a capture move
							for(int i = 0; i < this.board.getBlacks().size(); i++) {
								if(this.board.getBlacks().get(i).getPosition().equals(n_position)) {
									capture = true;
									c_piece_idx = i;
									break;
								}
							}
							if(!approved_move && !treasonous_move) {
								// if legal to move there, the move is approved
								approved_move = this.board.getWhites().get(s_piece_idx).move(n_position, capture);
							}
							// remove captured piece
							if(approved_move && capture) {
								this.board.getDestroyed().add(this.board.getBlacks().get(c_piece_idx));
								this.board.getBlacks().remove(c_piece_idx);
							}
							
							
						} else {
							for(int i = 0; i < this.board.getBlacks().size(); i++) {
								if(this.board.getBlacks().get(i).getPosition().equals(n_position)) {
									// not really, just lets me know if n_position corresponds to ally position
									treasonous_move = true;
								}
							}
							// check if its a capture move
							for(int i = 0; i < this.board.getWhites().size(); i++) {
								if(this.board.getBlacks().get(i).getPosition().equals(n_position)) {
									capture = true;
									c_piece_idx = i;
									break;
								}
							}
							// if n_position is an empty or enemy-occupied space, check if legal to move there
							if(!approved_move && !treasonous_move) {
								// if legal to move there, the move is approved
								approved_move = this.board.getBlacks().get(s_piece_idx).move(n_position, capture);
							}
							// remove captured piece
							if(approved_move && capture) {
								this.board.getDestroyed().add(this.board.getWhites().get(c_piece_idx));
								this.board.getWhites().remove(c_piece_idx);
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
				
				if(!approved_move) {
					String invalid_move = (treasonous_move && inMap) ? 
													"Cannot move to ally-occupied space!" : 
														"Invalid move!";
					System.out.println(invalid_move);
					System.out.println("Enter position to move to: ");
				}
			}
			if(approved_move) {
				System.out.println("Move approved");
				this.board.resetSpace(o_position_X, o_position_Y);
				// if Pawn has reached enemy last line... it can transform
				if(selectedPiece.charAt(1) == 'p' && (n_position.charAt(1) == 8 || n_position.charAt(1) == 1)) {
					// get what to transform to and transform Piece to said transformation
					System.out.print("Select Piece to transform to (K, Q, b, k, r): ");
					String transform_to = this.scanner.nextLine();
					boolean transformed = false;
					while(!transformed) {
						if(transform_to.charAt(0) == 'K' && transform_to.length() == 1) {
							if(colorTurn.equals("Whites")) {
								this.board.getWhites().get(s_piece_idx).setType("King");
								transformed = true;
							} else {
								this.board.getBlacks().get(s_piece_idx).setType("King");
								transformed = true;
							}
						} else if(transform_to.charAt(0) == 'Q' && transform_to.length() == 1) {
							if(colorTurn.equals("Whites")) {
								this.board.getWhites().get(s_piece_idx).setType("Queen");
								transformed = true;
							} else {
								this.board.getBlacks().get(s_piece_idx).setType("Queen");
								transformed = true;
							}
						} else if(transform_to.charAt(0) == 'b' && transform_to.length() == 1) {
							if(colorTurn.equals("Whites")) {
								this.board.getWhites().get(s_piece_idx).setType("Bishop");
								transformed = true;
							} else {
								this.board.getBlacks().get(s_piece_idx).setType("Bishop");
								transformed = true;
							}
						} else if(transform_to.charAt(0) == 'k' && transform_to.length() == 1) {
							if(colorTurn.equals("Whites")) {
								this.board.getWhites().get(s_piece_idx).setType("Knight");
								transformed = true;
							} else {
								this.board.getBlacks().get(s_piece_idx).setType("Knight");
								transformed = true;
							}
						} else if(transform_to.charAt(0) == 'r' && transform_to.length() == 1) {
							if(colorTurn.equals("Whites")) {
								this.board.getWhites().get(s_piece_idx).setType("Rook");
								transformed = true;
							} else {
								this.board.getBlacks().get(s_piece_idx).setType("Rook");
								transformed = true;
							}
						} else {
							System.out.print("Enter correct code (K, Q, b, k, r): ");
							transform_to = this.scanner.nextLine();
						}
					}
				}
			}
			if(approved_move = true && n_position.equals("surrender")) {
				this.gameOver = true;
				break;
				
			}
			// Update game board and print
			System.out.println("\n\n---------------------\n");
			for(int i = 0; i < this.board.getWhites().size(); i++) {
				System.out.println(this.board.getWhites().get(i));
			}
			//this.board.updateBoard(colorTurn);
			this.board.printBoard();
			turn++;
			// reset variables
			approved_piece = false;
			approved_move = false;
			inMap = false;
			capture = false;
			treasonous_move = false;
		}
		
		// Establish winner and exit
		String winner = (this.turn & 1) == 0 ? "Whites" : "Blacks";
		System.out.println("\n---------------------\n" + winner + " wins!");
		
		this.scanner.close();
	}
}
