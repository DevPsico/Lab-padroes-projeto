package br.com.fuctura.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import br.com.fuctura.dao.ITipoDAO;
import br.com.fuctura.entidade.Tipo;

public class TipoDAOImpl implements ITipoDAO {

	@Override
	public void cadastrarTipo(Connection conexao, Tipo tipo) {
		// TODO Auto-generated method stub

		try {

			String comandoSQL = "INSERT INTO tipo (descricao) values (?)";

			PreparedStatement pstm;

			pstm = conexao.prepareStatement(comandoSQL);
			
			pstm.setString(1, tipo.getDescricao());

			pstm.execute();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}