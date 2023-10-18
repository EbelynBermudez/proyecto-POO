package Logica;

import java.util.ArrayList;
import java.util.List;

public class Combos {
    private int codigo;
    private String nombre;
    private double precio;
    private List<Productos> productosEnCombo = new ArrayList<>();
    public Combos(String nombre, int codigo) {
    	this.codigo = codigo;
        this.nombre = nombre;
        this.precio = 0.0; 
        this.productosEnCombo = new ArrayList<>();
    }


    public Combos(int codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }


    public int getCodigoc() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public List<Productos> getProductosEnCombo() {
        return productosEnCombo;
    }

    public void setProductosEnCombo(List<Productos> productosEnCombo) {
        this.productosEnCombo = productosEnCombo;
    }

    public void agregarProductoAlCombo(Productos producto) {
        productosEnCombo.add(producto);
    }
   
    

}
