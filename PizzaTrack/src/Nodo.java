/**
 * Clase Nodo
 * Representa cada elemento (nodo) de la lista ligada que forma la pila manual.
 *
 * Estructura de la lista ligada:
 *   [Nodo1 | siguiente] --> [Nodo2 | siguiente] --> [Nodo3 | null]
 *
 * El tope de la pila siempre apunta al primer nodo (el más reciente).
 */
public class Nodo {

    // El dato que guarda este nodo: un objeto Pizza
    Pizza dato;

    // Puntero al siguiente nodo en la lista
    // Si es null, significa que este es el último nodo (fondo de la pila)
    Nodo siguiente;

    /**
     * Constructor del nodo.
     * @param dato La pizza que este nodo almacenará
     */
    public Nodo(Pizza dato) {
        this.dato = dato;
        this.siguiente = null; // Por defecto no apunta a ningún nodo
    }
}
