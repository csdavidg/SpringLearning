package co.com.david.springbootdatajpa.dao;

import co.com.david.springbootdatajpa.entity.Producto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface IProductoDAO extends PagingAndSortingRepository<Producto, Long> {

    @Query("SELECT p FROM Producto p WHERE p.nombre like %?1%")
    List<Producto> findAllLikeName(String nombre);
}
