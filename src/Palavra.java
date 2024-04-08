public class Palavra {
    String palavra;
    ListaEncadeadaInteiros ocorrencias;
    
    public Palavra(String palavra, ListaEncadeadaInteiros ocorrencias) {
        this.palavra = palavra;
        this.ocorrencias = ocorrencias;
    }

    public String getPalavra() {
        return palavra;
    }
    public void setPalavra(String palavra) {
        this.palavra = palavra;
    }
    public ListaEncadeadaInteiros getLista() {
        return ocorrencias;
    }
    public String formatarOcorrencias() {
        String ocorrenciasFormatadas = "";
       for(int i = 0; i < ocorrencias.tamanho(); i++) {
        //System.out.print(palavra+": ");
        ocorrenciasFormatadas = ocorrencias.imprimeLista();
       }
       return ocorrenciasFormatadas;
    }
    public void setLista(int linha) {
        ocorrencias.insereFinal(linha);
    }
}
