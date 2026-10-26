/**
 * Super clase abstracta que define lo común a todo vehículo. El tipo de
 * vehículo ya no es un atributo de texto: lo determina la subclase concreta
 * con la que se instancia el objeto.
 */
public abstract class Vehiculo {

    private String placa;
    private String marca;
    private String modelo;
    private int annio;

    public Vehiculo(String pPlaca, String pMarca, String pModelo, int pAnnio) {
        placa  = pPlaca;
        marca  = pMarca;
        modelo = pModelo;
        annio  = pAnnio;
    }

    /** Monto de peaje que le corresponde pagar al vehículo, según su tipo. */
    public abstract double calcularMontoPeaje();

    public String toString() {
        return "Placa= " + placa + "\n" +
               "Marca= " + marca + "\n" +
               "Modelo= " + modelo + "\n" +
               "Año= " + annio;
    }
}
