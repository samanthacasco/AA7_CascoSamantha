/**
 * Tour de aventura: su precio por persona es el precio base más un recargo
 * por seguro de aventura. Tiene un nivel de dificultad (bajo, medio o alto)
 * que por omisión es medio.
 *
 * @author Samantha Casco
 * @version 1.0
 */
public class TourAventura extends Tour {

  /** Texto con el que se identifica el tipo de tour en su estado. */
  public static final String TIPO = "Aventura";

  /** Recargo por seguro de aventura sobre el precio base: 15 %. */
  public static final double PORCENTAJE_SEGURO_AVENTURA = 0.15;

  /** Nivel de dificultad bajo. */
  public static final String NIVEL_BAJO = "bajo";

  /** Nivel de dificultad medio. */
  public static final String NIVEL_MEDIO = "medio";

  /** Nivel de dificultad alto. */
  public static final String NIVEL_ALTO = "alto";

  /** Nivel de dificultad cuando no se indica uno: medio. */
  public static final String NIVEL_POR_OMISION = NIVEL_MEDIO;

  /** Nivel de dificultad del tour. */
  private String nivelDificultad;

  /**
   * Construye un tour de aventura con el nivel de dificultad indicado.
   *
   * @param pNombre nombre del tour.
   * @param pPrecioBase precio base por persona, en colones.
   * @param pCupoMaximo cupo máximo de personas.
   * @param pGuia persona guía que dirige el tour.
   * @param pNivelDificultad "bajo", "medio" o "alto".
   */
  public TourAventura(String pNombre, double pPrecioBase, int pCupoMaximo, Guia pGuia,
      String pNivelDificultad) {
    super(pNombre, pPrecioBase, pCupoMaximo, pGuia);
    nivelDificultad = normalizarNivel(pNivelDificultad);
  }

  /**
   * Construye un tour de aventura con el nivel de dificultad por omisión (medio).
   *
   * @param pNombre nombre del tour.
   * @param pPrecioBase precio base por persona, en colones.
   * @param pCupoMaximo cupo máximo de personas.
   * @param pGuia persona guía que dirige el tour.
   */
  public TourAventura(String pNombre, double pPrecioBase, int pCupoMaximo, Guia pGuia) {
    this(pNombre, pPrecioBase, pCupoMaximo, pGuia, NIVEL_POR_OMISION);
  }

  /**
   * Precio base más el recargo por seguro de aventura.
   *
   * @return el precio por persona, en colones.
   */
  public double calcularPrecioPorPersona() {
    return precioBase + precioBase * PORCENTAJE_SEGURO_AVENTURA;
  }

  /**
   * Convierte el nivel recibido en uno de los tres niveles válidos. Un nivel
   * desconocido se registra como NIVEL_POR_OMISION.
   *
   * @param pNivel nivel recibido.
   * @return NIVEL_BAJO, NIVEL_MEDIO o NIVEL_ALTO.
   */
  private String normalizarNivel(String pNivel) {
    if (pNivel != null) {
      String nivel = pNivel.trim();
      if (nivel.equalsIgnoreCase(NIVEL_BAJO)) {
        return NIVEL_BAJO;
      }
      if (nivel.equalsIgnoreCase(NIVEL_ALTO)) {
        return NIVEL_ALTO;
      }
    }
    return NIVEL_POR_OMISION;
  }

  /**
   * Retorna el estado completo del tour de aventura: lo común más su tipo y
   * nivel de dificultad.
   *
   * @return cadena con el estado del tour.
   */
  public String toString() {
    String msg = super.toString();
    msg += "  Tipo: " + TIPO + "\n";
    msg += "  Nivel de dificultad: " + nivelDificultad + "\n";
    return msg;
  }
}
