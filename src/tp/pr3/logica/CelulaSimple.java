package tp.pr3.logica;

import java.io.PrintWriter;
import java.util.Scanner;

import tp.pr3.control.PalabraIncorrecta;

/**
 * Clase que especializa la clase Celula. Representa una celula simple del
 * mundo. Contiene atributos privados para contabilizar el numero de pasos da la
 * celula (tanto si se ha movido como si no) y cuantos lleva sin moverse.
 * 
 * @version 3.0, 15/01/2016
 * @author Raul Murillo Montero
 * @author Antonio Valdivia de la Torre
 */

public class CelulaSimple implements Celula {
	// Contador de pasos dados en el mundo.
	private int pasosDados;
	// Contador de pasos sin moverse.
	private int pasosNoMovidos;
	// Nº máximo de pasos que puede estar una célula sin moverse antes de morir.
	public final int MAX_PASOS_SIN_MOVER = 3;
	// Nº de pasos que debe dar una celula para reproducirse.
	public final int PASOS_REPRODUCCION = 2;

	/**
	 * Constructor de la clase. Crea una nueva celula simple, con todos sus
	 * contadores a 0.
	 */
	public CelulaSimple() {
		pasosDados = 0;
		pasosNoMovidos = 0;
	}

	/**
	 * Incrementa el numero de pasos que ha dado una celula (se haya movido o
	 * no) e indica si le toca reproducirse.
	 * 
	 * @return true si le toca reproducirse.
	 */
	public boolean incPasDad() { // Pasos reproducción
		pasosDados++;
		if (pasosDados % PASOS_REPRODUCCION == 0)
			return true;
		else
			return false;
	}

	/**
	 * Indica si la celula debe morir por inactividad. Si no, incrementa el
	 * numero de pasos que lleva la celula sin moverse.
	 * 
	 * @return true si ha llegado al maximo permitido y la celula debe morir.
	 */
	public boolean incPasNoMov() {
		pasosNoMovidos++;
		return pasosNoMovidos == MAX_PASOS_SIN_MOVER;
	}

	@Override
	public Casilla ejecutaMovimiento(int f, int c, Superficie superficie) {
		Casilla origen = new Casilla(f, c);
		Casilla destino = casillaLibre(origen, superficie);
		if (destino == null) { // No se puede mover
			// Le toca reproducirse.
			if (incPasDad()) {
				superficie.eliminarCelula(origen);
				System.out.println("Muere la celula de la casilla " + origen + " por no reproducirse");
			}
			// No le toca reproducirse. Aumentan sus pasos no dados.
			else if (incPasNoMov()) { // Decide si debe morir
				superficie.eliminarCelula(origen);
				System.out.println("Muere la celula de la casilla " + origen + " por falta de actividad.");
			}
		} else/* if (destino != null) */ { // Se puede mover
			superficie.moverA(origen, destino);
			System.out.println("Movimiento de " + origen + " a " + destino);
			// Le toca reproducirse.
			if (incPasDad()) {
				superficie.crearCelulaSimple(origen);
				System.out.println("Nace nueva celula en " + origen + " cuyo padre ha sido " + destino);
			}
			// No le toca reproducirse.
			// No hace nada.
		}
		return destino;
	}

	@Override
	public String toString() {
		return "X";
	}

	@Override
	public Casilla casillaLibre(Casilla origen, Superficie superficie) {
		int filas = superficie.getFilas();
		int columnas = superficie.getColumnas();
		Casilla destino = null;
		Casilla[] libres = new Casilla[filas * columnas - 1];
		int cont = 0;
		int i = origen.getFila() - 1;
		int j = origen.getColumna() - 1;
		int p;
		if (i < 0)
			i = 0;
		if (j < 0)
			j = 0;
		// Genera el array de casillas libres posibles.
		while (i < filas && i <= origen.getFila() + 1) {
			p = j;
			while (p < columnas && p <= origen.getColumna() + 1) {
				Casilla aux = new Casilla(i, p);
				if (superficie.vacia(aux)) {
					libres[cont] = new Casilla(i, p);
					cont++;
				}
				p++;
			}
			i++;
		}
		if (cont != 0) {
			int aleatorio = (int) (Math.random() * cont);
			destino = new Casilla(libres[aleatorio].getFila(), libres[aleatorio].getColumna());
		}
		return destino;
	}

	@Override
	public void guardar(PrintWriter salida) {
		salida.println("simple " + this.pasosDados + " " + this.pasosNoMovidos);
	}

	@Override
	public void cargar(Scanner entrada) throws PalabraIncorrecta {
		try {
			pasosDados = Integer.parseInt(entrada.next());
			pasosNoMovidos = Integer.parseInt(entrada.next());
			if (pasosDados < 0 || pasosNoMovidos < 0 || pasosNoMovidos >= MAX_PASOS_SIN_MOVER)
				throw new PalabraIncorrecta();
		} catch (NumberFormatException e) {
			throw new PalabraIncorrecta();
		}
	}

}
