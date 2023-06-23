package segundaEntrega;

import java.util.ArrayList;
import java.util.Iterator;

import src.Arco;

public class Greedy {
	private GrafoNoDirigido<Integer> estaciones;
	private Integer [] distancias;
	private Integer [] padres;
	ArrayList<Arco<Integer>> solucion;
	int iteraciones;
	private ArrayList<Integer> visitados;
	
	public Greedy(GrafoNoDirigido<Integer> grafo) {
		this.estaciones = grafo;
		this.distancias = new Integer [grafo.cantidadVertices()+1];
		this.padres = new Integer [grafo.cantidadVertices()+1];
		this.visitados = new ArrayList<>();
		this.solucion = new ArrayList<>();
		iteraciones = 0;
	}

	public void dijkstra() {
	    this.inicializarEstadoVertices();
	    Integer actual = getPrimerVertice();
	    
	    while (!visitados.contains(actual) && this.visitados.size() < this.estaciones.cantidadVertices()) {
	        Iterator<Arco<Integer>> iterador = estaciones.obtenerArcos(actual);
	        iteraciones++;
	        
	        while (iterador.hasNext()) {
	        	iteraciones++;
	            Arco<Integer> arco = iterador.next();
	            Integer distancia = arco.getEtiqueta();
	            Integer adyacente = arco.getVerticeDestino();
	            
	            if (!visitados.contains(adyacente) && distancias[actual] + distancia < distancias[adyacente]) {
	            	//elimino si existe ya un arco que me lleve a ese vertice
	                solucion.removeIf(arcoB -> arcoB.getVerticeDestino() == adyacente);
	                solucion.add(arco);
	                //actualizo el padre y la distancia hacia ese vertice
	                distancias[adyacente] = distancias[actual] + distancia;
	                padres[adyacente] = actual;
	            }
	        }
	        visitados.add(actual);
	        actual = getVerticeMasCercano();
	    }
	}
	
	private void inicializarEstadoVertices() {
		for(int i=0; i<this.distancias.length; i++) {
			this.distancias[i] = Integer.MAX_VALUE;
		}
		for(int i=0; i<padres.length; i++) {
			this.padres[i] = null;
		}
		
	}
	
	private Integer getPrimerVertice() {
		Iterator<Integer> it = this.estaciones.obtenerVertices();
		System.out.println(it.next());
		return it.next();
	}
	
	private Integer getVerticeMasCercano() {
		int minDistancia = Integer.MAX_VALUE;
        int minVertice = -1;

        for (int i = 0; i < distancias.length; i++) {
            if (!visitados.contains(i) && distancias[i] < minDistancia) {
                minDistancia = distancias[i];
                minVertice = i;
            }
        }

        return minVertice;
	}
}
