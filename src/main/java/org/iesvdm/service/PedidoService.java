package org.iesvdm.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.iesvdm.dao.ClienteDAO;
import org.iesvdm.dao.PedidoDAO;
import org.iesvdm.modelo.Cliente;
import org.iesvdm.modelo.Pedido;
import org.iesvdm.modelo.PedidoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    @Autowired
    private PedidoDAO pedidoDAO;

    @Autowired
    private ClienteDAO clienteDAO;

    public List<Pedido> listAll() {

        return pedidoDAO.getAll();

    }

    public Pedido one(Integer id) {
        Optional<Pedido> optProd = pedidoDAO.find(id);
        return optProd.orElse(null);
    }

    public void newPedido(Pedido pedido) {

        pedidoDAO.create(pedido);

    }

    public void replacePedido(Pedido pedido) {

        pedidoDAO.update(pedido);

    }

    public void deletePedido(int id) {

        pedidoDAO.delete(id);

    }

    public List<PedidoDTO> getPedidoDTOs(){
        List<Pedido> pedidos = listAll();
        List<PedidoDTO> dtos = new ArrayList<PedidoDTO>();
        List<Cliente> clientes = clienteDAO.getAll();
        dtos = pedidos.stream().map(pedido -> {
            PedidoDTO dto = new PedidoDTO();
            dto.setPedido(pedido);
            dto.setCliente(clientes.stream()
                    .filter(cliente1 -> cliente1.getId()==pedido.getId_cliente())
                    .findFirst().orElse(null));
            return dto;
        }).toList();
        return dtos;
    }

}
