import java.util.ArrayList;

/**
 * Super clase abstracta que define lo común a todo tour: código consecutivo,
 * guía, recorrido de atractivos, reservas e ingreso total. La forma de
 * calcular el precio por persona depende del tipo de tour, por eso es un
 * método abstracto que implementa cada subclase concreta.
 *
 * @author Samantha Casco
 * @version 1.0
 */
public abstract class Tour {

  /** Prefijo común de todos los códigos de tour. */
  public static final String PREFIJO_CODIGO = "TOUR-";

  /** Cantidad de personas de una reserva cuando no se indica: una sola persona. */
  public static final int CANTIDAD_PERSONAS_POR_OMISION = 1;

  /** Cantidad mínima de personas para que una reserva sea válida. */
  public static final int CANTIDAD_MINIMA_PERSONAS = 1;

  /** Descuento sobre el monto de una reserva de una persona cliente nacional: 20 %. */
  public static final double PORCENTAJE_DESCUENTO_NACIONAL = 0.20;

  /** Descuento de una persona cliente extranjera: ninguno. */
  public static final double SIN_DESCUENTO = 0.0;

  /** Separador entre los nombres de los atractivos del recorrido. */
  public static final String SEPARADOR = ", ";

  /** Texto que se muestra cuando el recorrido no tiene atractivos. */
  public static final String TEXTO_SIN_ATRACTIVOS = "(sin atractivos)";

  /** Cantidad de tours registrados; sirve para el código (atributo de clase). */
  private static int cantidadTours;

  /** Código único del tour, formado por el prefijo y un consecutivo. */
  private String codigo;

  /** Nombre del tour. */
  private String nombre;

  /** Precio base por persona, en colones. Lo usan las subclases. */
  protected double precioBase;

  /** Cupo máximo de personas del tour. */
  private int cupoMaximo;

  /** Persona guía que dirige el tour. */
  private Guia miGuia;

  /** Atractivos del recorrido, en el orden en que se agregan. Lo usa TourCultural. */
  protected ArrayList<Atractivo> misAtractivos;

  /** Reservas registradas en el tour (composición). */
  private ArrayList<Reserva> misReservas;

  /**
   * Construye la parte común de un tour. Incrementa la cantidad de tours y
   * genera su código consecutivo.
   *
   * @param pNombre nombre del tour.
   * @param pPrecioBase precio base por persona, en colones.
   * @param pCupoMaximo cupo máximo de personas.
   * @param pGuia persona guía que dirige el tour.
   */
  public Tour(String pNombre, double pPrecioBase, int pCupoMaximo, Guia pGuia) {
    cantidadTours++;
    codigo = PREFIJO_CODIGO + cantidadTours;
    nombre = pNombre;
    precioBase = pPrecioBase;
    cupoMaximo = pCupoMaximo;
    miGuia = pGuia;
    misAtractivos = new ArrayList<>();
    misReservas = new ArrayList<>();
  }

  /**
   * Consulta el código del tour.
   *
   * @return el código.
   */
  public String getCodigo() {
    return codigo;
  }

  /**
   * Consulta el nombre del tour.
   *
   * @return el nombre.
   */
  public String getNombre() {
    return nombre;
  }

  /**
   * Consulta la cantidad total de tours registrados, sin importar su tipo.
   *
   * @return la cantidad de tours registrados.
   */
  public static int getCantidadTours() {
    return cantidadTours;
  }

  /**
   * Calcula el precio por persona del tour. Cada tipo de tour lo calcula
   * de forma distinta.
   *
   * @return el precio por persona, en colones.
   */
  public abstract double calcularPrecioPorPersona();

  /**
   * Cambia la persona guía que dirige el tour. Una guía nula se ignora,
   * porque todo tour debe tener siempre una guía.
   *
   * @param pGuia nueva persona guía.
   */
  public void cambiarGuia(Guia pGuia) {
    if (pGuia != null) {
      miGuia = pGuia;
    }
  }

  /**
   * Consulta el nombre completo de la persona guía que dirige el tour.
   *
   * @return el nombre completo de la guía.
   */
  public String consultarNombreGuia() {
    return miGuia.getNombreCompleto();
  }

  /**
   * Agrega un atractivo previamente registrado al final del recorrido. Si el
   * atractivo ya forma parte del recorrido, la solicitud se ignora.
   *
   * @param pAtractivo atractivo que se agrega.
   */
  public void agregarAtractivo(Atractivo pAtractivo) {
    if (pAtractivo != null && !existeAtractivo(pAtractivo)) {
      misAtractivos.add(pAtractivo);
    }
  }

  /**
   * Consulta el nombre de todos los atractivos del recorrido, en orden.
   *
   * @return los nombres separados por SEPARADOR, o TEXTO_SIN_ATRACTIVOS.
   */
  public String consultarNombresAtractivos() {
    if (misAtractivos.isEmpty()) {
      return TEXTO_SIN_ATRACTIVOS;
    }
    String msg = "";
    for (int i = 0; i < misAtractivos.size(); i++) {
      if (i > 0) {
        msg += SEPARADOR;
      }
      msg += misAtractivos.get(i).getNombre();
    }
    return msg;
  }

  /**
   * Registra una reserva para la cantidad de personas indicada. La reserva no
   * se registra si la cantidad no es mayor que cero o si excede los cupos
   * disponibles. El número de la reserva es consecutivo dentro de este tour.
   *
   * @param pCliente persona cliente que reserva.
   * @param pCantidadPersonas cantidad de personas incluidas en la reserva.
   */
  public final void registrarReserva(Cliente pCliente, int pCantidadPersonas) {
    if (pCliente == null || !hayCupoPara(pCantidadPersonas)) {
      return;
    }
    int numero = misReservas.size() + 1;
    double monto = calcularMontoReserva(pCliente, pCantidadPersonas);
    misReservas.add(new Reserva(numero, pCliente.getNombre(), pCantidadPersonas, monto));
  }

  /**
   * Registra una reserva para una sola persona.
   *
   * @param pCliente persona cliente que reserva.
   */
  public final void registrarReserva(Cliente pCliente) {
    registrarReserva(pCliente, CANTIDAD_PERSONAS_POR_OMISION);
  }

  /**
   * Consulta los cupos disponibles: el cupo máximo menos las personas ya
   * incluidas en las reservas registradas.
   *
   * @return la cantidad de cupos disponibles.
   */
  public int consultarCuposDisponibles() {
    return cupoMaximo - calcularPersonasReservadas();
  }

  /**
   * Calcula el ingreso total del tour: la suma de los montos de todas sus
   * reservas. La regla es igual para todo tipo de tour y no puede cambiarse.
   *
   * @return el ingreso total, en colones.
   */
  public final double calcularIngresoTotal() {
    double total = 0;
    for (Reserva r : misReservas) {
      total += r.getMonto();
    }
    return total;
  }

  /**
   * Indica si el atractivo ya forma parte del recorrido, comparando códigos.
   *
   * @param pAtractivo atractivo que se busca.
   * @return true si ya está en el recorrido.
   */
  private boolean existeAtractivo(Atractivo pAtractivo) {
    for (Atractivo a : misAtractivos) {
      if (a.getCodigo().equals(pAtractivo.getCodigo())) {
        return true;
      }
    }
    return false;
  }

  /**
   * Indica si una reserva de la cantidad indicada es válida: al menos
   * CANTIDAD_MINIMA_PERSONAS y sin exceder los cupos disponibles.
   *
   * @param pCantidadPersonas cantidad de personas de la reserva.
   * @return true si la reserva puede registrarse.
   */
  private boolean hayCupoPara(int pCantidadPersonas) {
    return pCantidadPersonas >= CANTIDAD_MINIMA_PERSONAS
        && pCantidadPersonas <= consultarCuposDisponibles();
  }

  /**
   * Suma la cantidad de personas de todas las reservas registradas.
   *
   * @return la cantidad de personas reservadas.
   */
  private int calcularPersonasReservadas() {
    int total = 0;
    for (Reserva r : misReservas) {
      total += r.getCantidadPersonas();
    }
    return total;
  }

  /**
   * Calcula el monto de una reserva: precio por persona por la cantidad de
   * personas, con el descuento nacional cuando corresponde.
   *
   * @param pCliente persona cliente que reserva.
   * @param pCantidadPersonas cantidad de personas de la reserva.
   * @return el monto de la reserva, en colones.
   */
  private double calcularMontoReserva(Cliente pCliente, int pCantidadPersonas) {
    double montoBruto = calcularPrecioPorPersona() * pCantidadPersonas;
    return montoBruto - montoBruto * obtenerDescuento(pCliente);
  }

  /**
   * Obtiene el descuento que le corresponde a la persona cliente.
   *
   * @param pCliente persona cliente que reserva.
   * @return el descuento como fracción del monto.
   */
  private double obtenerDescuento(Cliente pCliente) {
    if (pCliente.esNacional()) {
      return PORCENTAJE_DESCUENTO_NACIONAL;
    }
    return SIN_DESCUENTO;
  }

  /**
   * Retorna el estado común de un tour. Las subclases agregan su tipo y
   * su dato específico.
   *
   * @return cadena con el estado del tour.
   */
  public String toString() {
    String msg = "Tour " + codigo + "\n";
    msg += "  Nombre: " + nombre + "\n";
    msg += "  Guía: " + consultarNombreGuia() + "\n";
    msg += "  Precio base: " + precioBase + "\n";
    msg += "  Precio por persona: " + calcularPrecioPorPersona() + "\n";
    msg += "  Cupo máximo: " + cupoMaximo + "\n";
    msg += "  Cupos disponibles: " + consultarCuposDisponibles() + "\n";
    msg += "  Recorrido: " + consultarNombresAtractivos() + "\n";
    msg += "  Reservas (" + misReservas.size() + "):\n";
    for (Reserva r : misReservas) {
      msg += "   - " + r.toString() + "\n";
    }
    msg += "  Ingreso total: " + calcularIngresoTotal() + "\n";
    return msg;
  }
}
