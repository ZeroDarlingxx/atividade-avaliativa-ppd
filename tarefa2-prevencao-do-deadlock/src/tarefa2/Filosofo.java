package tarefa2;

import java.util.Random;

/**
 * Solucao que previne deadlock ao impor uma ordem global
 * na aquisicao dos recursos (garfos).
 * 
 * Um dos filosofos pega primeiro o garfo direito,
 * quebrando a condicao de espera circular.
 */
public class Filosofo extends Thread {

    private final int id;
    private final Garfo garfoEsquerdo;
    private final Garfo garfoDireito;
    private final Random random = new Random();

    // Contador de quantas vezes o filosofo conseguiu comer
    private int vezesComeu = 0;

    public Filosofo(int id, Garfo esquerdo, Garfo direito) {
        this.id = id;
        this.garfoEsquerdo = esquerdo;
        this.garfoDireito = direito;
    }

    // Simula o tempo em que o filosofo esta pensando
    private void pensar() throws InterruptedException {
        log("esta pensando");
        Thread.sleep((random.nextInt(3) + 1) * 1000);
    }

    /**
     * Metodo responsavel por controlar a ordem
     * de aquisicao dos garfos.
     * 
     * A inversao da ordem para um filosofo
     * impede que todos fiquem bloqueados
     * aguardando indefinidamente (deadlock).
     */
    private void comer() throws InterruptedException {

        // O filosofo de id 4 pega os garfos em ordem inversa
        if (id == 4) {
            pegarGarfos(garfoDireito, garfoEsquerdo);
        } else {
            pegarGarfos(garfoEsquerdo, garfoDireito);
        }
    }

    /**
     * Metodo que realiza a aquisicao sequencial
     * dos dois garfos utilizando exclusao mutua.
     */
    private void pegarGarfos(Garfo primeiro, Garfo segundo) throws InterruptedException {

        log("tentando pegar o garfo " + primeiro.getId());
        synchronized (primeiro) {
            log("pegou o garfo " + primeiro.getId());

            log("tentando pegar o garfo " + segundo.getId());
            synchronized (segundo) {
                log("pegou os dois garfos e esta comendo");
                vezesComeu++;

                // Simula o tempo de alimentacao
                Thread.sleep((random.nextInt(3) + 1) * 1000);

                log("terminou de comer e soltou os garfos");
            }
        }
    }

    private void log(String mensagem) {
        System.out.println("Filosofo " + id + ": " + mensagem);
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
