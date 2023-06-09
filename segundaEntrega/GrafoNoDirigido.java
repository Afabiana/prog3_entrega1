package segundaEntrega;
import primeraEntrega.GrafoDirigido;

public class GrafoNoDirigido<T> extends GrafoDirigido<T> {
  //borro de ida y de vuelta
  @Override
  public void agregarArco(int verticeId1, int verticeId2, T etiqueta){
	  super.agregarArco(verticeId1, verticeId2, etiqueta);
	  super.agregarArco(verticeId2, verticeId1, etiqueta);
  }

  //borro de ida y de vuelta
  @Override
  public void borrarArco(int verticeId1, int verticeId2) {
	  super.borrarArco(verticeId1, verticeId2);
	  super.borrarArco(verticeId2, verticeId1);
  }

  @Override
  public int cantidadArcos(){
	  return (super.cantidadArcos() / 2);
  }
}
