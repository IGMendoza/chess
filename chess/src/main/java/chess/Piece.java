package chess;

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
	
	public boolean move(String n_position, boolean capture) {
		switch (this.type) {
		// Set possible moves for a pawn
		case "Pawn": 
			// set if 'forward' means up or down the board
			int forward = this.color.equals("White") ? 1 : -1;
			int forward_2 = this.color.equals("White") ? 2 : -2;
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
				if((int)n_position.charAt(1) == (int)this.position.charAt(1) + 1 &&
						((int)n_position.charAt(0) == (int)this.position.charAt(0) + 1 ||
								(int)n_position.charAt(0) == (int)this.position.charAt(0) - 1)) {
					this.position = n_position;
					this.hasMoved++;
					return true;
				}
			}
			return false;
		}
		return false;
	}
}
