package Logica;

public class Cajero {
    private String nombre;
    private int id ;

    public Cajero (String nombre, int id ) {
        this.nombre = nombre;
        this.id= id;
    }

    public Cajero(String string, float f, String string2) {
	}

	public String getNombre() {
        return nombre;
    }

	public int getId() {
		return id;
	}

}
