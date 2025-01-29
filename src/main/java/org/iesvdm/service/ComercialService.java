package org.iesvdm.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.iesvdm.dao.ComercialDAO;
import org.iesvdm.modelo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComercialService {

    @Autowired
    private ComercialDAO comercialDAO;

    @Autowired
    private PedidoService pedidoService;

    public List<Comercial> listAll() {

        return comercialDAO.getAll();

    }

    public Comercial one(Integer id) {
        Optional<Comercial> optProd = comercialDAO.find(id);
        return optProd.orElse(null);
    }

    public void newComercial(Comercial comercial) {

        comercialDAO.create(comercial);

    }

    public void replaceComercial(Comercial comercial) {

        comercialDAO.update(comercial);

    }

    public void deleteComercial(int id) {

        comercialDAO.delete(id);

    }

    public List<ComercialDTO> getComercialDTOs(){

        List<ComercialDTO> dtos = new ArrayList<>();

        List<Comercial> comercials = listAll();

        List<PedidoDTO> pedidoDTOs = pedidoService.getPedidoDTOs();

        for(Comercial comercial : comercials){
            ComercialDTO dto = new ComercialDTO();
            dto.setComercial(comercial);
            dto.setPedidos(pedidoDTOs.stream()
                    .filter(pedidoDTO -> pedidoDTO.getPedido()
                            .getId_comercial()==comercial.getId()).toList());
            dtos.add(dto);
        }
        return dtos;
    }
}

