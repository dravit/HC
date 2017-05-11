package com.huntingCube.dataView.dao;

import com.huntingCube.dataView.model.ResourceDetails;
import com.huntingCube.global.resources.dao.AbstractDao;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by dgup27 on 1/10/2017.
 */
@Repository("resourceDao")
public class ResourceDaoImpl extends AbstractDao<Integer, ResourceDetails> implements ResourceDao {

    static final Logger logger = LoggerFactory.getLogger(ResourceDaoImpl.class);

    @Override
    public void save(ResourceDetails resourceDetails) {
        saveOrUpdate(resourceDetails);
    }

    @Override
    public ResourceDetails findById(int id) {
        return getByKey(id);
    }

    @Override
    public List<ResourceDetails> findResources(int maxRecords, ResourceDetails resourceDetails) {
        if (resourceDetails.getName().isEmpty() && resourceDetails.getContactNumber().isEmpty() && resourceDetails.getEmailId().isEmpty()) {
            return null;
        }
        Criteria criteria = createEntityCriteria();
        if (!resourceDetails.getName().isEmpty()) {
            criteria.add(Restrictions.ilike("name", resourceDetails.getName(), MatchMode.ANYWHERE));
        }
        if (!resourceDetails.getContactNumber().isEmpty()) {
            criteria.add(Restrictions.ilike("contactNumber", resourceDetails.getContactNumber(), MatchMode.ANYWHERE));
        }
        if (!resourceDetails.getEmailId().isEmpty()) {
            criteria.add(Restrictions.ilike("emailId", resourceDetails.getEmailId(), MatchMode.ANYWHERE));
        }
        criteria.addOrder(Order.asc("name"));
        //criteria.setMaxResults(maxRecords);
        List<ResourceDetails> resourceDetailsList = (List<ResourceDetails>) criteria.list();
        return resourceDetailsList;
    }
}
