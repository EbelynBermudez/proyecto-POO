package Logica;

import java.util.ArrayList;
import java.util.List;

public class Factura {
    private int idCajero;
    private int numeroFactura;
    private String fechaCompra;
    private double total;
    private List<Productos> productosComprados;
    private List<Combos> combosComprados;
    private static List<Factura> facturas = new ArrayList<>();

    public Factura(int idCajero, int numeroFactura, String fechaCompra, double total) {
        this.idCajero = idCajero;
        this.numeroFactura = numeroFactura;
        this.fechaCompra = fechaCompra;
        this.total = total;
        this.productosComprados = new ArrayList<>();
        this.combosComprados = new ArrayList<>();
        facturas.add(this);
    }

    public List<Productos> getProductosComprados() {
        return productosComprados;
    }

    public List<Combos> getCombosComprados() {
        return combosComprados;
    }

    public int getIdCajero() {
        return idCajero;
    }

    public int getNumeroFactura() {
        return numeroFactura;
    }

    public String getFechaCompra() {
        return fechaCompra;
    }

    public double getTotal() {
        return total;
    }

    public static List<Factura> getFacturas() {
        return facturas;
    }

    public void agregarProducto(Productos producto) {
        productosComprados.add(producto);
        total += producto.getPrecio();
    }

    public void agregarCombo(Combos combo) {
        combosComprados.add(combo);
        total += combo.getPrecio();
    }

   
}

