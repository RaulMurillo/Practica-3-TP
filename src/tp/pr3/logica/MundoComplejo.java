package tp.pr3.logica;

import java.io.BufferedReader;
import java.io.IOException;

import tp.pr3.control.PalabraIncorrecta;

public class MundoComplejo extends Mundo {
	// nº de células simples con las que se inicia la superficie.
	private int numComplejas;

	public MundoComplejo(int filas, int columnas, int numSimples, int numComplejas) {
		super(filas, columnas); // ERROR
		this.numSimples = numSimples;
		this.numComplejas = numComplejas;
		inicializaMundo();// ???

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
	 * Dadas unas coordenadas, crea una celula compleja en dicha posicion de la
	 * superficie.
	 * 
	 * @param f
	 *            coordenada fila.
	 * @param c
	 *            coordenada columna.
	 * @return true si se ha creado la celula.
	 */
	public boolean crearCelulaCompleja(Casilla cas) {
		return superficie.crearCelulaCompleja(cas);
	}

	@Override
	public void cargar(BufferedReader entrada) throws PalabraIncorrecta {
		String s;
		try {
			while ((s = entrada.readLine()) != null) {
				String[] array = s.split("\\s+");
				if (array.length == 5 && array[2].equals("simple")) {
					int f = Integer.parseInt(array[0]);
					int c = Integer.parseInt(array[1]);
					int n = Integer.parseInt(array[3]);
					int m = Integer.parseInt(array[4]);
					if (f < 0 || f >= filas || c < 0 || c >= columnas) {
						throw new PalabraIncorrecta();
					}
					superficie.cargar(f, c, n, m);
				} else if (array.length == 4 && array[2].equals("compleja")) {
					int f = Integer.parseInt(array[0]);
					int c = Integer.parseInt(array[1]);
					int n = Integer.parseInt(array[3]);
					int m = -1;
					
					if (f < 0 || f >= filas || c < 0 || c >= columnas) {
						throw new PalabraIncorrecta();
					}
					superficie.cargar(f, c, n, m);// m=-1
				} else{
					System.out.println("X");
					throw new PalabraIncorrecta();
				}
			}
		} catch (IOException e) {
			throw new PalabraIncorrecta();
		} catch (NumberFormatException e) {
			throw new PalabraIncorrecta();
		}
	}
}
