package com.huntingCube.dataView.dao;

import com.huntingCube.dataView.model.ClientHistory;

import java.util.List;

/**
 * Created by dgup27 on 5/7/2017.
 */
public interface ClientHistoryDao {

    ClientHistory findById(int id);

    List<ClientHistory> findByClientID(String name);

    List<ClientHistory> findByRecruiter(String ssoID);

    List<ClientHistory> findAll();

    void save(ClientHistory clientHistory);

    void deleteById(int id);
}
