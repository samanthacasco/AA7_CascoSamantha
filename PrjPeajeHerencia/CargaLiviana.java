public class CargaLiviana extends Vehiculo {

    public static final String TIPO = "carga liviana";
    public static final double MONTO_PEAJE = 200;

    public CargaLiviana(String pPlaca, String pMarca, String pModelo, int pAnnio) {
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
