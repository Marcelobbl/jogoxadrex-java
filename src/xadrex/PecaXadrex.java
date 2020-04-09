package xadrex;

import tabuleiro.Borda;
import tabuleiro.Peca;
import tabuleiro.Posicao;

public abstract class PecaXadrex extends Peca{
	
	private Cor cor;

	public PecaXadrex(Borda borda, Cor cor) {
		super(borda);
		this.cor = cor;
	}

	public Cor getCor() {
		return cor;
	}
	
	protected boolean temUmaPecaAdversario(Posicao posicao) {
		PecaXadrex p = (PecaXadrex)getBorda().peca(posicao);
		return p != null && p.getCor() != cor;
	}

		
	
}
