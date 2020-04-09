package xadrex;

import java.util.ArrayList;
import java.util.List;

import tabuleiro.Borda;
import tabuleiro.Peca;
import tabuleiro.Posicao;
import xadrex.pecas.Rei;
import xadrex.pecas.Torre;

public class PartidaXadrex {
	
	private int turno;
	private Cor jogadorVez;
	private Borda borda;
	
	private List<Peca> pecasNoTabuleiro = new ArrayList<>();
	private List<Peca> pecasCapturadas = new ArrayList<>();
	
	
	public PartidaXadrex() {
		borda = new Borda(8, 8);
		turno = 1;
		jogadorVez =Cor.Branco;
		iniciaPartida();
	}
	
	public int getTurno() {
		return turno;
	}
	
	public Cor getJogadorVez() {
		return jogadorVez;
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
	
	public boolean[][] possivelMovimentos(PosicaoXadrex origemPosicao){
		Posicao posicao = origemPosicao.toPosicao();
		validarPosicaoOrigem(posicao);
		return borda.peca(posicao).possivelMovimentos();
	}
	
	public PecaXadrex ExecutarMovimentoXadrex(PosicaoXadrex posicaoOrigem, PosicaoXadrex posicaoDestino) {
		Posicao origem = posicaoOrigem.toPosicao();
		Posicao destino = posicaoDestino.toPosicao();
		validarPosicaoOrigem(origem);
		validarPosicaoDestino(origem, destino);
		Peca capturarPeca = movePeca(origem, destino);
		proximoTurno();
		return (PecaXadrex)capturarPeca;
	}
	
	private Peca movePeca(Posicao origem, Posicao destino) {
		Peca p = borda.removePeca(origem);
		Peca capturaPeca = borda.removePeca(destino);
		borda.colocarPeca(p, destino);
		if (capturaPeca != null) {
			pecasNoTabuleiro.remove(capturaPeca);
			pecasCapturadas.add(capturaPeca);
		}
		return capturaPeca;
	}
	private void validarPosicaoOrigem(Posicao posicao) {
		if (!borda.existeUmaPeca(posicao)){
		throw new ExcessaoXadrex("N�o existe pe�a na posi��o de origem");
		}
		if (jogadorVez != ((PecaXadrex)borda.peca(posicao)).getCor()) {
			throw new ExcessaoXadrex("A pe�a ecolhida n�o � sua: ");
		}
		if (!borda.peca(posicao).existeMovimentoPossivel()) {
			throw new ExcessaoXadrex("N�o existe movimentos possiveis para a pe�a escolhida");
		}
	}
	
	private void validarPosicaoDestino(Posicao origem, Posicao destino) {
		if (!borda.peca(origem).possivelMovimento(destino)) {
			throw new ExcessaoXadrex("A pe�a escolhida n�o pode se mover para a posi��o de destino");
		}
	}
	
	private void proximoTurno() {
		turno ++;
		jogadorVez = (jogadorVez== Cor.Branco) ? Cor.Preto : Cor.Branco;
	}
		
	private void colocarNovaPeca(char coluna, int linha, PecaXadrex peca) {
		borda.colocarPeca(peca, new PosicaoXadrex(coluna, linha).toPosicao());
		pecasNoTabuleiro.add(peca);
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
