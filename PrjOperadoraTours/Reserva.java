/**
 * Representa una reserva de un tour. Es una PARTE del tour (composición):
 * solo el tour la construye, no se comparte con otro tour y ningún objeto
 * externo obtiene una referencia a ella. No guarda referencia a la persona
 * cliente, únicamente su nombre.
 *
 * @author Samantha Casco
 * @version 1.0
 */
public class Reserva {

  /** Número consecutivo de la reserva dentro de su tour. */
  private int numero;

  /** Nombre de la persona cliente que realizó la reserva. */
  private String nombreCliente;

  /** Cantidad de personas incluidas en la reserva. */
  private int cantidadPersonas;

  /** Monto de la reserva, en colones. */
  private double monto;

  /**
   * Construye una reserva. Solo la invoca la clase Tour.
   *
   * @param pNumero número consecutivo dentro del tour.
   * @param pNombreCliente nombre de la persona cliente.
   * @param pCantidadPersonas cantidad de personas incluidas.
   * @param pMonto monto de la reserva, en colones.
   */
  public Reserva(int pNumero, String pNombreCliente, int pCantidadPersonas, double pMonto) {
    numero = pNumero;
    nombreCliente = pNombreCliente;
    cantidadPersonas = pCantidadPersonas;
    monto = pMonto;
  }

  /**
   * Consulta la cantidad de personas incluidas en la reserva.
   *
   * @return la cantidad de personas.
   */
  public int getCantidadPersonas() {
    return cantidadPersonas;
  }

  /**
   * Consulta el monto de la reserva.
   *
   * @return el monto, en colones.
   */
  public double getMonto() {
    return monto;
  }

  /**
   * Retorna el estado de la reserva: número, cliente, cantidad de personas y monto.
   *
   * @return cadena con el estado de la reserva.
   */
  public String toString() {
    return "Reserva #" + numero + " | Cliente: " + nombreCliente
        + " | Personas: " + cantidadPersonas + " | Monto: " + monto;
  }
}
