package Controller;

import java.util.*;

import ClassesAuxiliares.MyInt;
import Model.Arc;
import Model.Place;
import Model.Transition;



public class ElementosCPN {
	
	ArrayList<Place> places;
	ArrayList<Transition> transitions;
	ArrayList<Arc> arcs;
	
	private int matrizPre  [][];
	private int matrizPost [][];
	private int matrizC    [][];
	
	private int placeToPlace [][];
	
	//elementos da rede
	Arc arc;
	Arc currentArc;
	Transition transitionShow;
	Transition  currentTransitionShowMatrizesPost;
	Transition  currentTransitionShowMatrizesPre;
	Transition  currentTransitionShowMatrizC;
	Transition  currentTransitionFillM;
	
	MyInt val1;
	MyInt val2;
	
	CpnXmlReader reader;
	
	public int [][] getPlaceToPlace() {
		return placeToPlace;
	} 
	
	
	private void fillSaidaPlace() {
				
		for (int p=0; p<matrizC.length; p++) {
			Place currentPlace = (Place) places.get(p);
			ArrayList<Place> saida = new ArrayList<Place>();
			ArrayList<MyInt> pass = new ArrayList<MyInt>();
			for (int t=0; t<matrizC[p].length; t++) {
				if (matrizC[p][t]<0)
					for (int k=0; k<matrizC.length;k++)
						if (matrizC[k][t]>0) {
								saida.add(places.get(k));
								val1 = new MyInt (k);
								pass.add(val1);
						}		

			}
			currentPlace.setSaida(saida);
			currentPlace.setPass(pass);
		}
		
		placeToPlace = new int[places.size()][];
		for (int i=0; i<places.size(); i++) {
			Place currentPlace = (Place) places.get(i);
			placeToPlace[i] = new int[currentPlace.getPass().size()];
			
			System.out.println("Principal -> "+currentPlace.getText());
			for (int j=0; j<currentPlace.getPass().size(); j++){
				val2 = (MyInt)currentPlace.getPass().get(j);
				Place saidaPlace = (Place) places.get(val2.getX());
				placeToPlace[i][j]=val2.getX();
				System.out.println("Saida -> "+saidaPlace.getText());
			}
		}
		
	}
	
	
	
	private void ordCrescente (int vet [ ]) {
		int aux = 0;
        for (int i = vet.length-1; i>=0; i--)
			for (int j = 0; j<i; j++ )
			 if (vet[j] > vet[j+1]) {
				aux = vet[j+1];
			    vet[j+1] = vet [j];  
			    vet[j] = aux;
			 }   
	}
	
	private void ordDecrescente (int vet [ ]) {
		int aux = 0;
        for (int i = vet.length-1; i>=0; i--)
			for (int j = 0; j<i; j++ )
			 if (vet[j] < vet[j+1]) {
				aux = vet[j+1];
			    vet[j+1] = vet [j];  
			    vet[j] = aux;
			 }   
	}
	
	
	private int[] vetXOrd () {
        Place currentPlace;
        int vet[] = new int[places.size()];
		for (int i=0; i<places.size(); i++) {
			currentPlace = (Place) places.get(i);
            vet[i] = currentPlace.getX(); 
		}
		ordCrescente (vet);
		return vet;
	}
	
	
	private int[] vetYOrd () {
        Place currentPlace;
        int vet[] = new int[places.size()];
		for (int i=0; i<places.size(); i++) {
			currentPlace = (Place) places.get(i);
            vet[i] = currentPlace.getY(); 
		}
		ordDecrescente (vet);
		return vet;
	}
	
	
	private void geraTopList() {
		Place currentPlace;
   	 	int vetY[] = vetYOrd ();
   	
		for (int i=0; i<places.size(); i++) {
  			currentPlace = (Place) places.get(i);
  			int index = 0;
  			int anterior = vetY[0]; 
  			for (int j = 0; j<vetY.length; j++) {
  				if (anterior!=vetY[j])
  					 index++;
                 if (vetY[j]==currentPlace.getY() ) { 
                 	currentPlace.setRelativeTop(index);
                 	break;
                 }	
                 anterior=vetY[j];
          }
        }
	}
	
	
	
	private void geraLeftList() {
		Place currentPlace;
   	 	int vetX[] = vetXOrd ();
   	
		for (int i=0; i<places.size(); i++) {
  			currentPlace = (Place) places.get(i);
  			int index = 0;
  			int anterior = vetX[0]; 
  			for (int j = 0; j<vetX.length; j++) {
  				if (anterior!=vetX[j])
  					 index++;
                 if (vetX[j]==currentPlace.getX() ) { 
                 	currentPlace.setRelativeLeft(index);
                 	break;
                 }	
                 anterior=vetX[j];
          }
        }
	}
	
	public void showRelativeTopLeft() {
		Place currentPlace = null;
		for (int i=0; i<places.size(); i++) {
			currentPlace = (Place) places.get(i);
			System.out.println(currentPlace.getText() + ", "+
							   "("+currentPlace.getX() + ", "+ currentPlace.getY() +")  "+ 
							   "("+currentPlace.getRelativeLeft() + ", "
					              +currentPlace.getRelativeTop() +")" );
		}
	}
	
	
	
			
	
    public ArrayList<Place> getPlaces() {
		return places;
	}
	

	public ArrayList<Transition> getTransitions() {
		return transitions;
	}

	public ArrayList<Arc> getArcs() {
		return arcs;
	}
	
	public int[][] getMatrizPre() {
		return matrizPre;
	}
	
	public int[][] getMatrizPost() {
		return matrizPost;
	}
	
	public int[][] getMatrizC() {
		return matrizC;
	}
	
	public ElementosCPN() {  
    try {  
      	
       // informe o caminho correto do seu arquivo xm cpn tools
//      reader = new CpnXmlReader("CPN_testes/GameTesteArtigoColorida.cpn" ); 	//teste
      reader = new CpnXmlReader("CPN_testes/GameTeste.cpn" ); 	//teste  

       
       /** quando tiver a interface de escolha da rede, colocar o
           caminho absoluto na variável "path"
       */
       //String path = "";
       //CpnXmlReader reader = new CpnXmlReader( path );
       
        places = reader.lerPlaces();  
        transitions = reader.lerTransitions();        
        arcs = reader.lerArcs();    
       
        geraLeftList();
        geraTopList();
        
        fillMatrizes(); 
        
        fillSaidaPlace();
        
        
        
    } catch( Exception e ) {  
        e.printStackTrace();  
    }  
}  
  
	//(erro da leitura das fichas coloridas para preencher a matriz): erro resolvido
    private void fillMatrizes() {
    	matrizPost  = new int[places.size()][transitions.size()];
        matrizPre  = new int[places.size()][transitions.size()];
        matrizC    = new int[places.size()][transitions.size()];
  
  /*  	System.out.println("imprimir os arcos: ");
    	for(int v = 0; v<arcs.size();v++)
    		System.out.println("arco "+v+": "+arcs.get(v));
    	System.out.println("-----------");
    */	
    	for (int p=0; p<matrizPost.length; p++) {
    			Place currentPlace = (Place) places.get(p);
    		for (int t=0; t<matrizPost[p].length; t++) {
    			currentTransitionFillM = (Transition) transitions.get(t);
    			String idPlace = currentPlace.getId();
    			String idTrans = currentTransitionFillM.getId();
    			 
    			 
    			matrizPost[p][t] =0;
    			matrizPre [p][t] =0;
    			for (int a=0; a< arcs.size(); a++ ) {
    				 currentArc = (Arc) arcs.get( a );
    				 String transend = currentArc.getTransend();
    				 String placeend = currentArc.getPlaceend();
    				 String orientation = currentArc.getOrientation();
    				 String textoArco = currentArc.getText();
    				 if (textoArco.contains("`"))
    					 textoArco = textoArco.substring(0, textoArco.indexOf("`"));
    				 
    				// System.out.println("texto do current arc em "+a+": "+textoArco);
    				 
    				 if ( idTrans.equals(transend) && idPlace.equals(placeend)) {
    					 if (orientation.equals("TtoP"))
    					    matrizPost[p][t]= Integer.parseInt(textoArco);
    					 else if (orientation.equals("PtoT")) {
//    						 if (  textoArco )
    							 matrizPre[p][t]= Integer.parseInt(textoArco);
    					 }
    					  
    				 }   
    			}
    			matrizC[p][t]=matrizPost[p][t]-matrizPre[p][t];
    			 
    		}
    	}
    	
    	
    }      
    
    
    public void showMatrizC() { 
    	System.out.println("\n*** Matriz C ***");
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
    	System.out.println();
    	System.out.println();
    }
      
    public void showMatrizPre() {
    	System.out.println("\n*** Matriz Pre ***");
    	for (int p=0; p<matrizPre.length; p++) {
    		System.out.println();
    		Place currentPlace = (Place) places.get(p);
            String textPLace = currentPlace.getText();
    		for (int t=0; t<matrizPre[p].length; t++) {
    			currentTransitionShowMatrizesPre = (Transition) transitions.get(t);
    			String textTrans = currentTransitionShowMatrizesPre.getText();
    			System.out.print("["+textPLace+","+textTrans+"]="+matrizPre[p][t]+"\t");
    		}
    	}
    	System.out.println();
    	System.out.println();
    }
    
    public void showMatrizPost() {
    	System.out.println("\n*** Matriz Post ***");
    	for (int p=0; p<matrizPost.length; p++) {
    		System.out.println();
    		Place currentPlace = (Place) places.get(p);
            String textPLace = currentPlace.getText();
    		for (int t=0; t<matrizPost[p].length; t++) {
    			currentTransitionShowMatrizesPost = (Transition) transitions.get(t);
    			String textTrans = currentTransitionShowMatrizesPost.getText();
    			System.out.print("["+textPLace+","+textTrans+"]="+matrizPost[p][t]+"\t");
    		}
    	}
    	System.out.println();
    	System.out.println();
    }
    
    // imprime na tela os places  
	public void showPlaces() {
		System.out.println( "Num Places "+places.size());
        for( int i=0; i<places.size(); i++ ) {  
            Place place = (Place) places.get( i );  
        
            System.out.println( place.toString());
        }  	
	}
	
	// imprime na tela os arcs 
	public void showArcs() {
	    System.out.println( "Num arcs "+arcs.size());
        for( int i=0; i<arcs.size(); i++ ) {  
        	arc = (Arc) arcs.get( i );  
        
            System.out.println( arc.toString());
        }
	}
	
	 // imprime na tela os transitions 
	public void showTransitions() {
        System.out.println( "Num transitions "+transitions.size());
        for( int i=0; i<transitions.size(); i++ ) {  
        	transitionShow = (Transition) transitions.get( i );  
        
            System.out.println( transitionShow.toString());
        }
		
	}
    
    public static void main( String[] args ) {  
        
    	ElementosCPN elementosCPN = new ElementosCPN();  
    	
    	elementosCPN.showPlaces();
    	elementosCPN.showTransitions();
    	elementosCPN.showArcs();
    	elementosCPN.showMatrizPre();
    	elementosCPN.showMatrizPost();
    	elementosCPN.showMatrizC();
    	elementosCPN.showRelativeTopLeft();
    	
    	    	
    	    
    }  
}



/*public Place down(Place p) {
Vector downPlaces = new Vector();

Place currentPlace = null;
for (int i=0; i<places.size(); i++) {
	currentPlace = (Place) places.get(i);
	if (p.getY() > currentPlace.getY())
		downPlaces.addElement(currentPlace);
}
if (downPlaces.size()>0)
	return topLeft(downPlaces);
else
	return null;
}*/


/*public Place left(Place p) {

Vector leftPlaces = new Vector();

Place currentPlace = null;
for (int i=0; i<places.size(); i++) {
	currentPlace = (Place) places.get(i);
	if (p.getX() < currentPlace.getX())
		leftPlaces.addElement(currentPlace);
}
if (leftPlaces.size()>0)
	return topLeft(leftPlaces);
else
	return null;

}*/



/*public Place topLeft(Vector places) {
Place currentPlace = (Place) places.get(0);

int top= currentPlace.getY();
int left= currentPlace.getX();
double distMaior = Math.sqrt(top*top+left*left);
int indexPlace=0;

for (int i = 1; i<places.size(); i++) {
	currentPlace = (Place) places.get(i);
	top= currentPlace.getY();
	left= currentPlace.getX();

	//if (top>=0 && left<=0) {
	
		double distCurrent = Math.sqrt(top*top+left*left);
	
		if (distMaior<distCurrent) {
			indexPlace = i;
			distMaior=distCurrent;
		//}	
	}	
}

return (Place) places.get(indexPlace);
}*/

