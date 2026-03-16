/**
 * Clase GestionPedidos
 * Controlador principal que coordina las dos pilas manuales para implementar
 * la funcionalidad Undo/Redo del sistema Pizza-Track.
 *
 * Arquitectura de dos pilas:
 *
 *   PILA PRINCIPAL (Undo)      PILA SECUNDARIA (Redo)
 *   =====================      ======================
 *   [Último pedido]  <--TOPE   [Último deshecho] <--TOPE
 *   [Pedido 2]                 [...]
 *   [Pedido 1]
 *
 * Flujo de operaciones:
 *   - Registrar: push() a pila principal, se limpia pila redo
 *   - Undo:      pop() de principal --> push() a secundaria
 *   - Redo:      pop() de secundaria --> push() a principal
 */
public class GestionPedidos {

    // Pila principal: almacena los pedidos activos (permite Deshacer)
    private PilaManual pilaUndo;

    // Pila secundaria: almacena pedidos deshechos (permite Rehacer)
    private PilaManual pilaRedo;

    /**
     * Constructor: inicializa ambas pilas vacías.
     */
    public GestionPedidos() {
        this.pilaUndo = new PilaManual();
        this.pilaRedo = new PilaManual();
    }

    // ----------------------------------------------------------------
    // REGISTRAR PEDIDO
    // ----------------------------------------------------------------
    /**
     * Registra un nuevo pedido en el sistema.
     * - Hace push() a la pila principal.
     * - Vacía la pila Redo (un nuevo pedido cancela el historial de rehacer).
     *
     * @param pizza La pizza a registrar
     */
    public void registrarPedido(Pizza pizza) {
        pilaUndo.push(pizza);
        // Al registrar un nuevo pedido, el historial de redo se invalida
        limpiarRedo();
        System.out.println("\n✅ Pedido registrado: " + pizza);
    }

    /**
     * Vacía la pila Redo descartando todos sus elementos.
     * Se usa cuando se hace un nuevo registro tras haber deshecho pedidos.
     */
    private void limpiarRedo() {
        while (!pilaRedo.isEmpty()) {
            pilaRedo.pop();
        }
    }

    // ----------------------------------------------------------------
    // DESHACER (UNDO)
    // ----------------------------------------------------------------
    /**
     * Deshace el último pedido registrado.
     * - pop() de la pila principal
     * - push() del mismo pedido a la pila secundaria (Redo)
     *
     * @return true si se pudo deshacer, false si no hay pedidos
     */
    public boolean deshacer() {
        if (pilaUndo.isEmpty()) {
            System.out.println("\n⚠️  No hay pedidos para deshacer.");
            return false;
        }
        Pizza pizzaDeshecha = pilaUndo.pop(); // Sacar de la pila principal
        pilaRedo.push(pizzaDeshecha);          // Mover a la pila de redo
        System.out.println("\n↩️  Pedido deshecho: " + pizzaDeshecha);
        return true;
    }

    // ----------------------------------------------------------------
    // REHACER (REDO)
    // ----------------------------------------------------------------
    /**
     * Rehace el último pedido deshecho.
     * - pop() de la pila secundaria (Redo)
     * - push() del mismo pedido de regreso a la pila principal
     *
     * @return true si se pudo rehacer, false si no hay pedidos para rehacer
     */
    public boolean rehacer() {
        if (pilaRedo.isEmpty()) {
            System.out.println("\n⚠️  No hay pedidos para rehacer.");
            return false;
        }
        Pizza pizzaRecuperada = pilaRedo.pop(); // Sacar de la pila redo
        pilaUndo.push(pizzaRecuperada);          // Devolver a la pila principal
        System.out.println("\n↪️  Pedido recuperado: " + pizzaRecuperada);
        return true;
    }

    // ----------------------------------------------------------------
    // MOSTRAR PEDIDO ACTUAL (PEEK)
    // ----------------------------------------------------------------
    /**
     * Muestra el pedido en el tope de la pila (el próximo a producir).
     * Usa peek() para no modificar la pila.
     */
    public void mostrarPedidoActual() {
        Pizza actual = pilaUndo.peek();
        if (actual == null) {
            System.out.println("\n⚠️  No hay pedidos activos en este momento.");
        } else {
            System.out.println("\n🔍 Pedido actual (próximo a producir):");
            System.out.println("   " + actual);
        }
    }

    // ----------------------------------------------------------------
    // MOSTRAR ESTADO COMPLETO DEL SISTEMA
    // ----------------------------------------------------------------
    /**
     * Muestra el estado de ambas pilas para tener visibilidad completa del sistema.
     */
    public void mostrarEstado() {
        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println("║         ESTADO DEL SISTEMA               ║");
        System.out.println("╠══════════════════════════════════════════╣");
        System.out.println("║  📋 PEDIDOS ACTIVOS (Pila Undo): " + pilaUndo.getTamanio() + " item(s)");
        pilaUndo.mostrarTodos();
        System.out.println("║  🗑️  PEDIDOS DESHECHOS (Pila Redo): " + pilaRedo.getTamanio() + " item(s)");
        pilaRedo.mostrarTodos();
        System.out.println("╚══════════════════════════════════════════╝");
    }
}
