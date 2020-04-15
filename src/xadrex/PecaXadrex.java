package xadrex;

import tabuleiro.Borda;
import tabuleiro.Peca;
import tabuleiro.Posicao;

public abstract class PecaXadrex extends Peca{
	
	private Cor cor;
	private int contagemMovimento;

	public PecaXadrex(Borda borda, Cor cor) {
		super(borda);
		this.cor = cor;
	}

	public Cor getCor() {
		return cor;
	}
	
	public int getContagemMovimento() {
		return contagemMovimento;
	}
	
	public void aumentaContagemMovimento() {
		contagemMovimento ++;
	}
	
	public void diminuiContagemMovimento() {
		contagemMovimento --;
	}
	
	public PosicaoXadrex getPosicaoXadrex() {
		return PosicaoXadrex.fromPosicao(posicao);
	}

	
	
	protected boolean temUmaPecaAdversario(Posicao posicao) {
		PecaXadrex p = (PecaXadrex)getBorda().peca(posicao);
		return p != null && p.getCor() != cor;
	}

		
	
}
