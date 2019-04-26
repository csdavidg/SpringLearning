package co.com.david.springbootdatajpa.dao;

import co.com.david.springbootdatajpa.entity.Cliente;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ClienteDAO {

    @PersistenceContext
    private EntityManager em;

    public List<Cliente> findAll() {
        return em.createQuery("select c from Cliente c", Cliente.class).getResultList();
    }

    public void save(Cliente cliente) {
        em.persist(cliente);
    }

    public Cliente obtenerPorId(Long id) {
        return em.find(Cliente.class, id);
    }

    public void eliminar(Cliente cliente) {
        em.remove(cliente);
    }

}
