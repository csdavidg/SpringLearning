package co.com.david.springbootdatajpa.service;

import co.com.david.springbootdatajpa.dao.ClienteInterface;
import co.com.david.springbootdatajpa.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteInterface clienteInterface;

    @Transactional(readOnly = true)
    public List<Cliente> findAll() {
        return (List<Cliente>) clienteInterface.findAll();
    }

    @Transactional
    public void save(Cliente cliente) {
        clienteInterface.save(cliente);
    }

    @Transactional(readOnly = true)
    public Cliente obtenerPorId(Long id) {
        return clienteInterface.findById(id).orElse(null);
    }

    @Transactional
    public void eliminar(Cliente cliente) {
        clienteInterface.delete(cliente);
    }

    @Transactional(readOnly = true)
    public Page<Cliente> findAll(Pageable pageable) {
        return clienteInterface.findAll(pageable);
    }
}
