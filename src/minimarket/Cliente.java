/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package minimarket;
import java.util.ArrayList;
/**
 *
 * @author Ingenieria
 */
public class Cliente {
    // Atributos
    private String nombre;
        private int edad;
        private String email;
        private String tipoCliente;
        private String fechaUltimaCompra;
        private boolean frecuente;
        // Lista de productos comprados
        private ArrayList<Producto> productosComprados = new ArrayList<>();
    
        // Constructor
        public Cliente(String nombre, int edad, String email, String tipoCliente, String fechaUltimaCompra) {
            this.nombre = nombre;
            this.edad = edad;
            this.email = email;
            this.tipoCliente = tipoCliente;
            this.fechaUltimaCompra = fechaUltimaCompra;
            //this.frecuente=frecuente;
        }

        public String getNombre() { return nombre; }
        public void setNombre(String nombre) { this.nombre = nombre; }

        public int getEdad() { return edad; }
        public void setEdad(int edad) { this.edad = edad; }

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }

        public String getTipoCliente() { return tipoCliente; }
        public void setTipoCliente(String tipoCliente) { this.tipoCliente = tipoCliente; }
        
        public String getfechaUltimaCompra(){return fechaUltimaCompra; }
        public void setfechaUltimaCompra(String fechaUltimaCompra) {this.fechaUltimaCompra=fechaUltimaCompra;}
        
        // Asociar producto comprado
        public void agregarProducto(Producto producto) {
        productosComprados.add(producto);
        }

        public ArrayList<Producto> getProductosComprados() {
        return productosComprados;
        }
        
        public void mostrarProductosComprados() {
        System.out.println("🧾 Productos comprados por " + nombre + ":");
        if (productosComprados.isEmpty()) {
            System.out.println("  - No ha comprado productos.");
        } else {
            for (Producto p : productosComprados) {
                System.out.println("  - " + p.getNombre() + " | Código: " + p.getCodigo() + " | Precio: $" + p.getPrecioUnitario()+ " | Categoria: "+p.getCategoria());
            }
        }
}
    
        // Métodos opcionales
        public void mostrarCliente() {
            System.out.println("\n_____Datos del cliente_____");
        System.out.println("Nombre: " + nombre);
        System.out.println("Edad: " + edad);
        System.out.println("Email: " + email);
        System.out.println("Tipo: " + tipoCliente);
        System.out.println("Última compra: " + fechaUltimaCompra);
        }

        public boolean tieneDescuento() {
            return tipoCliente.equalsIgnoreCase("frecuente");
        }
        
        public boolean isFrecuente() {
    return frecuente;
}

}
