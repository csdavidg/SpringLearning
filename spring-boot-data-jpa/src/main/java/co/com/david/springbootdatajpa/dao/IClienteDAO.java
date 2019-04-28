package co.com.david.springbootdatajpa.dao;

import co.com.david.springbootdatajpa.entity.Cliente;
import org.springframework.data.repository.PagingAndSortingRepository;

//public interface IClienteDAO extends CrudRepository<Cliente, Long> {
public interface IClienteDAO extends PagingAndSortingRepository<Cliente, Long> {

}
