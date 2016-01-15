package tp.pr3.logica;

import java.util.NoSuchElementException;
import java.util.Scanner;

import tp.pr3.control.PalabraIncorrecta;

/**
 * Clase que especializa a Mundo. Contiene como atributo propio en numero de
 * celulas complejas iniciales.
 * 
 * @version 1.0, 15/01/2016
 * @author Raul Murillo Montero
 * @author Antonio Valdivia de la Torre
 *
 */
public class MundoComplejo extends Mundo {
	// Nº de células complejas con las que se inicia la superficie.
	private int numComplejas;

	/**
	 * Constructora de la clase. Crea un mundo con los parametros dados.
	 * 
	 * @param filas
	 *            Numero de filas del mundo.
	 * @param columnas
	 *            Numero de columnas que tendra la superficie del mundo.
	 * @param numSimples
	 *            Numero de celulas simples que tendra al cominezo la superficie
	 *            del mundo.
	 * @param numComplejas
	 *            Numero de celulas complejas que tendra al cominezo la
	 *            superficie del mundo.
	 */
	public MundoComplejo(int filas, int columnas, int numSimples, int numComplejas) {
		super(filas, columnas);
		this.numSimples = numSimples;
		this.numComplejas = numComplejas;
		inicializaMundo();

	}

	@Override
	public void inicializaMundo() {
		int aleatorio[] = new int[filas * columnas];
		/*
		 * Se crea un array de enteros con numSimples celulas simples y
		 * numComplejas celulas complejas, y se barajea aleatoriamente. El
		 * resultado indicará la posición de las células iniciales de la
		 * superficie.
		 */
		for (int i = 0; i < numSimples; i++) {
			aleatorio[i] = 1;
		}
		for (int i = numSimples; i < numSimples + numComplejas; i++) {
			aleatorio[i] = 2;
		}
		for (int i = numSimples + numComplejas; i < filas * columnas; i++) {
			aleatorio[i] = 0;
		}
		int aux;
		int rnd;
		int i = filas * columnas;
		/*
		 * Utilizamos una variante del algoritmo de Fisher-Yates para conseguir
		 * un barajado eficiente y sin desviaciones en las posibles
		 * permutaciones (todas son asi equiprobables).
		 * https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle
		 */
		while (i > 1) {
			i--;
			rnd = (int) (Math.random() * (i + 1));
			aux = aleatorio[i];
			aleatorio[i] = aleatorio[rnd];
			aleatorio[rnd] = aux;
		}

		// Se crean células en las posiciones indicadas.
		for (i = 0; i < filas * columnas; i++) {
			// Se entiende que la matriz no es vacía (0x0)
			Casilla cas = new Casilla(i / columnas, i % columnas);
			if (aleatorio[i] == 1)
				crearCelulaSimple(cas);
			else if (aleatorio[i] == 2)
				crearCelulaCompleja(cas);
		}
	}

	/**
	 * Dadas una casilla, crea una celula compleja en dicha posicion de la
	 * superficie.
	 * 
	 * @param casilla
	 *            Casilla donde crear la celula.
	 * @return true si se ha creado la celula, false en caso contrario.
	 */
	public boolean crearCelulaCompleja(Casilla casilla) {
		return superficie.crearCelulaCompleja(casilla);
	}

	@Override
	public void cargar(Scanner entrada) throws PalabraIncorrecta {
		String s;
		try {
			while ((s = entrada.next()) != null) {
				int f = Integer.parseInt(s);
				int c = Integer.parseInt(entrada.next());
				if (f < 0 || f >= filas || c < 0 || c >= columnas)
					throw new PalabraIncorrecta();
				s = entrada.next();
				Celula celula;
				if (s.equals("simple")) {
					celula = new CelulaSimple();
				} else if (s.equals("compleja")) {
					celula = new CelulaCompleja();
				} else
					throw new PalabraIncorrecta();
				Casilla casilla = new Casilla(f, c);
				superficie.cargarCelula(entrada, casilla, celula);
			}
		} catch (NumberFormatException e) {
			throw new PalabraIncorrecta();
		} catch (NoSuchElementException e) {
		} // Fin de archivo.
	}
}
