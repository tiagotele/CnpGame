package Model;

import java.util.ArrayList;

public class UnitDeclatarion {

	private String ID;
	private String varCol;	//colset ou variável
	private String nome;	// exemplo: "Bombeiro"
	private String tipo;	// int ; product ; ...
	private ArrayList<String> elementosProduct;
	
	public UnitDeclatarion (String id, String valOuColset, String nome, String type, ArrayList<String> complemento) {
		this.ID = id;
		this.varCol = valOuColset;
		this.nome = nome;
		this.tipo = type;
		this.elementosProduct = complemento;
	}
	
	public UnitDeclatarion (String id, String valOuColset, String nome, String type, String... complemento) {
		this.ID = id;
		this.varCol = valOuColset;
		this.nome = nome;
		this.tipo = type;

		this.elementosProduct = new ArrayList<String>();
		for (int i=0; i<complemento.length; i++)
			elementosProduct.add(complemento[i]);
		
	}
	
	public String getID() {
		return ID;
	}
	
	public ArrayList<String> getElementosProduct() {
		return elementosProduct;
	}

	public String getVarCol() {
		return varCol;
	}

	public String getNome() {
		return nome;
	}

	public String getTipo() {
		return tipo;
	}
	
}
