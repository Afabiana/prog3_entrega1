package segundaEntrega;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import src.Arco;

public class Greedy {
    private ArrayList<Arco<Integer>> solucion;
    int cantidadMts;
    ArrayList<Arco<Integer>> arcos;
    int cantidadEstaciones;
    ArrayList<Integer> vertices;
    UnionFind union;
    int [] distancias;
    GrafoNoDirigido<Integer> grafo;
    
    public Greedy(ArrayList<Arco<Integer>> arcos) {
    	this.arcos = arcos;
    	this.vertices = new ArrayList<Integer>();
    	this.grafo = new GrafoNoDirigido<>();
    	cargarGrafo();
        this.solucion = new ArrayList<>();
        this.cantidadMts = 0;
        this.union = new UnionFind(grafo.cantidadVertices() + 1);
        
    }

    private void cargarGrafo() {
		for(Arco<Integer> arco : this.arcos) {
			if(!vertices.contains(arco.getVerticeOrigen())) {
				vertices.add(arco.getVerticeOrigen());
			}
			if(!vertices.contains(arco.getVerticeDestino())) {
				vertices.add(arco.getVerticeDestino());
			}
			this.grafo.agregarArco(arco.getVerticeOrigen(), arco.getVerticeDestino(), arco.getEtiqueta());
		}
	}
    
    public ArrayList<Arco<Integer>> greedy() {
        ArrayList<Integer> visitados = new ArrayList<>();
        System.out.println(vertices);
        
        Integer origen = vertices.get(0);
        visitados.add(origen);

        
        while (visitados.size() < grafo.cantidadVertices()) {
        	Arco<Integer> mejorArco = encontrarMejorArco(visitados, origen);
        	
            if (mejorArco != null) {
                
                solucion.add(mejorArco);
                this.cantidadMts += mejorArco.getEtiqueta();
                this.union.unir(mejorArco.getVerticeOrigen(), mejorArco.getVerticeDestino());
                int v1 = mejorArco.getVerticeOrigen();
                int v2 = mejorArco.getVerticeDestino();
                int nuevoVertice = visitados.contains(v1) ? v2 : v1;
                visitados.add(nuevoVertice);
                origen = nuevoVertice;
            }
        }

        if(union.todosConectados())
        	return solucion;
        
        return null;
    }

	private Arco<Integer> encontrarMejorArco(ArrayList<Integer> visitados, Integer origen) {
        int menorDistancia = Integer.MAX_VALUE;
        Arco<Integer> mejorArco = null;

        for (Arco<Integer> arco : arcos) {
            int v1 = arco.getVerticeOrigen();
            int v2 = arco.getVerticeDestino();

            if ((visitados.contains(v1) && !visitados.contains(v2)) ||
                (visitados.contains(v2) && !visitados.contains(v1))) {
                int distancia = arco.getEtiqueta();

                if (distancia < menorDistancia) {
                    menorDistancia = distancia;
                    mejorArco = arco;
                }
            }
        }
        
        return mejorArco;
	}

}
