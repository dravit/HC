package com.huntingCube.dataView.dao;

import com.huntingCube.dataView.model.ClientPosition;

import java.util.List;

/**
 * Created by dgup27 on 5/12/2017.
 */
public interface ClientPositionDao {
    ClientPosition findById(int id);

    ClientPosition findByPosition(String position);

    List<ClientPosition> findAllPositions();

    void save(ClientPosition clientPosition);

    void updateClientPosition(ClientPosition clientPosition);

    void deleteById(int id);
}
