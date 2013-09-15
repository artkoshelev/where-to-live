package ru.yandex.hackaton.server.db.dao;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import ru.yandex.hackaton.server.db.model.DistrictsSummary;
import ru.yandex.hackaton.server.db.model.Elementary;
import ru.yandex.hackaton.server.resources.SearchParams;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Sergey Polovko
 */
@Singleton
public class DistrictsSummaryDao extends CrudDao<DistrictsSummary> {

    @Inject
    public DistrictsSummaryDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<DistrictsSummary> find(SearchParams params) {
        return list(toQuery(params));
    }

    private Query toQuery(SearchParams params) {
        String query = "SELECT " + getColumns(params) + " FROM districts_summary ORDER BY summ DESC";
        System.out.println(query);
        return currentSession().createSQLQuery(query)
                .setResultTransformer(Transformers.aliasToBean(DistrictsSummary.class));
    }

    public String getColumns(SearchParams params) {
        StringBuffer result = new StringBuffer("districts_summary.districtid");
        for (String param : params.getParams().keySet()) {
            result.append(", " + param);
        }
        result.append(", ");

        for (String param : params.getParams().keySet()) {
            result.append(param + " * " + params.getParams().get(param) + " + ");
        }

        String res = result.substring(0, result.length() - 3);
        res += " as summ";

        return res;
    }
}
