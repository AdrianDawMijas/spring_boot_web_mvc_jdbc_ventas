package org.iesvdm.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.iesvdm.dao.ClienteDAO;
import org.iesvdm.modelo.Cliente;
import org.iesvdm.modelo.ClienteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

	@Autowired
	private ClienteDAO clienteDAO;

	@Autowired
	private PedidoService pedidoService;

	public List<Cliente> listAll() {

		return clienteDAO.getAll();

	}

	public Cliente one(Integer id) {
		Optional<Cliente> optProd = clienteDAO.find(id);
		return optProd.orElse(null);
	}

	public void newCliente(Cliente cliente) {

		clienteDAO.create(cliente);

	}

	public void replaceCliente(Cliente cliente) {

		clienteDAO.update(cliente);

	}

	public void deleteCliente(int id) {

		clienteDAO.delete(id);

	}

	public List<ClienteDTO> listClientesDTOs(){

		List<Cliente> clientes = clienteDAO.getAll();
		List<ClienteDTO> clientesDTOs = new ArrayList<ClienteDTO>();
		for(Cliente cliente : clientes){
			ClienteDTO clienteDTO = new ClienteDTO();
			clienteDTO.setCliente(cliente);
			clienteDTO.setCantidadPedidos(pedidoService.listAll()
					.stream().filter(pedido -> pedido.getId_cliente()==cliente.getId())
					.toList().size());
			clientesDTOs.add(clienteDTO);
		}
		return clientesDTOs;
	}

}
