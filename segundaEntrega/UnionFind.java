package segundaEntrega;

public class UnionFind {
    private int[] padre;
    private int[] rango;

    public UnionFind(int tamaño) {
        padre = new int[tamaño];
        rango = new int[tamaño];
        for (int i = 0; i < tamaño; i++) {
            padre[i] = i;
            rango[i] = 0;
        }
    }

    public UnionFind(UnionFind o) {
		int[] old_padre = o.getPadre();
		padre = new int[old_padre.length];
		for (int i = 0; i < old_padre.length; i++) {
			padre[i] = old_padre[i];
		}

		int[] old_rango = o.getRango();
		rango = new int[old_rango.length];
		for (int i = 0; i < old_rango.length; i++) {
			rango[i] = old_rango[i];
		}
	}
    
    private int[] getPadre() {
		// TODO Esbozo de método generado automáticamente
		return this.padre;
	}

	private int[] getRango() {
		return this.rango;
	}

	public int encontrar(int x) {
        if (padre[x] != x) {
            padre[x] = encontrar(padre[x]);
        }
        return padre[x];
    }

    public void unir(int x, int y) {
        int raizX = encontrar(x);
        int raizY = encontrar(y);
        if (raizX != raizY) {
            if (rango[raizX] < rango[raizY]) {
                padre[raizX] = raizY;
            } else if (rango[raizX] > rango[raizY]) {
                padre[raizY] = raizX;
            } else {
                padre[raizY] = raizX;
                rango[raizX]++;
            }
        }
    }

    public boolean estanConectadas(int x, int y) {
        return encontrar(x) == encontrar(y);
    }
    
   

    public boolean todosConectados() {
        int root = encontrar(1); 
       
        for (int i = 2; i < padre.length; i++) {
            if (encontrar(i) != root) {
                return false; 
            }
        }
        return true;
    }
    
    public boolean estaConectada(int x) {
    	return this.padre[x] != x;
    }

    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < padre.length; i++) {
            sb.append("Element: ").append(i).append(", Parent: ").append(padre[i]).append(", Rank: ").append(rango[i]).append("\n");
        }
        return sb.toString();
    }
    
}
