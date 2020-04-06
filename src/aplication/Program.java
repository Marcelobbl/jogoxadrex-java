package aplication;


import xadrex.PartidaXadrex;


public class Program {

	public static void main(String[] args) {
		PartidaXadrex partidaXadrex = new PartidaXadrex();
		UI.imprimirTabuleiro(partidaXadrex.getPeca());
		
	}

}
