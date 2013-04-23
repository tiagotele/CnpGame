package Model;

public class Transition {

	private String id;
	private String text;
	private String cond;
	
	public String getCond() {
		return cond;
	}
	
	public void setCond(String cond) {
		this.cond = cond;
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
	
	public Transition (String id, String text, String cond) {
		setId(id);
		setText(text);
		setCond(cond);
	}
	
	@Override
	public String toString() {
		return "Transition [id=" + id + ", text=" + text + ", cond = "+ cond + "]";
	}
	
}
