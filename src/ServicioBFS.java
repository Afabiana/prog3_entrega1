package src;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


public class ServicioBFS {
	private Grafo<?> grafo;

    public ServicioBFS(Grafo<?> grafo) {
        this.grafo = grafo;
    }

    public List<Integer> bfsForest() {
        List<Integer> resultado = new ArrayList<>();
        HashMap<Integer, String> vertices = new HashMap<>(); //consultar si esto puede ser atributo de clase o rompe con la estructura que se pide

        Iterator<Integer> it = grafo.obtenerVertices();
        while (it.hasNext()) {
        	vertices.put(it.next(), "blanco");
        }

        for (Integer vertice : vertices.keySet()) {
            if (vertices.get(vertice).equals("blanco")) {
                resultado.addAll(bfsVisit(vertice, vertices));
            }
        }

        return resultado;
    }

    private List<Integer> bfsVisit(Integer inicio, HashMap<Integer, String> vertices) {
        List<Integer> camino = new ArrayList<>();
        ArrayList<Integer> fila = new ArrayList<>();

        vertices.put(inicio, "amarillo");
        fila.add(inicio);

        while (!fila.isEmpty()) {
            Integer actual = fila.remove(0);
            camino.add(actual);

            Iterator<Integer> it = grafo.obtenerAdyacentes(actual);
            while (it.hasNext() ) {
            	Integer adyacente = it.next();
            	System.out.println(adyacente);
                if (vertices.get(adyacente).equals("blanco")) {
                	vertices.put(adyacente, "amarillo");
                    fila.add(adyacente);
                }
            }

            vertices.put(actual, "negro");
        }

        return camino;
    }
}
