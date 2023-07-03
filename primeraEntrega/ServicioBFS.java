package primeraEntrega;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


public class ServicioBFS {
	private Grafo<?> grafo;
	private HashMap<Integer, String> vertices; 

    public ServicioBFS(Grafo<?> grafo) {
        this.grafo = grafo;
        this.vertices = new HashMap<>();
    }

    public List<Integer> bfsForest() {
        List<Integer> resultado = new ArrayList<>();

        Iterator<Integer> it = grafo.obtenerVertices();
        
        while (it.hasNext()) {
        	this.vertices.put(it.next(), "blanco");
        }

        for (Integer vertice : vertices.keySet()) {
            if (this.vertices.get(vertice).equals("blanco")) {
                resultado.addAll(bfsVisit(vertice));
            }
        }

        return resultado;
    }

    private List<Integer> bfsVisit(Integer inicio) {
        List<Integer> camino = new ArrayList<>();
        ArrayList<Integer> fila = new ArrayList<>();

        this.vertices.put(inicio, "amarillo");
        fila.add(inicio);

        while (!fila.isEmpty()) {
            Integer actual = fila.remove(0);
            
            camino.add(actual);

            Iterator<Integer> it = grafo.obtenerAdyacentes(actual);
            
            while (it.hasNext() ) {
            	Integer adyacente = it.next();
            	
                if (this.vertices.get(adyacente).equals("blanco")) {
                	this.vertices.put(adyacente, "amarillo");
                    fila.add(adyacente);
                }
            }

            this.vertices.put(actual, "negro");
        }

        return camino;
    }
}
