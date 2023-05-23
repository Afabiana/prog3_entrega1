package src;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


public class ServicioDFS {
	private Grafo<?> grafo;
	HashMap<Integer, String> vertices = new HashMap<>();
	List<Integer> camino = new ArrayList<>();

	public ServicioDFS(Grafo<?> grafo) {
		this.grafo = grafo;
		this.vertices = new HashMap<>();
		this.camino = new ArrayList<>();
	}
	
	public List<Integer> dfsForest() {
		//traigo iterador de los vertices del grafo
		Iterator<Integer> verticesIt = this.grafo.obtenerVertices();
		while(verticesIt.hasNext()) { //guardo los vertices y le asigno el estado de "blanco"
			vertices.put(verticesIt.next(), "blanco");
		}
		
		//ahora me interesa recorrer el hashM con los vertices y sus estados
		verticesIt = vertices.keySet().iterator();
		while(verticesIt.hasNext()) {
			Integer tmp = verticesIt.next();
			 if(vertices.get(tmp).equals("blanco")) {
				 System.out.println(tmp);
				 DFS_Visit(tmp,vertices); //si vertices no puede ir como atributo de clase tocara mandarlo como parametro
			 }
		}
		// Resolver DFS
		//Collections.reverse(resultado);
		return new ArrayList<>(this.camino);
	}

	private void DFS_Visit(Integer vertice, HashMap<Integer, String> vertices ) {
		this.camino.add(vertice);
		this.vertices.put(vertice,"amarillo"); //va a pasar de blanco a amarillo
		
		Iterator<Integer> adyacentes = this.grafo.obtenerAdyacentes(vertice); //traigo el iterador de los adyacentes al vertice
		
		while(adyacentes.hasNext()) {
			Integer ady = adyacentes.next();
			
			if(this.vertices.get(ady).equals("blanco")) {
				DFS_Visit(ady, vertices);
			}
		}
		this.vertices.put(vertice,"negro"); //marcar como visitado porque ya recorri sus adyacentes
	}
}
