package segundaEntrega;

import java.util.ArrayList;

import primeraEntrega.Arco;

public class Backtracking{
    private ArrayList<Arco<Integer>> solucion;
    private int menorCantidadMts;
    private ArrayList<Arco<Integer>> arcosDisponibles;
    private UnionFind union;
    private int iteraciones;
    private int cantidadEstaciones;
    
    public Backtracking() {
    	this.solucion = new ArrayList<Arco<Integer>>();
    	this.menorCantidadMts = Integer.MAX_VALUE;
    	this.arcosDisponibles =  new ArrayList<Arco<Integer>>();
    	this.iteraciones = 0;
    }

    public int getIteraciones() {
		return this.iteraciones;
	}
    
    public int getCantidadMts() {
    	return this.menorCantidadMts;
    }
    
    public ArrayList<Arco<Integer>> construirMenorCantidadMts(ArrayList<Arco<Integer>> arcosDisponibles) {
    	Timer timer = new Timer();
    	timer.start();
    	
    	this.arcosDisponibles = arcosDisponibles;
    	cargarVertices(arcosDisponibles);
    	this.union = new UnionFind(this.cantidadEstaciones+1);
        backtracking(new ArrayList<Arco<Integer>>(), 0 );
        
        
        System.out.println("tiempo en milisegundos: "+ timer.stop());
        
        return solucion;
    }


	private void backtracking(ArrayList<Arco<Integer>> solucionParcial, int cantMtsActual) {
		this.iteraciones ++;
        if (this.union.todosConectados()) {
            if (cantMtsActual < menorCantidadMts) {
            	menorCantidadMts = cantMtsActual;
            	this.solucion.clear();
            	this.solucion.addAll(new ArrayList<>(solucionParcial));
            	
            }
        } else {
        	Arco<Integer> arcoActual = arcosDisponibles.remove(0);
        
        	//se decide no usar el arco en la solucion 
        	if(!this.arcosDisponibles.isEmpty()&&!solucionParcial.contains(arcoActual)) {
				backtracking(solucionParcial, cantMtsActual);
			}
            
            
            //se decide usar el arco en la solucion siempre y cuando sea factible
            if(solucionParcial.size() < this.cantidadEstaciones) {
            	Integer v1 = arcoActual.getVerticeOrigen();
        		Integer v2 = arcoActual.getVerticeDestino();
        		int distancia = arcoActual.getEtiqueta();
            	if(cantMtsActual + distancia < menorCantidadMts) {
            		if(!solucionParcial.contains(arcoActual)&&
            				!this.union.estanConectadas(v1, v2)) { 
            			
            			cantMtsActual += distancia;
            			solucionParcial.add(arcoActual);
            			UnionFind old_uf = new UnionFind(this.union);
            			union.unir(v1, v2);
            			
            			if(!this.arcosDisponibles.isEmpty()) {
            				
            				backtracking(solucionParcial, cantMtsActual);
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

 
}

