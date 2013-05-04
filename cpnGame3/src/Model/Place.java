package Model;
import java.util.*;

import ClassesAuxiliares.MyInt;

public class Place {
	
	private String id;
	private String text;
	private String initmark;
	private String type;
	private int x;
	private int y;
	private ArrayList<Place> saida;
	private ArrayList<MyInt> pass;
	
	ArrayList<Token> tokens;
	Token tokenTeste;
	
	
	public Place (String id, String text, String initmark, String type, int x, int y ) {
		setId (id);
		setText(text);
		setInitmark(initmark);
		setType(type);
		setX(x);
		setY(y);
		
		//this.tipoFicha = tipoDeFicha;
		tokens = new ArrayList<Token>();
		
		Token t = new Token(type, initmark);
		tokens.add(t);
	}
	
	//teste inicial com fichas coloridas
	public void addTokens (Token f) {
		tokens.add(f);
	}

	//teste inicial com fichas coloridas
	public void addTokens (ArrayList<Token> f) {
		for (int i = 0; i<f.size(); i++)
				tokens.add(f.get(i));
	}

	//teste inicial com fichas coloridas
	public void addTokens (String tipo, String val) {
		Token f = new Token(tipo, val);
		tokens.add(f);
	}

	//teste inicial com fichas coloridas	
	public ArrayList<Token> getTodasOsTokens () {
		return tokens;
	}
	
	//teste inicial com fichas coloridas	
	public Token getTokenNaPosicaoX (int X) {
		return tokens.get(X);
	}
	
	//teste inicial com fichas coloridas	
	public ArrayList<Token> getFichasDoTipoX (String X) {
		ArrayList<Token> fichasDoMesmoTipo = new ArrayList<Token>();
		
		for (int i = 0; i<tokens.size(); i++)
			fichasDoMesmoTipo.add(tokens.get(i));
		
		if (fichasDoMesmoTipo.size()==0)
			return null;
		else
			return fichasDoMesmoTipo;
	}
	
	public ArrayList<MyInt> getPass() {
		return pass;
	}
	
	public void setPass(ArrayList<MyInt> pass) {
		this.pass = pass;
	}
	
	public ArrayList<Place> getSaida() {
		return saida;
	}
	
	public void setSaida(ArrayList<Place> saida) {
		this.saida = saida;
	}

	private int relativeTop;
	
	private int relativeLeft;
	
	
	public int getRelativeTop() {
		return relativeTop;
	}
	public void setRelativeTop(int relativeTop) {
		this.relativeTop = relativeTop;
	}
	public int getRelativeLeft() {
		return relativeLeft;
	}
	public void setRelativeLeft(int relativeLeft) {
		this.relativeLeft = relativeLeft;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public String getInitmark() {
		return initmark;
	}
	public void setInitmark(String initmark) {
		this.initmark = initmark.trim();
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type.trim();
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id.substring(2); //retira a substring ID
	}
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	

	
	@Override
	public String toString() {
		String place = "Place [id =" + getId() + ", " +
			      "text = " + getText() + ", " +
			      "initmark = " + getInitmark() + ", "+ 
			      "type = " + getType() + ", "+ 
			      "x = " + getX() + ", " +
			      "y = " + getY() +"]";
		
		String tokensPlace = "";
		
		for (Token i : tokens) {
			tokensPlace = tokensPlace + " Token "+ i.toString() + "\n";
		}
		
		
		return place+tokensPlace;
	}
	
}
