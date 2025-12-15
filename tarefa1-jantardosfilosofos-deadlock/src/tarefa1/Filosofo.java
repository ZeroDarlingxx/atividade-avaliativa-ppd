package tarefa1;

import java.util.Random;

/**
 * Implementacao basica do problema do Jantar dos Filosofos.
 * 
 * Cada filosofo tenta pegar primeiro o garfo esquerdo
 * e depois o garfo direito, sem qualquer mecanismo adicional
 * de controle de concorrencia.
 * 
 * Essa solucao pode levar a DEADLOCK caso todos os filosofos
 * peguem simultaneamente o garfo esquerdo e fiquem aguardando
 * indefinidamente pelo garfo direito.
 */
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

    // Simula o tempo em que o filosofo esta pensando
    private void pensar() throws InterruptedException {
        log("esta pensando");
        Thread.sleep((random.nextInt(3) + 1) * 1000);
    }

    /**
     * REGIAO CRITICA:
     * O filosofo tenta pegar os dois garfos utilizando
     * blocos synchronized.
     * 
     * Nao ha qualquer tecnica de prevencao de deadlock
     * nesta abordagem.
     */
    private void comer() throws InterruptedException {
        log("tentando pegar o garfo esquerdo (" + garfoEsquerdo.getId() + ")");
        synchronized (garfoEsquerdo) {
            log("pegou o garfo esquerdo (" + garfoEsquerdo.getId() + ")");

            log("tentando pegar o garfo direito (" + garfoDireito.getId() + ")");
            synchronized (garfoDireito) {
                log("pegou os dois garfos e esta comendo");

                // Simula o tempo de alimentacao
                Thread.sleep((random.nextInt(3) + 1) * 1000);

                log("terminou de comer e soltou os garfos");
            }
        }
    }

    private void log(String mensagem) {
        System.out.println("Filosofo " + id + ": " + mensagem);
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
