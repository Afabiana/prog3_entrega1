package src;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


public class ServicioDFS {
	private Grafo<?> grafo;
	//puede ser atributo de clase? o rompe con lo que quieren que sigamos?
	HashMap<Integer, String> vertices = new HashMap<>();

	public ServicioDFS(Grafo<?> grafo) {
		this.grafo = grafo;
	}
	
	public List<Integer> dfsForest() {
		List<Integer> resultado = new ArrayList<>();
		//traigo iterador de los vertices del grafo
		Iterator<Integer> verticesIt = this.grafo.obtenerVertices();
		while(verticesIt.hasNext()) { //guardo los vertices y le asigno el estado de "blanco"
			vertices.put(verticesIt.next(), "blanco");
		}
		
		//ahora me interesa recorrer el hashM con los vertices y sus estados
		verticesIt = vertices.keySet().iterator();
		while(verticesIt.hasNext()) {
			Integer tmp = verticesIt.next();
			 if(vertices.get(tmp).equals("blanco"))
				 resultado.addAll(DFS_Visit(tmp)); //si vertices no puede ir como atributo de clase tocara mandarlo como parametro
		}
		// Resolver DFS
		return resultado;
	}

	private List<Integer> DFS_Visit(Integer vertice) {
		List<Integer> resultado = new ArrayList<>();
		this.vertices.put(vertice,"AMARILLO"); //va a pasar de blanco a amarillo
	
		Iterator<Integer> adyacentes = this.grafo.obtenerAdyacentes(vertice); //traigo el iterador de los adyacentes al vertice
		
		while(adyacentes.hasNext()) {
			Integer ady = adyacentes.next();
			if(this.vertices.get(ady).equals("blanco")) {
				DFS_Visit(ady);
			}else if (vertices.get(ady).equals("AMARILLO")){
	            System.out.println("Hay ciclo");
	        }
		}
		
		this.vertices.put(vertice,"NEGRO"); //marcar como visitado
		resultado.add(vertice);
		return resultado;
	}
}
