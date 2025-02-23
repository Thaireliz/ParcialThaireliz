import java.util.ArrayList;
import java.util.Scanner;

class Producto {
    private String nombre;
    private int inventarioInicial;
    private int unidadesVendidas;

    public Producto(String nombre, int inventarioInicial) {
        this.nombre = nombre;
        this.inventarioInicial = inventarioInicial;
        this.unidadesVendidas = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public int getInventarioInicial() {
        return inventarioInicial;
    }

    public int getUnidadesVendidas() {
        return unidadesVendidas;
    }

    public int getDisponibilidad() {
        return inventarioInicial - unidadesVendidas;
    }

    public void venderUnidades(int cantidad) {
        if (cantidad <= getDisponibilidad()) {
            unidadesVendidas += cantidad;
            System.out.printf("✅ Venta exitosa: %d unidades de %s vendidas.\n", cantidad, nombre);
        } else {
            System.out.printf("❌ Error: No hay suficiente stock para vender %d unidades de %s. Disponible: %d\n",
                    cantidad, nombre, getDisponibilidad());
        }
    }

    public void duplicarInventario() {
        if (getDisponibilidad() == 0) {
            inventarioInicial *= 2;
            System.out.printf("🔄 Inventario de %s duplicado. Nuevo inventario inicial: %d\n", nombre, inventarioInicial);
        } else {
            System.out.printf("⚠ No se puede duplicar %s: aún quedan %d unidades disponibles.\n",
                    nombre, getDisponibilidad());
        }
    }

    @Override
    public String toString() {
        return String.format("| %-15s | %-10d | %-10d | %-10d |", nombre, inventarioInicial, unidadesVendidas, getDisponibilidad());
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Producto> inventario = new ArrayList<>();

        // Encabezado de bienvenida
        System.out.println("=========================================");
        System.out.println("   Gestión de Inventario - Tienda de Zapatos   ");
        System.out.println("=========================================");
        System.out.printf("¡Bienvenido! Administra tu inventario fácilmente.\n");

        while (true) {
            // Menú principal
            System.out.println("\n+---------------------------------------+");
            System.out.println("|          Menú de Opciones             |");
            System.out.println("+---------------------------------------+");
            System.out.println("| 1. Agregar producto                   |");
            System.out.println("| 2. Vender unidades                    |");
            System.out.println("| 3. Duplicar inventario de un producto |");
            System.out.println("| 4. Mostrar inventario                 |");
            System.out.println("| 5. Salir                              |");
            System.out.println("+---------------------------------------+");
            System.out.print("👉 Selecciona una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            if (opcion == 1) {
                System.out.println("\n=== Agregar Nuevo Producto ===");
                System.out.print("Nombre del producto: ");
                String nombre = scanner.nextLine();
                System.out.print("Cantidad inicial en inventario: ");
                int cantidad = scanner.nextInt();
                inventario.add(new Producto(nombre, cantidad));
                System.out.println("✅ Producto agregado correctamente.");

            } else if (opcion == 2) {
                System.out.println("\n=== Vender Unidades ===");
                System.out.print("Nombre del producto: ");
                String nombre = scanner.nextLine();
                System.out.print("Cantidad a vender: ");
                int cantidad = scanner.nextInt();
                boolean encontrado = false;
                for (Producto p : inventario) {
                    if (p.getNombre().equalsIgnoreCase(nombre)) {
                        p.venderUnidades(cantidad);
                        encontrado = true;
                        break;
                    }
                }
                if (!encontrado) System.out.println("❌ Error: Producto no encontrado en el inventario.");

            } else if (opcion == 3) {
                System.out.println("\n=== Duplicar Inventario ===");
                System.out.print("Nombre del producto: ");
                String nombre = scanner.nextLine();
                boolean encontrado = false;
                for (Producto p : inventario) {
                    if (p.getNombre().equalsIgnoreCase(nombre)) {
                        p.duplicarInventario();
                        encontrado = true;
                        break;
                    }
                }
                if (!encontrado) System.out.println("❌ Error: Producto no encontrado en el inventario.");

            } else if (opcion == 4) {
                System.out.println("\n=== Inventario Actual ===");
                if (inventario.isEmpty()) {
                    System.out.println("⚠ El inventario está vacío.");
                } else {
                    System.out.println("+-----------------+------------+------------+------------+");
                    System.out.println("| Nombre          | Inicial    | Vendidas   | Disponible |");
                    System.out.println("+-----------------+------------+------------+------------+");
                    for (Producto p : inventario) {
                        System.out.println(p);
                    }
                    System.out.println("+-----------------+------------+------------+------------+");
                }

            } else if (opcion == 5) {
                System.out.println("\n=========================================");
                System.out.println("   Gracias por usar Gestión de Inventario   ");
                System.out.println("=========================================");
                break;

            } else {
                System.out.println("❌ Opción inválida. Por favor, selecciona una opción entre 1 y 5.");
            }
        }
        scanner.close();
    }
}