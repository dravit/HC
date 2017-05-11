package com.huntingCube.dataView.dao;

import com.huntingCube.dataView.model.Client;
import com.huntingCube.dataView.model.ClientHistory;
import com.huntingCube.global.resources.dao.AbstractDao;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by dgup27 on 5/7/2017.
 */
@Repository("clientHistoryDao")
public class ClientHistoryDaoImpl extends AbstractDao<Integer, ClientHistory> implements ClientHistoryDao {
    @Override
    public ClientHistory findById(int id) {
        ClientHistory clientHistory = getByKey(id);
        return clientHistory;
    }

    /*@Override
    public List<ClientHistory> findByClientID(Client client) {
        Criteria criteria = createEntityCriteria().add(Restrictions.eq("clientName", name.toUpperCase()));
        Client client = (Client) criteria.uniqueResult();
        return client;

    }*/

    @Override
    public List<ClientHistory> findByRecruiter(String ssoID) {
        Criteria criteria = createEntityCriteria().add(Restrictions.eq("addedBy", ssoID)).addOrder(Order.desc("addedDate"));
        List<ClientHistory> clientHistoryList = (List<ClientHistory>) criteria.list();
        return clientHistoryList;
    }

    @Override
    public List<ClientHistory> findByResource(int resourceID) {
        Criteria criteria = createEntityCriteria().add(Restrictions.eq("resourceID", resourceID)).addOrder(Order.desc("addedDate"));
        List<ClientHistory> clientHistoryList = (List<ClientHistory>) criteria.list();
        return clientHistoryList;
    }

    @Override
    public List<ClientHistory> findAll() {
        return null;
    }

    @Override
    public void save(ClientHistory clientHistory) {
        saveOrUpdate(clientHistory);
    }

    @Override
    public void deleteById(int id) {
        delete(findById(id));
    }
}
