/**
 * Representa un atractivo turístico. Existe de forma independiente de los
 * tours y puede formar parte del recorrido de varios tours a la vez.
 *
 * @author Samantha Casco
 * @version 1.0
 */
public class Atractivo {

  /** Código del atractivo, por ejemplo A-01. */
  private String codigo;

  /** Nombre del atractivo. */
  private String nombre;

  /** Monto de la entrada al atractivo, en colones. */
  private double montoEntrada;

  /**
   * Construye un atractivo.
   *
   * @param pCodigo código del atractivo.
   * @param pNombre nombre del atractivo.
   * @param pMontoEntrada monto de la entrada en colones.
   */
  public Atractivo(String pCodigo, String pNombre, double pMontoEntrada) {
    codigo = pCodigo;
    nombre = pNombre;
    montoEntrada = pMontoEntrada;
  }

  /**
   * Consulta el código del atractivo.
   *
   * @return el código.
   */
  public String getCodigo() {
    return codigo;
  }

  /**
   * Consulta el nombre del atractivo.
   *
   * @return el nombre.
   */
  public String getNombre() {
    return nombre;
  }

  /**
   * Consulta el monto de la entrada del atractivo.
   *
   * @return el monto de la entrada, en colones.
   */
  public double getMontoEntrada() {
    return montoEntrada;
  }

  /**
   * Retorna el estado del atractivo: código, nombre y monto de la entrada.
   *
   * @return cadena con el estado del atractivo.
   */
  public String toString() {
    return "Atractivo " + codigo + " | Nombre: " + nombre
        + " | Entrada: " + montoEntrada + " colones";
  }
}
