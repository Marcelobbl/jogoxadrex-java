package xadrex;

import tabuleiro.Borda;
import xadrex.pecas.Rei;
import xadrex.pecas.Torre;

public class PartidaXadrex {
	
	private Borda borda;
	
	public PartidaXadrex() {
		borda = new Borda(8, 8);
		iniciaPartida();
	}
	public PecaXadrex[][] getPeca(){
		PecaXadrex[][] mat = new PecaXadrex[borda.getLinhas()][borda.getColunas()];
		for(int i=0 ; i<borda.getLinhas();i++) {
			for(int j=0; j<borda.getColunas(); j++) {
				mat[i][j] = (PecaXadrex) borda.peca(i ,j);
			}
		}
		return mat;
	}
	
	private void colocarNovaPeca(char coluna, int linha, PecaXadrex peca) {
		borda.colocarPeca(peca, new PosicaoXadrex(coluna, linha).toPosicao());
	}
	private void iniciaPartida() {
		colocarNovaPeca('b', 6, new Torre(borda, Cor.Branco));
		colocarNovaPeca('e', 8, new Rei(borda, Cor.Preto));
		colocarNovaPeca('e', 1, new Rei(borda, Cor.Branco));
	}

}
