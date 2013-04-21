package Model;

public class Arc {
	private String id;
	private String orientation;
	private String transend; // idRef
	private String placeend; // idRef
	private String text;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id.substring(2); // retira a substring ID
	}

	public String getOrientation() {
		return orientation;
	}

	public void setOrientation(String orientation) {
		this.orientation = orientation;
	}

	public String getTransend() {
		return transend;
	}

	public void setTransend(String transend) {
		this.transend = transend.substring(2); // retira a substring ID
	}

	public String getPlaceend() {
		return placeend;
	}

	public void setPlaceend(String placeend) {
		this.placeend = placeend.substring(2); // retira a substring ID
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		// System.out.println(text);
		String aux = "";
		
		if (text.startsWith("(")) { //exemplo: cor = (b,a)
			String aux2 = text.substring(1, text.length()-1);
			System.out.println("aux2 == "+aux2);
			aux = aux2;

		} else {

			if (text.length() < 2) // exemplo: cor == b
				aux = text;
			else
				aux = text.substring(0, text.length() - 2);	//restante. exemplo: cor = 1`1
		
		}
		
		this.text = aux;
	}

	@Override
	public String toString() {
		return "Arc [id=" + id + ", transend=" + transend + ", placeend="
				+ placeend + ", text=" + text + ", orientation=" + orientation
				+ "]";
	}

	public Arc(String id, String orientation, String transend, String placeend,
			String text) {
		setId(id);
		setOrientation(orientation);
		setTransend(transend);
		setPlaceend(placeend);
		setText(text);
	}

	public Arc() {
		this("", "", "", "", "");
	}

}
