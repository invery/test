package testgroup.test.dao;

import testgroup.test.model.Comp;

import java.util.List;

public interface CompDAO {
    List<Comp> allComps(int page, String search);
    void add(Comp comp);
    void delete(Comp comp);
    void edit(Comp comp);
    Comp getById(int id);
    public int compsCount(String search);
    public int compsReadyCount();
    public List<Comp> searchComps(String search);
}