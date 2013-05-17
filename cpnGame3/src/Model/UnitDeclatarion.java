package Model;

import java.util.ArrayList;

public class UnitDeclatarion {

	private String nome;	// exemplo: "Bombeiro"
	private ArrayList<String> tipo;	// int ; product ; ...
	
	public UnitDeclatarion (String nome, String t) {
		this.nome = nome;
		this.tipo = new ArrayList<String>();
		this.tipo.add(t);
	}
	
	public UnitDeclatarion (String nome, String... t) {
		this.nome = nome;
		this.tipo = new ArrayList<String>();
		
		for (int i=0; i<t.length; i++)
			tipo.add(t[i]);
	}
	
	public UnitDeclatarion (String nome, ArrayList<String> t) {
		this.nome = nome;
		this.tipo = t;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public ArrayList<String> getTipo() {
		return tipo;
	}
	
	public void setTipo(ArrayList<String> tipo) {
		this.tipo = tipo;
	}
	
		
}
