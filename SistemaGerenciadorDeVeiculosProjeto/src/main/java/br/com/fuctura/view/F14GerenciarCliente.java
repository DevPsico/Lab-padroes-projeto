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

public class F14GerenciarCliente {

	Connection conn = ConnectionSingleton.getInstance().getConnection();

	Scanner scan = new Scanner(System.in);
	int opcao;

	String menuGerenciarCliente = "\n     FRENTE DE LOJA\n     GERENCIAR CLIENTE\n\n1) Cadastrar cliente\n2) Consultar cliente por CPF"
			+ "\n3) Voltar FRENTE DE LOJA";

	private static F01FrenteDeLoja frenteDeLoja = new F01FrenteDeLoja();
	private static ClienteDAOImpl clienteDAO = new ClienteDAOImpl();
	private static F14GerenciarCliente gerenciarCliente = new F14GerenciarCliente();

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

				frenteDeLoja.exibirMenuFrenteDeLoja();

				ConnectionSingleton.getInstance().closeConnection();

				break;

			case 2:

				System.out.println("Informe o CPF do vendedor que deseja localizar");
				String cpf = scan.next();

				ClienteDAOImpl clienteDAO = new ClienteDAOImpl();
				Cliente cliente = new Cliente();

				cliente = clienteDAO.localizarVendedorCPF(conn, cpf);

				System.out.println(cliente);
				
				ConnectionSingleton.getInstance().closeConnection();

				break;

			case 3:

				frenteDeLoja.exibirMenuFrenteDeLoja();

				break;

			default:
				System.out.println("Só pode escolher as opções de: 1 a 3");
				gerenciarCliente.exibirMenuGerenciarCliente();
				// throw new IllegalArgumentException("Unexpected value: " + opcao);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}