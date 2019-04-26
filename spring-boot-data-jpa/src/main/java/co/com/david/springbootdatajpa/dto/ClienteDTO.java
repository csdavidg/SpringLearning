package co.com.david.springbootdatajpa.dto;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

public class ClienteDTO {

    private Long id;

    @NotEmpty
    private String nombre;
    @NotEmpty
    private String apellido;
    @NotEmpty
    private String email;
    private LocalDate creadoEn;
    private String foto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getCreadoEn() {
        return creadoEn;
    }

    public void setCreadoEn(LocalDate creadoEn) {
        this.creadoEn = creadoEn;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
