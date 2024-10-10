package checkpoint2;

public class App {
    public static void main(String[] args) {
        NucleotideoRandomGenerator generator = new SimpleNucleotideGenerator();
        
        // Aqui modificamos sequenceSize para o tamanho que queremos gerar
        String randomSequence = generator.generate(10);
        System.out.println("Generated sequence: " + randomSequence);
    }
}
