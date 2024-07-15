package br.com.fuctura.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.com.fuctura.dao.ILojaDAO;
import br.com.fuctura.entidade.Endereco;
import br.com.fuctura.entidade.Loja;
import br.com.fuctura.entidade.Veiculo;

public class LojaDAOImpl implements ILojaDAO {

	@Override
	public void cadastrarLoja(Connection conn, Loja loja) {
		// TODO Auto-generated method stub

		try {

			String comandoSQL = "insert into Loja (nomeloja, fk_veiculo_codigo, enderecocep, endereconumero, "
					+ "enderecologradouro, enderecocomplemento, enderecobairro, enderecocidade, enderecouf)"
					+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?)";

			PreparedStatement pstm = conn.prepareStatement(comandoSQL);

			pstm.setString(1, loja.getNomeLoja());
			pstm.setInt(2, loja.getVeiculoDigitado());

			if (!veiculoExiste(conn, loja.getVeiculoDigitado())) {
				System.out.println("Veículo não cadastrado no banco de dados !!!");
				return;
			}

			pstm.setString(3, loja.getEndereco().getCep());
			pstm.setString(4, loja.getEndereco().getNumero());
			pstm.setString(5, loja.getEndereco().getLogradouro());
			pstm.setString(6, loja.getEndereco().getComplemento());
			pstm.setString(7, loja.getEndereco().getBairro());
			pstm.setString(8, loja.getEndereco().getLocalidade());
			pstm.setString(9, loja.getEndereco().getUf());
			// pstm.setString(9, loja.getEndereco().getIbge());
			// pstm.setString(10, loja.getEndereco().getGia());
			// pstm.setString(11, loja.getEndereco().getDdd());
			// pstm.setString(12, loja.getEndereco().getSiafi());

			System.out.println("Loja cadastrado com sucesso !!!");
			pstm.execute();

			pstm.close();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	@Override
	public boolean veiculoExiste(Connection conexao, int codigoVeiculo) {
		// TODO Auto-generated method stub

		try {

			String veiculoExisteSQL = "select count(*) as total from veiculo where codigo = ?";

			PreparedStatement pstm = conexao.prepareStatement(veiculoExisteSQL);
			pstm.setInt(1, codigoVeiculo);
			ResultSet rs = pstm.executeQuery();

			if (rs.next()) {
				int total = rs.getInt("total");
				return total > 0;
			}

			rs.close();
			pstm.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Loja> listarLojas(Connection conexao) {
		// TODO Auto-generated method stub

		List<Loja> listaLojas = new ArrayList<>();

		try {

			String comandoSQL = "select * from loja";

			PreparedStatement pstm = conexao.prepareStatement(comandoSQL);

			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {
				Loja loja = new Loja();
				loja.setCodigo(rs.getInt("codigo"));
				loja.setNomeLoja(rs.getString("nomeloja"));
				loja.setVeiculoDigitado(rs.getInt("fk_veiculo_codigo"));

				Endereco endereco = new Endereco();
				endereco.setCep(rs.getString("enderecocep"));
				endereco.setNumero(rs.getString("endereconumero"));
				endereco.setLogradouro(rs.getString("enderecologradouro"));
				endereco.setComplemento(rs.getString("enderecocomplemento"));
				endereco.setBairro(rs.getString("enderecobairro"));
				endereco.setLocalidade(rs.getString("enderecocidade"));
				endereco.setUf(rs.getString("enderecouf"));

				loja.setEndereco(endereco);

				listaLojas.add(loja);
			}

			rs.close();
			pstm.close();

		} catch (Exception e) {
			// TODO: handle exception
		}

		return listaLojas;
	}
	
	@Override
	public void excluiLoja(Connection conexao, String nomeLoja) {
	    try {
	        // Verifica se a loja com o código especificado existe no banco de dados
	        if (!lojaExiste(conexao, nomeLoja)) {
	            System.out.println("Loja não encontrada no banco de dados.");
	            return;
	        }

	        String comandoSQL = "DELETE FROM Loja WHERE nomeloja = ?";
	        PreparedStatement pstm = conexao.prepareStatement(comandoSQL);

	        // Define o código da loja a ser excluída
	        pstm.setString(1, nomeLoja);

	        // Executa o comando de exclusão
	        pstm.executeUpdate();
	        System.out.println("Loja excluída com sucesso !!!");

	        // Fecha recursos
	        pstm.close();

	    } catch (SQLException e) {
	        // Tratamento de exceções
	        e.printStackTrace();
	    }
	}

	// Método para verificar se a loja existe no banco de dados
	private boolean lojaExiste(Connection conexao, String nomeLoja) {
	    try {
	        String sql = "SELECT COUNT(*) AS total FROM Loja WHERE nomeloja = ?";
	        PreparedStatement stmt = conexao.prepareStatement(sql);
	        stmt.setString(1, nomeLoja);
	        ResultSet rs = stmt.executeQuery();

	        if (rs.next()) {
	            int total = rs.getInt("total");
	            return total > 0;
	        }

	        // Fechando recursos
	        rs.close();
	        stmt.close();

	    } catch (SQLException e) {
	        // Tratamento de exceções
	        e.printStackTrace();
	    }

	    return false;
	}
	
	@Override
	public void alterarLoja(Connection conn, Loja loja, String nomeLoja) {
	    try {
	        // Verifica se a loja com o código especificado existe no banco de dados
	        if (!lojaExiste(conn, nomeLoja)) {
	            System.out.println("Loja não encontrada no banco de dados.");
	            return;
	        }

	        String comandoSQL = "UPDATE Loja SET nomeloja = ?, fk_veiculo_codigo = ?, "
	                + "enderecocep = ?, endereconumero = ?, enderecologradouro = ?, "
	                + "enderecocomplemento = ?, enderecobairro = ?, enderecocidade = ?, "
	                + "enderecouf = ? WHERE nomeloja = ?";

	        PreparedStatement pstm = conn.prepareStatement(comandoSQL);

	        // Define os valores para os parâmetros do UPDATE
	        pstm.setString(1, loja.getNomeLoja());
	        pstm.setInt(2, loja.getVeiculoDigitado());
	        pstm.setString(3, loja.getEndereco().getCep());
	        pstm.setString(4, loja.getEndereco().getNumero());
	        pstm.setString(5, loja.getEndereco().getLogradouro());
	        pstm.setString(6, loja.getEndereco().getComplemento());
	        pstm.setString(7, loja.getEndereco().getBairro());
	        pstm.setString(8, loja.getEndereco().getLocalidade());
	        pstm.setString(9, loja.getEndereco().getUf());
	        pstm.setString(10, nomeLoja); // Define o código da loja para o WHERE

	        // Executa o comando SQL
	        pstm.executeUpdate();
	        System.out.println("Loja alterada com sucesso !!!");

	        // Fecha recursos
	        pstm.close();

	    } catch (SQLException e) {
	        // Tratamento de exceções
	        e.printStackTrace();
	    }
	}
}