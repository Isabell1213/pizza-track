/**
 * Clase PilaManual
 * Implementación de una pila (stack) desde cero usando una lista ligada de Nodos.
 *
 * Restricción cumplida: NO se usa java.util.Stack ni ninguna colección de Java.
 *
 * Visualización de la estructura (la pila crece hacia arriba):
 *
 *   TOPE --> [NodoA | sig] --> [NodoB | sig] --> [NodoC | null]
 *
 * - push(): agrega un nuevo nodo al TOPE (inicio de la lista)
 * - pop():  retira el nodo del TOPE y redirige el puntero al siguiente
 * - peek(): consulta el dato del TOPE sin modificar la estructura
 */
public class PilaManual {

    // Puntero al nodo en el tope de la pila (el más recientemente insertado)
    private Nodo tope;

    // Contador de elementos en la pila
    private int tamanio;

    /**
     * Constructor: inicializa la pila vacía.
     * El tope apunta a null porque no hay ningún nodo todavía.
     */
    public PilaManual() {
        this.tope = null;
        this.tamanio = 0;
    }

    // ----------------------------------------------------------------
    // MÉTODO push() - Insertar un elemento en el tope de la pila
    // ----------------------------------------------------------------
    /**
     * Agrega una pizza al tope de la pila.
     *
     * Lógica de punteros:
     *   1. Se crea un nuevo nodo con la pizza recibida.
     *   2. El puntero "siguiente" del nuevo nodo apunta al actual tope.
     *   3. El tope de la pila se redirige al nuevo nodo.
     *
     * Antes: TOPE --> [B | sig--> C]
     * push(A)
     * Después: TOPE --> [A | sig--> B] --> [B | sig--> C]
     *
     * @param pizza La pizza a insertar en el tope
     */
    public void push(Pizza pizza) {
        Nodo nuevoNodo = new Nodo(pizza);  // 1. Crear el nuevo nodo
        nuevoNodo.siguiente = tope;        // 2. El nuevo nodo apunta al tope actual
        tope = nuevoNodo;                  // 3. El tope ahora es el nuevo nodo
        tamanio++;
    }

    // ----------------------------------------------------------------
    // MÉTODO pop() - Retirar el elemento del tope de la pila
    // ----------------------------------------------------------------
    /**
     * Retira y devuelve la pizza que está en el tope de la pila.
     *
     * Lógica de punteros:
     *   1. Se guarda el dato del nodo actual en el tope.
     *   2. El tope se redirige al nodo siguiente (el que estaba debajo).
     *   3. El nodo antiguo queda sin referencias y será recolectado por el GC.
     *
     * Antes: TOPE --> [A | sig--> B] --> [B | sig--> C]
     * pop() devuelve A
     * Después: TOPE --> [B | sig--> C]
     *
     * @return La pizza retirada del tope, o null si la pila está vacía
     */
    public Pizza pop() {
        if (isEmpty()) {
            return null; // No hay nada que retirar
        }
        Pizza pizzaRetirada = tope.dato; // 1. Guardar el dato del tope
        tope = tope.siguiente;           // 2. Mover el tope al nodo siguiente
        tamanio--;
        return pizzaRetirada;            // 3. Retornar el dato guardado
    }

    // ----------------------------------------------------------------
    // MÉTODO peek() - Consultar el tope sin modificar la pila
    // ----------------------------------------------------------------
    /**
     * Devuelve la pizza en el tope sin retirarla.
     * La estructura de la pila permanece intacta.
     *
     * @return La pizza en el tope, o null si la pila está vacía
     */
    public Pizza peek() {
        if (isEmpty()) {
            return null;
        }
        return tope.dato; // Solo leemos el dato, no movemos punteros
    }

    // ----------------------------------------------------------------
    // MÉTODO isEmpty() - Verificar si la pila está vacía
    // ----------------------------------------------------------------
    /**
     * Verifica si la pila no contiene ningún elemento.
     * Una pila está vacía cuando el tope apunta a null.
     *
     * @return true si la pila está vacía, false en caso contrario
     */
    public boolean isEmpty() {
        return tope == null;
    }

    /**
     * Devuelve el número de elementos actualmente en la pila.
     */
    public int getTamanio() {
        return tamanio;
    }

    /**
     * Muestra todos los pedidos en la pila de arriba hacia abajo.
     * Recorre la lista ligada siguiendo los punteros "siguiente".
     */
    public void mostrarTodos() {
        if (isEmpty()) {
            System.out.println("   (Pila vacía)");
            return;
        }
        // Recorremos desde el tope hasta el fondo siguiendo los punteros
        Nodo actual = tope;
        int posicion = 1;
        while (actual != null) {
            System.out.println("   " + posicion + ". " + actual.dato);
            actual = actual.siguiente; // Avanzar al siguiente nodo
            posicion++;
        }
    }
}
