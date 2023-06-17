package src;


import java.nio.file.Path;
import java.nio.file.Paths;

public class ProbandoTP {

	public static void main(String[] args) {
		GrafoDirigido <Integer> grafito = new GrafoDirigido<>();
		
		//Creo vertices
		grafito.agregarVertice(5);
		grafito.agregarVertice(1);
		grafito.agregarVertice(7);
		grafito.agregarVertice(2);
		grafito.agregarVertice(9);
		grafito.agregarVertice(15);
		grafito.agregarVertice(23);
		grafito.agregarVertice(18);
		
		//arcos de vertice 5
		grafito.agregarArco(5, 2, null);
		grafito.agregarArco(5, 1, null);
		grafito.agregarArco(5, 18, null);
		
		//arcos de vertice 2
		grafito.agregarArco(2, 7, null);
		grafito.agregarArco(2, 15, null);
		
		//Arcos de vertice 7
		grafito.agregarArco(7, 5, null);
		
		//Arcos de vertice 1
		grafito.agregarArco(1, 9, null);
		grafito.agregarArco(1, 18, null);
		
		//Arcos de vertice 9
		grafito.agregarArco(9, 23, null);
		
		//Arcos de vertice 18
		grafito.agregarArco(18, 23, null);
	

		ServicioCaminos recorrido = new ServicioCaminos(grafito, 5, 23, 10);
		System.out.println(recorrido.caminos());
		
		ServicioBFS bfs = new ServicioBFS (grafito);
		System.out.println(bfs.bfsForest());
		
		ServicioDFS dfs = new ServicioDFS(grafito);
		System.out.println(dfs.dfsForest());
		
		
		
	}

}
