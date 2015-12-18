package tp.pr3.logica;

/**
 * Es una clase abstracta de la que heredan las clases concretas
 * celula compleja y celula simple.
 * 
 * @version 1.0, 07/11/2015
 * @author Raul Murillo Montero
 * @author Antonio Valdivia de la Torre
 */

abstract public class Celula {
	/* Indica si una celula es simple(comestible)o no(compleja). */
	protected boolean esComestible;

	/**
	 * Crea una celula nueva.
	 */
	public Celula() {
	}

	/**
	 * Realiza el movimiento de una celula colocada en la posicion 
	 * (f, c) de la superficie.
	 * @param f coordenada fila
	 * @param c coordenada columna
	 * @param superficie superficie donde se encuentra la celula
	 * @return casilla a la que se ha movido la celula, null en
	 *  caso contrario
	 */
	public abstract Casilla ejecutaMovimiento(int f, int c,
			Superficie superficie);

	/**
	 * Indica si la celula es comestible o no.
	 * 
	 * @return true si la celula es simple, false si es compleja
	 */
	public abstract boolean esComestible();

}
