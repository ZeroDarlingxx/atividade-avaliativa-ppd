package tarefa2;

import java.util.Random;

public class Filosofo extends Thread {

    private final int id;
    private final Garfo garfoEsquerdo;
    private final Garfo garfoDireito;
    private final Random random = new Random();

    private int vezesComeu = 0;

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

        // Filosofo 4 vai pegar os garfos em ordem inversa
        if (id == 4) {
            pegarGarfos(garfoDireito, garfoEsquerdo);
        } else {
            pegarGarfos(garfoEsquerdo, garfoDireito);
        }
    }

    private void pegarGarfos(Garfo primeiro, Garfo segundo) throws InterruptedException {

        log("tentando pegar o garfo " + primeiro.getId());
        synchronized (primeiro) {
            log("pegou o garfo " + primeiro.getId());

            log("tentando pegar o garfo " + segundo.getId());
            synchronized (segundo) {
                log("pegou os dois garfos e está comendo");
                vezesComeu++;
                Thread.sleep((random.nextInt(3) + 1) * 1000);
                log("terminou de comer e soltou os garfos");
            }
        }
    }

    private void log(String mensagem) {
        System.out.println("Filósofo " + id + ": " + mensagem);
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
