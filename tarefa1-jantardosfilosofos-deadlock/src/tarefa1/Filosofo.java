package tarefa1;

import java.util.Random;

public class Filosofo extends Thread {

    private final int id;
    private final Garfo garfoEsquerdo;
    private final Garfo garfoDireito;
    private final Random random = new Random();

    public Filosofo(int id, Garfo esquerdo, Garfo direito) {
        this.id = id;
        this.garfoEsquerdo = esquerdo;
        this.garfoDireito = direito;
    }

    private void pensar() throws InterruptedException {
        log("está pensando");
        Thread.sleep((random.nextInt(3) + 1) * 1000);
    }

    private void comer() throws InterruptedException {
        log("tentando pegar o garfo esquerdo (" + garfoEsquerdo.getId() + ")");
        synchronized (garfoEsquerdo) {
            log("pegou o garfo esquerdo (" + garfoEsquerdo.getId() + ")");

            log("tentando pegar o garfo direito (" + garfoDireito.getId() + ")");
            synchronized (garfoDireito) {
                log("pegou os dois garfos e está comendo");
                Thread.sleep((random.nextInt(3) + 1) * 1000);
                log("terminou de comer e soltou os garfos");
            }
        }
    }

    private void log(String mensagem) {
        System.out.println("Filósofo " + id + ": " + mensagem);
    }

    @Override
    public void run() {
        try {
            while (true) {
                pensar();
                comer();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
