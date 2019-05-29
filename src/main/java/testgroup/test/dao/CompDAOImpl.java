package testgroup.test.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import testgroup.test.model.Comp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class CompDAOImpl implements CompDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Comp> allComps(int page, String search) {
        Session session = sessionFactory.getCurrentSession();
        Query query;


        List<Comp> list = new ArrayList<>();
        try {
            query = session.createQuery(constructQuery(search)).setFirstResult(10 * (page - 1)).setMaxResults(10);
            list = query.getResultList();
        } catch (Exception e) {}

        return list;
    }

    private String constructQuery (String search) {
        StringBuilder sql = new StringBuilder();
        switch (search) {
            case "orderNameAsc":
                sql.append("from Comp order by name asc");
                break;
            case "orderNameDesc":
                sql.append("from Comp order by name desc");
                break;
            case "orderNeedAsc":
                sql.append("from Comp order by isNeeded asc");
                break;
            case "orderNeedDesc":
                sql.append("from Comp order by isNeeded desc");
                break;
            case "orderQuantityAsc":
                sql.append("from Comp order by quantity asc");
                break;
            case "orderQuantityDesc":
                sql.append("from Comp order by quantity desc");
                break;
            case "filterNeed":
                sql.append("from Comp where isNeeded=true");
                break;
            case "filterNotNeed":
                sql.append("from Comp where isNeeded=false");
                break;
            default:
                sql.append("from Comp");
                break;
        }
        return sql.toString();
    }

    @Override
    public List<Comp> searchComps(String search) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Comp s where s.name LIKE :search");
        query.setParameter("search",search);

        List<Comp> list = query.getResultList();

        return list;
    }

    @Override
    public void add(Comp comp) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(comp);
    }

    @Override
    public void delete(Comp comp) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(comp);
    }

    @Override
    public void edit(Comp comp) {
        Session session = sessionFactory.getCurrentSession();
        session.update(comp);
    }

    @Override
    public Comp getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Comp.class, id);
    }

    public int compsCount(String search) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select count(*) "+constructQuery(search), Number.class).getSingleResult().intValue();
    }

    @Override
    public int compsReadyCount() {
        Session session = sessionFactory.getCurrentSession();
        int i = 0;
        try {
            i = session.createQuery("select MIN(quantity) from Comp Where isNeeded=true", Number.class).getSingleResult().intValue();
        } catch (Exception e) {}
        return i;
    }
}
