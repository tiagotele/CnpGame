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
	
	
	
}
