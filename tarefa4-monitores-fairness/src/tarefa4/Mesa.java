package tarefa4;

import java.util.LinkedList;
import java.util.Queue;

public class Mesa {

    private final boolean[] garfos = new boolean[5]; // true = ocupado
    private final Queue<Integer> fila = new LinkedList<>();

    public synchronized void pegarGarfos(int id) throws InterruptedException {
        fila.add(id);
        log("entrou na fila");

        while (!podeComer(id)) {
            wait();
        }

        fila.remove();
        garfos[id] = true;
        garfos[(id + 1) % 5] = true;

        log("pegou os dois garfos e está comendo");
    }

    public synchronized void soltarGarfos(int id) {
        garfos[id] = false;
        garfos[(id + 1) % 5] = false;

        log("soltou os garfos");
        notifyAll();
    }

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
