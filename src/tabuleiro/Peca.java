package tabuleiro;

public abstract class Peca {
	protected Posicao posicao;
	private Borda borda;
	
	public Peca(Borda borda) {
		this.borda = borda;
		posicao = null;
	}

	protected Borda getBorda() {
		return borda;
	}
		
	public abstract boolean [][] possivelMovimentos();
	
	public boolean possivelMovimento(Posicao posicao) {
		return possivelMovimentos()[posicao.getLinha()][posicao.getColuna()];
		}
	
	public boolean existeMovimentoPossivel() {
		boolean[][] mat = possivelMovimentos();
		for (int i=0; i<mat.length; i++) {
			for (int j=0;j<mat.length; j++) {
				if (mat[i][j]) {
					return true;
				}
			}
			
		}
		return false;
			
		}
	}
	
		
	

	