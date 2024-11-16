package Modelo.Enum;

public enum Categoria {
    HISTORIA (1),
    GEOGRAFIA (2),
    ENTRETENIMIENTO (3),
    CIENCIA (4),
    TECNOLOGIA (5),
    DEPORTE (6);

    //atributo para guardar el id
    private final int id;

    //constructor para guardar el id
    Categoria(int id) {
        this.id = id;
    }

    //metodo
    public int getId() {
        return id;
    }
}
