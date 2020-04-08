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

	public Peca peca(int linhas, int colunas) {
		if (!posicaoExiste(linhas, colunas)){
			throw new ExcessaoTabuleiro("Posicao fora do tabuleiro");
		}
		return pecas[linhas][colunas];
	}
	public Peca peca(Posicao posicao) {
		if (!posicaoExiste(posicao)){
			throw new ExcessaoTabuleiro("Posicao fora do tabuleiro");
		}
		return pecas[posicao.getLinhas()][posicao.getColunas()];
	}
	
	public void colocarPeca(Peca peca, Posicao posicao) {
		if(existePeca(posicao)) {
			throw new ExcessaoTabuleiro("Já existe uma peça na posição " + posicao);
		}
		pecas[posicao.getLinhas()][posicao.getColunas()] = peca;
		peca.posicao = posicao;
	}
	
	private boolean posicaoExiste(int linha, int coluna) {
		
		return linha >= 0 && linha < linhas && coluna >= 0 && coluna < colunas; 
	}
	public boolean posicaoExiste(Posicao posicao) {
		return posicaoExiste(posicao.getLinhas(), posicao.getColunas());
	}
	public boolean existePeca(Posicao posicao) {
		if (!posicaoExiste(posicao)){
			throw new ExcessaoTabuleiro("Posicao fora do tabuleiro");
		}
		return peca(posicao) != null;
	}
}
