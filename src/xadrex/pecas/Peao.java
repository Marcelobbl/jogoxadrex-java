package xadrex.pecas;

import tabuleiro.Borda;
import tabuleiro.Posicao;
import xadrex.Cor;
import xadrex.PecaXadrex;

public class Peao extends PecaXadrex {

	public Peao(Borda borda, Cor cor) {
		super(borda, cor);
	}

	@Override
	public boolean[][] possivelMovimentos() {
		boolean[][] mat = new boolean[getBorda().getLinhas()][getBorda().getColunas()];

		Posicao p = new Posicao(0, 0);
		
		if (getCor() == Cor.Branco) {
			p.setValores(posicao.getLinha() - 1, posicao.getColuna());
			if (getBorda().posicaoExiste(p) &&  !getBorda().existeUmaPeca(p)){
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValores(posicao.getLinha() - 2, posicao.getColuna());
			Posicao p2 = new Posicao (posicao.getLinha() - 1, posicao.getColuna());
			if (getBorda().posicaoExiste(p) &&  !getBorda().existeUmaPeca(p) && getBorda().posicaoExiste(p2) &&  !getBorda().existeUmaPeca(p2) && getContagemMovimento()== 0 ){
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValores(posicao.getLinha() - 1, posicao.getColuna()-1);
			if (getBorda().posicaoExiste(p) &&  temUmaPecaAdversario(p)){
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValores(posicao.getLinha() - 1, posicao.getColuna()+1);
			if (getBorda().posicaoExiste(p) &&  temUmaPecaAdversario(p)){
				mat[p.getLinha()][p.getColuna()] = true;
			}
		}
		else {
			p.setValores(posicao.getLinha() + 1, posicao.getColuna());
			if (getBorda().posicaoExiste(p) &&  !getBorda().existeUmaPeca(p)){
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValores(posicao.getLinha() + 2, posicao.getColuna());
			Posicao p2 = new Posicao (posicao.getLinha() + 1, posicao.getColuna());
			if (getBorda().posicaoExiste(p) &&  !getBorda().existeUmaPeca(p) && getBorda().posicaoExiste(p2) &&  !getBorda().existeUmaPeca(p2) && getContagemMovimento()== 0 ){
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValores(posicao.getLinha() + 1, posicao.getColuna()-1);
			if (getBorda().posicaoExiste(p) &&  temUmaPecaAdversario(p)){
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValores(posicao.getLinha() + 1, posicao.getColuna()+1);
			if (getBorda().posicaoExiste(p) &&  temUmaPecaAdversario(p)){
				mat[p.getLinha()][p.getColuna()] = true;
			}
		}
		return mat;
	}
	@Override
	public String toString() {
		return "P";
	}

}
