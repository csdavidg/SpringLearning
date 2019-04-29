package co.com.david.springbootdatajpa.dto;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FacturaDTO {

    private Long id;
    @NotEmpty
    private String descripcion;
    @NotEmpty
    private String observacion;
    private LocalDate createAt;
    private ClienteDTO clienteDTO;
    //@NotEmpty
    private List<ItemFacturaDTO> itemFacturasList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public LocalDate getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDate createAt) {
        this.createAt = createAt;
    }

    public ClienteDTO getClienteDTO() {
        return clienteDTO;
    }

    public void setClienteDTO(ClienteDTO clienteDTO) {
        this.clienteDTO = clienteDTO;
    }

    public List<ItemFacturaDTO> getItemFacturasList() {
        if (this.itemFacturasList == null) {
            this.itemFacturasList = new ArrayList<>();
        }
        return this.itemFacturasList;
    }

    public void setItemFacturasList(List<ItemFacturaDTO> itemFacturasList) {
        this.itemFacturasList = itemFacturasList;
    }


    /**
     * Metodo que calcula el total de la factura.
     * Se incluye en el DTO ya que aun no se ha creado una logica de negocio
     *
     * @return
     */
    public Double getTotal() {
        if (this.itemFacturasList != null && !this.itemFacturasList.isEmpty()) {
            return this.itemFacturasList.stream().mapToDouble(ItemFacturaDTO::calcularImporte).sum();
        }
        return 0D;
    }
}
