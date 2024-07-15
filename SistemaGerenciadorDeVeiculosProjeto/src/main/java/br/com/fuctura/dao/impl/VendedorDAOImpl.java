package br.com.fuctura.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import br.com.fuctura.dao.IVendedorDAO;
import br.com.fuctura.entidade.Vendedor;

public class VendedorDAOImpl implements IVendedorDAO {

	@Override
	public void cadastrarVendedor(Connection conexao, Vendedor vendedor) {
		// TODO Auto-generated method stub

		try {

			String comandoSQL = "INSERT into vendedor (nomevendedor) values (?)";

			PreparedStatement pstm = conexao.prepareStatement(comandoSQL);

			pstm.setString(1, vendedor.getNome());

			System.out.println("Vendedor cadastrado com sucesso !!!");

			pstm.executeQuery();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	@Override
	public Vendedor consultarVendedorNome(Connection conexao, String nome) {
		// TODO Auto-generated method stub

		Vendedor vendedor = new Vendedor();

		try {

			String comandoSQL = "select * from vendedor where nomevendedor= ?";
			PreparedStatement pstm = conexao.prepareStatement(comandoSQL);

			pstm.setString(1, nome);

			ResultSet rs = pstm.executeQuery();

			if (rs.next()) {

				vendedor.setCodigo(rs.getInt("codigo"));
				vendedor.setNome(rs.getString("nomevendedor"));
			}

			rs.close();
			pstm.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return vendedor;
	}
	
	@Override
	public void excluirVendedor(Connection conexao, String nome) {
	    try {
	        String comandoSQL = "DELETE FROM vendedor WHERE nomevendedor = ?";
	        
	        PreparedStatement pstm = conexao.prepareStatement(comandoSQL);
	        
	        pstm.setString(1, nome);
	        
	        int linhasAfetadas = pstm.executeUpdate();
	        
	        if (linhasAfetadas > 0) {
	            System.out.println("Vendedor excluído com sucesso!");
	        } else {
	            System.out.println("Nenhum vendedor encontrado com o ID informado.");
	        }
	        
	    } catch (SQLException e) {
	        System.err.println("Erro ao excluir vendedor: " + e.getMessage());
	    }
	}
	
	@Override
	public void editarVendedor(Connection conexao, Vendedor vendedor, String nome) {
	    try {
	        String comandoSQL = "UPDATE vendedor SET nomevendedor = ? WHERE nomevendedor = ?";
	        
	        PreparedStatement pstm = conexao.prepareStatement(comandoSQL);
	        
	        pstm.setString(1, vendedor.getNome());
	        pstm.setString(2, nome); 
	        
	        int linhasAfetadas = pstm.executeUpdate();
	        
	        if (linhasAfetadas > 0) {
	            System.out.println("Vendedor atualizado com sucesso!");
	        } else {
	            System.out.println("Nenhum vendedor encontrado com o ID informado.");
	        }
	        
	    } catch (SQLException e) {
	        System.err.println("Erro ao atualizar vendedor: " + e.getMessage());
	    }
	}
}