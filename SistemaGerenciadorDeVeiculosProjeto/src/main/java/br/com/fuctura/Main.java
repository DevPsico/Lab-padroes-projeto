package br.com.fuctura;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import br.com.fuctura.view.AMenuPrincipal;

public class Main {

	public static void main(String[] args) throws SQLException, IOException, InterruptedException, URISyntaxException {
		// TODO Auto-generated method stub

		AMenuPrincipal menuPrincipal = new AMenuPrincipal();

		menuPrincipal.exibirMenuPrincipal();

	}
}