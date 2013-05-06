package Model;

import java.util.ArrayList;

public class StructDeclarations {
	
	private ArrayList<UnitDeclatarion> declaracoes;
	
	public StructDeclarations () {
		this.declaracoes = new ArrayList<UnitDeclatarion>();
	}

	public ArrayList<UnitDeclatarion> getDeclaracoes() {
		return declaracoes;
	}

	public void setDeclaracoes(ArrayList<UnitDeclatarion> declaracoes) {
		this.declaracoes = declaracoes;
	}

	public void addDeclaracoes (UnitDeclatarion ud) {
		declaracoes.add(ud);
	}
	
	public void addDeclaracoes (String id, String valOuColset, String nome, String type, ArrayList<String> complemento) {
		UnitDeclatarion ud = new UnitDeclatarion(id, valOuColset, nome, type, complemento);
		declaracoes.add(ud);
	}
	
	public void addDeclaracoes (String id, String valOuColset, String nome, String type, String... complemento) {
		UnitDeclatarion ud = new UnitDeclatarion(id, valOuColset, nome, type, complemento);
		declaracoes.add(ud);
	}
	
}
