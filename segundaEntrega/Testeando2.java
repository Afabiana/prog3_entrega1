package segundaEntrega;

import java.util.ArrayList;

import src.Arco;

public class Testeando2 {

	public static void main(String[] args) {
		//estaria bueno pedir la ruta por consola
		CSVReader lector = new CSVReader(".\\src\\datasets\\dataset3.txt");
		lector.read();
		ArrayList<Arco<Integer>> arcos= lector.cargarArcos();
		
		//System.out.println(estaciones);
		
		//Backtracking tuneles = new Backtracking(estaciones);
		
		//System.out.println("B"+tuneles.backtracking(1, new ArrayList<Arco<Integer>>(), 0));
		//System.out.println(tuneles.getCantIteraciones());
		
		//Ideas invento = new Ideas();
		//System.out.println(invento.construirMenorCantidadMts(arcos));
		
		//System.out.println(invento.iteraciones);
		
		Greedy greedy = new Greedy(arcos);
		System.out.println(greedy.greedy());
		System.out.println(greedy.cantidadMts);
		/*System.out.println(greedy.getCantIteraciones());
		
		System.out.println(tuneles.getMenorCantidadMts());
		System.out.println(greedy.getMenorCantidadMts());*/
		
	}

}
