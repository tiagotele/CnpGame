package Model;

import java.util.ArrayList;

public class Token {
	
	//propriedades de uma ficha
	private String type;	//tipo da ficha: INT, ...
	private String val;	//nome da ficha
	private int quant;
	
	public Token (String type, String val) {
		this.type = type;
		
		splitToken(val);
	}

	private void splitToken (String val) {
		String aux[] = val.split("`");
		
		//teste
		System.out.println("+++teeste split");
		for (int i=0;i<aux.length;i++)
			System.out.println("aux["+i+"]: "+aux[i]);
		
		this.quant = Integer.parseInt(aux[0]);
		this.val = aux[1];		
	} 
	
	public int getQuant() {
		return quant;
	}

	public void setQuant(int quant) {
		this.quant = quant;
	}

	public String getType() {
		return type;
	}

	public String getVal() {
		return val;
	}
	
	@Override
	public String toString() {
		return "[type =" + getType() + ", " +
				"quant = " + getQuant() + ", "+ 
			      "val = " + getVal() +"]";
	}
	
}
