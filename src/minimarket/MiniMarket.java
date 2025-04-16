package minimarket;

import java.util.ArrayList;
import java.util.Scanner;

public class MiniMarket {

    static Scanner sc = new Scanner(System.in);
    static ArrayList<Cliente> listclientes = new ArrayList<>();
    static ArrayList<Producto> listProductos = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("_____BIENVENIDOS AL MINIMARKET GR4_____");

        // Registro de clientes
        String continuarCliente;
        do {
            System.out.println("\n-- REGISTRO DE CLIENTE --");

            String nombre;
            while (true) {
                System.out.print("Nombre del cliente: ");
                nombre = sc.nextLine();
                if (nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) break;
                else System.out.println(" El nombre solo debe contener letras.");
            }

            int edad;
            while (true) {
                System.out.print("Edad: ");
                try {
                    edad = Integer.parseInt(sc.nextLine());
                    if (edad > 0) break;
                    else System.out.println("La edad debe ser positiva.");
                } catch (NumberFormatException e) {
                    System.out.println("Edad inválida. Debe ser numérica.");
                }
            }

            System.out.print("Email: ");
            String email = sc.nextLine();

            System.out.print("Tipo de cliente (nuevo/frecuente): ");
            String tipo = sc.nextLine();

            String fecha;
            while (true) {
                System.out.print("Fecha de última compra (dd/mm/aaaa): ");
                fecha = sc.nextLine();
                if (fecha.matches("\\d{2}/\\d{2}/\\d{4}")) break;
                else System.out.println(" Formato de fecha inválido.");
            }

            Cliente cliente = new Cliente(nombre, edad, email, tipo, fecha);
            listclientes.add(cliente);

            cliente.mostrarCliente();

            if (cliente.tieneDescuento()) {
                System.out.println("Se aplicará un 2% de descuento en sus productos.");
            } else {
                System.out.println("️ No se aplicará descuento.");
            }

            System.out.print("\n¿Desea registrar otro cliente? (si/no): ");
            continuarCliente = sc.nextLine().trim().toLowerCase();

        } while (continuarCliente.equals("si"));

        // Registro de productos
        System.out.print("\n¿Desea registrar productos? (si/no): ");
        String respuesta = sc.nextLine().trim().toLowerCase();

        if (respuesta.equals("si")) {
            int opcion;
            do {
                System.out.println("\n-- SISTEMA DE REGISTRO DE PRODUCTOS --");
                System.out.println("1. Registrar producto");
                System.out.println("2. Mostrar productos");
                System.out.println("3. Buscar producto por código");
                System.out.println("4 Eliminar Producto por código ");
                System.out.println("5 Imprimir boleta");
                System.out.println("6 Imprimir boleta de un cliente:");
                System.out.println("7. Salir");
                System.out.print("Seleccione una opción: ");
                opcion = Integer.parseInt(sc.nextLine());

                switch (opcion) {
                    case 1 -> {registrarProducto();

                        System.out.print("Ingrese el nombre del cliente que compra el producto: ");
                        String nombreCliente = sc.nextLine();

                        Cliente clienteSeleccionado = null;
                    for (Cliente c : listclientes) {
                         if (c.getNombre().equalsIgnoreCase(nombreCliente)) {
                            clienteSeleccionado = c;
                         break;
                        }
                    }
                    if (clienteSeleccionado == null) {
                        System.out.println("Cliente no encontrado.");
                        break;
                        }
                    Producto ultimoProducto = listProductos.get(listProductos.size() - 1);
                    clienteSeleccionado.agregarProducto(ultimoProducto);
                    System.out.println("Producto asignado a " + clienteSeleccionado.getNombre());
                    }                      
                    case 2 -> {mostrarProductos();
                        for (Cliente c : listclientes) {
                            System.out.println("? Cliente: " + c.getNombre());
                            c.mostrarProductosComprados();
                        }
                    }
                    case 3 -> {buscarProducto();
                        System.out.print("Ingrese el código del producto a buscar: ");
                        String codigoBuscado = sc.nextLine();
                        boolean encontrado = false;
                        
                        for (Cliente c : listclientes) {
                            for (Producto p : c.getProductosComprados()) {
                                if (p.getCodigo().equalsIgnoreCase(codigoBuscado)) {
                                System.out.println(" Producto encontrado:");
                                System.out.println("Nombre: " + p.getNombre());
                                System.out.println("Pertenece al cliente: " + c.getNombre());
                                
                                encontrado = true;
                                break;
                                }
                            }
                            if (!encontrado) {
                            System.out.println("Producto no encontrado en ninguna compra.");
                            }
                        }
                        
                    }
                    case 4 -> {eliminarProducto();
                        System.out.print("Ingrese el nombre del cliente: ");
                        String nombreClienteEliminar = sc.nextLine();
                        Cliente clienteEliminar = null;

                        for (Cliente c : listclientes) {
                            if (c.getNombre().equalsIgnoreCase(nombreClienteEliminar)) {
                            clienteEliminar = c;
                            break;
                            }
                        }

                        if (clienteEliminar == null) {
                            System.out.println(" Cliente no encontrado.");
                            break;
                        }

                        System.out.print("Ingrese el código del producto a eliminar: ");
                        String codigoEliminar = sc.nextLine();

                        boolean eliminado = clienteEliminar.getProductosComprados().removeIf(p -> p.getCodigo().equalsIgnoreCase(codigoEliminar));

                        if (eliminado) {
                            System.out.println("Producto eliminado de la lista del cliente.");
                        } else {
                            System.out.println(" Producto no encontrado en las compras del cliente.");
                        }
                    }
                    case 5 -> imprimirBoleta();
                    case 6-> imprimirBoletaPorCliente();
                    case 7 -> System.out.println("Saliendo del sistema..................\nGracias por usar nuestro programa...................");
                    default -> System.out.println("Opción inválida.");
                }
            } while (opcion != 7);
        } else {
            System.out.println("No se registraron productos.");
        }
    }

    public static void registrarProducto() {
        System.out.print("Ingrese código: ");
        String codigo = sc.nextLine();

        for (Producto p : listProductos) {
            if (p.getCodigo().equals(codigo)) {
                System.out.println("Ya existe un producto con ese código.");
                return;
            }
        }

        System.out.print("Ingrese nombre del producto: ");
        String nombre = sc.nextLine();

        double precio;
        while (true) {
            System.out.print("Ingrese precio: ");
            try {
                precio = Double.parseDouble(sc.nextLine());
                if (precio < 0) {
                    System.out.println("El precio no puede ser negativo.");
                } else break;
            } catch (NumberFormatException e) {
                System.out.println("Precio inválido.");
            }
        }

        int cantidad;
        while (true) {
            System.out.print("Ingrese cantidad: ");
            try {
                cantidad = Integer.parseInt(sc.nextLine());
                if (cantidad < 0) {
                    System.out.println("La cantidad no puede ser negativa.");
                } else break;
            } catch (NumberFormatException e) {
                System.out.println("Cantidad inválida.");
            }
        }

        String[] categorias = {"Bebidas", "Lácteos", "Snacks", "Abarrotes", "Frutas y Verduras"};
        System.out.println("Categorías disponibles:");
        for (int i = 0; i < categorias.length; i++) {
            System.out.println((i + 1) + ". " + categorias[i]);
        }

        String categoria;
        while (true) {
            System.out.print("Seleccione una categoría (1-" + categorias.length + "): ");
            try {
                int opcionCat = Integer.parseInt(sc.nextLine());
                if (opcionCat >= 1 && opcionCat <= categorias.length) {
                    categoria = categorias[opcionCat - 1];
                    break;
                } else {
                    System.out.println("Categoría inválida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Opción inválida.");
            }
        }

        Producto producto = new Producto(codigo, nombre, cantidad, precio, categoria);
        listProductos.add(producto);
        System.out.println("Producto registrado correctamente.");
    }

    public static void mostrarProductos() {
        if (listProductos.isEmpty()) {
            System.out.println("No hay productos registrados.");
        } else {
            System.out.println("\n--- LISTA DE PRODUCTOS ---");
            for (Producto p : listProductos) {
                System.out.println(p);
            }
        }
    }

    public static void buscarProducto() {
        System.out.print("Ingrese el código del producto a buscar: ");
        String codigo = sc.nextLine();
        boolean encontrado = false;
        for (Producto p : listProductos) {
            if (p.getCodigo().equalsIgnoreCase(codigo)) {
                System.out.println("Producto encontrado:\n" + p);
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("Producto no encontrado.");
        }
    }
    public static void eliminarProducto() {
    if (listProductos.isEmpty()) {
        System.out.println("No hay productos registrados.");
        return;
    }

    System.out.print("Ingrese el código del producto a eliminar: ");
    String codigo = sc.nextLine();

    boolean eliminado = false;
        for (int i = 0; i < listProductos.size(); i++) {
            if (listProductos.get(i).getCodigo().equalsIgnoreCase(codigo)) {
               listProductos.remove(i);
                System.out.println(" Producto eliminado correctamente.");
                eliminado = true;
                break;
            }
        }

        if (!eliminado) {
            System.out.println(" No se encontró un producto con ese código.");
           }
    }
    public static void imprimirBoleta()
    {
        if(listclientes.isEmpty())
        {
            System.out.println("No hay clientes registrados");
            return;
        }
        if(listProductos.isEmpty())
        {
            System.out.println("No hay productos registrados");
            return;
        }
        System.out.println("\n__________Boleta de compra__________");
        
        for(Cliente c : listclientes)
        {
        System.out.println("\nCliente: " + c.getNombre());
        System.out.println("Email: " + c.getEmail());
        System.out.println("Tipo: " + c.getTipoCliente());
        System.out.println("Fecha de última compra: " + c.getfechaUltimaCompra());
        
       double total = 0;
       for(Producto p : listProductos)
       {
           double subtotal = p.calcularValorInventario(); // stock * precioUnitario
            total += subtotal;
            System.out.printf("- Producto: %s | Stock: %d | Precio Unit.: S/ %.2f | Subtotal: S/ %.2f%n",
                p.getNombre(), p.getStock(), p.getPrecioUnitario(), subtotal);
       }
       if (c.tieneDescuento()) {
            double descuento = total * 0.02;
            total -= descuento;
            System.out.println("Descuento aplicado (2%): S/ " + String.format("%.2f", descuento));
        }
         System.out.println("TOTAL A PAGAR: S/ " + String.format("%.2f", total));
        System.out.println("====================================");
        
        }
    }
    private static void imprimirBoletaPorCliente() {
    System.out.print("Ingrese el nombre del cliente: ");
    String nombre = sc.nextLine();

    Cliente clienteEncontrado = null;
    for (Cliente c : listclientes) {
        if (c.getNombre().equalsIgnoreCase(nombre)) {
            clienteEncontrado = c;
            break;
        }
    }

    if (clienteEncontrado == null) {
        System.out.println("Cliente no encontrado.");
        return;
    }

    System.out.println("\n_____ Boleta de compra para " + clienteEncontrado.getNombre() + " _____");
    double total = 0;
    for (Producto p : clienteEncontrado.getProductosComprados()) {
           double subtotal = p.getPrecioUnitario() * p.getStock(); // o cantidad si se maneja aparte
            total += subtotal;
            System.out.printf("- Producto: %s | Stock: %d | Precio Unit.: S/ %.2f | Subtotal: S/ %.2f%n",
                p.getNombre(), p.getStock(), p.getPrecioUnitario(), subtotal);
        }

        if (clienteEncontrado.tieneDescuento()) {
            double descuento = total * 0.02;
            total -= descuento;
            System.out.println("Descuento aplicado (2%): S/ " + String.format("%.2f", descuento));
        }

        System.out.println("TOTAL A PAGAR: S/ " + String.format("%.2f", total));
        System.out.println("====================================");
    }

}





