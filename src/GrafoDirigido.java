package src;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;



public class GrafoDirigido<T> implements Grafo<T> {
	private HashMap <Integer, ArrayList<Arco<T>>> vertices;
	
	public GrafoDirigido () {
		this.vertices = new HashMap<>();
	}
	
	//este metodo se va. Nomas esta para probar y crear vertices rapidos en el main
	public void agregarVertice(Integer verticeId, ArrayList<Arco<T>> arcos) {
			vertices.put(verticeId, arcos);
	}
	
	/**
	* Complejidad: O(1) dado que agregar una entrada 
	* al hashMap implica un tiempo constante
	*/
	@Override
	public void agregarVertice(int verticeId) {
		vertices.put(verticeId, new ArrayList<Arco<T>>());
	}
	
	/**
	* Complejidad: O(n * a) donde n es la cantidad total de vertices debido a que debe
	* recorrer todos los vertices con el for para verificar si existe un arco.
	* Y "a" es la cantidad total de arcos debido a que se debe recorrer todos los arcos 
	* en el bluce de arcosList.removeIf CHEQUEAR SI NO USA *
	*/
	@Override
	public void borrarVertice(int verticeId) {
		for(ArrayList<Arco<T>> arcosList : this.vertices.values()) {
				arcosList.removeIf(arco -> arco.getVerticeDestino() == verticeId); //borro todos los arcos que tengan coom destino verticeId
		}
		this.vertices.remove(verticeId);
	}

	/**
	* Complejidad: O(n) donde n es la cantidad de arcos que tiene el verticeId1 
	* debido a que debe recorrer todos los arcos del vertice 
	* para verificar si ya existe un arco igual al que se esta queriendo agregar.
	*/
	@Override
	public void agregarArco(int verticeId1, int verticeId2, T etiqueta) {
		ArrayList<Arco<T>> arcos = vertices.get(verticeId1);
		Arco<T> nuevo = new Arco<T>(verticeId1, verticeId2, etiqueta);
		if(!arcos.contains(nuevo))
			arcos.add(nuevo);
	}

	
	/**
	* Complejidad: O(n) donde n es la cantidad de arcos del vertice debido a que hay que
	* recorrer los arcos del vertice para saber si cumple con la condicion en el removeIf
	*/
	@Override
	public void borrarArco(int verticeId1, int verticeId2) {
		if(vertices.containsKey(verticeId1)&&vertices.containsKey(verticeId2)) {
			ArrayList<Arco<T>> arcos = vertices.get(verticeId1);
		    arcos.removeIf(arco -> arco.getVerticeDestino() == verticeId2); 
		}
	}

	/**
	* Complejidad: O(1) ya que verificar la existencia
	* de una clave en el hashmap es constante porque se accede directamente
	*/
	@Override
	public boolean contieneVertice(int verticeId) {
		return this.vertices.containsKey(verticeId);
	}

	/**
	* De nuevo la complejidad es O(n) donde n es la cantidad de 
	* arcos del vertice.
	*  Y en el peor de los casos verticeId1 es adyacente a todos vertices del grafo
	*/
	@Override
	public boolean existeArco(int verticeId1, int verticeId2) {
		if(vertices.containsKey(verticeId1)&&vertices.containsKey(verticeId2)) {
			ArrayList<Arco<T>> arcos = this.vertices.get(verticeId1);
			return arcos.contains(new Arco<T>(verticeId1, verticeId2,null)); //aca tambien tendria que evaluar la etiqueta? o depende del equals de arco? CONSULTAR
		}
		return false;
	}

	/**
	* Complejidad: O(n) donde n es la cantidad de 
	* arcos del vertice ya que en el peor de los casos se recorrera
	* la totalidad de los arcos del vertice
	*/
	@Override
	public Arco<T> obtenerArco(int verticeId1, int verticeId2) {
		ArrayList<Arco<T>> arcos = this.vertices.get(verticeId1);
		for(Arco<T> a : arcos) {
			if(a.getVerticeDestino() == verticeId2) //no tiene sentido chequear el origen porque justamente estoy recorriendo los ady de origen
				return a;
		}
		return null;
	}

	/**
	* Complejidad: O(1) ya que el size es un valor al que se
	* accede directamente, lo que implica un tiempo constante
	*/
	@Override
	public int cantidadVertices() {
		return  this.vertices.size();
	}

	/**
	* Complejidad: O(n + 1) donde n es la cantidad de vertices y 1
	* es la complejidad que implica acceder al tamaño del array listaDeArcos
	*/
	@Override
	public int cantidadArcos() {
		int cantidad = 0;
	    for (ArrayList<Arco<T>> listaDeArcos : vertices.values()) {
	        cantidad += listaDeArcos.size();
	    }
	    return cantidad;
	}

	
	/**
	* Complejidad: O(1) ya que no implica hacer un recorrido 
	* de todos los vertices, sino de obtener directamente el iterador
	* lo que implica un tiempo constante
	*/
	@Override
	public Iterator<Integer> obtenerVertices() {
		return this.vertices.keySet().iterator();
	}

	/**
	* Complejidad: O(a) donde a es la cantidad de arcos del verticeId debido a que debe
	* recorrer los adyacentes para realizar una copia de los adyacentes y
	* asi retornar un iterador de los mismos.
	*/
	@Override
	public Iterator<Integer> obtenerAdyacentes(int verticeId) {
		if(vertices.containsKey(verticeId)){
	        ArrayList<Integer> adyacentes = new ArrayList<>();
	        Iterator<Arco<T>> arcoIterator = vertices.get(verticeId).iterator();
	        while (arcoIterator.hasNext()){ // O(a) En el peor de los casos verticeId es el origen de todos los arcos.
	            adyacentes.add(arcoIterator.next().getVerticeDestino()); // O(1)
	        }
	        return adyacentes.iterator(); //O(1)
	    }
	        return null;
	}

	/**
	* Complejidad: O(n * a) donde n es la cantidad de vertices del grafo y
	* a es la cantidad de arcos del grafo. Ya que por cada vertice del grafo
	* voy a recorrer todos sus arcos para agregarlos a la copia listaArcos
	*/
	@Override
	public Iterator<Arco<T>> obtenerArcos() {
	    ArrayList<Arco<T>> listaArcos = new ArrayList<>();
	    for (ArrayList<Arco<T>> lista : vertices.values()) {
	        listaArcos.addAll(lista);
	    }
	    return listaArcos.iterator();
	}

	/**
	* Complejidad: O(a) donde a es la cantidad de arcos
	* asociados a verticeId
	*/
	@Override
	public Iterator<Arco<T>> obtenerArcos(int verticeId) {
		if (vertices.containsKey(verticeId)) {
			ArrayList<Arco<T>> resultado = new ArrayList<>();
			ArrayList<Arco<T>> listaArcos = vertices.get(verticeId);
			
			for(Arco<T> arco : listaArcos) {
				resultado.add(arco);
			}
			return resultado.iterator();
	    }
	    return null;
	}

}
