package br.com.fuctura.view;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.InputMismatchException;
import java.util.Scanner;

import br.com.fuctura.dao.impl.VendaDAOImpl;
import br.com.fuctura.dao.impl.VendedorDAOImpl;
import br.com.fuctura.entidade.Vendedor;
import br.com.fuctura.util.ConnectionSingleton;

public class M13GerenciarVendedor {

	Connection conn = ConnectionSingleton.getInstance().getConnection();

	Scanner scan = new Scanner(System.in);

	int opcao;

	String menuGerenciarVendedor = "\n     RETAGUARDA\n     GERENCIAR VENDEDOR\n\n1) Cadastrar Vendedor\n2) Excluir Vendedor"
			+ "\n3) Alterar dados do vendedor\n4) Voltar RETAGUARDA";

	private static M01Manutencao manutencao = new M01Manutencao();
	private static M13GerenciarVendedor gerenciarVendedor = new M13GerenciarVendedor();

	public void exibirMenuGerenciarVendedor() throws IOException, InterruptedException, URISyntaxException {
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

				System.out.println("Informe o nome do vendedor");
				vendedor.setNome(scan.next());

				vendedorDAO.cadastrarVendedor(conn, vendedor);

				ConnectionSingleton.getInstance().closeConnection();

				break;

			case 2:

				VendedorDAOImpl vendedorDAOExcluir = new VendedorDAOImpl();

				System.out.println("Informe o nome do vendedor que deseja excluir");
				String nome = scan.next();

				vendedorDAOExcluir.excluirVendedor(conn, nome);

				ConnectionSingleton.getInstance().closeConnection();

				break;

			case 3:

				VendedorDAOImpl vendedorDAOEditar = new VendedorDAOImpl();
				Vendedor vendedorEditar = new Vendedor();

				System.out.println("Informe o nome do vendedor que deseja editar");
				String nomeEditar = scan.next();

				System.out.println("Informe o NOVO nome do vendedor");
				vendedorEditar.setNome(scan.next());

				vendedorDAOEditar.editarVendedor(conn, vendedorEditar, nomeEditar);

				ConnectionSingleton.getInstance().closeConnection();

				break;

			case 4:
				manutencao.exibirMenuManutencao();
				break;

			default:
				System.out.println("Só pode escolher as opções de: 1 a 4");
				gerenciarVendedor.exibirMenuGerenciarVendedor();
				// throw new IllegalArgumentException("Unexpected value: " + opcao);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}