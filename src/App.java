import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.text.Normalizer;

public class App {
    public static void main(String[] args) throws Exception {
        String inputFile = "input.txt";
        String inputFilePalavras = "palavraschaves.txt";
        String outputFile = "output.txt";
        int linhaAtual = 0;
        int linhaAtualP = 0;

        ListaEncadeada<Palavra> lista_palavras = new ListaEncadeada<>();
        TabelaHashGenerico hash = new TabelaHashGenerico(25);


        // Ler o arquivo de palavras chaves
        try {
            FileReader fileReaderP = new FileReader(inputFilePalavras);
            BufferedReader bufferedReaderP = new BufferedReader(fileReaderP);

            Scanner scannerP = new Scanner(fileReaderP);

            while (scannerP.hasNextLine()) {
                linhaAtualP++;
                String lineP = scannerP.nextLine();
                String[] partsP = lineP.split(" ");

                for(int i = 0; i < partsP.length; i++) {
                    // NORMALIZAÇÃO
                    String palavraAtualP = partsP[i].toLowerCase();  // Convertendo para minúsculas para evitar diferenças de maiúsculas e minúsculas
                    String normalizadoP = Normalizer.normalize(palavraAtualP, Normalizer.Form.NFD);
                    palavraAtualP = normalizadoP.replaceAll("[^\\p{ASCII}]", "");

                    lista_palavras.insereFinal(new Palavra(palavraAtualP, new ListaEncadeadaInteiros()));
                    int posicaoHash = 0;
                    posicaoHash = hash.posicaoHash(palavraAtualP);
                    Palavra palavraInserida = new Palavra(palavraAtualP, new ListaEncadeadaInteiros());
                    Boolean existeAbb = hash.existe(posicaoHash);
                    if(existeAbb == false) {
                        ArvoreBinariaBusca abb = new ArvoreBinariaBusca();
                        abb.insere(palavraInserida);
                        hash.insere(abb, posicaoHash);
                    }
                    else {
                        ArvoreBinariaBusca abbExistente = hash.busca(posicaoHash);
                        abbExistente.insere(palavraInserida);
                    }
                }
            }
            
            //scanner.close();

            String lineP;
            while ((lineP = bufferedReaderP.readLine()) != null) {
                System.out.println(lineP); // Imprime cada linha na saída padrão
            }

            bufferedReaderP.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Ler o arquivo de texto
        try {
            FileReader fileReader = new FileReader(inputFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            Scanner scanner = new Scanner(fileReader);

            while (scanner.hasNextLine()) {
                linhaAtual++;
                String line = scanner.nextLine();
                String[] parts = line.split(" ");
                int indexPalavraEncontrada = 0;

                for(int i = 0; i < parts.length; i++) {
                    String palavraAtual = parts[i].toLowerCase();  // Convertendo para minúsculas para evitar diferenças de maiúsculas e minúsculas
                    palavraAtual = normalizar(palavraAtual);
                    
                    // Verificando se a palavraAtual está presente no array palavras chaves
                    boolean palavraChaveEncontrada = false;
                    for(int c = 0 ; c < lista_palavras.tamanho(); c++) {
                        String palavraAtualLista = lista_palavras.acesse(c).getPalavra();
                        if (palavraAtualLista.equals(palavraAtual)) {
                            int posicaoHash = 0;
                            posicaoHash = hash.posicaoHash(palavraAtual);
                            ArvoreBinariaBusca abbEdit = hash.busca(posicaoHash);
                            Palavra palavraEdit = abbEdit.buscaPorPalavra(palavraAtual);
                            palavraEdit.setLista(linhaAtual);
                            
                            palavraChaveEncontrada = true;
                            indexPalavraEncontrada = c;
                            break;  // Se encontrou a palavra-chave, não é necessário continuar o loop
                        }
                    }
                    
                    if (palavraChaveEncontrada) {
                        // VER INFORMAÇÕES SEREM ATUALIZADAS DURANTE A ITERAÇÃO
                        //System.out.println("A palavra-chave foi encontrada: " + palavraAtual + " na linha "+linhaAtual+" - Posição na Hash: "+posicaoHash+" - Lista: "+lista_palavras.acesse(indexPalavraEncontrada).formatarOcorrencias());
                        
                    }
                }
            }

            // for(int contador = 0; contador < lista_palavras.tamanho(); contador++) {
            //     // VER TODAS AS INFORMAÇÕES PÓS ITERAÇÃO
            //     System.out.println("A palavra-chave foi encontrada: " + lista_palavras.acesse(contador).palavra +" - Posição na Hash: "+hash.posicaoHash(lista_palavras.acesse(contador).palavra)+" - Lista: "+lista_palavras.acesse(contador).formatarOcorrencias());
            // }

            FileWriter fileWriter = new FileWriter(outputFile);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for(int tamanhoHash = 0;tamanhoHash < 25; tamanhoHash++) {
                Boolean existeAbb = hash.existe(tamanhoHash);
                if(existeAbb == true) {
                    ArvoreBinariaBusca abbEdit = hash.busca(tamanhoHash);
                    System.out.println(abbEdit.obterEmOrdemComOcorrencias());
                    try {
                        bufferedWriter.write(abbEdit.obterEmOrdemComOcorrencias());
                        bufferedWriter.newLine();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            bufferedWriter.close();

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line); // Imprime cada linha na saída padrão
            }

            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String normalizar(String input) {
        String normalizado = Normalizer.normalize(input, Normalizer.Form.NFD);
        return normalizado.replaceAll("[^\\p{ASCII}]", "").replaceAll("[,.?]", "");
    }
}
