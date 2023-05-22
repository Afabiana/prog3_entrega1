package src;

import java.util.ArrayList;

public class probandoTP {

	public static void main(String[] args) {
		GrafoDirigido <Integer> grafito = new GrafoDirigido<>();
		Arco<Integer> arcoA = new Arco<>(5, 3, null);
		Arco<Integer> arcoB = new Arco<>(5, 1, null);
		Arco<Integer> arcoC = new Arco<>(5, 8, null);
		
		ArrayList<Arco<Integer>> arcos5 = new ArrayList<Arco<Integer>>();
		arcos5.add(arcoA);
		arcos5.add(arcoB);
		arcos5.add(arcoC);
		
		grafito.agregarVertice(5, arcos5);
		
		Arco<Integer> arcoD = new Arco<>(1, 3, null);
		Arco<Integer> arcoE = new Arco<>(1, 11, null);
		
		ArrayList<Arco<Integer>> arcos1 = new ArrayList<Arco<Integer>>();
		arcos1.add(arcoD);
		arcos1.add(arcoE);
		
		grafito.agregarVertice(1, arcos1);
		
		Arco<Integer> arcoF = new Arco<>(3, 11, null);
		
		ArrayList<Arco<Integer>> arcos3 = new ArrayList<Arco<Integer>>();
		arcos3.add(arcoF);
		
		grafito.agregarVertice(3, arcos3);
		
		Arco<Integer> arcoG = new Arco<>(11, 7, null);
		Arco<Integer> arcoH = new Arco<>(11, 20, null);
		
		ArrayList<Arco<Integer>> arcos11 = new ArrayList<Arco<Integer>>();
		arcos11.add(arcoG);
		arcos11.add(arcoH);
		
		grafito.agregarVertice(11, arcos11);
		
		
		Arco<Integer> arcoI = new Arco<>(7, 24, null);
		
		ArrayList<Arco<Integer>> arcos7 = new ArrayList<Arco<Integer>>();
		arcos7.add(arcoI);
		
		grafito.agregarVertice(7, arcos7);
		
	
		ArrayList<Arco<Integer>> arcos8 = new ArrayList<Arco<Integer>>();
		
		grafito.agregarVertice(8, arcos8);
		
		ArrayList<Arco<Integer>> arcos20 = new ArrayList<Arco<Integer>>();
		
		grafito.agregarVertice(20, arcos20);
		
		ArrayList<Arco<Integer>> arcos24 = new ArrayList<Arco<Integer>>();
		
		grafito.agregarVertice(24, arcos24);
		
		ServicioCaminos recorrido = new ServicioCaminos(grafito, 5, 24, 10);
		System.out.println(recorrido.caminos());

	}

}
