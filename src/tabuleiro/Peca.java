package tabuleiro;

public class Peca {
	protected Posicao posicao;
	private Borda borda;
	
	public Peca(Borda borda) {
		this.borda = borda;
		posicao = null;
	}

	protected Borda getBorda() {
		return borda;
	}

	
	
}
