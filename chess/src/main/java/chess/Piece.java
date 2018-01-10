package chess;

import java.util.ArrayList;

public class Piece {
	private String type;
	private String name;
	private String position;
	private String color;
	private int hasMoved = 0;
	
	Piece(String type, String name, String color) {
		this.type	= type;
		this.name	= name;
		this.color	= color;
	}
	
	public void setPosition(String n_position) {
		this.position = n_position;
	}
	
	public String getPosition() {
		return this.position;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getX() {
		return 7 - 104 + (int)(this.position.charAt(0));
		
	}
	
	public int getY() {
		return 7 - (int)(this.position.charAt(1)) + 49;
	}
	
	public String getType() {
		return this.type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String toString(){
		return this.color + " " + this.type + ": " + this.name + " at: " + this.position;
	}
	
	public boolean move(String n_position, boolean capture, ArrayList<Piece> allies, ArrayList<Piece> enemies) {
		int forward;
		int forward_2;
		boolean done_goofed = false;
		switch (this.type) {
		// Set possible moves for a pawn
		case "Pawn": 
			// set if 'forward' means up or down the board
			forward = this.color.equals("White") ? 1 : -1;
			forward_2 = this.color.equals("White") ? 2 : -2;
			// If the move is not a capture...
			if(!capture) {
				// Allow a double step as first move for pawn
				if((int)n_position.charAt(1) == (int)this.position.charAt(1) + forward_2 && 
						n_position.charAt(0) == this.position.charAt(0) && this.hasMoved == 0) {
					this.position = n_position;
					this.hasMoved++;
					return true;
				}
				// Allow pawn to move forward
				if((int)n_position.charAt(1) == (int)this.position.charAt(1) + forward &&
						n_position.charAt(0) == this.position.charAt(0)) {
					this.position = n_position;
					this.hasMoved++;
					return true;				
				}
			}
			// If the move is a capture...
			if(capture) {
				if((int)n_position.charAt(1) == (int)this.position.charAt(1) + forward &&
						((int)n_position.charAt(0) == (int)this.position.charAt(0) + 1 ||
								(int)n_position.charAt(0) == (int)this.position.charAt(0) - 1)) {
					this.position = n_position;
					this.hasMoved++;
					return true;
				}
			}
			// cannot move to that position
			return false;
		
		case "Rook":
			// Rook can only move horizontally or vertically and cannot jump
			String old_position = this.position;
			// See if Rook moves vertically
			if(n_position.charAt(0) == this.position.charAt(0)) {
				forward = this.position.charAt(1) < n_position.charAt(1) ? 1 : -1;
				while(!n_position.equals(this.position)) {
					// check if Rook passes through ally Pieces on its way to its new position
					for(int i = 0; i < allies.size(); i++) {
						if(allies.get(i).getPosition().equals(this.position) &&
								!allies.get(i).getName().equals(this.name)) {
							// If Rook do, he done goofed
							done_goofed = true;
						}
					}
					// check if Rook passes through enemy Pieces on its way to its new position
					for(int i = 0; i < enemies.size() && !done_goofed; i++) {
						if(enemies.get(i).getPosition().equals(this.position)) {
							// If Rook do, he done goofed
							done_goofed = true;
						}
					}
					// If done goofed, return Rook to original position and let GameController know the move failed
					if(done_goofed) {
						this.position = old_position;
						return false;
					} else {
						// If not done goofed, move a step closer (vertically) to new position and check again
						this.position = "" + this.position.charAt(0) + (char)(this.position.charAt(1) + forward);
					}
				}
				return true;
			}
			// See if Rook moves horizontally
			if(n_position.charAt(1) == this.position.charAt(1)) {
				forward = this.position.charAt(0) < n_position.charAt(0) ? 1 : -1;
				while(!n_position.equals(this.position)) {
					// check if Rook passes through ally Pieces on its way to its new position
					for(int i = 0; i < allies.size(); i++) {
						if(allies.get(i).getPosition().equals(this.position) &&
								!allies.get(i).getName().equals(this.name)) {
							// If Rook do, he done goofed
							done_goofed = true;
						}
					}
					// check if Rook passes through enemy Pieces on its way to its new position
					for(int i = 0; i < enemies.size() && !done_goofed; i++) {
						if(enemies.get(i).getPosition().equals(this.position)) {
							// If Rook do, he done goofed
							done_goofed = true;
						}
					}
					// If done goofed, return Rook to original position and let Game Controller know the move failed
					if(done_goofed) {
						this.position = old_position;
						return false;
					} else {
						// If not done goofed, move a step closer (horizontally) to new position and check again
						this.position = "" + (char)(this.position.charAt(0) + forward) + this.position.charAt(1);
					}
				}
				return true;
			}
		}
		return false;
	}
}
