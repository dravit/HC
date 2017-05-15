package com.huntingCube.dataView.service;

import com.huntingCube.dataView.model.ClientPosition;

import java.util.List;

/**
 * Created by dgup27 on 5/12/2017.
 */
public interface ClientPositionService {

    ClientPosition findById(int id);

    ClientPosition findByPosition(String position);

    List<ClientPosition> findAllPositions();

    void save(ClientPosition clientPosition);

    void updateClientPosition(ClientPosition clientPosition);

    void deleteById(int id);
}
