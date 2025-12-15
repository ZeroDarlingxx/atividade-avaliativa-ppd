package tarefa2;

/**
 * Classe principal responsavel por iniciar a simulacao
 * do Jantar dos Filosofos utilizando a tecnica
 * de ordem diferente de aquisicao dos recursos.
 */
public class JantarDosFilosofos {

    public static void main(String[] args) throws InterruptedException {

        Garfo[] garfos = new Garfo[5];
        Filosofo[] filosofos = new Filosofo[5];

        // Inicializacao dos garfos
        for (int i = 0; i < 5; i++) {
            garfos[i] = new Garfo(i);
        }

        // Criacao e inicializacao dos filosofos
        for (int i = 0; i < 5; i++) {
            Garfo esquerdo = garfos[i];
            Garfo direito = garfos[(i + 1) % 5];

            filosofos[i] = new Filosofo(i, esquerdo, direito);
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
