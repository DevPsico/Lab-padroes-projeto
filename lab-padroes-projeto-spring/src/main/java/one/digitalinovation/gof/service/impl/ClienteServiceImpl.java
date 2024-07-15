package one.digitalinovation.gof.service.impl;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import one.digitalinovation.gof.model.Cliente;
import one.digitalinovation.gof.model.ClienteRepository;
import one.digitalinovation.gof.model.Endereco;
import one.digitalinovation.gof.model.EnderecoRepository;
import one.digitalinovation.gof.service.ClienteService;
import one.digitalinovation.gof.service.ViaCepService;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	private ViaCepService viaCepService = new ViaCepService() {

		/*
		@Override
		public Endereco consultaCep(String cep) {
			// TODO Auto-generated method stub
			return null;
		}
		*/
		/*
		@Override
		public Endereco consultaCep(String cep) {
			// TODO Auto-generated method stub
			Endereco novoEndereco = viaCepService.consultaCep(cep);
			return enderecoRepository.save(novoEndereco);
		}
		*/
		
		/*
		@Override
		public ResponseEntity<Endereco> consultaCep(String cep) {
		    // Consulta o serviço externo para obter os dados do endereço
		    ResponseEntity<Endereco> novoEndereco = viaCepService.consultaCep(cep);
		    
		    // Verifica se o endereço foi encontrado antes de salvar
		    if (novoEndereco != null) {
		        // Salva o novo endereço no banco de dados
		        return enderecoRepository.save(novoEndereco);
		    } else {
		        // Tratar caso o endereço não tenha sido encontrado
		       System.out.println("     NAO ENCONTRADO");
		    }
			return novoEndereco;
		}
		*/
		
		
		@Override
		public ResponseEntity<Endereco> consultaCep(String cep) {
		    // Consulta o serviço externo para obter os dados do endereço
		    ResponseEntity<Endereco> response = viaCepService.consultaCep(cep);
		    
		    // Verifica se o serviço externo retornou com sucesso
		    if (response.getStatusCode() == HttpStatus.OK) {
		        Endereco novoEndereco = response.getBody();
		        
		        // Verifica se o endereço foi encontrado antes de salvar
		        if (novoEndereco != null) {
		            // Salva o novo endereço no banco de dados
		            Endereco enderecoSalvo = enderecoRepository.save(novoEndereco);
		            
		            // Retorna a resposta com o endereço salvo
		            return ResponseEntity.ok(enderecoSalvo);
		        } else {
		            throw new RuntimeException("Endereço retornado pelo serviço está nulo para o CEP: " + cep);
		        }
		    } else {
		        // Trata caso o serviço retorne um código de erro
		        throw new RuntimeException("Erro ao consultar o CEP: " + cep + ". Status: " + response.getStatusCode());
		    }
		}

		

		
		
	};
	{

		/*
		
		*/
	};

	@Override
	public Iterable<Cliente> buscarTodos() {
		// TODO Auto-generated method stub
		return clienteRepository.findAll();
	}

	@Override
	public Cliente buscarPorId(Long id) {
		// TODO Auto-generated method stub

		Optional<Cliente> cliente = clienteRepository.findById(id);

		return cliente.get();
	}

	@Override
	public void inserir(Cliente cliente) {
		// TODO Auto-generated method stub

		salvarClienteComCep(cliente);
	}

	@Override
	public void atualizar(Long id, Cliente cliente) {
		// TODO Auto-generated method stub

		Optional<Cliente> clienteBd = clienteRepository.findById(id);

		if (clienteBd.isPresent()) {
			salvarClienteComCep(cliente);
		}
	}

	@Override
	public void deletar(Long id) {
		// TODO Auto-generated method stub
		clienteRepository.deleteById(id);

	}

	private void salvarClienteComCep(Cliente cliente) {
	    String cep = cliente.getEndereco().getCep();
	    
	    try {
	        ResponseEntity<Endereco> response = viaCepService.consultaCep(cep);

	        if (response.getStatusCode() == HttpStatus.OK) {
	            Endereco novoEndereco = response.getBody();
	            
	            // Verifica se o endereço foi encontrado antes de salvar
	            if (novoEndereco != null) {
	                // Salva o novo endereço no banco de dados
	                Endereco enderecoSalvo = enderecoRepository.save(novoEndereco);
	                
	                // Associa o endereço ao cliente
	                cliente.setEndereco(enderecoSalvo);

	                // Salva o cliente (com o endereço associado)
	                clienteRepository.save(cliente);
	            } else {
	                throw new RuntimeException("Endereço retornado pelo serviço está nulo para o CEP: " + cep);
	            }
	        } else {
	            throw new RuntimeException("Erro ao consultar o CEP: " + cep + ". Status: " + response.getStatusCode());
	        }
	    } catch (Exception e) {
	        // Log the exception for debugging purposes
	        //log.error("Erro ao consultar o CEP {}: {}", cep, e.getMessage());
	    	System.out.println("AQUI ONDE VC MUDOU ");
	        e.printStackTrace(); // Handle exception according to your application's requirements
	        throw new RuntimeException("Erro ao consultar o CEP: " + cep + ". Detalhes no log.");
	    }
	}


}