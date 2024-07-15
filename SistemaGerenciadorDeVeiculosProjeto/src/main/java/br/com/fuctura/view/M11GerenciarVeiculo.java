package br.com.fuctura.view;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.Scanner;
import br.com.fuctura.dao.impl.VeiculoDAOImpl;
import br.com.fuctura.entidade.Veiculo;
import br.com.fuctura.util.ConnectionSingleton;

public class M11GerenciarVeiculo {

	Connection conn = ConnectionSingleton.getInstance().getConnection();

	Scanner scan = new Scanner(System.in);

	int opcao;

	String menuGerenciarVeiculo = "\n     RETAGUARDA\n     GERENCIAR VEÍCULO\n\n1) Cadastrar Veículo\n2) Excluir Veículo"
			+ "\n3) Alterar dados do Veículo\n4) Voltar RETAGUARDA";

	private static M01Manutencao menuManutencao = new M01Manutencao();
	private static M11GerenciarVeiculo gerenciarVeiculo = new M11GerenciarVeiculo();

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

				VeiculoDAOImpl daoVeiculo = new VeiculoDAOImpl();
				Veiculo veiculo = new Veiculo();

				System.out.println("Informe a placa do veículo");
				veiculo.setPlaca(scan.next());

				System.out.println("Informe o modelo do veículo");
				veiculo.setModelo(scan.next());

				System.out.println("Informe o ano do veículo");
				Calendar cal = Calendar.getInstance();
				cal.set(Calendar.YEAR, scan.nextInt());

				// Convertendo para java.sql.Date
				java.sql.Date ano = new java.sql.Date(cal.getTimeInMillis());
				veiculo.setAno(ano);

				System.out.println("Informe o valor do veículo");
				veiculo.setValor(scan.nextDouble());

				System.out.println("Informe o tipo do veículo");
				veiculo.setTipoDigitado(scan.nextInt());

				daoVeiculo.adicionarVeiculo(conn, veiculo);
				
				ConnectionSingleton.getInstance().closeConnection();

				break;
			case 2:

				VeiculoDAOImpl daoVeiculoExcluir = new VeiculoDAOImpl();

				System.out.println("Informe a placa do veículo que deseja excluir");
				String placa = scan.next();

				daoVeiculoExcluir.excluiVeiculo(conn, placa);
				
				ConnectionSingleton.getInstance().closeConnection();

				break;

			case 3:

				VeiculoDAOImpl daoVeiculoEditar = new VeiculoDAOImpl();
				Veiculo veiculoEditar = new Veiculo();

				System.out.println("Informe a placa do veículo que deseja editar");
				String placaEditar = scan.next();

				System.out.println("Informe a nova placa do veículo");
				veiculoEditar.setPlaca(scan.next());

				System.out.println("Informe o modelo do veículo");
				veiculoEditar.setModelo(scan.next());

				System.out.println("Informe o ano do veículo");
				Calendar cale = Calendar.getInstance();
				cale.set(Calendar.YEAR, scan.nextInt());

				// Convertendo para java.sql.Date
				java.sql.Date anoo = new java.sql.Date(cale.getTimeInMillis());
				veiculoEditar.setAno(anoo);

				System.out.println("Informe o valor do veículo");
				veiculoEditar.setValor(scan.nextDouble());

				System.out.println("Informe o tipo do veículo");
				veiculoEditar.setTipoDigitado(scan.nextInt());

				daoVeiculoEditar.editarVeiculo(conn, veiculoEditar, placaEditar);
				
				ConnectionSingleton.getInstance().closeConnection();

				break;

			case 4:

				menuManutencao.exibirMenuManutencao();

				break;

			default:
				System.out.println("Só pode escolher as opções de: 1 a 4");
				gerenciarVeiculo.exibirMenuGerenciarVeiculo();
				// throw new IllegalArgumentException("Unexpected value: " + opcao);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}