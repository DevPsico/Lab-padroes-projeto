package br.com.fuctura.view;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import br.com.fuctura.dao.impl.LojaDAOImpl;
import br.com.fuctura.entidade.Loja;
import br.com.fuctura.util.ConnectionSingleton;

public class F12GerenciarLoja {

	Connection conn = ConnectionSingleton.getInstance().getConnection();

	Scanner scan = new Scanner(System.in);
	int opcao;

	String menuGerenciarVeiculo = "\n     FRENTE DE LOJA\n     GERENCIAR LOJA\n\n1) Listar todas as lojas\n2) Voltar FRENTE DE LOJA";

	private static F01FrenteDeLoja frenteDeLoja = new F01FrenteDeLoja();
	private static F12GerenciarLoja gerenciarLoja = new F12GerenciarLoja();

	public void exibirMenuGerenciarLoja() throws IOException, InterruptedException, URISyntaxException {
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
				List<Loja> listaLojas = lojaDAO.listarLojas(conn);

				for (Loja loja : listaLojas) {
					/*
					 * System.out.println("Código: " + loja.getCodigo());
					 * System.out.println("Nome da loja: " + loja.getNomeLoja());
					 * System.out.println("Veículo digitado: " + loja.getVeiculoDigitado());
					 * System.out.println("CEP: " + loja.getEndereco().getCep());
					 * System.out.println("Número: " + loja.getEndereco().getNumero());
					 * System.out.println("Logradouro: " + loja.getEndereco().getLogradouro());
					 * System.out.println("Complemento: " + loja.getEndereco().getComplemento());
					 * System.out.println("Bairro: " + loja.getEndereco().getBairro());
					 * System.out.println("Cidade: " + loja.getEndereco().getLocalidade());
					 * System.out.println("Estado: " + loja.getEndereco().getUf());
					 */
					System.out.println(loja);
					System.out.println("-----------------------------------------------------");

				}
				
				ConnectionSingleton.getInstance().closeConnection();

				break;

			case 2:
				frenteDeLoja.exibirMenuFrenteDeLoja();

				break;

			default:
				System.out.println("Só pode escolher as opções de: 1 a 2");
				gerenciarLoja.exibirMenuGerenciarLoja();
				// throw new IllegalArgumentException("Unexpected value: " + opcao);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}