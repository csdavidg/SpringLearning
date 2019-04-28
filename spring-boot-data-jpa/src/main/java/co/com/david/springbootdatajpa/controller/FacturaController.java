package co.com.david.springbootdatajpa.controller;

import co.com.david.springbootdatajpa.dto.ClienteDTO;
import co.com.david.springbootdatajpa.dto.FacturaDTO;
import co.com.david.springbootdatajpa.service.ClienteService;
import co.com.david.springbootdatajpa.utils.ParserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@Controller
@RequestMapping("/factura")
@SessionAttributes("factura")
public class FacturaController {

    @Autowired
    private ClienteService clietService;

    @Autowired
    private ParserUtils parserUtils;


    @GetMapping("/form/{clienteId}")
    public String crear(@PathVariable(name = "clienteId") Long clienteId, Map<String, Object> map, RedirectAttributes redirectAttributes) {

        ClienteDTO clienteDTO = parserUtils.convertirEntityToDTO(this.clietService.obtenerPorId(clienteId));
        if (clienteDTO == null) {
            redirectAttributes.addFlashAttribute("error", "El cliente no existe");
        }

        FacturaDTO facturaDTO = new FacturaDTO();
        facturaDTO.setClienteDTO(clienteDTO);
        map.put("facturaDTO", facturaDTO);
        map.put("titulo", "Crear Factura");

        return "factura/form";
    }


}
