package primeraEntrega;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class ServicioCaminos {
	private Grafo<?> grafo;
	private int origen;
	private int destino;
	private int lim;
	private HashMap<Integer, String> map;
	private List<List<Integer>> caminos;
	private List<Arco<Integer>> arcosConsiderados;

	public ServicioCaminos(Grafo<?> grafo, int origen, int destino, int lim) {
		this.grafo = grafo;
		this.origen = origen;
		this.destino = destino;
		this.lim = lim;
		this.map = new HashMap<>();
		this.caminos = new ArrayList<>();
		this.arcosConsiderados = new ArrayList<>();
		
	}

	public List<List<Integer>> caminos() {
		Iterator<Integer> it = grafo.obtenerVertices();
        while (it.hasNext()) {
        	this.map.put(it.next(), "blanco");
        }
        
    	List<Integer> caminoActual = new ArrayList<>();
		
        caminoActual.add(this.origen);
        
        encontrarCaminos(origen, caminoActual);

        return caminos;
	}

	private  void encontrarCaminos(int actual, List<Integer> caminoActual) {
		
		
		if (actual == destino && caminoActual.size() - 1 <= lim) {
			caminos.add(new ArrayList<>(caminoActual));
		} 
		else if (caminoActual.size() - 1 <= lim) {
			Iterator<Integer> itAdyacentes = grafo.obtenerAdyacentes(actual);
			while (itAdyacentes.hasNext() ) {
				Integer adyacente = itAdyacentes.next();
				Arco<Integer> arcoActual = new Arco<Integer>(actual, adyacente, null);
				List<Integer> nuevoCamino = new ArrayList<>(caminoActual);
				if (this.map.get(adyacente).equals("blanco")&&
						!arcosConsiderados.contains(arcoActual)) { //aca evaluo si es blanco para no pasar 2 veces por el mismo vertice en un mismo camino
					this.map.put(actual, "amarillo");
					nuevoCamino.add(adyacente);
					arcosConsiderados.add(arcoActual);
					encontrarCaminos(adyacente, nuevoCamino);
					nuevoCamino.remove(adyacente);
					arcosConsiderados.remove(arcoActual);
					this.map.put(actual, "blanco");
				}				
			}
		}
		
	}
	
}
