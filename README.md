TAREFA 1

Resultados da Execução

O programa referente à Tarefa 1 foi executado por períodos variando entre 1 e 5 minutos. Durante a execução, observou-se comportamento concorrente típico, com os filósofos alternando entre os estados de pensar e comer, conforme registrado pelo sistema de logging.

Em algumas execuções, foi possível observar a formação de situações de bloqueio, nas quais múltiplos filósofos permaneciam segurando o garfo esquerdo enquanto aguardavam indefinidamente pelo garfo direito. Nesses casos, o sistema deixou de apresentar progresso, caracterizando a ocorrência de deadlock. Em outras execuções, o escalonamento das threads permitiu progresso contínuo, sem bloqueio permanente.

Análise do Comportamento

A implementação da Tarefa 1 é propositalmente suscetível a deadlock, pois todos os filósofos seguem a mesma ordem de aquisição dos recursos, pegando primeiro o garfo esquerdo e depois o direito. Essa estratégia satisfaz as quatro condições necessárias para a ocorrência de deadlock: exclusão mútua, posse e espera, não preempção e espera circular.

Devido ao caráter não determinístico do escalonamento das threads, a manifestação do deadlock não ocorre necessariamente em todas as execuções. No entanto, a análise do código e dos logs demonstra claramente que a solução permite a formação de um ciclo de espera entre os filósofos, atendendo ao objetivo proposto pelo exercício.

Conclusão

A Tarefa 1 demonstra que, mesmo em sistemas aparentemente simples, decisões inadequadas de sincronização podem levar a situações de bloqueio permanente. Esta implementação serve como base conceitual para a compreensão do problema de deadlock e motiva a aplicação de técnicas de prevenção, exploradas na Tarefa 2.


////////////////


TAREFA 2 

Resultados da Execução

O programa foi executado por aproximadamente dois minutos. As estatísticas finais indicaram que todos os filósofos conseguiram se alimentar múltiplas vezes, conforme mostrado a seguir:

Filósofo 0: 20 refeições

Filósofo 1: 23 refeições

Filósofo 2: 24 refeições

Filósofo 3: 26 refeições

Filósofo 4: 21 refeições

Esses resultados demonstram que não ocorreu deadlock durante a execução e que o sistema apresentou progresso contínuo.


////////////////


TAREFA 3 

Solução com Semáforos

Nesta tarefa, foi implementada uma solução para o problema do Jantar dos Filósofos utilizando a classe Semaphore do Java. Um semáforo global com quatro permissões foi empregado para limitar o número de filósofos que podem tentar pegar os garfos simultaneamente.

Antes de tentar adquirir qualquer garfo, o filósofo deve obter uma permissão do semáforo. Apenas quatro filósofos podem estar nessa região crítica ao mesmo tempo, enquanto o quinto obrigatoriamente permanece pensando.

Prevenção de Deadlock

A prevenção de deadlock é garantida porque, ao limitar o número de filósofos tentando pegar garfos a quatro, sempre haverá pelo menos um garfo livre. Dessa forma, a condição de espera circular não pode se formar, pois não é possível que todos os filósofos estejam simultaneamente segurando um garfo e aguardando outro.

Assim, mesmo mantendo exclusão mútua nos garfos, o sistema garante progresso contínuo.


Comparação com a Tarefa 2

Na Tarefa 2, o deadlock foi prevenido por meio da inversão da ordem de aquisição dos garfos para um dos filósofos. Já nesta tarefa, a prevenção ocorre através da limitação explícita do número de filósofos concorrendo pelos recursos.

A solução com semáforos apresenta uma abordagem mais geral e robusta, pois não depende de regras específicas para filósofos individuais. No entanto, ela pode introduzir maior contenção, já que restringe o paralelismo máximo do sistema.


Vantagens e Desvantagens


Vantagens:

Prevenção de deadlock garantida

Implementação clara e conceitualmente simples

Independente da ordem de aquisição dos garfos


Desvantagens:

Redução do paralelismo máximo

Possibilidade teórica de starvation, dependendo do escalonamento

Uso de um recurso global adicional (semaforo)


Conclusão

A utilização de semáforos mostrou-se uma solução eficaz para prevenir deadlock no problema do Jantar dos Filósofos. Embora reduza o paralelismo, a abordagem garante progresso do sistema e oferece uma alternativa robusta às soluções baseadas apenas na ordem de aquisição de recursos.

Resultados da Execução

O programa foi executado por aproximadamente 2 minutos. Durante todo o período de
execução, não foi observada a ocorrência de deadlock. Todos os filósofos conseguiram
acessar a região crítica e realizar suas refeições múltiplas vezes, conforme indicado
pelas estatísticas finais abaixo:

Filósofo 0: 29 refeições  
Filósofo 1: 27 refeições  
Filósofo 2: 32 refeições  
Filósofo 3: 31 refeições  
Filósofo 4: 30 refeições  

Os resultados demonstram progresso contínuo do sistema e confirmam a eficácia da
solução baseada em semáforos para prevenção de deadlock.


TAREFA 4    

Tarefa 4 – Solução com Monitores e Fairness

Nesta solução, foi introduzida a classe Mesa, que atua como um monitor responsável por gerenciar o acesso aos garfos de forma centralizada. Os filósofos não acessam diretamente os garfos, solicitando permissão à Mesa para comer.

O monitor utiliza métodos sincronizados juntamente com wait() e notifyAll() para coordenar o acesso aos recursos. Quando um filósofo não pode comer, ele entra em espera, garantindo que não ocorra aquisição parcial de recursos.

A fairness é garantida porque todos os filósofos que entram na fila têm oportunidade de comer, evitando starvation. O deadlock é prevenido pois a aquisição e liberação dos garfos ocorre de forma atômica sob controle do monitor.

Em comparação com as soluções anteriores, esta abordagem oferece maior justiça entre os filósofos, ao custo de maior centralização e menor paralelismo potencial.