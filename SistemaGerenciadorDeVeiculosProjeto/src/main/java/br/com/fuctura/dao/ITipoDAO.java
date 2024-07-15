package br.com.fuctura.dao;

import java.sql.Connection;

import br.com.fuctura.entidade.Tipo;

public interface ITipoDAO {

	void cadastrarTipo(Connection conn, Tipo tipo);
	
	

}