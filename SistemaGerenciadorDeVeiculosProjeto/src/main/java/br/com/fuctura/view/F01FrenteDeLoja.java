package br.com.fuctura.view;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class F01FrenteDeLoja {

	Scanner scan = new Scanner(System.in);
	int opcao;

	String menuFrenteDeLoja = "\n     FRENTE DE  LOJA\n		\n1) Gerenciar Veículo \n2) Gerenciar Loja \n3) Gerenciar Vendedor \n"
			+ "4) Gerenciar Cliente \n5) Gerenciar Venda \n6) Voltar MENU PRINCIPAL";

	private static AMenuPrincipal menuPrincipal = new AMenuPrincipal();
	private static F11GerenciarVeiculo gerenciarVeiculo = new F11GerenciarVeiculo();
	private static F12GerenciarLoja gerenciarLoja = new F12GerenciarLoja();
	private static F13GerenciarVendedor gerenciarVendedor = new F13GerenciarVendedor();
	private static F14GerenciarCliente gerenciarCliente = new F14GerenciarCliente();
	private static F15GerenciarVenda gerenciarVenda = new F15GerenciarVenda();
	private static F01FrenteDeLoja frenteDeLoja = new F01FrenteDeLoja();

	public void exibirMenuFrenteDeLoja() throws IOException, InterruptedException, URISyntaxException {

		System.out.println(menuFrenteDeLoja);
		//opcao = scan.nextInt();
		
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

			gerenciarVeiculo.exibirMenuGerenciarVeiculo();

			break;

		case 2:
			gerenciarLoja.exibirMenuGerenciarLoja();
			break;

		case 3:
			gerenciarVendedor.exibirMenunGerenciarVendedor();
			break;

		case 4:
			gerenciarCliente.exibirMenuGerenciarCliente();
			break;

		case 5:
			gerenciarVenda.exibirMenuGerenciarVenda();
			break;

		case 6:
			menuPrincipal.exibirMenuPrincipal();
			break;

		default:
			System.out.println("Só pode escolher as opções de: 1 a 6");
			frenteDeLoja.exibirMenuFrenteDeLoja();
			//throw new IllegalArgumentException("Unexpected value: " + opcao);
		}
	}
}