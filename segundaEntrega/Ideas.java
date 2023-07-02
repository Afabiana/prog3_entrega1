package segundaEntrega;

import java.util.ArrayList;
import java.util.Collections;

import src.Arco;

public class Ideas {
    private ArrayList<Arco<Integer>> solucion;
    private int menorCantidadMts;
    private ArrayList<Arco<Integer>> arcosDisponibles;
    private UnionFind union;
    int iteraciones;
    int cantidadEstaciones;
    
    public Ideas() {
    	this.solucion = new ArrayList<Arco<Integer>>();
    	this.menorCantidadMts = Integer.MAX_VALUE;
    	this.arcosDisponibles =  new ArrayList<Arco<Integer>>();
    	this.iteraciones = 0;
    }

    public ArrayList<Arco<Integer>> construirMenorCantidadMts(ArrayList<Arco<Integer>> arcosDisponibles) {
    	Timer timer = new Timer();
    	timer.start();
    	System.out.println(timer.startTime);
    	this.arcosDisponibles = arcosDisponibles;
    	cargarVertices(arcosDisponibles);
    	Collections.sort(arcosDisponibles);
    	this.union = new UnionFind(this.cantidadEstaciones+1);
        dfsBacktracking(new ArrayList<Arco<Integer>>(), 0 );
        //la solucion la cargo en un grafo dirigido
        
        System.out.println(timer.stop());
        return solucion;
    }

    

	private void cargarVertices(ArrayList<Arco<Integer>> arcos) {
		ArrayList<Integer> vertices = new ArrayList<Integer>();
		
		for (Arco<Integer> arco : arcos) {
            int verticeOrigen = arco.getVerticeOrigen();
            int verticeDestino = arco.getVerticeDestino();

            if (!vertices.contains(verticeOrigen)) {
            	vertices.add(verticeOrigen);
                this.cantidadEstaciones++;
            }

            if (!vertices.contains(verticeDestino)) {
            	vertices.add(verticeDestino);
            	this.cantidadEstaciones++;
            }
        }
		
	}

	private void dfsBacktracking(ArrayList<Arco<Integer>> solucionParcial, int cantMtsActual) {
		this.iteraciones ++;
        if (this.union.todosConectados()) {
            if (cantMtsActual < menorCantidadMts) {
            	System.out.println("tuki"+ solucionParcial);
            	menorCantidadMts = cantMtsActual;
            	this.solucion.clear();
            	this.solucion.addAll(new ArrayList<>(solucionParcial));
            	
            }
        } else {
        	Arco<Integer> arcoActual = arcosDisponibles.remove(0);
        	
            //Arco<Integer> arcoInverso = new Arco<Integer> (arcoActual.getVerticeDestino(), arcoActual.getVerticeOrigen(), null);

        	//se decide no usar el arco en la solucion 
        	if(!this.arcosDisponibles.isEmpty()&&!solucionParcial.contains(arcoActual)) {
				dfsBacktracking(solucionParcial, cantMtsActual);
			}
            
            
            //se decide usar el arco en la solucion siempre y cuando sea factible
            if(solucionParcial.size() < this.cantidadEstaciones) {
            	Integer v1 = arcoActual.getVerticeOrigen();
        		Integer v2 = arcoActual.getVerticeDestino();
        		int distancia = arcoActual.getEtiqueta();
            	if(cantMtsActual + distancia < menorCantidadMts) {
            		if(!solucionParcial.contains(arcoActual)&&
            				!this.union.estanConectadas(v1, v2)) { //esto es mas que nada para evitar arcos inversos
            			
            			cantMtsActual += distancia;
            			solucionParcial.add(arcoActual);
            			UnionFind old_uf = new UnionFind(this.union);
            			union.unir(v1, v2);
            			if(this.union.todosConectados() ||
            					!this.arcosDisponibles.isEmpty()) {
            				
            				dfsBacktracking(solucionParcial, cantMtsActual);
            			}
            			this.union = old_uf;
            			cantMtsActual -= distancia;
            			solucionParcial.remove(arcoActual);
            		}
            	}
        	}
            
            arcosDisponibles.add(arcoActual);
        }
    }


 
}

/*
        	Iterator<Arco<Integer>> it = this.grafo.obtenerArcos();
	        while (it.hasNext()) {
	            Arco<Integer> arco = it.next();
	            Integer v1 = arco.getVerticeOrigen();
	            Integer v2 = arco.getVerticeDestino();
	            Integer distancia = arco.getEtiqueta();

	            // Si aún no agregué el arco y aún no visité v1
	            if (!solucionParcial.contains(arco)) {
	                cantMtsActual += distancia;
	                solucionParcial.add(arco);
	                
	                UnionFind old_uf = new UnionFind(this.union);
        			union.unir(v1, v2);
        			
	                dfsBacktracking(solucionParcial, cantMtsActual);
	                
	                this.union = old_uf;
	                solucionParcial.remove(solucionParcial.size() - 1);
	                cantMtsActual -= distancia;
	            }
	        }*/
