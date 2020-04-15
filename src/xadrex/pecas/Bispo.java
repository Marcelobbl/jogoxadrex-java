package xadrex.pecas;

import tabuleiro.Borda;
import tabuleiro.Posicao;
import xadrex.Cor;
import xadrex.PecaXadrex;

public class Bispo extends PecaXadrex {

	public Bispo(Borda borda, Cor cor) {
		super(borda, cor);
		}
	
	@Override
	public String toString() {
		return "B";
	}
	@Override
	public boolean[][] possivelMovimentos() {
		boolean[][] mat = new boolean [getBorda().getLinhas()][getBorda().getColunas()];
		
		Posicao p = new Posicao(0,0);
		
		p.setValores(posicao.getLinha() - 1 , posicao.getColuna()-1);
		while (getBorda().posicaoExiste(p) && !getBorda().existeUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValores(p.getLinha() - 1, p.getColuna() - 1);
		}
		if (getBorda().posicaoExiste(p) && temUmaPecaAdversario(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		p.setValores(posicao.getLinha() - 1  , posicao.getColuna() + 1 );
		while (getBorda().posicaoExiste(p) && !getBorda().existeUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValores(p.getLinha() - 1, p.getColuna() + 1);
		}
		if (getBorda().posicaoExiste(p) && temUmaPecaAdversario(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		p.setValores(posicao.getLinha() + 1 , posicao.getColuna() + 1);
		while (getBorda().posicaoExiste(p) && !getBorda().existeUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValores(p.getLinha() + 1, p.getColuna() + 1);
		}
		if (getBorda().posicaoExiste(p) && temUmaPecaAdversario(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		p.setValores(posicao.getLinha() + 1 , posicao.getColuna() - 1);
		while (getBorda().posicaoExiste(p) && !getBorda().existeUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValores(p.getLinha() + 1, p.getColuna() - 1);
		}
		if (getBorda().posicaoExiste(p) && temUmaPecaAdversario(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		return mat;
	}
	
	
}
