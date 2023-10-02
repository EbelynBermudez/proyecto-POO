package Logica;

import java.util.ArrayList;
import java.util.List;

public class Productos {
    private String nombre;
    private double precio;
    private String tipoProducto;
    private int codigo;
    private List<String> ingredientes;
    private int cantidad; 

    public Productos(String nombre, double precio, String tipoProducto, int codigo) {
        this.nombre = nombre;
        this.precio = precio;
        this.tipoProducto = tipoProducto;
        this.codigo = codigo;
        this.ingredientes = new ArrayList<>();
        this.cantidad = 0; 
    }

    public List<String> getIngredientes() {
        return ingredientes;
    }

    public void agregarIngrediente(String ingrediente) {
        ingredientes.add(ingrediente);
    }

    public void eliminarIngrediente(String ingrediente) {
        ingredientes.remove(ingrediente);
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public String getTipoProducto() {
        return tipoProducto;
    }

    public int getCodigo() {
        return codigo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}

