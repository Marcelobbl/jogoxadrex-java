package xadrex.pecas;

import tabuleiro.Borda;
import tabuleiro.Posicao;
import xadrex.Cor;
import xadrex.PartidaXadrex;
import xadrex.PecaXadrex;

public class Rei extends PecaXadrex {

	private PartidaXadrex partidaXadrex;
	
	public Rei(Borda borda, Cor cor, PartidaXadrex partidaXadrex) {
		super(borda, cor);
		this.partidaXadrex = partidaXadrex;
		}
	@Override
	public String toString() {
		return "K";
	}
	
	private boolean possoMover(Posicao posicao) {
		PecaXadrex p = (PecaXadrex)getBorda().peca(posicao);
		return p == null || p.getCor() != getCor();
		}
	
	private boolean testTorreRoque(Posicao posicao) {
		PecaXadrex p = (PecaXadrex)getBorda().peca(posicao);
		return p != null && p instanceof Torre && p.getCor() == getCor() && p.getContagemMovimento() == 0;
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
		if (getContagemMovimento()== 0 && !partidaXadrex.getCheck()) {
			Posicao posT1 = new Posicao(posicao.getLinha(), posicao.getColuna() + 3);
			if (testTorreRoque(posT1)) {
				Posicao p1 = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
				Posicao p2 = new Posicao(posicao.getLinha(), posicao.getColuna() + 2);
				if (getBorda().peca(p1)== null && getBorda().peca(p2)== null) {
					mat[posicao.getLinha()][posicao.getColuna() + 2] = true;
				}
			}
			Posicao posT2 = new Posicao(posicao.getLinha(), posicao.getColuna() - 4);
			if (testTorreRoque(posT2)) {
				Posicao p1 = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
				Posicao p2 = new Posicao(posicao.getLinha(), posicao.getColuna() - 2);
				Posicao p3 = new Posicao(posicao.getLinha(), posicao.getColuna() - 3);
				if (getBorda().peca(p1)== null && getBorda().peca(p2)== null && getBorda().peca(p3)== null) {
					mat[posicao.getLinha()][posicao.getColuna() - 2] = true;
				}
			}
		}
			
		return mat;
	
	}
}
