package tarefa1;

/**
 * Representa um garfo compartilhado entre dois filosofos.
 * 
 * Cada garfo é tratado como um recurso exclusivo
 * e protegido por sincronizacao implicita
 * atraves do uso de blocos synchronized.
 */
public class Garfo {

    // Identificador do garfo
    private final int id;

    public Garfo(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
