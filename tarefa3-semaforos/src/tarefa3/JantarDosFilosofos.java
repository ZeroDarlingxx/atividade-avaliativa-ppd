package tarefa3;

import java.util.concurrent.Semaphore;

/**
 * Classe principal responsavel por iniciar a simulacao
 * do problema do Jantar dos Filosofos utilizando semaforos.
 */
public class JantarDosFilosofos {

    public static void main(String[] args) throws InterruptedException {

        Garfo[] garfos = new Garfo[5];
        Filosofo[] filosofos = new Filosofo[5];

        // Semaforo que limita a quantidade de filosofos
        // que podem tentar pegar os garfos simultaneamente
        Semaphore semaforo = new Semaphore(4);

        // Inicializacao dos garfos
        for (int i = 0; i < 5; i++) {
            garfos[i] = new Garfo(i);
        }

        // Criacao e inicializacao dos filosofos
        for (int i = 0; i < 5; i++) {
            Garfo esquerdo = garfos[i];
            Garfo direito = garfos[(i + 1) % 5];

            filosofos[i] = new Filosofo(i, esquerdo, direito, semaforo);
            filosofos[i].start();
        }

        // Tempo de execucao da simulacao (5 minutos)
        Thread.sleep(300_000);

        // Impressao das estatisticas coletadas
        System.out.println("\n=== Estatisticas de Execucao ===");
        for (Filosofo f : filosofos) {
            System.out.println(
                "Filosofo " + f.getIdFilosofo() + " comeu " + f.getVezesComeu() + " vezes"
            );
        }

        System.exit(0);
    }
}
