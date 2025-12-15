package tarefa3;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Filosofo extends Thread {

    private final int id;
    private final Garfo garfoEsquerdo;
    private final Garfo garfoDireito;
    private final Semaphore semaforo;
    private final Random random = new Random();

    private int vezesComeu = 0;

    public Filosofo(int id, Garfo esquerdo, Garfo direito, Semaphore semaforo) {
        this.id = id;
        this.garfoEsquerdo = esquerdo;
        this.garfoDireito = direito;
        this.semaforo = semaforo;
    }

    private void pensar() throws InterruptedException {
        log("está pensando");
        Thread.sleep((random.nextInt(3) + 1) * 1000);
    }

    private void comer() throws InterruptedException {

        log("tentando entrar na região crítica (semaforo)");
        semaforo.acquire(); // limita a 4 filosofos

        try {
            log("tentando pegar o garfo esquerdo (" + garfoEsquerdo.getId() + ")");
            synchronized (garfoEsquerdo) {

                log("pegou o garfo esquerdo (" + garfoEsquerdo.getId() + ")");
                log("tentando pegar o garfo direito (" + garfoDireito.getId() + ")");

                synchronized (garfoDireito) {
                    log("pegou os dois garfos e está comendo");
                    vezesComeu++;
                }
            }

            // Simula tempo de comer fora da regiao critica
            Thread.sleep((random.nextInt(3) + 1) * 1000);
            log("terminou de comer e soltou os garfos");

        } finally {
            semaforo.release();
        }
    }

    private void log(String msg) {
        System.out.println("Filósofo " + id + ": " + msg);
    }

    public int getVezesComeu() {
        return vezesComeu;
    }

    public int getIdFilosofo() {
        return id;
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
