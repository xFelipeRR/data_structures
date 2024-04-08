public class TabelaHashGenerico {

    public ArvoreBinariaBusca vetor[];
    public int nElementos;

    public TabelaHashGenerico(int capacidade) {
        this.vetor = new ArvoreBinariaBusca[capacidade];
        this.nElementos = 0;
    }

    public int posicaoHash(String texto) {
        if (texto == null || texto.isEmpty()) {
            throw new IllegalArgumentException("Texto inválido");
        }
        char primeiroCaractere = texto.charAt(0);
        int resultado = (int) primeiroCaractere - 97;
        return resultado;
    }

    public int tamanho() {
        return this.nElementos;
    }

    public void imprime() {
        System.out.println("Chave\tValor");
        for (int i = 0; i < vetor.length; i++) {
            System.out.println(i + " -->\t[ " + vetor[i] + " ]");
        }
    }

    private int funcaoHashDiv(int pos) {
        return pos % this.vetor.length;
    }

    public void insere(ArvoreBinariaBusca elemento, int pos) {
        this.vetor[pos] = elemento;
        this.nElementos++;
        //System.out.println(elemento + " inserido com sucesso na posição " + pos);
    }

    public ArvoreBinariaBusca remove(int pos) {
        ArvoreBinariaBusca removido = this.vetor[pos];
        this.vetor[pos] = null;
        this.nElementos--;
        return removido;
    }

    public ArvoreBinariaBusca busca(int pos) {
        return this.vetor[pos];
    }

    public boolean existe(int pos) {
        ArvoreBinariaBusca elemento = this.vetor[pos];
        return elemento != null;
    }

    public boolean contem(int pos) {
        return this.vetor[pos] != null;
    }
}
