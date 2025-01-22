package org.iesvdm.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {
    private int id;
    private double total;
    private Date fecha;
    private int id_cliente;
    private int id_comercial;
}
