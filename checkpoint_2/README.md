# Checkpoint Fiap ESPW - Checkpoint 2
## Descrição

Este projeto implementa uma interface chamada NucleotideoRandomGenerator para gerar cadeias de nucleotídeos aleatórias (A, C, G, T) em Java. A implementação atual gera uma sequência aleatória com base no tamanho fornecido como entrada.

## Funcionalidades

Geração de cadeias de nucleotídeos aleatórias de tamanho variável.
Utilização de uma interface para facilitar a criação de diferentes implementações.

## Estrutura Principal

Interface NucleotideoRandomGenerator: Define o método generate(int sequenceSize) que gera uma sequência de nucleotídeos com o tamanho especificado.
Classe SimpleNucleotideGenerator: Implementa a interface e gera cadeias de nucleotídeos aleatórias.
Classe App: Classe principal que roda o programa e exibe a sequência gerada no console.

## Exemplo de Uso

Entrada: 10
Saída: ACTGTCAGGT