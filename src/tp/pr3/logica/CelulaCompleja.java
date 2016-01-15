package tp.pr3.logica;

import java.io.PrintWriter;
import java.util.Scanner;

import tp.pr3.control.PalabraIncorrecta;

/**
 * Clase que especializa la clase Celula. Representa una celulas complejas del
 * mundo. Contiene un atributo privado para contabilizar cuantas celulas se ha
 * comido y una constante para saber si se ha comido el maximo posible de
 * celulas.
 * 
 * @version 3.0, 15/01/2016
 * @author Raul Murillo Montero
 * @author Antonio Valdivia de la Torre
 */
public class CelulaCompleja implements Celula {
	// Numero maximo de celulas que puede comer antes de morir.
	public final int MAX_COMER = 2;
	// Contador de celulas comidas.
	private int comidas;

	/**
	 * Constructor de la clase. Crea una celula compleja con su contador a 0.
	 */
	public CelulaCompleja() {
		comidas = 0;
	}

	/**
	 * Incrementa el contador de celulas comidas e indica si la celula debe
	 * morir.
	 * 
	 * @return true si se ha comido el maximo de celulas posibles.
	 */
	public boolean comidas() {
		comidas++;
		return comidas == MAX_COMER;
	}

	@Override
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
			} else { // if (destino esta libre)
				superficie.moverA(origen, destino);
				System.out.print(" --NO COME--" + '\n');
			}
		}
		return destino;
	}

	@Override
	public String toString() {
		return "*";
	}

	@Override
	public Casilla casillaLibre(Casilla origen, Superficie superficie) {
		int filas = superficie.getFilas();
		int columnas = superficie.getColumnas();
		int aleatorio = (int) (Math.random() * filas * columnas - 1);
		if (aleatorio >= origen.getFila() * columnas + origen.getColumna())
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

	@Override
	public void guardar(PrintWriter salida) {
		salida.println("compleja " + this.comidas);
	}

	@Override
	public void cargar(Scanner entrada) throws PalabraIncorrecta {
		try {
			comidas = Integer.parseInt(entrada.next());
			if (comidas < 0 || comidas >= MAX_COMER)
				throw new PalabraIncorrecta();
		} catch (NumberFormatException e) {
			throw new PalabraIncorrecta();
		}
	}

}
