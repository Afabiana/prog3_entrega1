package segundaEntrega;

import java.util.ArrayList;

import src.Arco;

public class Testeando2 {

	public static void main(String[] args) {
		//estaria bueno pedir la ruta por consola
		CSVReader lector = new CSVReader(".\\src\\datasets\\dataset1.txt");
		lector.read();
		GrafoNoDirigido<Integer> estaciones = lector.cargarGrafo();
		
		System.out.println(estaciones);
		
		Backtracking tuneles = new Backtracking(estaciones);
		
		System.out.println("S"+tuneles.backtracking(1, new ArrayList<Arco<Integer>>(), 0));
		System.out.println(tuneles.iteraciones);
		
		Greedy greedy = new Greedy(estaciones);
		greedy.dijkstra();
		System.out.println(greedy.solucion);
		System.out.println(greedy.iteraciones);
		
	}

}
