package tarefa1;

public class JantarDosFilosofos {

    public static void main(String[] args) {

        Garfo[] garfos = new Garfo[5];
        Filosofo[] filosofos = new Filosofo[5];

        for (int i = 0; i < 5; i++) {
            garfos[i] = new Garfo(i);
        }

        for (int i = 0; i < 5; i++) {
            Garfo esquerdo = garfos[i];
            Garfo direito = garfos[(i + 1) % 5];

            filosofos[i] = new Filosofo(i, esquerdo, direito);
            filosofos[i].start();
        }
    }
}
