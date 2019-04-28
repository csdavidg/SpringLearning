package co.com.david.springbootdatajpa.utils;

import co.com.david.springbootdatajpa.dto.ClienteDTO;
import co.com.david.springbootdatajpa.dto.FacturaDTO;
import co.com.david.springbootdatajpa.dto.ItemFacturaDTO;
import co.com.david.springbootdatajpa.dto.ProductoDTO;
import co.com.david.springbootdatajpa.entity.Cliente;
import co.com.david.springbootdatajpa.entity.Factura;
import co.com.david.springbootdatajpa.entity.ItemFactura;
import co.com.david.springbootdatajpa.entity.Producto;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.stream.Collectors;

/**
 * Clase creada como utilitaria para parsear datos, no debe ser implementado de esta manera
 * ya que cada objeto deberia tener una logica independiente para respetar el principio de unica responsablidad
 */
@Component
public class DTOParser {

    public Cliente convertirDTOToEntity(ClienteDTO clienteDTO) {
        if (clienteDTO == null) {
            return null;
        }
        Cliente cliente = new Cliente();
        cliente.setId(clienteDTO.getId());
        cliente.setNombre(clienteDTO.getNombre());
        cliente.setApellido(clienteDTO.getApellido());
        cliente.setEmail(clienteDTO.getEmail());
        cliente.setCreadoEn(LocalDate.now());
        cliente.setFoto(clienteDTO.getFoto());
        if (clienteDTO.getFacturaDTOList() != null) {
            cliente.setFacturas(clienteDTO.getFacturaDTOList().stream()
                    .map(this::convertirDTOToEntity).collect(Collectors.toList()));
        }
        return cliente;
    }

    public ClienteDTO convertirEntityToDTO(Cliente cliente) {
        if (cliente == null) {
            return null;
        }
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setId(cliente.getId());
        clienteDTO.setNombre(cliente.getNombre());
        clienteDTO.setApellido(cliente.getApellido());
        clienteDTO.setEmail(cliente.getEmail());
        clienteDTO.setCreadoEn(LocalDate.now());
        clienteDTO.setFoto(cliente.getFoto());
        if (cliente.getFacturas() != null) {
            clienteDTO.setFacturaDTOList(cliente.getFacturas().stream()
                    .map(this::convertirEntityToDTO).collect(Collectors.toList()));
        }
        return clienteDTO;
    }

    public FacturaDTO convertirEntityToDTO(Factura factura) {
        if (factura == null) {
            return null;
        }
        FacturaDTO facturaDTO = new FacturaDTO();
        facturaDTO.setId(factura.getId());
        facturaDTO.setClienteDTO(this.convertirEntityToDTO(factura.getCliente()));
        facturaDTO.setCreateAt(factura.getCreateAt());
        facturaDTO.setDescripcion(factura.getDescripcion());
        facturaDTO.setObservacion(factura.getObservacion());
        if (factura.getItemFacturas() != null) {
            facturaDTO.setItemFacturasList(factura.getItemFacturas().stream()
                    .map(this::convertirEntityToDTO).collect(Collectors.toList()));
        }
        return facturaDTO;
    }

    public Factura convertirDTOToEntity(FacturaDTO facturaDTO) {
        if (facturaDTO == null) {
            return null;
        }
        Factura factura = new Factura();
        factura.setId(facturaDTO.getId());
        if (facturaDTO.getClienteDTO() != null) {
            factura.setCliente(this.convertirDTOToEntity(facturaDTO.getClienteDTO()));
        }
        factura.setCreateAt(facturaDTO.getCreateAt());
        factura.setDescripcion(facturaDTO.getDescripcion());
        factura.setObservacion(facturaDTO.getObservacion());
        if (facturaDTO.getItemFacturasList() != null) {
            factura.setItemFacturas(facturaDTO.getItemFacturasList().stream()
                    .map(this::convertirDTOToEntity).collect(Collectors.toList()));
        }
        return factura;
    }

    public Producto convertirDTOToEntity(ProductoDTO productoDTO) {
        if (productoDTO == null) {
            return null;
        }
        Producto producto = new Producto();
        producto.setId(productoDTO.getId());
        producto.setNombre(productoDTO.getNombre());
        producto.setPrecio(productoDTO.getPrecio());
        producto.setCreateAt(productoDTO.getCreateAt());
        return producto;
    }

    public ProductoDTO convertirEntityToDTO(Producto producto) {
        if (producto == null) {
            return null;
        }
        ProductoDTO productoDTO = new ProductoDTO();
        productoDTO.setId(producto.getId());
        productoDTO.setNombre(producto.getNombre());
        productoDTO.setPrecio(producto.getPrecio());
        productoDTO.setCreateAt(producto.getCreateAt());
        return productoDTO;
    }

    public ItemFactura convertirDTOToEntity(ItemFacturaDTO itemFacturaDTO) {
        if (itemFacturaDTO == null) {
            return null;
        }
        ItemFactura itemFactura = new ItemFactura();
        itemFactura.setId(itemFacturaDTO.getId());
        itemFactura.setCantidad(itemFacturaDTO.getCantidad());
        itemFactura.setProducto(this.convertirDTOToEntity(itemFacturaDTO.getProductoDTO()));
        return itemFactura;
    }

    public ItemFacturaDTO convertirEntityToDTO(ItemFactura itemFactura) {
        if (itemFactura == null) {
            return null;
        }
        ItemFacturaDTO itemFacturaDTO = new ItemFacturaDTO();
        itemFacturaDTO.setId(itemFactura.getId());
        itemFacturaDTO.setCantidad(itemFactura.getCantidad());
        itemFacturaDTO.setProductoDTO(this.convertirEntityToDTO(itemFactura.getProducto()));
        return itemFacturaDTO;
    }
}
