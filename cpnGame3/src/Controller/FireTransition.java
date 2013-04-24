package Controller;

import java.util.ArrayList;

import Model.Arc;
import Model.Place;
import Model.Transition;

public class FireTransition {

	private ArrayList<Arc> inputArcs;
	
	public FireTransition() {
		
	}
	
	
	public ArrayList<Arc> getInputArcs() {
		return inputArcs;
	}
	
	public void setInputArcs(ArrayList<Arc> inputArcs) {
		this.inputArcs = inputArcs;
	}
	
}
