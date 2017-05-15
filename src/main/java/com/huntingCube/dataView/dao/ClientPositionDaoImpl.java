package com.huntingCube.dataView.dao;

import com.huntingCube.dataView.model.ClientPosition;
import com.huntingCube.global.resources.dao.AbstractDao;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by dgup27 on 5/12/2017.
 */
@Repository("clientPositionDao")
public class ClientPositionDaoImpl extends AbstractDao<Integer, ClientPosition> implements ClientPositionDao {

    @Override
    public ClientPosition findById(int id) {
        return getByKey(id);
    }

    @Override
    public ClientPosition findByPosition(String position) {
        Criteria criteria = createEntityCriteria().add(Restrictions.eq("clientPosition", position.toUpperCase()));
        ClientPosition clientPosition = (ClientPosition) criteria.uniqueResult();
        return clientPosition;
    }

    @Override
    public List<ClientPosition> findAllPositions() {
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("clientPosition"));
        List<ClientPosition> clientStatusList = (List<ClientPosition>) criteria.list();
        return clientStatusList;
    }

    @Override
    public void save(ClientPosition clientPosition) {
        persist(clientPosition);
    }

    @Override
    public void updateClientPosition(ClientPosition clientPosition) {
        update(clientPosition);
    }

    @Override
    public void deleteById(int id) {
        delete(findById(id));
    }
}
