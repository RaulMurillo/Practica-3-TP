package tp.pr3.logica;

import java.util.NoSuchElementException;
import java.util.Scanner;

import tp.pr3.control.PalabraIncorrecta;

/**
 * Clase que especializa a Mundo.
 * 
 * @version 1.0, 15/01/2016
 * @author Raul Murillo Montero
 * @author Antonio Valdivia de la Torre
 *
 */
public class MundoSimple extends Mundo {

	/**
	 * Constructora de la clase. Crea un mundo con los parametros dados.
	 * 
	 * @param filas
	 *            Numero de filas del mundo.
	 * @param columnas
	 *            Numero de columnas que tendra la superficie del mundo.
	 * @param numCelulas
	 *            Numero de celulas simples que tendra al cominezo la superficie
	 *            del mundo.
	 */
	public MundoSimple(int filas, int columnas, int numCelulas) {
		super(filas, columnas);
		this.numSimples = numCelulas;
		inicializaMundo();
	}

	@Override
	public void inicializaMundo() {
		boolean aleatorio[] = new boolean[filas * columnas];
		/*
		 * Se crea un array de booleans con numCels elementos a true, y se
		 * barajea aleatoriamente. El resultado indicará la posición de las
		 * células iniciales de la superficie.
		 */
		for (int i = 0; i < numSimples; i++) {
			aleatorio[i] = true;
		}
		boolean aux;
		int rnd;
		int i = filas * columnas;
		/*
		 * Utilizamos una variante del algoritmo de Fisher-Yates para conseguir
		 * un barajado eficiente y sin desviaciones en las posibles
		 * permutaciones (todas son asi equiprobables).
		 * (https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle)
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
			if (aleatorio[i])
				crearCelulaSimple(cas);
		}
	}

	@Override
	public void cargar(Scanner entrada) throws PalabraIncorrecta {
		String s;
		try {
			while ((s = entrada.next()) != null) {
				int f = Integer.parseInt(s);
				int c = Integer.parseInt(entrada.next());
				if (f < 0 || f >= filas || c < 0 || c >= columnas) {
					throw new PalabraIncorrecta();
				}
				s = entrada.next();
				Celula celula;
				if (s.equals("simple")) {
					celula = new CelulaSimple();
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
