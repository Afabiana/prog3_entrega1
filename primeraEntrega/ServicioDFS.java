package primeraEntrega;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


public class ServicioDFS {
	private Grafo<?> grafo;
	private HashMap<Integer, String> vertices;
	private List<Integer> camino;

	public ServicioDFS(Grafo<?> grafo) {
		this.grafo = grafo;
		this.vertices = new HashMap<>();
		this.camino = new ArrayList<>();
	}
	
	public List<Integer> dfsForest() {
		
		Iterator<Integer> verticesIt = this.grafo.obtenerVertices();
		while(verticesIt.hasNext()) { //guardo los vertices y le asigno el estado de "blanco"
			this.vertices.put(verticesIt.next(), "blanco");
		}
		
		verticesIt = vertices.keySet().iterator();
		while(verticesIt.hasNext()) {
			Integer tmp = verticesIt.next();
			 if(this.vertices.get(tmp).equals("blanco")) {
				 DFS_Visit(tmp);
			 }
		}
		return new ArrayList<>(this.camino);
	}

	private void DFS_Visit(Integer vertice ) {
		this.camino.add(vertice);
		this.vertices.put(vertice,"amarillo"); //va a pasar de blanco a amarillo
		
		Iterator<Integer> adyacentes = this.grafo.obtenerAdyacentes(vertice); //traigo el iterador de los adyacentes al vertice
		
		while(adyacentes.hasNext()) {
			Integer ady = adyacentes.next();
			
			if(this.vertices.get(ady).equals("blanco")) {
				DFS_Visit(ady);
			}
		}
		this.vertices.put(vertice,"negro"); //marcar como visitado porque ya recorri sus adyacentes
	}
}
