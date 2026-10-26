/**
 * Representa a una persona cliente que reserva tours. Su condición
 * (nacional o extranjero) determina si recibe descuento en una reserva.
 *
 * @author Samantha Casco
 * @version 1.0
 */
public class Cliente {

  /** Condición de una persona cliente nacional. */
  public static final String CONDICION_NACIONAL = "nacional";

  /** Condición de una persona cliente extranjera. */
  public static final String CONDICION_EXTRANJERO = "extranjero";

  /** Nombre de la persona cliente. */
  private String nombre;

  /** Condición de la persona cliente: CONDICION_NACIONAL o CONDICION_EXTRANJERO. */
  private String condicion;

  /**
   * Construye una persona cliente.
   *
   * @param pNombre nombre de la persona cliente.
   * @param pCondicion "nacional" o "extranjero".
   */
  public Cliente(String pNombre, String pCondicion) {
    nombre = pNombre;
    condicion = normalizarCondicion(pCondicion);
  }

  /**
   * Consulta el nombre de la persona cliente.
   *
   * @return el nombre.
   */
  public String getNombre() {
    return nombre;
  }

  /**
   * Consulta la condición de la persona cliente.
   *
   * @return CONDICION_NACIONAL o CONDICION_EXTRANJERO.
   */
  public String getCondicion() {
    return condicion;
  }

  /**
   * Indica si la persona cliente es de condición nacional.
   *
   * @return true si es nacional, false si es extranjera.
   */
  public boolean esNacional() {
    return condicion.equals(CONDICION_NACIONAL);
  }

  /**
   * Convierte la condición recibida en una de las dos condiciones válidas.
   * Cualquier valor distinto de "nacional" se registra como extranjero.
   *
   * @param pCondicion condición recibida.
   * @return CONDICION_NACIONAL o CONDICION_EXTRANJERO.
   */
  private String normalizarCondicion(String pCondicion) {
    if (pCondicion != null && pCondicion.trim().equalsIgnoreCase(CONDICION_NACIONAL)) {
      return CONDICION_NACIONAL;
    }
    return CONDICION_EXTRANJERO;
  }

  /**
   * Retorna el estado de la persona cliente: nombre y condición.
   *
   * @return cadena con el estado del cliente.
   */
  public String toString() {
    return "Cliente: " + nombre + " | Condición: " + condicion;
  }
}
