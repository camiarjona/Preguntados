package Modelo.Enum;

public enum Categoria {
    /// categorias
    HISTORIA (1),
    GEOGRAFIA (2),
    ENTRETENIMIENTO (3),
    CIENCIA (4),
    TECNOLOGIA (5),
    DEPORTE (6);

    /// atributo para guardar el id
    private final int id;

    /// constructor para guardar el id
    Categoria(int id) {
        this.id = id;
    }

    /// get
    public int getId() {
        return id;
    }

    /// Metodo estático para obtener la categoría por su id
    public static Categoria obtenerCategoriaPorId(int id) {
        for (Categoria categoria : Categoria.values()) {
            if (categoria.getId() == id) {
                return categoria; // Si encontramos el id, retornamos la categoría correspondiente
            }
        }
        throw new IllegalArgumentException("ID no válido: " + id); // Si no se encuentra el id, lanzamos una excepción
    }
}
