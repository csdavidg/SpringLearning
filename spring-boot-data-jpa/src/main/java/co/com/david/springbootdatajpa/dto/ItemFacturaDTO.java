package co.com.david.springbootdatajpa.dto;

public class ItemFacturaDTO {

    private Long id;
    private Integer cantidad;
    private ProductoDTO productoDTO;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public ProductoDTO getProductoDTO() {
        return productoDTO;
    }

    public void setProductoDTO(ProductoDTO productoDTO) {
        this.productoDTO = productoDTO;
    }

    /**
     * Metodo que calcula el valor de cada item segun la cantidad de productos.
     * Se incluye en el DTO ya que aun no se ha creado una logica de negocio
     *
     * @return
     */
    public Double calcularImporte() {
        if (this.productoDTO != null && this.productoDTO.getPrecio() != null && this.cantidad != null) {
            return this.productoDTO.getPrecio() * this.cantidad;
        }
        return 0D;
    }
}
