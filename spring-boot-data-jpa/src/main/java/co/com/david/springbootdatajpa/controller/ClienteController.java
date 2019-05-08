package co.com.david.springbootdatajpa.controller;

import co.com.david.springbootdatajpa.dto.ClienteDTO;
import co.com.david.springbootdatajpa.entity.Cliente;
import co.com.david.springbootdatajpa.service.ClienteService;
import co.com.david.springbootdatajpa.utils.PageRender;
import co.com.david.springbootdatajpa.utils.ParserUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

@Controller
@SessionAttributes("clienteDTO")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ParserUtils parserUtils;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final String RUTA_IMAGENES = "/archivos/spring/";

    @RequestMapping(value = {"/listar", "/"}, method = RequestMethod.GET)
    public String listarClientes(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {


        Pageable pageable = PageRequest.of(page, 5);
        Page<Cliente> clientes = clienteService.findAll(pageable);
        PageRender<Cliente> pageRender = new PageRender<>("/listar", clientes);

        model.addAttribute("titulo", "Listar CLientes");
        //model.addAttribute("clientes", clienteService.findAll());
        model.addAttribute("clientes", clientes);
        model.addAttribute("page", pageRender);
        return "listar";
    }

    @RequestMapping("/form")
    public String crear(Map<String, Object> mapaModel) {
        ClienteDTO clienteDTO = new ClienteDTO();
        mapaModel.put("titulo", "Formulario");
        mapaModel.put("clienteDTO", clienteDTO);
        return "form";
    }

    private void eliminarFoto(String nombre) {
        File fotoFile = new File(RUTA_IMAGENES + nombre);
        if (fotoFile.exists()) {
            fotoFile.delete();
        }
    }

    @RequestMapping(value = "/guardar", method = RequestMethod.POST)
    public String guardar(@Valid ClienteDTO clienteDTO, BindingResult bindingResult, SessionStatus sessionStatus,
                          RedirectAttributes redirectAttributes, @RequestParam(name = "file") MultipartFile foto) {

        if (!foto.isEmpty()) {
            try {
                this.eliminarFoto(clienteDTO.getFoto());

                String ruta = RUTA_IMAGENES + foto.getOriginalFilename();
                Path rutaCompleta = Paths.get(ruta);
                Files.write(rutaCompleta, foto.getBytes());
                clienteDTO.setFoto(foto.getOriginalFilename());
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        if (bindingResult.hasErrors()) {
            return "form";
        }
        clienteService.save(parserUtils.convertirDTOToEntity(clienteDTO));
        redirectAttributes.addFlashAttribute("success", "Cliente guardado correctamente");
        sessionStatus.setComplete();
        return "redirect:/listar";
    }

    @RequestMapping(value = "/form/{id}")
    public String obtenerPorId(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes redirectAttributes) {
        if (id > 0) {
            ClienteDTO clienteDTO = parserUtils.convertirEntityToDTO(clienteService.obtenerPorId(id));
            model.put("clienteDTO", clienteDTO);
            model.put("titulo", "Editar Cliente");
        } else {
            redirectAttributes.addFlashAttribute("warning", "El cliente no existe");
            return "redirect:/listar";
        }
        return "form";
    }

    @RequestMapping(value = "/eliminar/{id}", method = RequestMethod.GET)
    public String eliminarCliente(@PathVariable(value = "id") Long id, RedirectAttributes redirectAttributes) {
        Cliente cliente = clienteService.obtenerPorId(id);
        clienteService.eliminar(cliente);
        redirectAttributes.addFlashAttribute("success", "Cliente eliminado correctamente");
        return "redirect:/listar";
    }

    @GetMapping("/ver/{id}")
    public String ver(@PathVariable(value = "id") Long id, Map<String, Object> map, RedirectAttributes redirectAttributes) {
        ClienteDTO clienteDTO = parserUtils.convertirEntityToDTO(clienteService.obtenerPorId(id));
        if (clienteDTO == null) {
            redirectAttributes.addFlashAttribute("error", "El cliente no existe");
            return "redirect:/listar";
        }
        map.put("clienteDTO", clienteDTO);
        map.put("titulo", "Detalle Cliente: " + clienteDTO.getNombre());
        return "ver";
    }

    /**
     * Consulta la imagen siempre y cuando la ruta sea en la raiz del directorio
     * EJEMPLO De Ruta = /home/david/workspaces/SpringUdemy/spring-boot-data-jpa/imagenes/Imagen.png
     * <p>
     * Para obtener el recurso desde ruta especifica, se usa la implementacion creada en la clase MvcConfig
     */

    //


    /*@GetMapping("/imagenes/{fileName:.+}")
    public ResponseEntity<Resource> verImagen(@PathVariable(name = "fileName") String fileName) {

        Path foto = Paths.get("imagenes").resolve(fileName).toAbsolutePath();
        logger.info(foto.toString());
        Resource resource = null;
        try {
            resource = new UrlResource(foto.toUri());
            if (!resource.exists() || !resource.isReadable()) {
                throw new RuntimeException("Erro Imagen");
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename = \" " + resource.getFilename() + "\"")
                .body(resource);
    }*/


}
