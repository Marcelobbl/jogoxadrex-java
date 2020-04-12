package aplication;


import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import xadrex.ExcessaoXadrex;
import xadrex.PartidaXadrex;
import xadrex.PecaXadrex;
import xadrex.PosicaoXadrex;


public class Program {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		PartidaXadrex partidaXadrex = new PartidaXadrex();
		List<PecaXadrex> capturada = new ArrayList<>();
		
		while(!partidaXadrex.getCheckMate()){
			try {
				UI.limpaTela();
				
				UI.imprimirPartida(partidaXadrex, capturada);
				System.out.println();
				System.out.print("Origem: ");
				PosicaoXadrex origem = UI.leituraPosicaoXadrex(sc);
				
				boolean[][] possivelMovimentos = partidaXadrex.possivelMovimentos(origem);
				UI.limpaTela();
				UI.imprimirTabuleiro(partidaXadrex.getPecas(), possivelMovimentos);
				
				System.out.println();
				System.out.print("Destino: ");
				PosicaoXadrex destino = UI.leituraPosicaoXadrex(sc);
				
				PecaXadrex captureXadrex = partidaXadrex.ExecutarMovimentoXadrex(origem, destino);
				
				if (captureXadrex != null) {
					capturada.add(captureXadrex);
				}
			}
			catch (ExcessaoXadrex e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
			catch (InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
		}
		UI.limpaTela();
		UI.imprimirPartida(partidaXadrex, capturada);

	}
}