package com.huntingCube.dataView.service;

import com.huntingCube.dataView.dao.*;
import com.huntingCube.dataView.model.*;
import com.huntingCube.utility.HuntingCubeUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by dgup27 on 1/10/2017.
 */
@Service("resourceHistoryService")
@Transactional
public class ResourceHistoryServiceImpl implements ResourceHistoryService {

    static final Logger logger = LoggerFactory.getLogger(ResourceHistoryServiceImpl.class);

    @Autowired
    ResourceHistoryDao resourceDao;

    @Autowired
    InstituteDao instituteDao;

    @Autowired
    LocationDao locationDao;

    @Autowired
    PassingYearDao passingYearDao;

    @Autowired
    ProgramDao programDao;

    @Autowired
    StreamDao streamDao;

    @Override
    public List<ResourceHistoryDetails> findResources(int maxRecords, ResourceHistoryDetails resourceDetails) {
        return resourceDao.findResources(maxRecords, resourceDetails);
    }

    @Override
    public ResourceHistoryDetails findById(int id) {
        return resourceDao.findById(id);
    }

    @Override
    public ResourceHistoryDetails findByEmail(String emailID) {
        return resourceDao.findByEmail(emailID);
    }

    @Override
    public void save(ResourceHistoryDetails resourceDetails) {
        /*ResourceDetails resourceByEmail = findByEmail(resourceDetails.getEmailId());
        resourceByEmail = resourceDao.findById(resourceByEmail.getId());
        if(resourceByEmail != null) {
            try {
                
            } catch (Exception e) {
                logger.error("Error while updating resource", e);
            }
        } else {
            resourceDao.save(resourceDetails);
        }*/
        resourceDao.save(resourceDetails);
    }

    @Override
    public void update(ResourceHistoryDetails resourceDetails) {
        ResourceHistoryDetails entity = resourceDao.findById(resourceDetails.getId());
        if (entity != null) {
            entity = resourceDetails;
        }
    }

    @Override
    public void getResourceHistoryFromResourceDetails(ResourceDetails resourceDetails, ResourceHistoryDetails resourceHistoryDetails) {
        if (HuntingCubeUtility.isNotEmptyOrNull(resourceDetails.getName()))
            resourceHistoryDetails.setName(resourceDetails.getName());

        if (HuntingCubeUtility.isNotEmptyOrNull(resourceDetails.getContactNumber()))
            resourceHistoryDetails.setContactNumber(resourceHistoryDetails.getContactNumber() + " / " + resourceDetails.getContactNumber());

        resourceHistoryDetails.setInstitute(resourceDetails.getInstitute());

        resourceHistoryDetails.setStream(resourceDetails.getStream());

        resourceHistoryDetails.setProgram(resourceDetails.getProgram());

        resourceHistoryDetails.setPassingYear(resourceDetails.getPassingYear());

        resourceHistoryDetails.setCGPA(resourceDetails.getCGPA());

        resourceHistoryDetails.setAirRank(resourceDetails.getAirRank());

        if (HuntingCubeUtility.isNotEmptyOrNull(resourceDetails.getOtherRank()))
            resourceHistoryDetails.setOtherRank(resourceDetails.getOtherRank());

        if (HuntingCubeUtility.isNotEmptyOrNull(resourceDetails.getAreaOfExpertise()))
            resourceHistoryDetails.setAreaOfExpertise(resourceDetails.getAreaOfExpertise());

        if (HuntingCubeUtility.isNotEmptyOrNull(resourceDetails.getSkills()))
            resourceHistoryDetails.setSkills(resourceDetails.getSkills());

        resourceHistoryDetails.setDesignation(resourceDetails.getDesignation());

        if (HuntingCubeUtility.isNotEmptyOrNull(resourceDetails.getCompany()))
            resourceHistoryDetails.setCompany(resourceDetails.getCompany());

        resourceHistoryDetails.setExperience(resourceDetails.getExperience());

        resourceHistoryDetails.setFixedCTC(resourceDetails.getFixedCTC());

        resourceHistoryDetails.setVariableCTC(resourceDetails.getVariableCTC());

        resourceHistoryDetails.setExpectedCTC(resourceDetails.getExpectedCTC());

        resourceHistoryDetails.setNoticePeriod(resourceDetails.getNoticePeriod());

        resourceHistoryDetails.setCurrentLocation(resourceDetails.getCurrentLocation());

        resourceHistoryDetails.setPreferredLocation(resourceDetails.getPreferredLocation());

        if (HuntingCubeUtility.isNotEmptyOrNull(resourceDetails.getLinkedinProfile()))
            resourceHistoryDetails.setLinkedinProfile(resourceDetails.getLinkedinProfile());

        if (HuntingCubeUtility.isNotEmptyOrNull(resourceDetails.getFacebookProfile()))
            resourceHistoryDetails.setFacebookProfile(resourceDetails.getFacebookProfile());

        resourceHistoryDetails.setAddedDate(resourceDetails.getAddedDate());
        resourceHistoryDetails.setAddedBy(resourceDetails.getAddedBy());
    }
}
