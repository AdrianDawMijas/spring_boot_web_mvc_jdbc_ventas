package org.iesvdm.controlador;

import java.util.ArrayList;
import java.util.List;

import org.iesvdm.modelo.*;
import org.iesvdm.service.ClienteService;
import org.iesvdm.service.ComercialService;
import org.iesvdm.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private ComercialService comercialService;

	@Autowired
	private PedidoService pedidoService;

	@GetMapping("/clientes")
	public String listar(Model model) {

		List<ClienteDTO> listAllCli =  clienteService.listClientesDTOs();
		model.addAttribute("listaClientes", listAllCli);

		return "clientes";

	}

	@GetMapping("/clientes/{id}")
	public String detalle(Model model, @PathVariable Integer id ) {

		Cliente cliente = clienteService.one(id);
		List<ComercialDTO> dtos = comercialService.getComercialDTOs();
		List<ComercialDTO> dtosFilter = new ArrayList<>();

		//Sacamos el clienteDTO correpondiente a este ID de cliente
		ClienteDTO clienteDTO = clienteService.listClientesDTOs().stream()
																		.filter(cTO -> cTO.getCliente().equals(cliente))
																		.findFirst()
																		.orElse(null);


		//Añadimos informacion al dtoFilter que contiene todos los comerciales

		List<Pedido> pedidos = pedidoService.listAll()
											.stream()
											.filter(pedido -> pedido.getId_cliente()==id).toList();

		var idPedidos = pedidos.stream().map(Pedido::getId_comercial).toList();

		for( Integer idPed : idPedidos){

			ComercialDTO comercialDTO = dtos.stream()
											.filter(comercialDTO1 -> comercialDTO1.getComercial()
													.getId()==idPed)
											.findFirst()
											.orElse(null);

			dtosFilter.add(comercialDTO);
		}


		model.addAttribute("dtosFilter", dtosFilter);
		model.addAttribute("clienteDTO", clienteDTO);

		return "detalle-cliente";

	}

	@GetMapping("/clientes/crear")
	public String crear(Model model) {

		Cliente cliente = new Cliente();
		model.addAttribute("cliente", cliente);

		return "crear-cliente";

	}

	@PostMapping("/clientes/crear")
	public RedirectView submitCrear(@ModelAttribute("cliente") Cliente cliente) {

		clienteService.newCliente(cliente);

		return new RedirectView("/clientes") ;

	}

	@GetMapping("/clientes/editar/{id}")
	public String editar(Model model, @PathVariable Integer id) {

		Cliente cliente = clienteService.one(id);
		model.addAttribute("cliente", cliente);

		return "editar-cliente";

	}


	@PostMapping("/clientes/editar/{id}")
	public RedirectView submitEditar(@ModelAttribute("cliente") Cliente cliente) {

		clienteService.replaceCliente(cliente);

		return new RedirectView("/clientes");
	}

	@PostMapping("/clientes/borrar/{id}")
	public RedirectView submitBorrar(@PathVariable Integer id) {

		clienteService.deleteCliente(id);

		return new RedirectView("/clientes");
	}

}
