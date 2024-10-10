
package br.com.fiap.twoespwx.libunclepresser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class App {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java -jar <path/to/jar/file> <path/to/input> <path/to/output>");
            return;
        }

        String inputFilePath = args[0];
        String outputFilePath = args[1];

        try {
            String input = new String(Files.readAllBytes(Paths.get(inputFilePath)));
            String output = compressRLE(input);
            Files.write(Paths.get(outputFilePath), output.getBytes());
            System.out.println("File compressed successfully.");
        } catch (IOException e) {
            System.err.println("Error processing files: " + e.getMessage());
        }
    }

    public static String compressRLE(String input) {
        StringBuilder compressed = new StringBuilder();
        int count = 1;

        for (int i = 1; i < input.length(); i++) {
            if (input.charAt(i) == input.charAt(i - 1)) {
                count++;
            } else {
                compressed.append(input.charAt(i - 1)).append(count);
                count = 1;
            }
        }
        compressed.append(input.charAt(input.length() - 1)).append(count);
        return compressed.toString();
    }
}

// Git hub copilot que fez xDD https://7tv.app/emotes/613937fcf7977b64f644c0d2