package xadrex.pecas;

import tabuleiro.Borda;
import tabuleiro.Posicao;
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
	@Override
	public boolean[][] possivelMovimentos() {
		boolean[][] mat = new boolean [getBorda().getLinhas()][getBorda().getColunas()];
		
		Posicao p = new Posicao(0,0);
		
		p.setValores(posicao.getLinha() - 1 , posicao.getColuna());
		while (getBorda().posicaoExiste(p) && !getBorda().existeUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setLinha(p.getLinha() - 1);
		}
		if (getBorda().posicaoExiste(p) && temUmaPecaAdversario(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		p.setValores(posicao.getLinha()  , posicao.getColuna() - 1);
		while (getBorda().posicaoExiste(p) && !getBorda().existeUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setColuna(p.getColuna() - 1);
		}
		if (getBorda().posicaoExiste(p) && temUmaPecaAdversario(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		p.setValores(posicao.getLinha()  , posicao.getColuna() + 1);
		while (getBorda().posicaoExiste(p) && !getBorda().existeUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setColuna(p.getColuna() + 1);
		}
		if (getBorda().posicaoExiste(p) && temUmaPecaAdversario(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		p.setValores(posicao.getLinha() + 1 , posicao.getColuna());
		while (getBorda().posicaoExiste(p) && !getBorda().existeUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setLinha(p.getLinha() + 1);
		}
		if (getBorda().posicaoExiste(p) && temUmaPecaAdversario(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		return mat;
	}
	
	
}
