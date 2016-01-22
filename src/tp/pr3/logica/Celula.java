package tp.pr3.logica;

import java.io.*;
import java.util.Scanner;
import tp.pr3.control.PalabraIncorrecta;

/**
 * Interfaz de la que heredan las clases CelulaCompleja y CelulaSimple.
 * 
 * @version 3.0, 15/01/2016
 * @author Raul Murillo Montero
 * @author Antonio Valdivia de la Torre
 */
interface Celula {

	/**
	 * Realiza el movimiento de la celula colocada en la posicion (f, c) de la
	 * superficie, siguiendo las regals establecidas para las celulas de cada
	 * tipo.
	 * 
	 * @param f
	 *            Coordenada fila.
	 * @param c
	 *            Coordenada columna.
	 * @param superficie
	 *            Superficie donde se encuentra la celula.
	 * @return Casilla a la que se ha movido la celula, null en caso contrario.
	 */
	public abstract Casilla ejecutaMovimiento(int f, int c, Superficie superficie);

	/**
	 * Devuelve una casilla de destino para moverse, de acuerdo con las
	 * posibilidades de cada celula.
	 * 
	 * @param origen
	 *            Casilla donde se encuantra la celula a mover.
	 * @param superficie
	 *            Superficie donde se encuentra la celula.
	 * @return Casilla de destino, null si no puede moverse.
	 */
	public abstract Casilla casillaLibre(Casilla origen, Superficie superficie);

	/**
	 * Muestra la celula.
	 * 
	 * @return X para celulas simples, * para celulas complejas.
	 */
	public abstract String toString();

	/**
	 * Dado un flujo de escritura, guarda la informacion de la celula.
	 * 
	 * @param salida
	 *            Flujo de escritura.
	 */
	public abstract void guardar(PrintWriter salida);

	/**
	 * Dado un flujo de lectura, carga la celula con la informacion que se lee.
	 * 
	 * @param entrada
	 *            Flujo de lectura.
	 * @throws PalabraIncorrecta
	 */
	public abstract void cargar(Scanner entrada) throws PalabraIncorrecta;
	
	/**
	 * Indica si una casilla contiene una celula comestible (simple).
	 * 
	 * @return true si la celula de la es simple.
	 */
	public abstract boolean esComestible();

}
