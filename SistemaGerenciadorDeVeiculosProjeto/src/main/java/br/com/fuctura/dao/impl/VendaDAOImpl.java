package br.com.fuctura.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import br.com.fuctura.dao.IVendaDAO;
import br.com.fuctura.entidade.Venda;

public class VendaDAOImpl implements IVendaDAO {

	@Override
	public void adicionarVenda(Connection conexao, Venda venda) {
		// TODO Auto-generated method stub

		try {

			String comandoSQL = "insert into venda (valortotal, nomevendedor, nomecliente, nomeloja, placaveiculo,"
					+ "fk_loja_codigo, fk_vendedor_codigo, fk_cliente_codigo) values (?, ?, ?, ?, ?, ?, ?, ?)";

			PreparedStatement pstm = conexao.prepareStatement(comandoSQL);
			
			// Verificar se a placa existe na tabela Loja através do fk_veiculo_codigo
	        String sqlVerificaPlaca = "SELECT 1 FROM loja WHERE fk_veiculo_codigo = (SELECT codigo FROM veiculo WHERE placa = ?)";
	        PreparedStatement stmtVerificaPlaca = conexao.prepareStatement(sqlVerificaPlaca);
	        stmtVerificaPlaca.setString(1, venda.getPlacaVeiculo());
	        ResultSet rsVerificaPlaca = stmtVerificaPlaca.executeQuery();

	        if (!rsVerificaPlaca.next()) {
	            System.out.println("Placa não encontrada na tabela Loja.");
	            return;
	        }
	        
	        pstm.setDouble(1, getValorVeiculo(conexao, venda.getPlacaVeiculo()));

	        // Fechando recursos
	        rsVerificaPlaca.close();
	        stmtVerificaPlaca.close();

			//pstm.setDouble(1, venda.getValorTotal());
			pstm.setString(2, venda.getNomeVendedor());
			pstm.setString(3, venda.getCpfCliente());
			pstm.setString(4, venda.getNomeLoja());
			pstm.setString(5, venda.getPlacaVeiculo());

			int codigoLoja = lojaExiste(conexao, venda.getNomeLoja());
			if (codigoLoja == -1) {
				System.out.println("Essa loja não existe no BANCO DE DADOS !!!");
				return;
			}
			pstm.setInt(6, codigoLoja);

			int codigoVendedor = vendedorExiste(conexao, venda.getNomeVendedor());
			if (codigoVendedor == -1) {
				System.out.println("Esse vendedor não existe no BANCO DE DADOS");
				return;
			}
			pstm.setInt(7, codigoVendedor);

			int codigoCliente = clienteExiste(conexao, venda.getCpfCliente());
			if (codigoCliente == -1) {
				System.out.println("Esse cliente não existe no BANCO DE DADOS !!!");
				return;
			}

			pstm.setInt(8, codigoCliente);
			
			

			System.out.println("Venda cadastrada com sucesso !!!");

			pstm.execute();
			pstm.close();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	@Override
	// Método para verificar se o tipo_codigo existe no banco de dados
	public int lojaExiste(Connection conexao, String nomeLoja) {
		try {
			String sql = "SELECT codigo FROM loja WHERE nomeloja = ?";

			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(1, nomeLoja);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				int codigo = rs.getInt("codigo");
				// Fechando recursos
				rs.close();
				stmt.close();
				return codigo;
			}

			// Fechando recursos
			rs.close();
			stmt.close();

		} catch (SQLException e) {
			// Tratamento de exceções
			e.printStackTrace();
		}

		return -1;
	}

	@Override
	// Método para verificar se o tipo_codigo existe no banco de dados
	public int vendedorExiste(Connection conexao, String nomeVendedor) {
		try {
			String sql = "SELECT codigo FROM vendedor WHERE nomevendedor = ?";

			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(1, nomeVendedor);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				int codigo = rs.getInt("codigo");
				// Fechando recursos
				rs.close();
				stmt.close();
				return codigo;
			}

			// Fechando recursos
			rs.close();
			stmt.close();

		} catch (SQLException e) {
			// Tratamento de exceções
			e.printStackTrace();
		}

		return -1;
	}

	@Override
	// Método para verificar se o tipo_codigo existe no banco de dados
	public int clienteExiste(Connection conexao, String cpf) {
		try {
			String sql = "SELECT codigo FROM cliente WHERE cpf = ?";

			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(1, cpf);
			// System.out.println(sql);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				int codigo = rs.getInt("codigo");
				// Fechando recursos
				rs.close();
				stmt.close();
				return codigo;
			}

			// Fechando recursos
			rs.close();
			stmt.close();

		} catch (SQLException e) {
			// Tratamento de exceções
			e.printStackTrace();
		}

		return -1;
	}
	
	@Override
	// Método para obter o valor do veículo baseado na placa
	public double getValorVeiculo(Connection conexao, String placaVeiculo) throws SQLException {
	    double valorVeiculo = 0.0;

	    String sql = "SELECT valor FROM veiculo WHERE placa = ?";
	    PreparedStatement stmt = conexao.prepareStatement(sql);
	    stmt.setString(1, placaVeiculo);
	    ResultSet rs = stmt.executeQuery();

	    if (rs.next()) {
	        valorVeiculo = rs.getDouble("valor");
	    }

	    // Fechando recursos
	    rs.close();
	    stmt.close();

	    return valorVeiculo;
	}
	
	@Override
	public void excluirVenda(Connection conexao, int codigoVenda) {
	    try {
	        String comandoSQL = "DELETE FROM venda WHERE codigo = ?";
	        PreparedStatement pstm = conexao.prepareStatement(comandoSQL);
	        pstm.setInt(1, codigoVenda);

	        int linhasAfetadas = pstm.executeUpdate();
	        if (linhasAfetadas > 0) {
	            System.out.println("Venda excluída com sucesso.");
	        } else {
	            System.out.println("Não foi encontrada uma venda com o código especificado.");
	        }

	        pstm.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
}