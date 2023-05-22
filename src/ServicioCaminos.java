package src;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ServicioCaminos {
	private Grafo<?> grafo;
	private int origen;
	private int destino;
	private int lim;
	
	// Servicio caminos
	public ServicioCaminos(Grafo<?> grafo, int origen, int destino, int lim) {
		this.grafo = grafo;
		this.origen = origen;
		this.destino = destino;
		this.lim = lim;
	}

	public List<List<Integer>> caminos() {

		List<List<Integer>> caminos = new ArrayList<>();
        List<Integer> caminoActual = new ArrayList<>();
        caminoActual.add(this.origen);

        encontrarCaminos(origen, caminoActual, caminos);

        return caminos;
	}

	private  void encontrarCaminos(int actual, List<Integer> caminoActual, List<List<Integer>> caminos) {
		if (actual == destino && caminoActual.size() - 1 <= lim) {
			// Si llegamos a destino y no se ha excedido el límite de arcos, añadimos el camino actual a la lista de caminos
			caminos.add(new ArrayList<>(caminoActual));
		} else if (caminoActual.size() - 1 <= lim) {
			// Si no hemos llegado al destino Y no se paso el límite de arcos,
			// seguimos explorando los caminos

			Iterator<Integer> itAdyacentes = grafo.obtenerAdyacentes(actual);
			while (itAdyacentes.hasNext() ) {
				Integer adyacente = itAdyacentes.next();
				if (!caminoActual.contains(adyacente)) {
					List<Integer> nuevoCamino = new ArrayList<>(caminoActual);
					nuevoCamino.add(adyacente);
					encontrarCaminos(adyacente, nuevoCamino, caminos);
				}
			}
		}
	}
	
	
}
