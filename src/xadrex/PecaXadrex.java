package xadrex;

import tabuleiro.Borda;
import tabuleiro.Peca;

public abstract class PecaXadrex extends Peca{
	
	private Cor cor;

	public PecaXadrex(Borda borda, Cor cor) {
		super(borda);
		this.cor = cor;
	}

	public Cor getCor() {
		return cor;
	}

		
	
}
