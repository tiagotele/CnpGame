package Controller;

import java.util.ArrayList;

import Model.Arc;
import Model.Token;
import Model.Place;
import Model.Transition;

public class FireTransition {

	private ArrayList<Arc> inputArcs;
	private ElementosCPN refElemCPN;
	
	private ArrayList<Arc> arcosEntrada;	
	private ArrayList<String> idPlacesEntrada;
	
	private Transition transitionFired;
	private String idTransFired;
	
	
	public FireTransition(ElementosCPN e, Transition t) {
		this.refElemCPN = e;
		this.transitionFired = t;
		this.idTransFired = transitionFired.getId();
		
		this.idPlacesEntrada = new ArrayList<String>();
		this.arcosEntrada = new ArrayList<Arc>();
	}
	
	public void verificar () {
		
		boolean fire = true;

		//passar por todos os places e verificar quais entram na transição a ser disparada
		//   (contando que existe só uma transição de saida para qualquer place da rede)
		// como? -> para todos os arcos, verificar quais possuem a transição analisada
		//          como chegada e armazemar os places de origem.
		
		ArrayList<Arc> arcos = refElemCPN.getArcs();
		
		for (int i=0; i<arcos.size(); i++) {
			//verifica se o arco possui a transição disparada no final, ou seja,
			// que seja PtoT (P->T)  -->  todo mundo que chega na transição
			if (arcos.get(i).getTransend().equals(idTransFired) &&
					arcos.get(i).getOrientation().equals("PtoT")) {
				
				idPlacesEntrada.add(arcos.get(i).getPlaceend());
				arcosEntrada.add(arcos.get(i));
			}
		}
		
		//ja com todos os ids dos places e os arcos de entrada, podemos verificar se as fichas presentes nos places
		// satisfazem as condições nos arcos
		for (int i=0; i<idPlacesEntrada.size() && fire==true; i++) {

			Arc a = null;
			Place p = null;
			//procurar o arco que esta ligado ao place
			for (int j=0; j<arcosEntrada.size(); j++)
				if (arcosEntrada.get(j).getPlaceend().equals(idPlacesEntrada.get(i)))
					a = arcosEntrada.get(j);
			
			if (a != null) {
				
				//procurar que tipo (type) eh a condição do arco
//				String typeA = ;
				
				
				ArrayList<Place> refPlaces = refElemCPN.getPlaces();
				// procurar o place com o id do laço atual
				for (int j = 0; j < refPlaces.size(); j++)
					if (refPlaces.get(j).getId().equals(idPlacesEntrada.get(i)))
						p = refPlaces.get(j);

				// verificar se o place possui fichas
				if (p != null) {
					// p.getInitmark() //->nao serve pois eu devo pegar o numero
					// de fichas atuais no place, nao só os iniciais
					ArrayList<Token> tokensP = p.getTodasOsTokens();
					
					//verificar se existe algum token que satisfaça a condição do arco 'a'
					boolean existeToken = false;
					for (int j=0; j<tokensP.size() && existeToken==false; j++) {
						if (tokensP.get(i).getType().equals("")) {}//parei aqui
					}

				}
				else {
					System.out.println("Place com o id "+idPlacesEntrada.get(i)+" não encontrado!");
				}
			}
			else {
				System.out.println("Arco não encontrado!");
			}
		}
		
		
		
		/***
		
		for (int trans=0; trans<refElemCPN.getMatrizPre()[0].length; trans++) {
			System.out.println("inplace - show PtoT: ["+inPlace+"]["+trans+"] = "+
		                                   refElemCPN.getMatrizPre()[inPlace][trans]);
			//verificar se tem algum caminho da casa para a transição
			if (refElemCPN.getMatrizPre()[inPlace][trans]!=0) {
				//guardar os pres em algum lugar
				System.out.println("condicao:  ["+inPlace+"]["+trans+"] =" +
						" "+refElemCPN.getTransitions().get(trans).getText());
				currentTrans = refElemCPN.getTransitions().get(trans).getId();
				
				for (int iArc=0; iArc<refElemCPN.getArcs().size(); iArc++) {
					String auxTransendArc = refElemCPN.getArcs().get(iArc).getTransend();
					String auxPlaceendArc  = refElemCPN.getArcs().get(iArc).getPlaceend();
					String auxOrientation  = refElemCPN.getArcs().get(iArc).getOrientation();
					
					System.out.println("currentInplace = "+currentInplace);
					
					if (currentTrans.equals(auxTransendArc) &&
						currentInplace.equals(auxPlaceendArc) &&
						auxOrientation.equals("PtoT")) {
						//achou o arco de entrada da transicao
						
						System.out.println("condicao de entrada inplace: "+ refElemCPN.getArcs().get(iArc).getText());
						//pra amanha: fazer a tabela
						
						//1o:
						placesEntrada.add(currentPlaceUpdate);
						
						//2o:
						//adicionar aos arcos de entrada
						arcosEntrada.add(refElemCPN.getArcs().get(iArc));
						
						//3o ----?
						transitionFired = refElemCPN.getTransitions().get(trans);
					}
				}
			}
		}
		
		//disparar:
		//existe alguma condição no arco?
		boolean existeCondArc = false;
		for (int b=0; b<arcosEntrada.size() && existeCondArc==false; b++) {
//			if (arcosEntrada.get(b).getCondicao().) {}
		}
		
		*/
	}
	
	
	private String getTypeArc (String condicaoArco) {
    	//teste
    	//exemplo: condicaoArco = "(b,a)";
		
//		String condicao = condicaoArco.substring(1, condicaoArco.length()-1);	// b,a
//		String[] arrayCond = condicao.split(",");	// b a 
    	
//		System.out.println("(teste) condicao arco: "+condicao);
//    	for (int i=0; i<arrayCond.length; i++) {
//    		System.out.print(arrayCond[i]+" ");
//    	}
		
		
		return "";
	}	
	
	public ArrayList<Arc> getInputArcs() {
		return inputArcs;
	}
	
	public void setInputArcs(ArrayList<Arc> inputArcs) {
		this.inputArcs = inputArcs;
	}
	
}
