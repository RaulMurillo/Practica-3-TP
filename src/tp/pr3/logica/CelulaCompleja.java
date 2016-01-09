package tp.pr3.logica;

/**
 * Esta clase representa una celula compleja del mundo. Contiene un atributo
 * privado para contabilizar cuantas celulas se ha comido y una constante
 * publica para ver si se ha comido el maximo posible de celulas.
 * 
 * @version 2.0, 11/12/2015
 * @author Raul Murillo Montero
 * @author Antonio Valdivia de la Torre
 */
public class CelulaCompleja extends Celula {
	public final int MAX_COMER = 2;
	private int comidas;

	/**
	 * Crea una celula compleja.
	 */
	public CelulaCompleja() {
		esComestible = false;
		comidas = 0;
	}

	/**
	 * Incrementa el contador de celulas comidas e indica si debe morir.
	 * 
	 * @return true si se ha comido el máximo de celulas posibles.
	 */
	public boolean comidas() {
		comidas++;
		return comidas == MAX_COMER;
	}

	/**
	 * Realiza el movimiento de una celula compleja colocada en la posicion
	 * (f,c).
	 * 
	 * @return la casilla a la que se ha movido la celula, o null en caso de que
	 *         no se mueva la celula.
	 */
	public Casilla ejecutaMovimiento(int f, int c, Superficie superficie) {
		Casilla origen = new Casilla(f, c);
		Casilla destino = casillaLibre(origen, superficie);
		if (destino != null) {
			System.out.print("Celula compleja en " + origen + " se mueve" + " a " + destino);
			if (!superficie.vacia(destino)) {
				// Si la casilla no esta vacia debe haber una celula simple
				// Si no se habria devuelto null
				superficie.moverA(origen, destino);
				System.out.print(" --COME--" + '\n');
				if (comidas()) {
					System.out.println("Explota la celula compleja en " + destino);
					superficie.eliminarCelula(destino);
				}
			} else { /* if (destino esta libre) */
				superficie.moverA(origen, destino);
				System.out.print(" --NO COME--" + '\n');
			}
		}
		return destino;
	}

	/**
	 * Indica que la celula es compleja.
	 * 
	 * @return false
	 */
	public boolean esComestible() {
		return esComestible;
	}

	/**
	 * Muestra una celula compleja.
	 * 
	 * @return * (celula compleja)
	 */
	public String toString() {
		return "*";
	}
	
	/**
	 * Devuelve una casilla de destino para moverse
	 * @param origen
	 * @param superficie
	 * @return casilla de destino, null si no puede moverse.
	 */
	private Casilla casillaLibre (Casilla origen, Superficie superficie){
		int filas = superficie.getFilas();
		int columnas = superficie.getColumnas();
		int aleatorio = (int) (Math.random() * filas * columnas - 1);
		if (aleatorio >= origen.getX() * columnas + origen.getY())
			aleatorio++; /*
							 * En caso de que la casilla aleatoria este por
							 * delante o sea la casilla donde se encuentra la
							 * celula a mover, se suma uno, para corregir la
							 * posicion.
							 */
		Casilla destino = new Casilla(aleatorio / columnas, aleatorio % columnas);
		// En la casilla destino hay una celula compleja
		if (!superficie.vacia(destino) && !superficie.esComestible(destino))
			destino = null;
		return destino;
	}
}
