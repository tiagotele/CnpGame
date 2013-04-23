package Controller;

import java.util.ArrayList;

import Model.Arc;
import Model.Place;
import Model.Transition;

public class FireTransition {

	//atributos
	
	private ElementosCPN e;
	
	private Transition t;
	
	
	private ArrayList<PAT_Struct> pat_Struct;
	private ArrayList<TAP_Struct> tap_Struct;
	
	public FireTransition (ElementosCPN eCPN, Transition t, ArrayList<Place> placesPre, ArrayList<Place> placesPost) {
		setE(eCPN);
		
		setT(t);
//		setPlacesPre(placesPre);
//		setPlacesPost(placesPost);
//		
//		pat_Struct = arrumarPAT();
//		tap_Struct = arrumarTAP();
	}
	
	public void disparo () {
		int [][] matrizC = e.getMatrizC();
		
		//exemplo: [casa4,45]=-2
		
		boolean permitido = true;		
		
		Transition currentTransitionShowMatrizC;
		
		ArrayList<Place> placesPre = new ArrayList<Place>();
		ArrayList<Place> placesPost = new ArrayList<Place>();
		
		for (int p=0; p<matrizC.length && permitido; p++) {
//    		System.out.println();
    		Place currentPlace = (Place) e.getPlaces().get(p);
            String textPLace = currentPlace.getText();
    		for (int a=0; a<matrizC[p].length && permitido; a++) {
    			currentTransitionShowMatrizC = (Transition) e.getTransitions().get(a);
    			String textTrans = currentTransitionShowMatrizC.getText();
    			
    			//System.out.print("["+textPLace+","+textTrans+"]="+matrizC[p][a]+"\t");
    			
    			if (t.getText().equals(textTrans)) {
    				
    				if (matrizC[p][a]==1) {	//saindo da transicao
    					placesPost.add(currentPlace);
    				}
    				else {
    					if (matrizC[p][a]==(-1)) {	//entrando na transicao
    						placesPre.add(currentPlace);
        				}
    					else {
    						if (matrizC[p][a]==2) {	//entrando na transicao colorida
    							placesPost.add(currentPlace);    							
    	    				}
    						else {
    							if (matrizC[p][a]==(-2)) {	//saindo da transicao colorida
    								placesPre.add(currentPlace);
    		    				}
    							else {
    								if (matrizC[p][a]==0) {	//nao permitido
    			    					//do nothing (?)
    			    				}
    							}
    						}
    					}
    				}
    			}
    			
    			
    			//achar os arcos
    			ArrayList<Arc> arcosListPre = new ArrayList<Arc>();
    			ArrayList<Arc> arcosListPost = new ArrayList<Arc>();
    			for (int k=0; k<e.getArcs().size(); k++) {
    				Arc arcAux = e.getArcs().get(k);
    				if (arcAux.getOrientation().equals("PtoT")) {
    				////PAREI AQUI!
    					
    				}
    				else {
    					if (arcAux.getOrientation().equals("TtoP")) {
    						
    					}
    				}
    				
    			}
    			
    			
    			
    			//se eu nao tenho mais os places pre definidos, terei que procurá-los nos arcos
    			
//    			for (int k=0; k<placesPre.size() && permitido; k++) {
//    				if (placesPre.get(k).getText().equals(textPLace)  ) {
//    					// localizado!
//    					
//    					if (matrizC[p][a]==0) {
//    						permitido = false;
//    					}
//    					else {
//    						if (Math.abs(matrizC[p][a])==1) {
//    							///passou!
//    							permitido = true;
//    						}
//    						else {
//    							if (Math.abs(matrizC[p][a])==2) {
//        							///condição a ser vista na tabela
//        						}
//    						}
//    					}
//    						
//    					
//    				}
//    				
//    			}
    			
    		}
    	} 
		
		/**
		 for (int p=0; p<matrizC.length; p++) {
    		System.out.println();
    		Place currentPlace = (Place) places.get(p);
            String textPLace = currentPlace.getText();
    		for (int t=0; t<matrizC[p].length; t++) {
    			currentTransitionShowMatrizC = (Transition) transitions.get(t);
    			String textTrans = currentTransitionShowMatrizC.getText();
    			System.out.print("["+textPLace+","+textTrans+"]="+matrizC[p][t]+"\t");
    		}
    	} 
		 * */
		
//		
//		
//		for (int k=0; k< placeToPlace[inPlace].length; k++)
//			 if (placeToPlace[inPlace][k]==edgePlace) {
//				 System.out.println("Permitido de "+inPlace+" Para "+edgePlace);
//				 permitido = true;
//				 break;
//			 }
//		
		
		
		
		
		/****
		//para todo PAT, verificar se a quantidade de fichas satisfazem a condição do arco
		//-------->(arcos com funcoes ainda nao estao sendo testados)<--------
		boolean firstVerification = true;
		for (int i = 0; i<pat_Struct.size(); i++) {
			if () {}
		}
		
		
		//se sim, o conjunto de PAT's, verificar se a condição da transicao esta valida
		//se sim, retirar as cores do disparo e adicionar no place de destino, de
		//  acordo com as regras nos arcos dos TAP'S
		
		*/
		
	}
	
//	private ArrayList<PAT_Struct> arrumarPAT () {
//		
//		ArrayList<PAT_Struct> pat = new ArrayList<FireTransition.PAT_Struct>();
//		
//		for (int i = 0; i<e.getArcs().size(); i++) {
//			Arc arcAux = e.getArcs().get(i);
//			if (arcAux.getOrientation().equals("PtoT")) {
//				if (t.getText().equals(arcAux.getTransend())) {
//					
//					for (int j=0; j<placesPre.size(); j++) {
//						if (placesPre.get(j).getText().equals(arcAux.getPlaceend())) {
//							PAT_Struct p = new PAT_Struct(placesPre.get(j), arcAux, t);
//							pat.add(p);
//						}
//					}
//				}
//				
//				
//			}
//		}
//		
//		return pat;
//		
//	}
//	
//	private ArrayList<TAP_Struct> arrumarTAP () {
//		
//		ArrayList<TAP_Struct> tap = new ArrayList<FireTransition.TAP_Struct>();
//		
//		for (int i = 0; i<e.getArcs().size(); i++) {
//			Arc arcAux = e.getArcs().get(i);
//			if (arcAux.getOrientation().equals("TtoP")) {
//				if (t.getText().equals(arcAux.getTransend())) {
//					
//					for (int j=0; j<placesPost.size(); j++) {
//						if (placesPost.get(j).getText().equals(arcAux.getPlaceend())) {
//							TAP_Struct p = new TAP_Struct(t, arcAux, placesPre.get(j));
//							tap.add(p);
//						}
//					}
//				}
//				
//				
//			}
//		}
//		
//		return tap;
//		
//	}
	
	private Place getPlaceWithId (String id) {
		ArrayList<Place> pls = e.getPlaces();
		for (int i = 0; i<pls.size(); i++) {
			if (pls.get(i).getText().equals(id)) {
				return pls.get(i);
			}
		}
		
		return null;
	}
	
	private void quantidadeFichas () {
		//verificar se a quantidade de fichas corresponde ao que tem no arco e ao que tem na transicao
	}
	
	private void condicaoTransicao () {
		t.getCond();
	}
	
	private void condicaoSaida () {}
	
	public ElementosCPN getE() {
		return e;
	}
	
	public void setE(ElementosCPN e) {
		this.e = e;
	}
	
	public Transition getT() {
		return t;
	}
	
	public void setT(Transition t) {
		this.t = t;
	}
	
//	public ArrayList<Place> getPlacesPost() {
//		return placesPost;
//	}
//	
//	private void setPlacesPost(ArrayList<Place> placesPost) {
//		this.placesPost = placesPost;
//	}
//	
//	public ArrayList<Place> getPlacesPre() {
//		return placesPre;
//	}
//	
//	private void setPlacesPre(ArrayList<Place> placesPre) {
//		this.placesPre = placesPre;
//	}
//	
	
	class PAT_Struct {	// estrutura Place-Arc-Transition
		private Place p;
		private Arc a;
		private Transition t;
		
		public PAT_Struct(Place place, Arc arc, Transition trans) {
			setP(place);
			setA(arc);
			setT(trans);
		}
		
		
		public Place getP() {
			return p;
		}
		
		public void setP(Place p) {
			this.p = p;
		}

		public Arc getA() {
			return a;
		}
		
		public void setA(Arc a) {
			this.a = a;
		}
		
		public Transition getT() {
			return t;
		}
		
		public void setT(Transition t) {
			this.t = t;
		}
	}
	
	class TAP_Struct {	// estrutura Place-Arc-Transition
		private Place p;
		private Arc a;
		private Transition t;
		
		public TAP_Struct(Transition trans, Arc arc, Place place) {
			setP(place);
			setA(arc);
			setT(trans);
		}
		
		
		public Place getP() {
			return p;
		}
		
		public void setP(Place p) {
			this.p = p;
		}

		public Arc getA() {
			return a;
		}
		
		public void setA(Arc a) {
			this.a = a;
		}
		
		public Transition getT() {
			return t;
		}
		
		public void setT(Transition t) {
			this.t = t;
		}
	}
}
