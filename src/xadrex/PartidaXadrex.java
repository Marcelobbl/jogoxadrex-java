package xadrex;

import tabuleiro.Borda;
import tabuleiro.Posicao;
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
	private void iniciaPartida() {
		borda.colocarPeca(new Torre(borda, Cor.Branco), new Posicao(9, 1));
		borda.colocarPeca(new Rei(borda, Cor.Preto), new Posicao(0, 4));
		borda.colocarPeca(new Rei(borda, Cor.Branco), new Posicao(7, 4));
	}

}
