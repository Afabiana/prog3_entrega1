package src;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;



public class GrafoDirigido<T> implements Grafo<T> {
	private HashMap <Integer, ArrayList<Arco<T>>> vertices;
	
	public GrafoDirigido () {
		this.vertices = new HashMap<>();
	}
	
	//este se va
	public void agregarVertice(Integer verticeId, ArrayList<Arco<T>> arcos) {
		if(!vertices.containsKey(verticeId)) {
			vertices.put(verticeId, arcos);
		}	
	}
	
	@Override
	public void agregarVertice(int verticeId) {
		if(!vertices.containsKey(verticeId)) {
			vertices.put(verticeId, new ArrayList<Arco<T>>());
		}	
	}

	@Override
	public void borrarVertice(int verticeId) {
		this.vertices.remove(verticeId);
		//borrar arcos que tengan como destino ese vertice
	}

	@Override
	public void agregarArco(int verticeId1, int verticeId2, T etiqueta) {
		ArrayList<Arco<T>> arcos = vertices.get(verticeId1);
		arcos.add(new Arco<T>(verticeId1, verticeId2, etiqueta));
	}

	@Override
	public void borrarArco(int verticeId1, int verticeId2) {
		if(vertices.containsKey(verticeId1)&&vertices.containsKey(verticeId2)) {
			ArrayList<Arco<T>> arcos = vertices.get(verticeId1);
		    arcos.removeIf(arco -> arco.getVerticeDestino() == verticeId2); //si no se acepta la funcion anonima puedo hacer un while con el iterator de arcos
		}
	}

	@Override
	public boolean contieneVertice(int verticeId) {
		return this.vertices.containsKey(verticeId);
	}

	@Override
	public boolean existeArco(int verticeId1, int verticeId2) {
		if(vertices.containsKey(verticeId1)&&vertices.containsKey(verticeId2)) {
			ArrayList<Arco<T>> arcos = this.vertices.get(verticeId1);
			return arcos.contains(new Arco<T>(verticeId1, verticeId2,null)); //aca tambien tendria que evaluar la etiqueta? o depende del equals de arco?
		}
		return false;
	}

	@Override
	public Arco<T> obtenerArco(int verticeId1, int verticeId2) {
		if(existeArco(verticeId1, verticeId2)) {
			ArrayList<Arco<T>> arcos = this.vertices.get(verticeId1);
			for(Arco<T> a : arcos) {
				if(a.getVerticeDestino()== verticeId2) //no tiene sentido chequear el origen porque justamente estoy recorriendo los ady de origen
					return a;
			}
		}
		return null;
	}

	@Override
	public int cantidadVertices() {
		return  this.vertices.size();
	}

	@Override
	public int cantidadArcos() {
		int cantidad = 0;
	    for (ArrayList<Arco<T>> listaDeArcos : vertices.values()) {
	        cantidad += listaDeArcos.size();
	    }
	    return cantidad;
	}

	@Override
	public Iterator<Integer> obtenerVertices() {
		return this.vertices.keySet().iterator();
	}

	@Override
	public Iterator<Integer> obtenerAdyacentes(int verticeId) {
		if(vertices.containsKey(verticeId)){
	        ArrayList<Integer> adyacentes = new ArrayList<>();
	        Iterator<Arco<T>> arcoIterator = vertices.get(verticeId).iterator();
	        while (arcoIterator.hasNext()){ // O(a) -> En el peor de los casos verticeId es el origen de todos los arcos.
	            adyacentes.add(arcoIterator.next().getVerticeDestino()); // poniendo next ya avanza al siguiente y del arco solo me interesa el destino
	        }
	        return adyacentes.iterator();
	    }
	        return null;
	}

	@Override
	public Iterator<Arco<T>> obtenerArcos() {//preguntar bien si es necesario copiar la lista
	    ArrayList<Arco<T>> listaArcos = new ArrayList<>();
	    for (ArrayList<Arco<T>> lista : vertices.values()) {
	        listaArcos.addAll(lista);
	    }
	    return listaArcos.iterator();
	}

	@Override
	public Iterator<Arco<T>> obtenerArcos(int verticeId) {
		if (vertices.containsKey(verticeId)) {
			ArrayList<Arco<T>> listaArcos = vertices.get(verticeId);
			for(ArrayList<Arco<T>> arcos : vertices.values()) {
				listaArcos.addAll(arcos);
			}	
				return listaArcos.iterator();
	    }
	    return null;
	}

}
