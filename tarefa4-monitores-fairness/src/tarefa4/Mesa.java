package tarefa4;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Classe Mesa atua como um monitor centralizado,
 * controlando o acesso aos garfos e garantindo fairness.
 * 
 * A utilizacao de uma fila FIFO assegura que os filosofos
 * sejam atendidos na ordem de chegada, prevenindo starvation.
 */
public class Mesa {

    // true = garfo ocupado | false = garfo livre
    private final boolean[] garfos = new boolean[5];

    // Fila FIFO para garantir ordem justa de atendimento
    private final Queue<Integer> fila = new LinkedList<>();

    /**
     * Metodo sincronizado que controla a entrada
     * dos filosofos na regiao critica.
     * 
     * O filosofo so pode comer se:
     * - Ambos os garfos estiverem livres
     * - Ele for o primeiro da fila
     */
    public synchronized void pegarGarfos(int id) throws InterruptedException {
        fila.add(id);
        log("entrou na fila");

        while (!podeComer(id)) {
            wait();
        }

        fila.remove();
        garfos[id] = true;
        garfos[(id + 1) % 5] = true;

        log("pegou os dois garfos e esta comendo");
    }

    /**
     * Libera os garfos e notifica todos os filosofos
     * para reavaliarem a condicao de entrada.
     */
    public synchronized void soltarGarfos(int id) {
        garfos[id] = false;
        garfos[(id + 1) % 5] = false;

        log("soltou os garfos");
        notifyAll();
    }

    /**
     * Verifica se o filosofo pode comer:
     * - Garfo esquerdo livre
     * - Garfo direito livre
     * - Primeiro da fila (fairness)
     */
    private boolean podeComer(int id) {
        boolean garfoEsqLivre = !garfos[id];
        boolean garfoDirLivre = !garfos[(id + 1) % 5];
        boolean ehPrimeiroDaFila = fila.peek() != null && fila.peek() == id;

        return garfoEsqLivre && garfoDirLivre && ehPrimeiroDaFila;
    }

    private void log(String msg) {
        System.out.println("Mesa: " + msg);
    }
}
