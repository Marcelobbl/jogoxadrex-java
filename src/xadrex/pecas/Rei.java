package xadrex.pecas;

import tabuleiro.Borda;
import xadrex.Cor;
import xadrex.PecaXadrex;

public class Rei extends PecaXadrex {

	public Rei(Borda borda, Cor cor) {
		super(borda, cor);
		}
	@Override
	public String toString() {
		return "K";
	}
	@Override
	public boolean[][] possivelMovimentos() {
		boolean[][] mat = new boolean [getBorda().getLinhas()][getBorda().getColunas()];
		return mat;
	}

}
