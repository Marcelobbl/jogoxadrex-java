package xadrex;

import tabuleiro.Borda;
import tabuleiro.Peca;
import tabuleiro.Posicao;
import xadrex.pecas.Rei;
import xadrex.pecas.Torre;

public class PartidaXadrex {
	
	private Borda borda;
	
	public PartidaXadrex() {
		borda = new Borda(8, 8);
		iniciaPartida();
	}
	public PecaXadrex[][] getPecas(){
		PecaXadrex[][] mat = new PecaXadrex[borda.getLinhas()][borda.getColunas()];
		for(int i=0 ; i<borda.getLinhas();i++) {
			for(int j=0; j<borda.getColunas(); j++) {
				mat[i][j] = (PecaXadrex) borda.peca(i ,j);
			}
		}
		return mat;
	}
	
	public PecaXadrex ExecutarMovimentoXadrex(PosicaoXadrex posicaoOrigem, PosicaoXadrex posicaoDestino) {
		Posicao origem = posicaoOrigem.toPosicao();
		Posicao destino = posicaoDestino.toPosicao();
		validarPosicaoOrigem(origem);
		Peca capturarPeca = movePeca(origem, destino);
		return (PecaXadrex)capturarPeca;
	}
	
	private Peca movePeca(Posicao origem, Posicao destino) {
		Peca p = borda.removePeca(origem);
		Peca capturaPeca = borda.removePeca(destino);
		borda.colocarPeca(p, destino);
		return capturaPeca;
	}
	private void validarPosicaoOrigem(Posicao posicao) {
		if (!borda.existeUmaPeca(posicao));{
		throw new ExcessaoXadrex("Não existe peça na posição de origem");
		}
	}
	
	private void colocarNovaPeca(char coluna, int linha, PecaXadrex peca) {
		borda.colocarPeca(peca, new PosicaoXadrex(coluna, linha).toPosicao());
	}
	
	private void iniciaPartida() {
		colocarNovaPeca('c', 1, new Torre(borda, Cor.Branco));
		colocarNovaPeca('c', 2, new Torre(borda, Cor.Branco));
		colocarNovaPeca('d', 2, new Torre(borda, Cor.Branco));
		colocarNovaPeca('e', 2, new Torre(borda, Cor.Branco));
		colocarNovaPeca('e', 1, new Torre(borda, Cor.Branco));
		colocarNovaPeca('d', 1, new Rei(borda, Cor.Branco));

		colocarNovaPeca('c', 7, new Torre(borda, Cor.Preto));
		colocarNovaPeca('c', 8, new Torre(borda, Cor.Preto));
		colocarNovaPeca('d', 7, new Torre(borda, Cor.Preto));
		colocarNovaPeca('e', 7, new Torre(borda, Cor.Preto));
		colocarNovaPeca('e', 8, new Torre(borda, Cor.Preto));
		colocarNovaPeca('d', 8, new Rei(borda, Cor.Preto));
	}

}
