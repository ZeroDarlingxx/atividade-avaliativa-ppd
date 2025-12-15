package tarefa1;

/**
 * Classe principal que inicia a simulacao basica
 * do problema do Jantar dos Filosofos.
 * 
 * Esta versao NAO possui mecanismos de prevencao
 * de deadlock ou starvation, sendo utilizada
 * apenas para demonstracao do problema.
 */
public class JantarDosFilosofos {

    public static void main(String[] args) {

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
    }
}
