     /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package minimarket;
/**
 *
 * @author Ingenieria
 */
public class Producto {
        private String codigo;
        private String nombre;
        private int stock;
        private double precioUnitario;
        private String categoria;
        

        public Producto(String codigo, String nombre, int stock, double precioUnitario, String categoria) {
            
            this.codigo=codigo;
            this.nombre = nombre;
            this.stock = stock;
            this.precioUnitario = precioUnitario;
            this.categoria = categoria;
           
        }
        
        public String getCodigo(){return codigo;}
        public void setcodigo(String codigo){this.codigo=codigo;}
        
        public String getNombre() { return nombre; }
        public void setNombre(String nombre) { this.nombre = nombre; }

        public int getStock() { return stock; }
        public void setStock(int stock) { this.stock = stock; }

        public double getPrecioUnitario() { return precioUnitario; }
        public void setPrecioUnitario(double precioUnitario) { this.precioUnitario = precioUnitario; }

        public String getCategoria() { return categoria; }
        public void setCategoria(String categoria) { this.categoria = categoria; }

        

        public double calcularValorInventario() {
            return stock * precioUnitario;
        }

        public void mostrarProducto() {
            System.out.println("_____Datos del producto:_____");
            System.out.println("ID_Codigo: " + codigo);
            System.out.println(" | Producto: " + nombre);
            System.out.println(" | Stock: " + stock);
            System.out.println(" | Precio: $" + precioUnitario);
            System.out.println(" | Categoría: " + categoria );
                    
        }
}
