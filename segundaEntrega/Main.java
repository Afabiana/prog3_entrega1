package segundaEntrega;

import java.util.ArrayList;
import java.util.Scanner;

import primeraEntrega.Arco;

public class Main {

	public static void main(String[] args) {
		
		
		Scanner scanner = new Scanner(System.in);
        //pido la ruta por consola 
		//en mi caso le paso ./src/datasets/dataset1.txt
        System.out.print("Ingrese la ruta del archivo: ");
        String rutaArchivo = scanner.nextLine();
		CSVReader lector = new CSVReader(rutaArchivo);
		
		
		//esos arcos los cargo en un array que voy a pasar por parametro
		ArrayList<Arco<Integer>> arcos= lector.cargarArcos();
		
		Backtracking back = new Backtracking();
		System.out.println("Backtracking");
		System.out.println(back.construirMenorCantidadMts(arcos));
		System.out.println(back.getCantidadMts() + " kms");
		System.out.println("cantidad de iteraciones: " + back.getIteraciones());
		
		Greedy greedy = new Greedy(arcos);
		System.out.println("Greedy");
		System.out.println(greedy.greedy());
		System.out.println(greedy.getMenorCantidadMts()+ " kms");
		System.out.println("cantidad de iteraciones: " + greedy.getCantIteraciones());
		
		
		
	}

}
