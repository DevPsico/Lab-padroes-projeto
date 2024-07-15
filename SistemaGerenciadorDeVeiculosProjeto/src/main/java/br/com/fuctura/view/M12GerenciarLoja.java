package br.com.fuctura.view;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.InputMismatchException;
import java.util.Scanner;

import br.com.fuctura.dao.impl.LojaDAOImpl;
import br.com.fuctura.entidade.Loja;
import br.com.fuctura.util.ConnectionSingleton;

public class M12GerenciarLoja {

	Connection conn = ConnectionSingleton.getInstance().getConnection();

	Scanner scan = new Scanner(System.in);

	int opcao;

	String menuGerenciarVeiculo = "\n     MANUTENÇÃO\n     GERENCIAR LOJA\n\n1) Cadastrar Loja\n2) Excluir Loja\n"
			+ "3) Alterar dados da Loja\n4) Voltar RETAGUARDA";

	private static M01Manutencao manutencao = new M01Manutencao();
	private static M12GerenciarLoja gerenciarLoja = new M12GerenciarLoja();

	public void exibirMenuGerenciarVeiculo() throws IOException, InterruptedException, URISyntaxException {
		System.out.println(menuGerenciarVeiculo);

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
				LojaDAOImpl lojaDAO = new LojaDAOImpl();
				Loja loja = new Loja();

				System.out.println("Informe o nome da loja");
				loja.setNomeLoja(scan.next());

				System.out.println("Informe o código do veículo");
				loja.setVeiculoDigitado(scan.nextInt());

				System.out.println("Informe o CEP da loja");
				loja.consultarEnderecoPorCep(scan.next());

				System.out.println("Informe o número o imóvel");
				loja.definirDadosDoEndereco(scan.next());

				lojaDAO.cadastrarLoja(conn, loja);
				
				ConnectionSingleton.getInstance().closeConnection();

				break;

			case 2:

				LojaDAOImpl lojaDaoExclui = new LojaDAOImpl();

				System.out.println("Informe o nome da loja que deseja excluir");
				String nomeLoja = scan.next();

				lojaDaoExclui.excluiLoja(conn, nomeLoja);
				
				ConnectionSingleton.getInstance().closeConnection();

				break;

			case 3:

				LojaDAOImpl lojaDAOEditar = new LojaDAOImpl();
				Loja lojaEditar = new Loja();

				System.out.println("Informe o nome da loja que deseja EDITAR");
				String nomeLojaEditar = scan.next();

				System.out.println("Informe o nome da loja");
				lojaEditar.setNomeLoja(scan.next());

				System.out.println("Informe o código do veículo");
				lojaEditar.setVeiculoDigitado(scan.nextInt());

				System.out.println("Informe o CEP da loja");
				lojaEditar.consultarEnderecoPorCep(scan.next());

				System.out.println("Informe o número o imóvel");
				lojaEditar.definirDadosDoEndereco(scan.next());

				lojaDAOEditar.alterarLoja(conn, lojaEditar, nomeLojaEditar);
				
				ConnectionSingleton.getInstance().closeConnection();

				break;

			case 4:
				manutencao.exibirMenuManutencao();
				break;

			default:
				System.out.println("Só pode escolher as opções de: 1 a 4");
				gerenciarLoja.exibirMenuGerenciarVeiculo();
				// throw new IllegalArgumentException("Unexpected value: " + opcao);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}