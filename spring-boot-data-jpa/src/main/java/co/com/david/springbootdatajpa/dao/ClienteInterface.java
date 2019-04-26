package co.com.david.springbootdatajpa.dao;

import co.com.david.springbootdatajpa.entity.Cliente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

//public interface ClienteInterface extends CrudRepository<Cliente, Long> {
public interface ClienteInterface extends PagingAndSortingRepository<Cliente, Long> {

}
