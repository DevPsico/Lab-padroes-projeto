package br.com.fuctura.view;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.InputMismatchException;
import java.util.Scanner;

import br.com.fuctura.dao.impl.VeiculoDAOImpl;
import br.com.fuctura.entidade.Veiculo;
import br.com.fuctura.util.ConnectionSingleton;

public class F11GerenciarVeiculo {

	Connection conn = ConnectionSingleton.getInstance().getConnection();

	Scanner scan = new Scanner(System.in);
	int opcao;

	String menuGerenciarVeiculo = "\n     FRENTE DE LOJA\n    GERENCIAR VEÍCULO\n\n1) Consultar veículo por placa\n2) Voltar FRENTE DE LOJA";

	private static F01FrenteDeLoja frenteDeLoja = new F01FrenteDeLoja();
	private static F11GerenciarVeiculo gerenciarVeiculo = new F11GerenciarVeiculo();

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
				VeiculoDAOImpl veiculoDAO = new VeiculoDAOImpl();
				Veiculo veiculo = new Veiculo();

				System.out.println("Informe a placa do veículo para consultar");
				String placaConsulta = scan.next();

				veiculo = veiculoDAO.consultarVeiculoPorPlaca(conn, placaConsulta);

				System.out.println(veiculo);
				
				ConnectionSingleton.getInstance().closeConnection();
				break;

			case 2:
				frenteDeLoja.exibirMenuFrenteDeLoja();
				break;

			default:
				System.out.println("Só pode escolher as opções de: 1 a 2");
				gerenciarVeiculo.exibirMenuGerenciarVeiculo();
				// throw new IllegalArgumentException("Unexpected value: " + opcao);

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}