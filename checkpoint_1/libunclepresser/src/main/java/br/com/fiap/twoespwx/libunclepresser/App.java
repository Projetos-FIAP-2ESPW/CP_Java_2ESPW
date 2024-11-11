/* 
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 *
 * Grupo: Repolho
 * Autores: 
 *      - André Lambert - RM99148
 *      - Guilherme Morais - RM551981
 
 * Para o melhor funcionamento do codigo os arquivos input1.txt e OUTPUT1.TXT foram movidos para a mesma pasta que se encontra este script
 * o local dos arquivos pode e deve ser alterado conforme a necessidade do usuario.
 */

//Package Project
package br.com.fiap.twoespwx.libunclepresser;

//Import
import java.io.*;
import java.nio.file.*;
import java.nio.charset.StandardCharsets;

public class App {
    public static void main(String[] args) {
        if (args.length < 2) { //Tratamento de erro da utilização do programa
            System.out.println("Comando para uso do programa: java -jar <caminho/para/app.jar> <input.txt> <output.txt>");
            return;
        }

        String inputFilePath = args[0]; // Recebendo caminho do input
        String outputFilePath = args[1]; // Recebendo caminho do output

        String nucleotideSequence = readFile(inputFilePath);
        if (nucleotideSequence == null) { //Tratamento de erro do caminho do arquivo
            System.out.println("Erro ao ler o arquivo de entrada, caminho nao encontrado.");
            return;
        }

        String compressedData = compress(nucleotideSequence);
        writeFile(outputFilePath, compressedData);

        printReport(inputFilePath, outputFilePath, nucleotideSequence, compressedData);
    }

    // Método para ler o arquivo
    public static String readFile(String filePath) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filePath), StandardCharsets.UTF_8)) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
            return null;
        }
        return content.toString();
    }

    // Método para escrever o arquivo
    public static void writeFile(String filePath, String data) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath), StandardCharsets.UTF_8)) {
            writer.write(data);
        } catch (IOException e) {
            System.out.println("Erro ao escrever no arquivo: " + e.getMessage());
        }
    }

    // Método para comprimir os dados
    public static String compress(String data) {
        StringBuilder compressed = new StringBuilder();
        int count = 1;

        for (int i = 1; i <= data.length(); i++) {
            if (i == data.length() || data.charAt(i) != data.charAt(i - 1)) {
                compressed.append(data.charAt(i - 1)).append(count);
                count = 1;
            } else {
                count++;
            }
        }
        return compressed.toString();
    }

    // Método para calcular a quantia de compressão dos dados
    public static double calculateCompressionRate(String originalData, String compressedData) {
        return (double) compressedData.getBytes(StandardCharsets.UTF_8).length
                / originalData.getBytes(StandardCharsets.UTF_8).length;
    }

    // Método para calcular a frequancia de caracteres
    public static String calculateFrequencies(String data) {
        long countA = data.chars().filter(ch -> ch == 'A').count();
        long countC = data.chars().filter(ch -> ch == 'C').count();
        long countT = data.chars().filter(ch -> ch == 'T').count();
        long countG = data.chars().filter(ch -> ch == 'G').count();

        int totalChars = data.length();
        return String.format(
                "A: %.1f (%.2f%%)\nC: %.1f (%.2f%%)\nT: %.1f (%.2f%%)\nG: %.1f (%.2f%%)",
                (float) countA, 100.0 * countA / totalChars,
                (float) countC, 100.0 * countC / totalChars,
                (float) countT, 100.0 * countT / totalChars,
                (float) countG, 100.0 * countG / totalChars);
    }

    // Método para devolver o report do "script'
    public static void printReport(String inputFilePath, String outputFilePath, String originalData,
            String compressedData) {
        File inputFile = new File(inputFilePath);
        File outputFile = new File(outputFilePath);

        System.out.println(" -----------------------------------------------------------");
        System.out.println("|           LIB UNCLE PRESSER - GRUPO BATATA-DOCE           |");
        System.out.println("|-----------------------------------------------------------");
        System.out.println("|                                                           |");
        System.out.printf("| INPUT  FILENAME: %-40s |\n", inputFile.getName());
        System.out.printf("| OUTPUT FILENAME: %-40s |\n", outputFile.getName());
        System.out.println("|                                                           |");
        System.out.println(" -----------------------------------------------------------");
        System.out.println("|                                                           |");
        System.out.printf("| INPUT FILE SIZE: %.1f KB                                   |\n",
                inputFile.length() / 1024.0);
        System.out.printf("| TOTAL INPUT CHARACTERS: %d                                 |\n", originalData.length());
        System.out.println("|                                                           |");
        System.out.println("| FREQUENCIES:                                              |");
        System.out.println("| " + calculateFrequencies(originalData).replace("\n", "\n| "));
        System.out.println("|                                                           |");
        System.out.println("| OPTIONS:                                                  |");
        System.out.println("|                                                           |");
        System.out.println("| ALGORITHM: Run-Length Encoding (RLE)                      |");
        System.out.println("| TEXT-CODIFICATION: UTF-8                                  |");
        System.out.printf("| COMPRESSION RATE: =~ %.2f%%                                |\n",
                calculateCompressionRate(originalData, compressedData) * 100);
        System.out.printf("| OUTPUT FILE SIZE: %.1f BYTES                              |\n",
                (double) compressedData.getBytes(StandardCharsets.UTF_8).length);
        System.out.println("|                                                           |");
        System.out.println(" -----------------------------------------------------------");
        System.out.println("|                                                           |");
        System.out.println("| SCORE: WELL-DONE                                          |");
        System.out.println("|                                                           |");
        System.out.println(" -----------------------------------------------------------");
    }
}
