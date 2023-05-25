package src;

public class Arco<T> {
	private int verticeOrigen;
	private int verticeDestino;
	private T etiqueta;

	public Arco(int verticeOrigen, int verticeDestino, T etiqueta) {
		this.verticeOrigen = verticeOrigen;
		this.verticeDestino = verticeDestino;
		this.etiqueta = etiqueta;
	}
	
	public int getVerticeOrigen() {
		return this.verticeOrigen;
	}
	
	public int getVerticeDestino() {
		return this.verticeDestino;
	}

	public T getEtiqueta() {
		return this.etiqueta;
	}
	
	public boolean equals(Object o) {
		try {
			Arco<T> nuevo=(Arco<T>)o;
			return nuevo.getVerticeDestino()==this.getVerticeDestino() &&
				nuevo.getVerticeOrigen()==this.getVerticeOrigen();
		}catch(Exception e){
			return false;
		}
	}
	
}
