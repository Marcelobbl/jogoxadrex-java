package xadrex;

import tabuleiro.Borda;

public class PartidaXadrex {
	
	private Borda borda;
	
	public PartidaXadrex() {
		borda = new Borda(8, 8);
	}
	public PecaXadrex[][] getPeca(){
		PecaXadrex[][] mat = new PecaXadrex[borda.getLinha()][borda.getColuna()];
		for(int i=0 ; i<borda.getLinha();i++) {
			for(int j=0; j<borda.getColuna(); j++) {
				mat[i][j] = (PecaXadrex) borda.peca(i ,j);
			}
		}
		return mat;
	}

}
