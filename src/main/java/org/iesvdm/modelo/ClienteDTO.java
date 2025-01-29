package org.iesvdm.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {
    private Cliente cliente;
    private List<Pedido> pedidos;
    private int cantidadPedidos;
    private int pedidosTrimestre;
    private int pedidosSemestre;
    private int pedidosAnio;
    private int pedidosLustro;
}
