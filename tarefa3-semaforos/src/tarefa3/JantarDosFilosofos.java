package tarefa3;


import java.util.concurrent.Semaphore;

public class JantarDosFilosofos {

    public static void main(String[] args) throws InterruptedException {

        Garfo[] garfos = new Garfo[5];
        Filosofo[] filosofos = new Filosofo[5];

        Semaphore semaforo = new Semaphore(4); 

        for (int i = 0; i < 5; i++) {
            garfos[i] = new Garfo(i);
        }

        for (int i = 0; i < 5; i++) {
            Garfo esquerdo = garfos[i];
            Garfo direito = garfos[(i + 1) % 5];

            filosofos[i] = new Filosofo(i, esquerdo, direito, semaforo);
            filosofos[i].start();
        }

        Thread.sleep(300_000);

        System.out.println("\n=== Estatísticas de Execução ===");
        for (Filosofo f : filosofos) {
            System.out.println(
                "Filósofo " + f.getIdFilosofo() + " comeu " + f.getVezesComeu() + " vezes"
            );
        }

        System.exit(0);
    }
}
