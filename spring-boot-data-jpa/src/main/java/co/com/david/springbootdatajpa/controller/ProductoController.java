package co.com.david.springbootdatajpa.controller;

import co.com.david.springbootdatajpa.dto.ProductoDTO;
import co.com.david.springbootdatajpa.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/producto")
@SessionAttributes("producto")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping(value = "/obtener/productos/{nombre}", produces = {"application/json"})
    public @ResponseBody List<ProductoDTO> obtenerProductosPorNombre(@PathVariable String nombre) {
        return productoService.findAllLikeName(nombre);
    }
}
