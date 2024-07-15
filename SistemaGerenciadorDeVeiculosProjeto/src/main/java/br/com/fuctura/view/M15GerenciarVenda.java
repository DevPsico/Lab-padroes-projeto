package br.com.fuctura.view;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.util.InputMismatchException;
import java.util.Scanner;

import br.com.fuctura.dao.impl.VendaDAOImpl;
import br.com.fuctura.util.ConnectionSingleton;

public class M15GerenciarVenda {

	Connection conn = ConnectionSingleton.getInstance().getConnection();

	Scanner scan = new Scanner(System.in);

	int opcao;

	String menuGerenciarVenda = "\n     RETAGUARDA\n     GERENCIAR VENDA\n\n1) Excluir Venda\n2) voltar RETAGUARDA";

	private static M01Manutencao manutencao = new M01Manutencao();
	private static M15GerenciarVenda gerenciarVenda = new M15GerenciarVenda();

	public void exibirMenuGerenciarVenda() throws IOException, InterruptedException, URISyntaxException {
		System.out.println(menuGerenciarVenda);

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

			VendaDAOImpl vendaDAOExlcluir = new VendaDAOImpl();

			System.out.println("Informe o código da venda que deseja excluir");
			int codigoVenda = scan.nextInt();

			vendaDAOExlcluir.excluirVenda(conn, codigoVenda);

			ConnectionSingleton.getInstance().closeConnection();

			break;

		case 2:
			manutencao.exibirMenuManutencao();
			break;

		default:
			System.out.println("Só pode escolher as opções de: 1 a 2");
			gerenciarVenda.exibirMenuGerenciarVenda();
			// throw new IllegalArgumentException("Unexpected value: " + opcao);
		}
	}
}