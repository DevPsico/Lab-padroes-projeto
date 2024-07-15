package br.com.fuctura.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import br.com.fuctura.dao.IVeiculoDAO;
import br.com.fuctura.entidade.Veiculo;

public class VeiculoDAOImpl implements IVeiculoDAO {

	@Override
	public void adicionarVeiculo(Connection conexao, Veiculo veiculo) {
		// TODO Auto-generated method stub

		try {

			String comandoSQL = "INSERT INTO Veiculo (Modelo, Valor, Ano, Placa, fk_Tipo_Codigo) values (?, ?, ?, ?, ?)";

			PreparedStatement pstm = conexao.prepareStatement(comandoSQL);
			;

			pstm.setString(1, veiculo.getModelo());
			pstm.setDouble(2, veiculo.getValor());
			pstm.setDate(3, (Date) veiculo.getAno());
			pstm.setString(4, veiculo.getPlaca());

			if (!tipoExiste(conexao, veiculo.getTipoDigitado())) {
				System.out.println("Tipo de veículo não encontrado no banco de dados.");
				return;
			}

			pstm.setInt(5, veiculo.getTipoDigitado());
			System.out.println("Veículo inserido com sucesso !!!");
			pstm.execute();

			pstm.close();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// Método para verificar se o tipo_codigo existe no banco de dados
	public boolean tipoExiste(Connection conexao, int codigoTipo) {
		try {
			String sql = "SELECT COUNT(*) AS total FROM tipo WHERE codigo = ?";
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, codigoTipo);
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
	public Veiculo consultarVeiculoPorPlaca(Connection conexao, String placa) {
		// TODO Auto-generated method stub

		Veiculo veiculo = new Veiculo();

		try {
			String comandoSQL = "Select * from veiculo where placa = ?";

			PreparedStatement pstm = conexao.prepareStatement(comandoSQL);

			pstm.setString(1, placa);

			// Pegamos tds os resultados da pesquisa no SQL e vamos percorrer
			ResultSet rs = pstm.executeQuery();

			if (rs.next()) {
				
				veiculo.setCodigo(rs.getInt("codigo"));
				veiculo.setModelo(rs.getString("Modelo"));
				veiculo.setValor(rs.getDouble("Valor"));
				veiculo.setAno(rs.getDate("Ano"));
				veiculo.setPlaca(rs.getString("Placa"));
				veiculo.setTipoDigitado(rs.getInt("fk_tipo_codigo"));
			}

			rs.close();
			pstm.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return veiculo;
	}
	
	@Override
	public void excluiVeiculo(Connection conexao, String placa) {
	    try {
	        // Verifica se o veículo com o código especificado existe no banco de dados
	        if (!veiculoExiste(conexao, placa)) {
	            System.out.println("Veículo não encontrado no banco de dados.");
	            return;
	        }

	        String comandoSQL = "DELETE FROM Veiculo WHERE placa = ?";
	        PreparedStatement pstm = conexao.prepareStatement(comandoSQL);

	        // Define o código do veículo a ser excluído
	        pstm.setString(1, placa);

	        // Executa o comando de exclusão
	        pstm.executeUpdate();
	        System.out.println("\nVeículo excluído com sucesso !!!\n");

	        // Fecha recursos
	        pstm.close();

	    } catch (SQLException e) {
	        // Tratamento de exceções
	        e.printStackTrace();
	    }
	}

	// Método para verificar se o veículo existe no banco de dados
	public boolean veiculoExiste(Connection conexao, String placa) {
	    try {
	        String sql = "SELECT COUNT(*) AS total FROM Veiculo WHERE placa = ?";
	        PreparedStatement stmt = conexao.prepareStatement(sql);
	        stmt.setString(1, placa);
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
	public void editarVeiculo(Connection conexao, Veiculo veiculo, String placa) {
	    try {
	        // Verifica se o veículo com o código especificado existe no banco de dados
	        if (!veiculoExiste(conexao, placa)) {
	            System.out.println("Veículo não encontrado no banco de dados.");
	            return;
	        }

	        String comandoSQL = "UPDATE Veiculo SET Modelo = ?, Valor = ?, Ano = ?, Placa = ?, fk_Tipo_Codigo = ? WHERE placa = ?";
	        PreparedStatement pstm = conexao.prepareStatement(comandoSQL);

	        // Define os novos valores para o veículo
	        pstm.setString(1, veiculo.getModelo());
	        pstm.setDouble(2, veiculo.getValor());
	        pstm.setDate(3, new Date(veiculo.getAno().getTime())); // Convertendo para java.sql.Date
	        pstm.setString(4, veiculo.getPlaca());
	        pstm.setInt(5, veiculo.getTipoDigitado());
	        pstm.setString(6, placa); // Condição WHERE (código do veículo a ser atualizado)
	       
	        // Executa o comando de atualização
	        pstm.executeUpdate();
	        System.out.println("Veículo editado com sucesso !!!");

	        // Fecha recursos
	        pstm.close();

	    } catch (SQLException e) {
	        // Tratamento de exceções
	        e.printStackTrace();
	    }
	}
}