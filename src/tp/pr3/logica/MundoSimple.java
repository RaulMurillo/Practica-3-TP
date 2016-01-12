package tp.pr3.logica;

public class MundoSimple extends Mundo {

	public MundoSimple(int filas, int columnas, int numCelulas) {
		super(filas, columnas);
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
	public void guardar() {
		// TODO Auto-generated method stub

	}

}
