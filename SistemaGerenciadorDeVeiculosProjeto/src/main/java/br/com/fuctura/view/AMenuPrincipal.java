package br.com.fuctura.view;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AMenuPrincipal {

	Scanner scan = new Scanner(System.in);
	int opcao;

	String menuPrincipal = "\n     MENU PRINCIPAL\n		\n1) Frente de loja\n2) Retaguarda\n3) Sair do sistema";

	private static F01FrenteDeLoja frenteDeLoja = new F01FrenteDeLoja();
	private static M01Manutencao manutecao = new M01Manutencao();
	private static AMenuPrincipal menuPrincipal1 = new AMenuPrincipal();

	public void exibirMenuPrincipal() throws IOException, InterruptedException, URISyntaxException {

		System.out.println(menuPrincipal);

		// Loop para garantir que o usuário digite um número válido
		while (true) {
			try {
				// System.out.print("Escolha uma opção: ");
				opcao = scan.nextInt();
				break; // Sai do loop se a entrada for válida
			} catch (InputMismatchException e) {
				System.out.println("Por favor, digite apenas números.");
				scan.nextLine(); // Limpa o buffer do Scanner
			}
		}

		switch (opcao) {
		case 1:

			frenteDeLoja.exibirMenuFrenteDeLoja();

			break;

		case 2:

			manutecao.exibirMenuManutencao();

			break;

		case 3:

			break;

		default:
			System.out.println("Só pode escolher as opções de: 1 a 3");
			menuPrincipal1.exibirMenuPrincipal();
			// throw new IllegalArgumentException("Unexpected value: "+ opcao);
		}
	}
}