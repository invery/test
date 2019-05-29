package testgroup.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import testgroup.test.dao.CompDAO;
import testgroup.test.dao.CompDAOImpl;
import testgroup.test.model.Comp;

import java.util.List;

@Service
public class CompServiceImpl implements CompService {

    private CompDAO compDAO = new CompDAOImpl();

    @Autowired
    public void setCompDAO(CompDAO compDAO) {
        this.compDAO = compDAO;
    }

    @Transactional
    @Override
    public List<Comp> allComps(int page, String search) {
        return compDAO.allComps(page,search);
    }

    @Transactional
    @Override
    public List<Comp> searchComps(String search) {
        return compDAO.searchComps(search);
    }

    @Transactional
    @Override
    public void add(Comp comp) {
        compDAO.add(comp);

    }
    @Transactional
    @Override
    public void delete(Comp comp) {
        compDAO.delete(comp);

    }
    @Transactional
    @Override
    public void edit(Comp comp) {
        compDAO.edit(comp);

    }
    @Transactional
    @Override
    public Comp getById(int id) {
        return compDAO.getById(id);
    }

    @Transactional
    @Override
    public int compsCount(String search) {
        return compDAO.compsCount(search);
    }

    @Transactional
    @Override
    public int compsReadyCount() {
        return compDAO.compsReadyCount();
    }
}
