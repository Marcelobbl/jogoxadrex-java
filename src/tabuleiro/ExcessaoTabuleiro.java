package tabuleiro;

public class ExcessaoTabuleiro extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public ExcessaoTabuleiro(String msg) {
		super(msg);
	}
}
