# RELATÓRIO COMPARATIVO – JANTAR DOS FILÓSOFOS


## 1-Introdução
O problema do Jantar dos Filósofos é um problema clássico da computação concorrente utilizado para ilustrar desafios relacionados à sincronização, como deadlock, starvation e fairness no acesso a recursos compartilhados. Neste trabalho, diferentes abordagens foram implementadas e comparadas utilizando threads em Java.


## 2-Metodologia
Foram implementadas três soluções distintas (Tarefas 2, 3 e 4). Cada solução foi executada por 5 minutos em um ambiente local, com cinco filósofos representados por threads concorrentes. Durante a execução, foram coletadas estatísticas sobre o número de refeições realizadas por cada filósofo e observado o comportamento do sistema.



## 3-Resultados
Número de Refeições em 5 minutos

| Solução | Filósofo 0 | Filósofo 1 | Filósofo 2 | Filósofo 3 | Filósofo 4 | **Média** |
|:--------|-----------:|-----------:|-----------:|-----------:|-----------:|:---------:|
| **Tarefa 2**<br/>Ordem de Garfos | 47 | 54 | 57 | 58 | 50 | **53,2** |
| **Tarefa 3**<br/>Semáforo | **77** | **78** | **75** | 73 | **75** | **75,6** |
| **Tarefa 4**<br/>Monitor | 36 | 37 | 37 | 34 | 37 | **36,2** |

*Nota: Valores representam número de vezes que cada filósofo comeu durante o teste.*

### Distribuição e Fairness

A Tarefa 4 apresentou a menor variação entre os filósofos, indicando maior fairness. A Tarefa 3 apresentou boa distribuição, enquanto a Tarefa 2 demonstrou maior variabilidade entre os filósofos.



## 4-Análise Comparativa




### Prevenção de Deadlock

Tarefa 1: Não há qualquer mecanismo de prevenção de deadlock. Todos os filosofos tentam adquirir os garfos na mesma ordem, o que pode gerar espera circular caso cada filosofo obtenha o garfo esquerdo e fique bloqueado aguardando o garfo direito.

Tarefa 2: Deadlock prevenido por meio da ordem assimétrica na aquisição dos garfos.

Tarefa 3: Deadlock prevenido pela limitação do número de filósofos concorrentes via semáforo.

Tarefa 4: Deadlock eliminado pelo controle centralizado do monitor.




### Prevenção de Starvation

Tarefa 1: Não há qualquer garantia de ausência de starvation. Um filosofo pode ficar bloqueado indefinidamente caso seus vizinhos monopolizem os garfos.

Tarefa 2: Não garante formalmente ausência de starvation.

Tarefa 3: Reduz significativamente a possibilidade de starvation.

Tarefa 4: Garante fairness e elimina starvation por construção.




### Performance e Throughput

A solução com semáforo apresentou o maior throughput, seguida pela solução da Tarefa 2. A solução com monitor apresentou menor throughput devido à centralização e maior controle.




### Complexidade de Implementação

Tarefa 1: Baixissima complexidade, porem conceitualmente inadequada por não tratar problemas de concorrencia.

Tarefa 2: Baixa complexidade.

Tarefa 3: Complexidade moderada.

Tarefa 4: Complexidade Alta devido ao uso de monitores e mecanismos de fairness.




## 5. Conclusão

Cada solução é adequada para diferentes cenários. A solução com semáforo é ideal quando o desempenho é prioritário, enquanto a solução com monitor é mais apropriada quando fairness e previsibilidade são requisitos essenciais. A solução por ordenação de recursos oferece simplicidade com desempenho intermediário.






