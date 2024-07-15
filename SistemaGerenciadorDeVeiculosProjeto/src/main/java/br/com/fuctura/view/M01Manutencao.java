package br.com.fuctura.view;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class M01Manutencao {

	Scanner scan = new Scanner(System.in);

	int opcao;

	String menuManutencao = "\n     RETAGUARDA\n\n1) Gerenciar Veículo\n2) Gerenciar Loja\n3) Gerenciar Vendedor\n4) Gerenciar Cliente\n"
			+ "5) Cadastrar Venda\n6) Voltar MENU PRINCIPAL";

	private static AMenuPrincipal menuPrincipal = new AMenuPrincipal();
	private static M11GerenciarVeiculo menuGerenciarVeiculo = new M11GerenciarVeiculo();
	private static M12GerenciarLoja menuGerenciarLoja = new M12GerenciarLoja();
	private static M13GerenciarVendedor menuGerenciarVendedor = new M13GerenciarVendedor();
	private static M14GerenciarCliente menuGerenciarCliente = new M14GerenciarCliente();
	private static M15GerenciarVenda menuGerenciarVenda = new M15GerenciarVenda();
	private static M01Manutencao manutencao = new M01Manutencao();

	public void exibirMenuManutencao() throws IOException, InterruptedException, URISyntaxException {

		System.out.println(menuManutencao);

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
			menuGerenciarVeiculo.exibirMenuGerenciarVeiculo();
			break;

		case 2:
			menuGerenciarLoja.exibirMenuGerenciarVeiculo();
			break;

		case 3:
			menuGerenciarVendedor.exibirMenuGerenciarVendedor();
			break;

		case 4:
			menuGerenciarCliente.exibirMenuGerenciarCliente();
			break;

		case 5:
			menuGerenciarVenda.exibirMenuGerenciarVenda();
			break;

		case 6:
			menuPrincipal.exibirMenuPrincipal();
			break;

		default:
			System.out.println("Só pode escolher as opções de: 1 a 6");
			manutencao.exibirMenuManutencao();
			//throw new IllegalArgumentException("Unexpected value: " + opcao);
		}
	}
}