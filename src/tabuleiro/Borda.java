package tabuleiro;

public class Borda {
	
	private int linhas;
	private int colunas;
	private Peca[][] pecas;
	
	public Borda(int linhas, int colunas) {
		if (linhas< 1 || colunas < 1) {
			throw new ExcessaoTabuleiro("Erro de tabuleiro: E necessario que haja pelo menos uma linha e uma coluna");
			}
		this.linhas = linhas;
		this.colunas = colunas;
		pecas = new Peca[linhas][colunas];
	}

	public int getLinhas() {
		return linhas;
	}

	
	public int getColunas() {
		return colunas;
	}

	public Peca peca(int linha, int coluna) {
		if (!posicaoExiste(linha, coluna)){
			throw new ExcessaoTabuleiro("Posicao fora do tabuleiro");
		}
		return pecas[linha][coluna];
	}
	public Peca peca(Posicao posicao) {
		if (!posicaoExiste(posicao)){
			throw new ExcessaoTabuleiro("Posicao fora do tabuleiro");
		}
		return pecas[posicao.getLinha()][posicao.getColuna()];
	}
	
	public void colocarPeca(Peca peca, Posicao posicao) {
		if(existeUmaPeca(posicao)) {
			throw new ExcessaoTabuleiro("Já existe uma peça na posição " + posicao);
		}
		pecas[posicao.getLinha()][posicao.getColuna()] = peca;
		peca.posicao = posicao;
	}
	
	public Peca removePeca(Posicao posicao) {
		if (!posicaoExiste(posicao)){
			throw new ExcessaoTabuleiro("Posicao fora do tabuleiro");	
		}
		if (peca(posicao)== null) {
			return null;
		}
		Peca aux = peca(posicao);
		aux.posicao = null;
		pecas[posicao.getLinha()][posicao.getColuna()] = null;
		return aux;
	}
	
	private boolean posicaoExiste(int linha, int coluna) {
		
		return linha >= 0 && linha < linhas && coluna >= 0 && coluna < colunas; 
	}
	
	public boolean posicaoExiste(Posicao posicao) {
		return posicaoExiste(posicao.getLinha(), posicao.getColuna());
	}
	
	public boolean existeUmaPeca(Posicao posicao) {
		if (!posicaoExiste(posicao)){
			throw new ExcessaoTabuleiro("Posicao fora do tabuleiro");
		}
		return peca(posicao) != null;
	}
}
