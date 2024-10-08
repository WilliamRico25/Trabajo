/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tienda;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;
import java.util.Iterator;
/**
 *
 * @author ricow
 */


class Producto {
    private String codigo;
    private String nombre;
    private int cantidad;
    private double precio;
    private double total;

    public Producto(String codigo, String nombre, int cantidad, double precio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
        this.total = cantidad * precio;
    }

    // Getters y setters
    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public int getCantidad() { return cantidad; }
    public double getPrecio() { return precio; }
    public double getTotal() { return total; }

    public void setPrecio(double precio) {
        this.precio = precio;
        this.total = this.cantidad * this.precio;
    }

    @Override
    public String toString() {
        return String.format("%-10s %-20s %-10d %-10.2f %-10.2f", 
                             codigo, nombre, cantidad, precio, total);
    }
}

class Supermercado {
    private Deque<Producto> productos;

    public Supermercado() {
        productos = new ArrayDeque<>(100);
    }

    // Nuevo método para inicializar 10 productos
    public void inicializarProductos() {
        agregarProducto(new Producto("P001", "Manzanas", 50, 750));
        agregarProducto(new Producto("P002", "Leche", 30, 3000));
        agregarProducto(new Producto("P003", "Pan", 40, 2000));
        agregarProducto(new Producto("P004", "Huevos", 60, 350));
        agregarProducto(new Producto("P005", "Arroz", 25, 2800));
        agregarProducto(new Producto("P006", "Pasta", 35, 2500));
        agregarProducto(new Producto("P007", "Aceite", 20, 4000));
        agregarProducto(new Producto("P008", "Sal", 45, 2450));
        agregarProducto(new Producto("P009", "Azúcar", 30, 2200));
        agregarProducto(new Producto("P010", "Café", 15, 6300));
    }

    public void agregarProducto(Producto p) {
        if (productos.size() < 100) {
            productos.push(p);
            System.out.println("Producto agregado.");
        } else {
            System.out.println("No se pueden agregar más productos. Límite alcanzado.");
        }
    }

    public void modificarProducto(String codigo) {
        Deque<Producto> temp = new ArrayDeque<>();
        boolean encontrado = false;
        while (!productos.isEmpty()) {
            Producto p = productos.pop();
            if (p.getCodigo().equals(codigo)) {
                p.setPrecio(p.getPrecio() * 1.05);
                encontrado = true;
                System.out.println("Precio actualizado.");
            }
            temp.push(p);
        }
        while (!temp.isEmpty()) {
            productos.push(temp.pop());
        }
        if (!encontrado) {
            System.out.println("Producto no encontrado.");
        }
    }

    public void eliminarProducto(String codigo) {
        Deque<Producto> temp = new ArrayDeque<>();
        boolean eliminado = false;
        while (!productos.isEmpty()) {
            Producto p = productos.pop();
            if (!p.getCodigo().equals(codigo)) {
                temp.push(p);
            } else {
                eliminado = true;
            }
        }
        while (!temp.isEmpty()) {
            productos.push(temp.pop());
        }
        if (eliminado) {
            System.out.println("Producto eliminado.");
        } else {
            System.out.println("Producto no encontrado.");
        }
    }

    public Producto buscarProducto(String codigo) {
        for (Producto p : productos) {
            if (p.getCodigo().equals(codigo)) {
                return p;
            }
        }
        return null;
    }

    public void mostrarProductos() {
        System.out.println("Código     Nombre               Cantidad   Precio     Total");
        for (Producto p : productos) {
            System.out.println(p);
        }
    }

    public void generarFactura() {
        double total = 0;
        int productosVendidos = 0;
        System.out.println("Código     Nombre               Cantidad   Precio     Total");
        for (Producto p : productos) {
            System.out.println(p);
            total += p.getTotal();
            productosVendidos += p.getCantidad();
        }
        double iva = total * 0.19;
        double precioFinal = total + iva;

        System.out.println("\nProductos vendidos: " + productosVendidos);
        System.out.printf("Subtotal: %.2f\n", total);
        System.out.printf("IVA (19%%): %.2f\n", iva);
        System.out.printf("Precio final: %.2f\n", precioFinal);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Supermercado supermercado = new Supermercado();

        // Inicializar los 10 productos
        supermercado.inicializarProductos();
        System.out.println("Se han agregado 10 productos iniciales.");

        while (true) {
            System.out.println("\n1. Agregar Producto");
            System.out.println("2. Modificar Producto");
            System.out.println("3. Eliminar Producto");
            System.out.println("4. Buscar Producto");
            System.out.println("5. Mostrar Productos");
            System.out.println("6. Generar Factura");
            System.out.println("7. Salir");
            System.out.print("Elija una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();  // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    System.out.print("Código: ");
                    String codigo = scanner.nextLine();
                    System.out.print("Nombre: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Cantidad: ");
                    int cantidad = scanner.nextInt();
                    System.out.print("Precio: ");
                    double precio = scanner.nextDouble();
                    supermercado.agregarProducto(new Producto(codigo, nombre, cantidad, precio));
                    break;
                case 2:
                    System.out.print("Código del producto a modificar: ");
                    supermercado.modificarProducto(scanner.nextLine());
                    break;
                case 3:
                    System.out.print("Código del producto a eliminar: ");
                    supermercado.eliminarProducto(scanner.nextLine());
                    break;
                case 4:
                    System.out.print("Código del producto a buscar: ");
                    Producto p = supermercado.buscarProducto(scanner.nextLine());
                    if (p != null) {
                        System.out.println(p);
                    } else {
                        System.out.println("Producto no encontrado.");
                    }
                    break;
                case 5:
                    supermercado.mostrarProductos();
                    break;
                case 6:
                    supermercado.generarFactura();
                    break;
                case 7:
                    System.out.println("Gracias por usar la aplicación.");
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }
}