package com.effecti.etapaencontrar.encode;

import java.io.*;
import java.util.Objects;

public class CorrecaoEncodingHtml {

    public static void main(String[] args) {

        // Define a codificação correta do arquivo
        String encoding = "UTF-8";

        // Percorre todos os arquivos HTML no diretório atual
        File dir = new File(".");
        for (File file : Objects.requireNonNull(dir.listFiles())) {
            if (file.getName().endsWith(".html")) {
                try {
                    File tempFile;
                    try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), encoding))) {
                        tempFile = File.createTempFile(file.getName(), null);
                        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(tempFile), encoding))) {
                            String line;
                            while ((line = reader.readLine()) != null) {
                                line = line.replace("Ã§", "ç");
                                line = line.replace("Ã£o", "ão");
                                line = line.replace("Ãª", "ê");
                                line = line.replace("Ã¡", "á");
                                line = line.replace("Ã©", "é");
                                line = line.replace("Ã³", "ó");
                                line = line.replace("Ãº", "ú");
                                writer.write(line);
                                writer.newLine();
                            }
                            reader.close();
                            writer.close();
                        }
                    }
                    if (file.delete()) {
                        tempFile.renameTo(file);
                    } else {
                        throw new IOException("Não foi possível substituir o arquivo original.");
                    }
                } catch (IOException e) {
                    System.err.println("Erro ao corrigir o arquivo " + file.getName() + ": " + e.getMessage());
                }
            }
        }
    }
}

