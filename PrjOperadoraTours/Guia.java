/**
 * Representa a una persona guía que puede dirigir uno o varios tours.
 * La guía no conoce los tours que dirige.
 *
 * @author Samantha Casco
 * @version 1.0
 */
public class Guia {

  /** Número de identificación de la persona guía. */
  private String identificacion;

  /** Nombre completo de la persona guía. */
  private String nombreCompleto;

  /**
   * Construye una persona guía.
   *
   * @param pIdentificacion número de identificación.
   * @param pNombreCompleto nombre completo.
   */
  public Guia(String pIdentificacion, String pNombreCompleto) {
    identificacion = pIdentificacion;
    nombreCompleto = pNombreCompleto;
  }

  /**
   * Consulta el nombre completo de la persona guía.
   *
   * @return el nombre completo.
   */
  public String getNombreCompleto() {
    return nombreCompleto;
  }

  /**
   * Retorna el estado de la persona guía: identificación y nombre completo.
   *
   * @return cadena con el estado de la guía.
   */
  public String toString() {
    return "Guía " + identificacion + " | Nombre completo: " + nombreCompleto;
  }
}
