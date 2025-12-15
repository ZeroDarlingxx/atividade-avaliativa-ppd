package tarefa4;

public class JantarDosFilosofos {

    public static void main(String[] args) throws InterruptedException {

        Mesa mesa = new Mesa();
        Filosofo[] filosofos = new Filosofo[5];

        for (int i = 0; i < 5; i++) {
            filosofos[i] = new Filosofo(i, mesa);
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
