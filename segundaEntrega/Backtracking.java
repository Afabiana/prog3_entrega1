package segundaEntrega;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import src.Arco;

public class Backtracking {
	GrafoNoDirigido<Integer> estaciones;
	ArrayList<Arco<Integer>> solucion;
	Set <Integer> visitados;
	int menorCantidadMts;
	int cantEstaciones;
	int iteraciones;
	
	public Backtracking(GrafoNoDirigido<Integer> grafo) {
		this.menorCantidadMts = Integer.MAX_VALUE;
		this.estaciones = grafo;
		this.visitados = new HashSet<Integer>();
		this.solucion = new ArrayList<Arco<Integer>>();
		this.cantEstaciones = this.estaciones.cantidadVertices();
		this.iteraciones=0;
	}
	

    public ArrayList<Arco<Integer>> backtracking(Integer estacion, ArrayList<Arco<Integer>> posibleSolucion , int cantMetrosActual) {
    	iteraciones++;
        visitados.add(estacion); 
    	if (visitados.size() == this.cantEstaciones) {
            if (cantMetrosActual < this.menorCantidadMts) {
                menorCantidadMts = cantMetrosActual;
                solucion.clear();
                solucion.addAll(new ArrayList<>(posibleSolucion));
            }
        } else { //sigo recorriendo el arbol
        	Iterator<Arco<Integer>> it = this.estaciones.obtenerArcos();
        	while(it.hasNext()) {
        		Arco<Integer> arco = it.next();
        		Integer v1 = arco.getVerticeDestino();
        		Integer distancia = arco.getEtiqueta();
        		//si aun no agregue el arco && aun no tengo un arco que me lleve a v1
        		if(!posibleSolucion.contains(arco) && !visitados.contains(v1)) {
        			cantMetrosActual += distancia;
        			posibleSolucion.add(arco);
        			backtracking(v1, posibleSolucion, cantMetrosActual);
        			posibleSolucion.remove(posibleSolucion.size()-1);
        			cantMetrosActual -= distancia;
        		}
        	}
        }
    	visitados.remove(estacion);
        return this.solucion;
    }
	
}
