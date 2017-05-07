package com.huntingCube.dataView.dao;

import com.huntingCube.dataView.model.ResourceDetails;

import java.util.List;

/**
 * Created by dgup27 on 1/10/2017.
 */
public interface ResourceDao {

    void save(ResourceDetails resourceDetails);

    ResourceDetails findById(int id);

    List<ResourceDetails> findResources(int maxRecords, ResourceDetails resourceDetails);
}
