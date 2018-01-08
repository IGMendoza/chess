package chess;

public class Piece {
	private String type;
	private String name;
	private String position;
	private String color;
	
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
		return (int)(this.position.charAt(1)) - 49;
	}
	
	public int getY() {
		return 104 - (int)(this.position.charAt(0));
	}
	
	public String getType() {
		return this.type;
	}
	
	public String toString(){
		return this.color + " " + this.type + ": " + this.name + " at: " + this.position;
	}
}
