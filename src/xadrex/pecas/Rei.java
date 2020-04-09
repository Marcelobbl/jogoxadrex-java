package xadrex.pecas;

import tabuleiro.Borda;
import tabuleiro.Posicao;
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
	
	private boolean possoMover(Posicao posicao) {
		PecaXadrex p = (PecaXadrex)getBorda().peca(posicao);
		return p == null || p.getCor() != getCor();
		}
	@Override
	public boolean[][] possivelMovimentos() {
		boolean[][] mat = new boolean [getBorda().getLinhas()][getBorda().getColunas()];
		
		Posicao p = new Posicao (0,0);
		p.setValores(posicao.getLinha() - 1 , posicao.getColuna());
		if (getBorda().posicaoExiste(p) && possoMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		p.setValores(posicao.getLinha() - 1 , posicao.getColuna() + 1);
		if (getBorda().posicaoExiste(p) && possoMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		p.setValores(posicao.getLinha()  - 1 , posicao.getColuna() - 1);
		if (getBorda().posicaoExiste(p) && possoMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		p.setValores(posicao.getLinha()  , posicao.getColuna() + 1);
		if (getBorda().posicaoExiste(p) && possoMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		p.setValores(posicao.getLinha()   , posicao.getColuna() - 1);
		if (getBorda().posicaoExiste(p) && possoMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		p.setValores(posicao.getLinha() + 1 , posicao.getColuna());
		if (getBorda().posicaoExiste(p) && possoMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		p.setValores(posicao.getLinha() + 1 , posicao.getColuna() + 1);
		if (getBorda().posicaoExiste(p) && possoMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		p.setValores(posicao.getLinha()  + 1 , posicao.getColuna() - 1);
		if (getBorda().posicaoExiste(p) && possoMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
			
		return mat;
	}

}
