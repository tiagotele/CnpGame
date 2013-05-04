package Cont;

import java.util.ArrayList;

import Model.Arc;
import Model.Ficha;
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
			// que seja PtoT
			if (arcos.get(i).getTransend().equals(idTransFired) &&
					arcos.get(i).getOrientation().equals("PtoT")) {
				
				idPlacesEntrada.add(arcos.get(i).getPlaceend());
				arcosEntrada.add(arcos.get(i));
			}
		}
		
		//ja com todos os places e arcos de entrada, podemos verificar se as fichas presentes nos places
		// satisfazem as condições nos arcos
		for (int i=0; i<idPlacesEntrada.size() && fire==true; i++) {

			Arc a = null;
			Place p = null;
			//procurar o arco que esta ligado ao place
			for (int j=0; j<arcosEntrada.size(); j++)
				if (arcosEntrada.get(j).getPlaceend().equals(idPlacesEntrada.get(i)))
					a = arcosEntrada.get(j);
			
			
			ArrayList<Place> refPlaces = refElemCPN.getPlaces();
			//procurar o place com o id do laço atual
			for (int j=0; j<refPlaces.size(); j++)
				if (refPlaces.get(j).getId().equals(idPlacesEntrada.get(i)))
					p = refPlaces.get(j);
			
			//verificar se o place possui fichas
			if (p!=null) {
				//p.getInitmark() //->nao serve pois eu devo pegar o numero de fichas atuais no place, nao só os iniciais
				ArrayList<Ficha> fichasP = p.getTodasAsFichas();
				
				
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
	
	
	public ArrayList<Arc> getInputArcs() {
		return inputArcs;
	}
	
	public void setInputArcs(ArrayList<Arc> inputArcs) {
		this.inputArcs = inputArcs;
	}
	
}
