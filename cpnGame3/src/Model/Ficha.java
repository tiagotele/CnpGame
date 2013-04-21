package Model;

import java.util.ArrayList;

public class Ficha {
	
	//propriedades de uma ficha
	private String type;	//tipo da ficha: INT, ...
	private String nome;	//nome da ficha
	
	
	public Ficha (String type, String name) {
		this.type = type;
		this.nome = name;
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getType() {
		return type;
	}
	
}
