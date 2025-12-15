package tarefa4;

import java.util.Random;

/**
 * Representa um filosofo como uma thread concorrente.
 * 
 * Nesta solucao, o filosofo NAO acessa diretamente os garfos.
 * Todo o controle de sincronizacao é delegado a classe Mesa,
 * que atua como um monitor centralizado.
 * 
 * Essa abordagem garante:
 * - Exclusao mutua
 * - Fairness (ordem justa de atendimento)
 * - Ausencia de deadlock
 * - Prevencao de starvation
 */
public class Filosofo extends Thread {

    private final int id;
    private final Mesa mesa;
    private final Random random = new Random();

    // Contador de quantas vezes o filosofo conseguiu comer
    private int vezesComeu = 0;

    public Filosofo(int id, Mesa mesa) {
        this.id = id;
        this.mesa = mesa;
    }

    // Simula o tempo em que o filosofo esta pensando
    private void pensar() throws InterruptedException {
        log("esta pensando");
        Thread.sleep((random.nextInt(3) + 1) * 1000);
    }

    /**
     * Solicita a Mesa permissao para pegar os garfos.
     * O filosofo so entra na regiao critica se as
     * condicoes de fairness e disponibilidade forem atendidas.
     */
    private void comer() throws InterruptedException {
        mesa.pegarGarfos(id);
        vezesComeu++;

        // Simula o tempo de alimentacao
        Thread.sleep((random.nextInt(3) + 1) * 1000);

        mesa.soltarGarfos(id);
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
