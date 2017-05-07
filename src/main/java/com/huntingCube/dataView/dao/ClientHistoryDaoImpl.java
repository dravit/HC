package com.huntingCube.dataView.dao;

import com.huntingCube.dataView.model.ClientHistory;
import com.huntingCube.global.resources.dao.AbstractDao;
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

    @Override
    public List<ClientHistory> findByClientID(String clientID) {
        return null;
    }

    @Override
    public List<ClientHistory> findByRecruiter(String ssoID) {
        return null;
    }

    @Override
    public List<ClientHistory> findAll() {
        return null;
    }

    @Override
    public void save(ClientHistory clientHistory) {
        persist(clientHistory);
    }

    @Override
    public void deleteById(int id) {
        delete(findById(id));
    }
}
