package com.huntingCube.dataView.service;

import com.huntingCube.dataView.model.ResourceDetails;

import java.util.List;

/**
 * Created by dgup27 on 1/10/2017.
 */

public interface ResourceService {

    List<ResourceDetails> findResources(int maxRecords, ResourceDetails resourceDetails);

    ResourceDetails findById(int id);

    void save(ResourceDetails resourceDetails);

    void saveExcelRecords(String filePath);
}
