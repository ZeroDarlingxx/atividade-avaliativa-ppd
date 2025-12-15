package tarefa4;

import java.util.Random;

public class Filosofo extends Thread {

    private final int id;
    private final Mesa mesa;
    private final Random random = new Random();

    private int vezesComeu = 0;

    public Filosofo(int id, Mesa mesa) {
        this.id = id;
        this.mesa = mesa;
    }

    private void pensar() throws InterruptedException {
        log("está pensando");
        Thread.sleep((random.nextInt(3) + 1) * 1000);
    }

    private void comer() throws InterruptedException {
        mesa.pegarGarfos(id);
        vezesComeu++;

        Thread.sleep((random.nextInt(3) + 1) * 1000);

        mesa.soltarGarfos(id);
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
