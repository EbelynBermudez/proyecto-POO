package Logica;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FacturaCombo {
    private int idCajero;
    private int numeroFactura;
    private Date fechaCompra;
    private float total;
    private ArrayList<Combos> combos; 
 
    public FacturaCombo(int idCajero, int numeroFactura, Date fechaCompra, float total, ArrayList<Combos> combos) {
        this.idCajero = idCajero;
        this.numeroFactura = numeroFactura;
        this.fechaCompra = fechaCompra;
        this.total = total;
        this.combos = combos;
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

    public ArrayList<Combos> getCombos() {
        return combos;
    }
    public List<Combos> getCombosVendidos() {
        return combos;
    }
    
   
}

