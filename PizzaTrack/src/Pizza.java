/**
 * Clase Pizza
 * Representa un pedido de pizza con nombre y un arreglo fijo de 3 ingredientes.
 * Es el objeto que se almacenará y moverá dentro de las pilas del sistema.
 */
public class Pizza {

    // Atributos del pedido
    private String nombre;
    private String[] ingredientes; // Arreglo de tamaño fijo: máximo 3 ingredientes

    /**
     * Constructor de la pizza.
     * @param nombre      Nombre de la pizza (ej: "Margarita")
     * @param ingredientes Arreglo de exactamente 3 ingredientes
     */
    public Pizza(String nombre, String[] ingredientes) {
        this.nombre = nombre;
        // Copiamos los ingredientes al arreglo interno de tamaño fijo
        this.ingredientes = new String[3];
        for (int i = 0; i < 3; i++) {
            this.ingredientes[i] = ingredientes[i];
        }
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public String[] getIngredientes() {
        return ingredientes;
    }

    /**
     * Representación en texto del pedido, mostrando nombre e ingredientes.
     */
    @Override
    public String toString() {
        return "🍕 Pizza: " + nombre +
               " | Ingredientes: [" + ingredientes[0] + ", " + ingredientes[1] + ", " + ingredientes[2] + "]";
    }
}
