public class Moto extends Vehiculo {

    public static final String TIPO = "moto";
    public static final double MONTO_PEAJE = 75;

    public Moto(String pPlaca, String pMarca, String pModelo, int pAnnio) {
        super(pPlaca, pMarca, pModelo, pAnnio);
    }

    public double calcularMontoPeaje() {
        return MONTO_PEAJE;
    }

    public String toString() {
        String s = super.toString();
        s = s + "\n" + "Tipo= " + TIPO;
        return s;
    }
}
