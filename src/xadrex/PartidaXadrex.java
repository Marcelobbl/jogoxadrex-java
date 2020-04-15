package xadrex;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import tabuleiro.Borda;
import tabuleiro.Peca;
import tabuleiro.Posicao;
import xadrex.pecas.Bispo;
import xadrex.pecas.Peao;
import xadrex.pecas.Rei;
import xadrex.pecas.Torre;

public class PartidaXadrex {
	
	private int turno;
	private Cor jogadorVez;
	private Borda borda;
	private boolean check;
	private boolean checkMate;
	
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
	
	public boolean getCheck() {
		return check;
	}
	public boolean getCheckMate() {
		return checkMate;
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
		
		if(testeCheck(jogadorVez)) {
			desfazMovimento(origem, destino, capturarPeca);
			throw new ExcessaoXadrex("Voçe não pode se colocar em check");
		}
		
		check = (testeCheck(oponente(jogadorVez))) ? true : false;
		
		if (testeCheckMate(oponente(jogadorVez))){
			checkMate = true;
		}
		else {
			proximoTurno();
		}
		return (PecaXadrex)capturarPeca;
	}
	
	private Peca movePeca(Posicao origem, Posicao destino) {
		PecaXadrex p = (PecaXadrex)borda.removePeca(origem);
		p.aumentaContagemMovimento();
		Peca capturaPeca = borda.removePeca(destino);
		borda.colocarPeca(p, destino);
		
		if (capturaPeca != null) {
			pecasNoTabuleiro.remove(capturaPeca);
			pecasCapturadas.add(capturaPeca);
		}
		return capturaPeca;
	}
	
	private void desfazMovimento(Posicao origem, Posicao destino, Peca pecaCapturada) {
		PecaXadrex p = (PecaXadrex)borda.removePeca(destino);
		p.diminuiContagemMovimento();
		borda.colocarPeca(p, origem);
		
		if (pecaCapturada!= null) {
			borda.colocarPeca(pecaCapturada, destino);
			pecasCapturadas.remove(pecaCapturada);
			pecasNoTabuleiro.add(pecaCapturada);
		}
	}
	
	private void validarPosicaoOrigem(Posicao posicao) {
		if (!borda.existeUmaPeca(posicao)){
		throw new ExcessaoXadrex("Não existe peça na posição de origem");
		}
		if (jogadorVez != ((PecaXadrex)borda.peca(posicao)).getCor()) {
			throw new ExcessaoXadrex("A peça ecolhida não é sua: ");
		}
		if (!borda.peca(posicao).existeMovimentoPossivel()) {
			throw new ExcessaoXadrex("Não existe movimentos possiveis para a peça escolhida");
		}
	}
	
	private void validarPosicaoDestino(Posicao origem, Posicao destino) {
		if (!borda.peca(origem).possivelMovimento(destino)) {
			throw new ExcessaoXadrex("A peça escolhida não pode se mover para a posição de destino");
		}
	}
	
	private void proximoTurno() {
		turno ++;
		jogadorVez = (jogadorVez== Cor.Branco) ? Cor.Preto : Cor.Branco;
	}
	
	private Cor oponente(Cor cor) {
		return (cor == Cor.Branco) ? Cor.Preto : Cor.Branco;
	}
		
	private PecaXadrex rei(Cor cor) {
		List<Peca> list = pecasNoTabuleiro.stream().filter(x ->((PecaXadrex)x).getCor()== cor).collect(Collectors.toList());
		for (Peca p : list) {
			if (p instanceof Rei) {
				return (PecaXadrex)p;
			}
		}
		throw new IllegalStateException("Não existe o rei " + cor + " no tabuleiro");
	}
	
	private boolean testeCheck(Cor cor) {
		Posicao reiPosicao = rei(cor).getPosicaoXadrex().toPosicao();
		List<Peca> pecasOponente = pecasNoTabuleiro.stream().filter(x ->((PecaXadrex)x).getCor()== oponente(cor)).collect(Collectors.toList());
		for (Peca p : pecasOponente) {
			boolean[][] mat = p.possivelMovimentos();
			if (mat[reiPosicao.getLinha()][reiPosicao.getColuna()]) {
				return true;
			}
		}
		return false;
	}
	
	private boolean testeCheckMate(Cor cor) {
		if(!testeCheck(cor)) {
			return false;
		}
		List<Peca> lista = pecasNoTabuleiro.stream().filter(x ->((PecaXadrex)x).getCor()== cor).collect(Collectors.toList());
		for(Peca p : lista) {
			boolean[][] mat = p.possivelMovimentos();
			for(int i = 0; i< borda.getLinhas();i++) {
				for(int j = 0; j< borda.getColunas();j++) {
					if(mat[i][j]) {
						Posicao origem = ((PecaXadrex)p).getPosicaoXadrex().toPosicao();
						Posicao destino = new Posicao(i,j);
						Peca pecaCapturada = movePeca(origem, destino);
						boolean testeCheck = testeCheck(cor);
						desfazMovimento(origem, destino, pecaCapturada);
						if(!testeCheck) {
							return false;
						}
					}
				}
			}
		}
		return true;	
	}
	
	
	private void colocarNovaPeca(char coluna, int linha, PecaXadrex peca) {
		borda.colocarPeca(peca, new PosicaoXadrex(coluna, linha).toPosicao());
		pecasNoTabuleiro.add(peca);
	}
	
	private void iniciaPartida() {
		colocarNovaPeca('a', 1, new Torre(borda, Cor.Branco));
		colocarNovaPeca('c', 1, new Bispo(borda, Cor.Branco));
		colocarNovaPeca('e', 1, new Rei(borda, Cor.Branco));
		colocarNovaPeca('f', 1, new Bispo(borda, Cor.Branco));
		colocarNovaPeca('h', 1, new Torre(borda, Cor.Branco));
		colocarNovaPeca('a', 2, new Peao(borda, Cor.Branco));
		colocarNovaPeca('b', 2, new Peao(borda, Cor.Branco));
		colocarNovaPeca('c', 2, new Peao(borda, Cor.Branco));
		colocarNovaPeca('d', 2, new Peao(borda, Cor.Branco));
		colocarNovaPeca('e', 2, new Peao(borda, Cor.Branco));
		colocarNovaPeca('f', 2, new Peao(borda, Cor.Branco));
		colocarNovaPeca('g', 2, new Peao(borda, Cor.Branco));
		colocarNovaPeca('h', 2, new Peao(borda, Cor.Branco));
		
		colocarNovaPeca('a', 8, new Torre(borda, Cor.Preto));
		colocarNovaPeca('c', 8, new Bispo(borda, Cor.Preto));
		colocarNovaPeca('e', 8, new Rei(borda, Cor.Preto));
		colocarNovaPeca('f', 8, new Bispo(borda, Cor.Preto));
		colocarNovaPeca('h', 8, new Torre(borda, Cor.Preto));
		colocarNovaPeca('a', 7, new Peao(borda, Cor.Preto));
		colocarNovaPeca('b', 7, new Peao(borda, Cor.Preto));
		colocarNovaPeca('c', 7, new Peao(borda, Cor.Preto));
		colocarNovaPeca('d', 7, new Peao(borda, Cor.Preto));
		colocarNovaPeca('e', 7, new Peao(borda, Cor.Preto));
		colocarNovaPeca('f', 7, new Peao(borda, Cor.Preto));
		colocarNovaPeca('g', 7, new Peao(borda, Cor.Preto));
		colocarNovaPeca('h', 7, new Peao(borda, Cor.Preto));
		
	}

}
