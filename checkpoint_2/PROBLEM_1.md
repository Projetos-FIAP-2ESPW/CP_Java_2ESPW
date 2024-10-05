# Problema 1

-  Data de publicação: 04/10/2024
-  Data limite de entrega: 18/10/2024

Continuando nossa saga trabalhando com cadeias de nucleotídeos, agora precisamos de uma forma prática de gerar cadeias aleatórias de qualquer tamanho que quisermos.

Para fazermos isso, definiremos uma interface comum, que será a seguinte:

```java

public interface NucleotideoRandomGenerator {
    public String generate(int sequenceSize);
}
```

Você deve criar um novo projeto Maven conforme visto em aula, com o nome **libunclegerardo**. Nesse projeto crie a estrutura necessária para implementar a interface solicitada.

O método **generate** recebe um número inteiro positivo, que é o tamanho da sequência que devemos gerar. Como essa sequência será gerada fica a cargo de você. Queremos exercitar a escrita de diferentes implementações dessa interface, assim estaremos explorando o verdadeiro poder do polimorfismo e contrato entre classes.

Alguns exemplos de geração de cadeias aleatórias:

```
entrada: 10
saída: AAAAAAAAAA

entrada: 10
saída: ACTGACTGACTG

entrada: 10
saída: TGCATGACTA

entrada: 4
saída: ACTG

entrada: 4
saída: GTCAACTG
```