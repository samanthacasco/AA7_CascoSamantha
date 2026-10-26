/**
 * Tour cultural: su precio por persona es el precio base más la suma de las
 * entradas de todos los atractivos del recorrido.
 *
 * @author Samantha Casco
 * @version 1.0
 */
public class TourCultural extends Tour {

  /** Texto con el que se identifica el tipo de tour en su estado. */
  public static final String TIPO = "Cultural";

  /** Idioma en que se ofrece el recorrido. */
  private String idioma;

  /**
   * Construye un tour cultural.
   *
   * @param pNombre nombre del tour.
   * @param pPrecioBase precio base por persona, en colones.
   * @param pCupoMaximo cupo máximo de personas.
   * @param pGuia persona guía que dirige el tour.
   * @param pIdioma idioma del recorrido.
   */
  public TourCultural(String pNombre, double pPrecioBase, int pCupoMaximo, Guia pGuia,
      String pIdioma) {
    super(pNombre, pPrecioBase, pCupoMaximo, pGuia);
    idioma = pIdioma;
  }

  /**
   * Precio base más la suma de las entradas de los atractivos del recorrido.
   *
   * @return el precio por persona, en colones.
   */
  public double calcularPrecioPorPersona() {
    double totalEntradas = 0;
    for (Atractivo a : misAtractivos) {
      totalEntradas += a.getMontoEntrada();
    }
    return precioBase + totalEntradas;
  }

  /**
   * Retorna el estado completo del tour cultural: lo común más su tipo e idioma.
   *
   * @return cadena con el estado del tour.
   */
  public String toString() {
    String msg = super.toString();
    msg += "  Tipo: " + TIPO + "\n";
    msg += "  Idioma: " + idioma + "\n";
    return msg;
  }
}
