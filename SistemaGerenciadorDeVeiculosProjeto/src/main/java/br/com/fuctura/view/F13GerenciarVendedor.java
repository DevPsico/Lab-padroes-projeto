package br.com.fuctura.view;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.InputMismatchException;
import java.util.Scanner;

import br.com.fuctura.dao.IVendedorDAO;
import br.com.fuctura.dao.impl.VendedorDAOImpl;
import br.com.fuctura.entidade.Vendedor;
import br.com.fuctura.util.ConnectionSingleton;

public class F13GerenciarVendedor {

	Connection conn = ConnectionSingleton.getInstance().getConnection();

	Scanner scan = new Scanner(System.in);
	int opcao;

	String menuGerenciarVendedor = "\n     FRENTE DE LOJA\n     GERENCIAR VENDEDOR\n\n1) Consultar vendedor por nome\n"
			+ "2) Voltar FRENTE DE LOJA";

	private static F01FrenteDeLoja frenteDeLoja = new F01FrenteDeLoja();
	private static F13GerenciarVendedor gerenciarVendedor = new F13GerenciarVendedor();

	IVendedorDAO vendedorDAO = null;
	Vendedor vendedor = new Vendedor();

	public void exibirMenunGerenciarVendedor() throws IOException, InterruptedException, URISyntaxException {

		System.out.println(menuGerenciarVendedor);

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

				VendedorDAOImpl vendedorDAO = new VendedorDAOImpl();
				Vendedor vendedor = new Vendedor();

				System.out.println("Informe o nome do vendedor para localizar");
				String nome = scan.next();

				vendedor = vendedorDAO.consultarVendedorNome(conn, nome);

				System.out.println(vendedor);
				
				ConnectionSingleton.getInstance().closeConnection();

				break;

			case 2:
				frenteDeLoja.exibirMenuFrenteDeLoja();
				break;

			default:
				System.out.println("Só pode escolher as opções de: 1 a 2");
				gerenciarVendedor.exibirMenunGerenciarVendedor();
				// throw new IllegalArgumentException("Unexpected value: " + opcao);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}