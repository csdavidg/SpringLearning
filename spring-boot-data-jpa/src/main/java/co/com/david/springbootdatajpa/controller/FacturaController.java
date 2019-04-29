package co.com.david.springbootdatajpa.controller;

import co.com.david.springbootdatajpa.dto.ClienteDTO;
import co.com.david.springbootdatajpa.dto.FacturaDTO;
import co.com.david.springbootdatajpa.dto.ItemFacturaDTO;
import co.com.david.springbootdatajpa.dto.ProductoDTO;
import co.com.david.springbootdatajpa.service.ClienteService;
import co.com.david.springbootdatajpa.service.FacturaService;
import co.com.david.springbootdatajpa.service.ProductoService;
import co.com.david.springbootdatajpa.utils.ParserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/factura")
@SessionAttributes("facturaDTO")
public class FacturaController {

    @Autowired
    private ClienteService clietService;

    @Autowired
    private ParserUtils parserUtils;

    @Autowired
    private ProductoService productoService;

    @Autowired
    private FacturaService facturaService;

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

    @PostMapping("/guardar")
    public String guardar(@Valid FacturaDTO facturaDTO, BindingResult bindingResult, Model model,
                          @RequestParam(name = "item_id[]", required = false) List<Long> itemsId,
                          @RequestParam(name = "cantidad[]", required = false) List<Integer> cantidadItems, RedirectAttributes redirectAttributes,
                          SessionStatus sessionStatus) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("titulo", "Crear Factura");
            return "factura/form";
        }

        if (itemsId == null || itemsId.isEmpty()) {
            model.addAttribute("error", "Debe agregar un item");
            return "factura/form";
        }

        for (int i = 0; i < itemsId.size(); i++) {
            ItemFacturaDTO itemFacturaDTO = new ItemFacturaDTO();
            itemFacturaDTO.setProductoDTO(new ProductoDTO(itemsId.get(i)));
            itemFacturaDTO.setCantidad(cantidadItems.get(i));
            facturaDTO.getItemFacturasList().add(itemFacturaDTO);
        }

        facturaService.saveFactura(parserUtils.convertirDTOToEntity(facturaDTO));
        redirectAttributes.addFlashAttribute("success", "Factura guardada con exito");
        sessionStatus.setComplete();

        return "redirect:/ver/" + facturaDTO.getClienteDTO().getId();

    }

    @GetMapping("/ver/{id}")
    public String ver(@PathVariable(value = "id") Long id, Model model, RedirectAttributes redirectAttributes) {

        FacturaDTO facturaDTO = parserUtils.convertirEntityToDTO(facturaService.findById(id));
        if (facturaDTO == null) {
            model.addAttribute("error", "El cliente no tiene facturas");
            return "redirect:/listar";
        }

        model.addAttribute("facturaDTO", facturaDTO);
        model.addAttribute("titulo", facturaDTO.getDescripcion());

        return "factura/ver";
    }
}
