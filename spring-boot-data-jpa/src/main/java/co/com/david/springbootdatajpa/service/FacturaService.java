package co.com.david.springbootdatajpa.service;

import co.com.david.springbootdatajpa.dao.IFacturaDAO;
import co.com.david.springbootdatajpa.entity.Factura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Service
public class FacturaService {

    @Autowired
    private IFacturaDAO iFacturaDAO;

    @Transactional
    public void saveFactura(Factura factura) {
        iFacturaDAO.save(factura);
    }

    @Transactional(readOnly = true)
    public Factura findById(Long id) {
        return iFacturaDAO.findById(id).orElse(null);
    }

}
