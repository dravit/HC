package com.huntingCube.dataView.service;

import com.huntingCube.dataView.dao.ClientPositionDao;
import com.huntingCube.dataView.model.ClientPosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by dgup27 on 5/12/2017.
 */
@Service("clientPositionService")
@Transactional
public class ClientPositionServiceImpl implements ClientPositionService {

    @Autowired
    ClientPositionDao clientPositionDao;

    @Override
    public ClientPosition findById(int id) {
        return clientPositionDao.findById(id);
    }

    @Override
    public ClientPosition findByPosition(String position) {
        return clientPositionDao.findByPosition(position);
    }

    @Override
    public List<ClientPosition> findAllPositions() {
        return clientPositionDao.findAllPositions();
    }

    @Override
    public void save(ClientPosition clientPosition) {
        clientPositionDao.save(clientPosition);
    }

    @Override
    public void updateClientPosition(ClientPosition clientPosition) {
        clientPositionDao.updateClientPosition(clientPosition);
    }

    @Override
    public void deleteById(int id) {
        clientPositionDao.deleteById(id);
    }
}
