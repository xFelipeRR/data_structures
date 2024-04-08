import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
public class ArvoreBinariaBusca {

	class Nodo {

		public Palavra elemento;
		public Nodo esquerdo;
		public Nodo direito;

		public Nodo(Palavra elemento) {
			this.elemento = elemento;
			this.esquerdo = null;
			this.direito = null;
		}
	}

	public Nodo raiz;
	public int nElementos;

	public ArvoreBinariaBusca() {
		this.raiz = null;
		this.nElementos = 0;
	}

	public Palavra buscaPorPalavra(String palavra) {
		return buscaPorPalavra(palavra, this.raiz);
	}
	
	private Palavra buscaPorPalavra(String palavra, Nodo nodo) {
		if (nodo == null) {
			return null;
		}
	
		int comparacao = palavra.compareTo(nodo.elemento.getPalavra());
	
		if (comparacao < 0) {
			return buscaPorPalavra(palavra, nodo.esquerdo);
		} else if (comparacao > 0) {
			return buscaPorPalavra(palavra, nodo.direito);
		} else {
			return nodo.elemento;
		}
	}

	public String obterEmOrdemComOcorrencias() {
		StringBuilder resultado = new StringBuilder();
		obterEmOrdemComOcorrencias(this.raiz, resultado);
		return resultado.toString();
	}
	
	private void obterEmOrdemComOcorrencias(Nodo nodo, StringBuilder resultado) {
		if (nodo != null) {
			obterEmOrdemComOcorrencias(nodo.esquerdo, resultado);
			resultado.append(nodo.elemento.getPalavra()).append(" - ").append(nodo.elemento.formatarOcorrencias()).append("\n");
			obterEmOrdemComOcorrencias(nodo.direito, resultado);
		}
	}

	public int tamanho() {
		return this.nElementos;
	}

	public boolean estaVazia() {
		return this.raiz == null;
	}

	public void imprimeEmLargura() {

		FilaDinamica<Nodo> fila = new FilaDinamica<Nodo>();

		fila.enfileira(this.raiz);
		while (!fila.estaVazia()) {

			Nodo cursor = fila.desenfileira();

			System.out.print(cursor.elemento + " ");

			if (cursor.esquerdo != null) {
				fila.enfileira(cursor.esquerdo);
			}

			if (cursor.direito != null) {
				fila.enfileira(cursor.direito);
			}
		}

		System.out.println();

	}

	public void imprimePreOrdem() {
		this.preOrdem(this.raiz);
		System.out.println();
	}

	public void imprimePosOrdem() {
		this.posOrdem(this.raiz);
		System.out.println();
	}

	public void imprimeEmOrdem() {
		this.emOrdem(this.raiz);
		System.out.println();
	}

	public void preOrdem(Nodo nodo) {

		if (nodo == null)
			return;

		System.out.print(nodo.elemento + " ");
		this.preOrdem(nodo.esquerdo);
		this.preOrdem(nodo.direito);
	}

	public void posOrdem(Nodo nodo) {

		if (nodo == null)
			return;

		this.posOrdem(nodo.esquerdo);
		this.posOrdem(nodo.direito);
		System.out.print(nodo.elemento + " ");
	}

	public void emOrdem(Nodo nodo) {

		if (nodo == null)
			return;

		this.emOrdem(nodo.esquerdo);
		System.out.print(nodo.elemento + " ");
		//System.out.print(nodo.elemento.palavra + " ");
		this.emOrdem(nodo.direito);
	}

	public void insere(Palavra elemento) {
		this.insere(elemento, this.raiz);
	}

	public void insere(Palavra elemento, Nodo nodo) {

		Nodo novo = new Nodo(elemento);

		if (nodo == null) {
			this.raiz = novo;
			this.nElementos++;
			return;
		}

		if (elemento.getPalavra().compareTo(nodo.elemento.getPalavra()) < 0) {
			if (nodo.esquerdo == null) {
				nodo.esquerdo = novo;
				this.nElementos++;
				return;
			} else {
				this.insere(elemento, nodo.esquerdo);
			}
		}

		if (elemento.getPalavra().compareTo(nodo.elemento.getPalavra()) > 0) {
			if (nodo.direito == null) {
				nodo.direito = novo;
				this.nElementos++;
				return;
			} else {
				this.insere(elemento, nodo.direito);
			}
		}
	}

	private Nodo maiorElemento(Nodo nodo) {
		while (nodo.direito != null) {
			nodo = nodo.direito;
		}
		return nodo;
	}

	private Nodo menorElemento(Nodo nodo) {
		while (nodo.esquerdo != null) {
			nodo = nodo.esquerdo;
		}
		return nodo;
	}

	public boolean remove(Palavra elemento) {
		return this.remove(elemento, this.raiz) != null;
	}

	private Nodo remove(Palavra elemento, Nodo nodo) {

		if (nodo == null) {
			System.out.println("Valor não encontrado");
			return null;
		}

		if (elemento.getPalavra().compareTo(nodo.elemento.getPalavra()) < 0) {
			nodo.esquerdo = this.remove(elemento, nodo.esquerdo);
		} else if (elemento.getPalavra().compareTo(nodo.elemento.getPalavra()) > 0) {
			nodo.direito = this.remove(elemento, nodo.direito);
		} else {

//	    	if(nodo.esquerdo == null && nodo.direito == null) {
//	    		return null;
//	    	}

			if (nodo.esquerdo == null) {
				this.nElementos--;
				return nodo.direito;
			} else if (nodo.direito == null) {
				this.nElementos--;
				return nodo.esquerdo;
			} else {
				Nodo substituto = this.menorElemento(nodo.direito);
				nodo.elemento = substituto.elemento;
				this.remove(substituto.elemento, nodo.direito);
			}
		}

		return nodo;
	}

	public boolean busca(Palavra elemento) {
		return this.busca(elemento, this.raiz);

	}

	public boolean busca(Palavra elemento, Nodo nodo) {

		if (nodo == null) {
			return false;
		}

		if (elemento.getPalavra().compareTo(nodo.elemento.getPalavra()) < 0) {
			return this.busca(elemento, nodo.esquerdo);
		} else if (elemento.getPalavra().compareTo(nodo.elemento.getPalavra()) > 0) {
			return this.busca(elemento, nodo.direito);
		} else {
			return true;
		}
	}

	private int altura(Nodo nodo) {

		if (nodo == null) {
			return -1;
		}

		int alturaEsquerda = this.altura(nodo.esquerdo) + 1;
		int alturaDireita = this.altura(nodo.direito) + 1;

		int altura = alturaEsquerda > alturaDireita ? alturaEsquerda : alturaDireita;

		return altura;

	}

	public int altura() {
		return this.altura(this.raiz);
	}
}