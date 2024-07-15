package br.com.fuctura.view;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.InputMismatchException;
import java.util.Scanner;

import br.com.fuctura.dao.impl.ClienteDAOImpl;
import br.com.fuctura.entidade.Cliente;
import br.com.fuctura.util.ConnectionSingleton;

public class M14GerenciarCliente {

	Connection conn = ConnectionSingleton.getInstance().getConnection();

	Scanner scan = new Scanner(System.in);

	int opcao;

	String menuGerenciarCliente = "\n     RETAGUARDA\n     GRENCIAR CLIENTE\n\n1) Cadastrar Cliente\n2) Excluir Cliente"
			+ "\n3) Alterar dados do Cliente\n4) voltar RETAGUARDA";

	private static M01Manutencao manutencao = new M01Manutencao();
	private static M14GerenciarCliente gerenciarCliente = new M14GerenciarCliente();

	private static ClienteDAOImpl clienteDAO = new ClienteDAOImpl();

	public void exibirMenuGerenciarCliente() throws IOException, InterruptedException, URISyntaxException {
		System.out.println(menuGerenciarCliente);

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
				Cliente clienteNovo = new Cliente();

				System.out.println("Informe o nome do cliente");
				clienteNovo.setNome(scan.next());

				System.out.println("Informe o CPF do cliente");
				clienteNovo.setCpf(scan.next());

				System.out.println("Informe o CEP do cliente");
				// String CEP = scan.next();
				clienteNovo.consultarEnderecoPorCep(scan.next());
				System.out.println("Informe o número o imóvel");
				clienteNovo.definirDadosDoEndereco(scan.next());

				System.out.println("Informe o telefone do cliente");
				clienteNovo.setCelular(scan.next());

				clienteDAO.cadastrarCliete(conn, clienteNovo);

				System.out.println("Cliente cadastrado com SUCESSO !!!");

				manutencao.exibirMenuManutencao();
				
				ConnectionSingleton.getInstance().closeConnection();

				break;

			case 2:

				ClienteDAOImpl clienteDAOExcluir = new ClienteDAOImpl();

				System.out.println("Informe o CPF do cliente que deseja excluir");
				String cpfExcluir = scan.next();

				clienteDAOExcluir.excluirCliente(conn, cpfExcluir);
				
				ConnectionSingleton.getInstance().closeConnection();

				break;

			case 3:

				ClienteDAOImpl clienteDAOEditar = new ClienteDAOImpl();
				Cliente clienteEditar = new Cliente();

				System.out.println("Informe o CPF do cliente que deseja EDITAR");
				String cpfEditar = scan.next();

				System.out.println("Informe o nome do cliente");
				clienteEditar.setNome(scan.next());

				System.out.println("Informe o CPF do cliente");
				clienteEditar.setCpf(scan.next());

				System.out.println("Informe o CEP do cliente");
				// String CEP = scan.next();
				clienteEditar.consultarEnderecoPorCep(scan.next());
				System.out.println("Informe o número o imóvel");
				clienteEditar.definirDadosDoEndereco(scan.next());

				System.out.println("Informe o telefone do cliente");
				clienteEditar.setCelular(scan.next());

				clienteDAOEditar.atualizaCliente(conn, clienteEditar, cpfEditar);
				
				ConnectionSingleton.getInstance().closeConnection();

				break;

			case 4:
				manutencao.exibirMenuManutencao();
				break;

			default:
				System.out.println("Só pode escolher as opções de: 1 a 4");
				gerenciarCliente.exibirMenuGerenciarCliente();
				// throw new IllegalArgumentException("Unexpected value: " + opcao);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}