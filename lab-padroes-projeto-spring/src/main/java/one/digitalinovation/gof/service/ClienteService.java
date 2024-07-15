package one.digitalinovation.gof.service;

import one.digitalinovation.gof.model.Cliente;

public interface ClienteService {
	
	
	Iterable<Cliente> buscarTodos();
	
	Cliente buscarPorId(Long id);
	
	public void inserir(Cliente cliente);
	
	public void atualizar(Long id, Cliente cliente);
	
	public void deletar(Long id);

}
