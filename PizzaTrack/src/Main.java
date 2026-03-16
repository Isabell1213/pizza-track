import java.util.Scanner;

/**
 * Clase Main - Punto de entrada del sistema Pizza-Track
 *
 * Presenta un menú interactivo en consola con las siguientes opciones:
 *   1. Registrar Pizza
 *   2. Deshacer (Undo)
 *   3. Rehacer (Redo)
 *   4. Mostrar Pedido Actual
 *   5. Ver Estado del Sistema
 *   0. Salir
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GestionPedidos gestion = new GestionPedidos();
        int opcion;

        System.out.println("╔════════════════════════════════════════════╗");
        System.out.println("║      🍕  PIZZA-TRACK - Sistema de Pedidos  ║");
        System.out.println("║         Gestión con Pilas Undo/Redo         ║");
        System.out.println("╚════════════════════════════════════════════╝");

        do {
            mostrarMenu();
            System.out.print("Seleccione una opción: ");

            // Validar que la entrada sea un número entero
            while (!scanner.hasNextInt()) {
                System.out.print("Entrada inválida. Ingrese un número: ");
                scanner.next();
            }
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea pendiente

            switch (opcion) {
                case 1:
                    // ---- REGISTRAR PIZZA ----
                    registrarPizza(scanner, gestion);
                    break;

                case 2:
                    // ---- DESHACER (UNDO) ----
                    gestion.deshacer();
                    break;

                case 3:
                    // ---- REHACER (REDO) ----
                    gestion.rehacer();
                    break;

                case 4:
                    // ---- MOSTRAR PEDIDO ACTUAL ----
                    gestion.mostrarPedidoActual();
                    break;

                case 5:
                    // ---- VER ESTADO DEL SISTEMA ----
                    gestion.mostrarEstado();
                    break;

                case 0:
                    System.out.println("\n👋 ¡Hasta luego! Cerrando Pizza-Track...");
                    break;

                default:
                    System.out.println("\n⚠️  Opción no válida. Intente de nuevo.");
            }

        } while (opcion != 0);

        scanner.close();
    }

    /**
     * Muestra el menú principal en consola.
     */
    private static void mostrarMenu() {
        System.out.println("\n┌──────────────────────────────────────────┐");
        System.out.println("│              MENÚ PRINCIPAL               │");
        System.out.println("├──────────────────────────────────────────┤");
        System.out.println("│  1. Registrar Pizza (push)                │");
        System.out.println("│  2. Deshacer último pedido (Undo / pop)   │");
        System.out.println("│  3. Rehacer último deshecho (Redo)        │");
        System.out.println("│  4. Ver pedido actual (peek)              │");
        System.out.println("│  5. Ver estado completo del sistema       │");
        System.out.println("│  0. Salir                                 │");
        System.out.println("└──────────────────────────────────────────┘");
    }

    /**
     * Solicita al usuario los datos de la pizza (nombre y 3 ingredientes)
     * y la registra en el sistema de gestión de pedidos.
     *
     * @param scanner  Scanner activo para leer la entrada del usuario
     * @param gestion  Instancia del controlador de pedidos
     */
    private static void registrarPizza(Scanner scanner, GestionPedidos gestion) {
        System.out.println("\n--- REGISTRAR NUEVO PEDIDO ---");

        System.out.print("Nombre de la pizza: ");
        String nombre = scanner.nextLine().trim();

        if (nombre.isEmpty()) {
            System.out.println("⚠️  El nombre no puede estar vacío.");
            return;
        }

        // Capturar exactamente 3 ingredientes en un arreglo fijo
        String[] ingredientes = new String[3];
        System.out.println("Ingrese los 3 ingredientes:");
        for (int i = 0; i < 3; i++) {
            System.out.print("  Ingrediente " + (i + 1) + ": ");
            ingredientes[i] = scanner.nextLine().trim();
            if (ingredientes[i].isEmpty()) {
                ingredientes[i] = "Sin especificar";
            }
        }

        // Crear el objeto Pizza y registrarlo
        Pizza nuevaPizza = new Pizza(nombre, ingredientes);
        gestion.registrarPedido(nuevaPizza);
    }
}
