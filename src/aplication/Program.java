package aplication;


import java.util.Scanner;

import tabuleiro.Peca;
import tabuleiro.Posicao;
import xadrex.PartidaXadrex;
import xadrex.PecaXadrex;
import xadrex.PosicaoXadrex;


public class Program {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		PartidaXadrex partidaXadrex = new PartidaXadrex();
		
		while(true){
			UI.imprimirTabuleiro(partidaXadrex.getPecas());
			System.out.println();
			System.out.print("Origem: ");
			PosicaoXadrex origem = UI.leituraPosicaoXadrex(sc);
			
			System.out.println();
			System.out.print("Destino: ");
			PosicaoXadrex destino = UI.leituraPosicaoXadrex(sc);
			
			PecaXadrex captureXadrex = partidaXadrex.ExecutarMovimentoXadrex(origem, destino);
		}
		
		
	}

}
