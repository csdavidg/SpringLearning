package co.com.david.springbootdatajpa.dao;

import co.com.david.springbootdatajpa.entity.Factura;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IFacturaDAO extends PagingAndSortingRepository<Factura, Long> {

}
