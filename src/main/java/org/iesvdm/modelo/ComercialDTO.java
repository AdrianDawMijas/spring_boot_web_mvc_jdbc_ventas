package org.iesvdm.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComercialDTO {

    private Comercial comercial;
    private List<PedidoDTO> pedidos;
    private double media;
    private double maximo;
    private double minimo;

    // Getter personalizado para media
    public double getMedia() {
        return (pedidos == null || pedidos.isEmpty())
                ? 0.0
                : pedidos.stream()
                .mapToDouble(pedidos->pedidos.getPedido()
                        .getTotal())
                .average().orElse(0.0);
    }

    // Getter personalizado para máximo
    public double getMaximo() {
        return (pedidos == null || pedidos.isEmpty())
                ? 0.0
                : pedidos.stream().mapToDouble(pedidos->pedidos.getPedido().getTotal()).max().orElse(0.0);
    }

    // Getter personalizado para mínimo
    public double getMinimo() {
        return (pedidos == null || pedidos.isEmpty())
                ? 0.0
                : pedidos.stream().mapToDouble(pedidos->pedidos.getPedido().getTotal()).min().orElse(0.0);
    }

}