public class FilaDinamica<T> {

    public class Nodo {

        public T elemento;
        public Nodo proximo;

        public Nodo(T elemento) {
            this.elemento = elemento;
            this.proximo = null;
        }
    }

    private Nodo inicio;
    private int nElementos;

    public FilaDinamica() {
        this.inicio = null;
        this.nElementos = 0;
    }

    public int tamanho() {
        return this.nElementos;
    }

    public boolean estaVazia() {
        return this.inicio == null;
    }

    public void imprime() {
        System.out.print("[");
        Nodo cursor = this.inicio;
        for (int i = 0; i < this.nElementos; i++) {
            System.out.print(cursor.elemento + " ");
            cursor = cursor.proximo;
        }
        System.out.println("]");
    }

    public T desenfileira() {
        if (this.estaVazia()) {
            System.out.println("Fila vazia. Não é possível desenfileirar.");
            return null;
        }
        Nodo nodoRemovido = this.inicio;
        this.inicio = nodoRemovido.proximo;
        this.nElementos--;
        nodoRemovido.proximo = null;
        return nodoRemovido.elemento;
    }

    public void enfileira(T elemento) {
        Nodo novo = new Nodo(elemento);
        if (this.estaVazia()) {
            this.inicio = novo;
        } else {
            Nodo cursor = this.inicio;
            for (int i = 1; i < this.nElementos; i++) {
                cursor = cursor.proximo;
            }
            cursor.proximo = novo;
        }
        this.nElementos++;
    }

    public boolean contem(T elemento) {
        Nodo cursor = this.inicio;
        for (int i = 0; i < this.nElementos; i++) {
            if (cursor.elemento.equals(elemento)) {
                return true;
            }
            cursor = cursor.proximo;
        }
        return false;
    }

    public T espia() {
        if (this.estaVazia()) {
            System.out.println("Fila vazia! Não é possível espiar.");
            return null;
        }
        return this.inicio.elemento;
    }
}
