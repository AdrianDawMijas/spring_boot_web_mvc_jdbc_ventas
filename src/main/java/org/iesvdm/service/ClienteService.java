package org.iesvdm.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.iesvdm.Utils.FechaFiltro;
import org.iesvdm.dao.ClienteDAO;
import org.iesvdm.modelo.Cliente;
import org.iesvdm.modelo.ClienteDTO;
import org.iesvdm.modelo.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

	@Autowired
	private ClienteDAO clienteDAO;

	@Autowired
	private FechaFiltro fechaFiltro;

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

			//Seteamos lista de pedidos.
			clienteDTO.setPedidos(pedidoService.listAll()
					.stream().filter(pedido -> pedido.getId_cliente()==cliente.getId())
					.toList());

			List<Pedido> pedidos = clienteDTO.getPedidos();

			//Usamos esa lista de pedidos para ir seteando valores
			clienteDTO.setCantidadPedidos(pedidos.size());

			clienteDTO.setPedidosTrimestre(pedidos.stream()
					.filter(pedido -> {
						boolean enUltimoTrimestre = fechaFiltro.estaEnUltimoTrimestre(pedido.getFecha());
						System.out.println("Pedido ID: " + pedido.getId() + ", Fecha: " + pedido.getFecha() + ", En último trimestre: " + enUltimoTrimestre);
						return enUltimoTrimestre;
					})
					.toList().size());

			clienteDTO.setPedidosSemestre(pedidos.stream()
					.filter(pedido -> fechaFiltro
							.estaEnUltimoSemestre(pedido.getFecha()))
					.toList().size());


			clienteDTO.setPedidosAnio(pedidos.stream()
					.filter(pedido -> fechaFiltro
							.estaEnUltimoAnio(pedido.getFecha()))
					.toList().size());

			clienteDTO.setPedidosLustro(pedidos.stream()
					.filter(pedido -> fechaFiltro
							.estaEnUltimoLustro(pedido.getFecha()))
					.toList().size());

			clientesDTOs.add(clienteDTO);
		}
		return clientesDTOs;
	}

}
