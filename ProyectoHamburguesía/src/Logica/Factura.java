package Logica;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Factura {
    private List<Productos> productosComprados;
    private int idCajero;
    private int numeroFactura;
    private Date fechaCompra;
    private float total;

    public Factura(int idCajero, int numeroFactura, Date fechaCompra, float total, List<Productos> productos) {
        this.productosComprados = new ArrayList<>(productos);
        this.idCajero = idCajero;
        this.numeroFactura = numeroFactura;
        this.fechaCompra = fechaCompra;
        this.total = total;
    }

    public List<Productos> getProductosComprados() {
        return productosComprados;
    }

    public int getIdCajero() {
        return idCajero;
    }

    public int getNumeroFactura() {
        return numeroFactura;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public float getTotal() {
        return total;
    }
}
