package chess;

import java.util.ArrayList;

public class Board {
	private ArrayList<ArrayList<String>> coords;
	Piece wp1; Piece bp1;
	Piece wp2; Piece bp2;
	Piece wp3; Piece bp3;
	Piece wp4; Piece bp4;
	Piece wp5; Piece bp5;
	Piece wp6; Piece bp6;
	Piece wp7; Piece bp7;
	Piece wp8; Piece bp8;
	
	Piece wr1; Piece br1;
	Piece wr2; Piece br2;
	
	Piece wk1; Piece bk1;
	Piece wk2; Piece bk2;
	
	Piece wb1; Piece bb1;
	Piece wb2; Piece bb2;
	
	Piece wq; Piece bq;
	Piece wK; Piece bK;
	
	Board() {
		this.coords = new ArrayList<ArrayList<String>>();
		
		ArrayList<String> a = new ArrayList<String>();
		ArrayList<String> b = new ArrayList<String>();
		ArrayList<String> c = new ArrayList<String>();
		ArrayList<String> d = new ArrayList<String>();
		ArrayList<String> e = new ArrayList<String>();
		ArrayList<String> f = new ArrayList<String>();
		ArrayList<String> g = new ArrayList<String>();
		ArrayList<String> h = new ArrayList<String>();
		ArrayList<String> tmp = new ArrayList<String>();
		for(int i = 0; i < 8; i++) {
			a.add("++"); b.add("++"); c.add("++"); d.add("++");
			e.add("++"); f.add("++"); g.add("++"); h.add("++");
		}
		a.add(" a"); b.add(" b"); c.add(" c"); d.add(" d"); 
		e.add(" e"); f.add(" f"); g.add(" g"); h.add(" h"); 

		for(int i = 0; i < 8; i++) {
			tmp.add(" " + (char)(49 + i));
		}
		tmp.add(" " + (char)248);
		
		this.coords.add(h);
		this.coords.add(g);
		this.coords.add(f);
		this.coords.add(e);
		this.coords.add(d);
		this.coords.add(c);
		this.coords.add(b);
		this.coords.add(a);
		this.coords.add(tmp);
		
		setNewGame();
	}
	
	public void printBoard() {
		updateBoard();
		for(int i = 0; i < this.coords.size(); i++) {
			for(int j = 0; j < this.coords.get(i).size(); j++) {
				System.out.print(this.coords.get(i).get(j));
				System.out.print(" ");
			}
			System.out.println(" ");
		}
	}
	
	private void setNewGame() {
		this.wp1 = new Piece("wp", "wp1"); this.bp1 = new Piece("bp", "bp1");
		this.wp2 = new Piece("wp", "wp2"); this.bp2 = new Piece("bp", "bp2");
		this.wp3 = new Piece("wp", "wp3"); this.bp3 = new Piece("bp", "bp3");
		this.wp4 = new Piece("wp", "wp4"); this.bp4 = new Piece("bp", "bp4");
		this.wp5 = new Piece("wp", "wp5"); this.bp5 = new Piece("bp", "bp5");
		this.wp6 = new Piece("wp", "wp6"); this.bp6 = new Piece("bp", "bp6");
		this.wp7 = new Piece("wp", "wp7"); this.bp7 = new Piece("bp", "bp7");
		this.wp8 = new Piece("wp", "wp8"); this.bp8 = new Piece("bp", "bp8");
		
		this.wr1 = new Piece("wr", "wr1"); this.br1 = new Piece("br", "br1");
		this.wr2 = new Piece("wr", "wr2"); this.br2 = new Piece("br", "br2");
		
		this.wk1 = new Piece("wk", "wk1"); this.bk1 = new Piece("bk", "bk1");
		this.wk2 = new Piece("wk", "wk2"); this.bk2 = new Piece("bk", "bk2");
		
		this.wb1 = new Piece("wb", "wb1"); this.bb1 = new Piece("bb", "bb1");
		this.wb2 = new Piece("wb", "wb2"); this.bb2 = new Piece("bb", "bb2");
		
		this.wq = new Piece("wq", "wq"); this.bq = new Piece("bq", "bq");
		this.wK = new Piece("wK", "wK"); this.bK = new Piece("bK", "bK");
		
		this.wp1.setPosition("b1"); this.bp1.setPosition("g1"); 
		this.wp2.setPosition("b2"); this.bp2.setPosition("g2");
		this.wp3.setPosition("b3"); this.bp3.setPosition("g3");
		this.wp4.setPosition("b4"); this.bp4.setPosition("g4");
		this.wp5.setPosition("b5"); this.bp5.setPosition("g5");
		this.wp6.setPosition("b6"); this.bp6.setPosition("g6");
		this.wp7.setPosition("b7"); this.bp7.setPosition("g7");
		this.wp8.setPosition("b8"); this.bp8.setPosition("g8");
		
		this.wr1.setPosition("a1"); this.br1.setPosition("h1");
		this.wr2.setPosition("a8"); this.br2.setPosition("h8");
		
		this.wk1.setPosition("a2"); this.bk1.setPosition("g2"); 
		this.wk2.setPosition("a7"); this.bk2.setPosition("g7");
		
		this.wb1.setPosition("a3"); this.bb1.setPosition("h3");
		this.wb2.setPosition("a6"); this.bb1.setPosition("h6");
		
		this.wq.setPosition("a4"); this.bq.setPosition("h5");
		this.wK.setPosition("a5"); this.wK.setPosition("h4");
	}
	
	public void updateBoard() {
		
	}
}