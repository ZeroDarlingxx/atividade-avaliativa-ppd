package tarefa3;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * Solucao utilizando semaforo para limitar o numero
 * de filosofos que podem tentar pegar os garfos simultaneamente.
 * 
 * O semaforo permite no maximo 4 filosofos na regiao critica,
 * garantindo que pelo menos um consiga obter ambos os garfos,
 * prevenindo deadlock.
 */
public class Filosofo extends Thread {

    private final int id;
    private final Garfo garfoEsquerdo;
    private final Garfo garfoDireito;
    private final Semaphore semaforo;
    private final Random random = new Random();

    // Contador utilizado para coleta de estatisticas
    // sobre quantas vezes o filosofo conseguiu comer
    private int vezesComeu = 0;

    public Filosofo(int id, Garfo esquerdo, Garfo direito, Semaphore semaforo) {
        this.id = id;
        this.garfoEsquerdo = esquerdo;
        this.garfoDireito = direito;
        this.semaforo = semaforo;
    }

    // Simula o tempo em que o filosofo esta pensando
    private void pensar() throws InterruptedException {
        log("esta pensando");
        Thread.sleep((random.nextInt(3) + 1) * 1000);
    }

    /**
     * Metodo responsavel por controlar o acesso
     * a regiao critica utilizando um semaforo.
     */
    private void comer() throws InterruptedException {

        // O semaforo limita a quantidade de filosofos
        // que podem tentar acessar os garfos ao mesmo tempo
        log("tentando entrar na regiao critica (semaforo)");
        semaforo.acquire(); // limita a 4 filosofos

        // REGIAO CRITICA: tentativa de pegar os dois garfos
        try {
            log("tentando pegar o garfo esquerdo (" + garfoEsquerdo.getId() + ")");
            synchronized (garfoEsquerdo) {

                log("pegou o garfo esquerdo (" + garfoEsquerdo.getId() + ")");
                log("tentando pegar o garfo direito (" + garfoDireito.getId() + ")");

                synchronized (garfoDireito) {
                    log("pegou os dois garfos e esta comendo");
                    vezesComeu++;
                }
            }

            // Simula o tempo de alimentacao fora da regiao critica
            Thread.sleep((random.nextInt(3) + 1) * 1000);
            log("terminou de comer e soltou os garfos");

        } finally {
            // Liberacao do semaforo garante progresso do sistema
            semaforo.release();
        }
    }

    private void log(String msg) {
        System.out.println("Filosofo " + id + ": " + msg);
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
