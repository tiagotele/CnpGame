package Model;
import java.util.*;

import ClassesAuxiliares.MyInt;

public class Place {
	
	private String id;
	private String text;
	private String initmark;
	private int x;
	private int y;
	private ArrayList<Place> saida;
	private ArrayList<MyInt> pass;
	
	private String tipoFicha;
	ArrayList<Ficha> fichas;
	Ficha fTeste;
	
	public Place (String id, String text, /*String tipoDeFicha, */String initmark, int x, int y ) {
		setId (id);
		setText(text);
		setInitmark(initmark);
		setX(x);
		setY(y);
		
		//this.tipoFicha = tipoDeFicha;
		fichas = new ArrayList<Ficha>();
		fTeste = new Ficha("lala", "lele");
	}
	
	//teste inicial com fichas coloridas
	public void addFichas (Ficha f) {
		fichas.add(f);
	}

	//teste inicial com fichas coloridas
	public void addFichas (ArrayList<Ficha> f) {
		for (int i = 0; i<f.size(); i++)
				fichas.add(f.get(i));
	}

	//teste inicial com fichas coloridas
	public void addFichas (String tipo, String nome) {
		Ficha f = new Ficha(tipo, nome);
		fichas.add(f);
	}

	//teste inicial com fichas coloridas	
	public ArrayList<Ficha> getTodasAsFichas () {
		return fichas;
	}
	
	//teste inicial com fichas coloridas	
	public Ficha getFichaNaPosicaoX (int X) {
		return fichas.get(X);
	}
	
	//teste inicial com fichas coloridas	
	public ArrayList<Ficha> getFichasDoTipoX (String X) {
		ArrayList<Ficha> fichasDoMesmoTipo = new ArrayList<Ficha>();
		
		for (int i = 0; i<fichas.size(); i++)
			fichasDoMesmoTipo.add(fichas.get(i));
		
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
		this.initmark = initmark.substring(0, initmark.length()-2);
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
		return "Place [id =" + getId() + ", " +
				      "text = " + getText() + ", " +
				      "initmark = " + getInitmark() + ", "+ 
				      "x = " + getX() + ", " +
				      "y = " + getY() +"]";
	}
	
}
