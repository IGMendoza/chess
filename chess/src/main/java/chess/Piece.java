package chess;

public class Piece {
	private String type;
	private String name;
	private String position;
	
	Piece(String type, String name) {
		this.type = type;
		this.name = name;
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
}