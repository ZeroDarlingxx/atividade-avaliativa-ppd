package tarefa2;

/**
 * Representa um garfo compartilhado entre dois filosofos.
 * 
 * O objeto Garfo é utilizado como monitor
 * para garantir exclusao mutua.
 */
public class Garfo {

    private final int id;

    public Garfo(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
