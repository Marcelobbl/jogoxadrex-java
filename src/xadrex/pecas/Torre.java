package xadrex.pecas;

import tabuleiro.Borda;
import xadrex.Cor;
import xadrex.PecaXadrex;

public class Torre extends PecaXadrex {

	public Torre(Borda borda, Cor cor) {
		super(borda, cor);
		}
	
	@Override
	public String toString() {
		return "T";
	}

}
