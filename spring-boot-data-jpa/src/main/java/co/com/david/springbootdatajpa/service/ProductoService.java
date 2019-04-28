package co.com.david.springbootdatajpa.service;

import co.com.david.springbootdatajpa.dao.IProductoDAO;
import co.com.david.springbootdatajpa.dto.ProductoDTO;
import co.com.david.springbootdatajpa.entity.Producto;
import co.com.david.springbootdatajpa.utils.ParserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoService {

    @Autowired
    private IProductoDAO iProductoDAO;

    @Autowired
    private ParserUtils parserUtils;

    public List<ProductoDTO> findAllLikeName(String nombre) {
        List<Producto> productosList = iProductoDAO.findAllLikeName(nombre);
        if (productosList != null) {
            return productosList.stream().map(p -> parserUtils.convertirEntityToDTO(p))
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }
}
