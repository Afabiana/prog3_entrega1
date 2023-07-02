package segundaEntrega;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import src.Arco;

public class Backtracking {
	private GrafoNoDirigido<Integer> estaciones;
	private ArrayList<Arco<Integer>> solucion;
	private Set <Integer> visitados;
	private int menorCantidadMts;
	private int cantEstaciones;
	private int iteraciones;
	
	public Backtracking(GrafoNoDirigido<Integer> grafo) {
		this.menorCantidadMts = Integer.MAX_VALUE;
		this.estaciones = grafo;
		this.visitados = new HashSet<Integer>();
		this.solucion = new ArrayList<Arco<Integer>>();
		this.cantEstaciones = this.estaciones.cantidadVertices();
		this.iteraciones=0;
	}
	

	public ArrayList<Arco<Integer>> backtracking(Integer estacion, ArrayList<Arco<Integer>> posibleSolucion, int cantMetrosActual) {
	    iteraciones++;
	    visitados.add(estacion);

	    if (visitados.size() == this.cantEstaciones) {
	        // Verificar si todos los vértices están conectados
	        if (verificarConectividad(posibleSolucion)) {
	            if (cantMetrosActual < this.menorCantidadMts) {
	                menorCantidadMts = cantMetrosActual;
	                solucion.clear();
	                solucion.addAll(new ArrayList<>(posibleSolucion));
	                
	            }
	        }
	    } else { // Sigo recorriendo el árbol
	        Iterator<Arco<Integer>> it = this.estaciones.obtenerArcos();
	        while (it.hasNext()) {
	            Arco<Integer> arco = it.next();
	            Integer v1 = arco.getVerticeDestino();
	            Integer distancia = arco.getEtiqueta();

	            // Si aún no agregué el arco y aún no visité v1
	            if (!posibleSolucion.contains(arco) && !visitados.contains(v1)) {
	                cantMetrosActual += distancia;
	                posibleSolucion.add(arco);
	                backtracking(v1, posibleSolucion, cantMetrosActual);
	                posibleSolucion.remove(posibleSolucion.size() - 1);
	                cantMetrosActual -= distancia;
	            }
	        }
	    }

	    visitados.remove(estacion);
	    return this.solucion;
	}

	public boolean verificarConectividad(ArrayList<Arco<Integer>> solucion) {
	    HashSet<Integer> verticesVisitados = new HashSet<>();
	    for (Arco<Integer> arco : solucion) {
	        verticesVisitados.add(arco.getVerticeOrigen());
	        verticesVisitados.add(arco.getVerticeDestino());
	    }
	    return verticesVisitados.size() == this.cantEstaciones;
	}

    
	public int getCantIteraciones() {
		return this.iteraciones;
	}
	
	public int getMenorCantidadMts() {
		return this.menorCantidadMts;
	}
	
	private boolean noEsInverso(Arco<Integer> a) {
		Arco<Integer> arcoInverso = new Arco<Integer>(a.getVerticeDestino(), a.getVerticeOrigen(), null);
			return a.getVerticeDestino() == arcoInverso.getVerticeOrigen() &&
					a.getVerticeOrigen() == arcoInverso.getVerticeDestino();
	}
	
	//establezco una conexion. Una vez que estableci la conexion tengo que buscar otro arco que tenga como destino el mismo destino anterior
	
}
