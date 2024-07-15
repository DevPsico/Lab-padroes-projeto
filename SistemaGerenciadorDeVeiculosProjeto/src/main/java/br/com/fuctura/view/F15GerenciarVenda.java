package br.com.fuctura.view;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.InputMismatchException;
import java.util.Scanner;

import br.com.fuctura.dao.impl.VendaDAOImpl;
import br.com.fuctura.entidade.Venda;
import br.com.fuctura.util.ConnectionSingleton;

public class F15GerenciarVenda {

	Connection conn = ConnectionSingleton.getInstance().getConnection();

	Scanner scan = new Scanner(System.in);
	int opcao;

	String menuGerenciarVenda = "\n     FRENTE DE LOJA\n     GERENCIAR VENDA\n\n1) Cadastrar venda\n2) Voltar FRENTE DE LOJA";

	private static F01FrenteDeLoja frenteDeLoja = new F01FrenteDeLoja();
	private static F15GerenciarVenda gerenciarVenda = new F15GerenciarVenda();

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

		try {

			switch (opcao) {
			case 1:

				VendaDAOImpl vendaDAO = new VendaDAOImpl();
				Venda venda = new Venda();
				System.out.println("Informe o nome do vendedor");
				venda.setNomeVendedor(scan.next());

				System.out.println("Informe o cpf do cliente");
				venda.setCpfCliente(scan.next());

				System.out.println("Informe o nome da loja");
				venda.setNomeLoja(scan.next());

				System.out.println("Informe a placa do veículo");
				venda.setPlacaVeiculo(scan.next());

				vendaDAO.adicionarVenda(conn, venda);
				
				ConnectionSingleton.getInstance().closeConnection();

				break;

			case 2:
				frenteDeLoja.exibirMenuFrenteDeLoja();
				break;

			default:
				System.out.println("Só pode escolher as opções de: 1 a 2");
				gerenciarVenda.exibirMenuGerenciarVenda();
				// throw new IllegalArgumentException("Unexpected value: " + opcao);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}