package com.huntingCube.dataView.service;

import com.huntingCube.dataView.dao.*;
import com.huntingCube.dataView.model.*;
import com.huntingCube.utility.HuntingCubeUtility;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

/**
 * Created by dgup27 on 1/10/2017.
 */
@Service("resourceService")
@Transactional
public class ResourceServiceImpl implements ResourceService {

    static final Logger logger = LoggerFactory.getLogger(ResourceServiceImpl.class);

    @Autowired
    ResourceDao resourceDao;

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
    public List<ResourceDetails> findResources(int maxRecords, ResourceDetails resourceDetails) {
        return resourceDao.findResources(maxRecords, resourceDetails);
    }

    @Override
    public ResourceDetails findById(int id) {
        return resourceDao.findById(id);
    }

    @Override
    public ResourceDetails findByEmail(String emailID) {
        return resourceDao.findByEmail(emailID);
    }

    @Override
    public void save(ResourceDetails resourceDetails) {
        /*ResourceDetails resourceByEmail = findByEmail(resourceDetails.getEmailId());
        resourceByEmail = resourceDao.findById(resourceByEmail.getId());
        if(resourceByEmail != null) {
            try {
                if(HuntingCubeUtility.isNotEmptyOrNull(resourceDetails.getName()))
                    resourceByEmail.setName(resourceDetails.getName());

                if(HuntingCubeUtility.isNotEmptyOrNull(resourceDetails.getContactNumber()))
                    resourceByEmail.setContactNumber(resourceByEmail.getContactNumber() + " / " + resourceDetails.getContactNumber());

                if(HuntingCubeUtility.isNotEmptyOrNull(resourceDetails.getInstitute().getInstituteName()))
                    resourceByEmail.setInstitute(resourceDetails.getInstitute());

                if(HuntingCubeUtility.isNotEmptyOrNull(resourceDetails.getStream().getStreamName()))
                    resourceByEmail.setStream(resourceDetails.getStream());

                if(HuntingCubeUtility.isNotEmptyOrNull(resourceDetails.getProgram().getProgramName()))
                    resourceByEmail.setProgram(resourceDetails.getProgram());

                if(HuntingCubeUtility.isNotEmptyOrNull(resourceDetails.getPassingYear().getPassingYear()))
                    resourceByEmail.setPassingYear(resourceDetails.getPassingYear());

                resourceByEmail.setCGPA(resourceDetails.getCGPA());

                resourceByEmail.setAirRank(resourceDetails.getAirRank());

                if(HuntingCubeUtility.isNotEmptyOrNull(resourceDetails.getOtherRank()))
                    resourceByEmail.setOtherRank(resourceDetails.getOtherRank());

                if(HuntingCubeUtility.isNotEmptyOrNull(resourceDetails.getAreaOfExpertise()))
                    resourceByEmail.setAreaOfExpertise(resourceDetails.getAreaOfExpertise());

                if(HuntingCubeUtility.isNotEmptyOrNull(resourceDetails.getSkills()))
                    resourceByEmail.setSkills(resourceDetails.getSkills());

                if(HuntingCubeUtility.isNotEmptyOrNull(resourceDetails.getDesignation()))
                    resourceByEmail.setDesignation(resourceDetails.getDesignation());

                if(HuntingCubeUtility.isNotEmptyOrNull(resourceDetails.getCompany()))
                    resourceByEmail.setCompany(resourceDetails.getCompany());

                resourceByEmail.setExperience(resourceDetails.getExperience());

                if(HuntingCubeUtility.isNotEmptyOrNull(resourceDetails.getCTC()))
                    resourceByEmail.setCTC(resourceDetails.getCTC());

                if(HuntingCubeUtility.isNotEmptyOrNull(resourceDetails.getCurrentLocation().getLocationName()))
                    resourceByEmail.setCurrentLocation(resourceDetails.getCurrentLocation());

                if(HuntingCubeUtility.isNotEmptyOrNull(resourceDetails.getPreferredLocation().getLocationName()))
                    resourceByEmail.setPreferredLocation(resourceDetails.getPreferredLocation());

                if(HuntingCubeUtility.isNotEmptyOrNull(resourceDetails.getLinkedinProfile()))
                    resourceByEmail.setLinkedinProfile(resourceDetails.getLinkedinProfile());

                if(HuntingCubeUtility.isNotEmptyOrNull(resourceDetails.getFacebookProfile()))
                    resourceByEmail.setFacebookProfile(resourceDetails.getFacebookProfile());

                resourceByEmail.setAddedDate(resourceDetails.getAddedDate());
                resourceByEmail.setAddedBy(resourceDetails.getAddedBy());
            } catch (Exception e) {
                logger.error("Error while updating resource", e);
            }
        } else {
            resourceDao.save(resourceDetails);
        }*/
        resourceDao.save(resourceDetails);
    }

    @Override
    public void update(ResourceDetails resourceDetails) {
        ResourceDetails entity = resourceDao.findById(resourceDetails.getId());
        if(entity != null) {
            entity = resourceDetails;
        }
    }

    @Override
    public void saveExcelRecords(String filePath) {
        try {
            logger.info("Excel File Path {}", filePath);
            int noOfRecordsPersisted = 0;
            int returnedNoOfRecords = readExcelAndSaveRecords(filePath, noOfRecordsPersisted);
            logger.info("No of Records Persisted first variable {} , second variable {}", noOfRecordsPersisted, returnedNoOfRecords);
        } catch (IOException io) {
            logger.error("Error fetching excel file", io);
        }

    }

    private int readExcelAndSaveRecords(String excelPath, int noOfRecordsPersisted) throws IOException {
        FileInputStream file = new FileInputStream(new File(excelPath));
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        int noOfSheets = workbook.getNumberOfSheets();
        for (int i = 0; i < noOfSheets; i++) {
            XSSFSheet sheet = workbook.getSheetAt(i);
            boolean isFirstRow = true;
            LinkedList<String> columnNamesList = new LinkedList<>();
            for (int rowCount = 0; rowCount <= sheet.getLastRowNum(); rowCount++) {
                Row row = sheet.getRow(rowCount);
                if (row.getCell(0) == null || row.getCell(0).getStringCellValue() == null || row.getCell(0).getStringCellValue().isEmpty()) {
                    rowCount++;
                    continue;
                }
                ResourceDetails resourceDetails = new ResourceDetails();
                Map<String, String> rowMap = new HashMap<>();
                for (int index = 0; index <= 18; index++) {
                    Cell cell = row.getCell(index);
                    if (cell != null) {
                        if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                            cell.setCellType(Cell.CELL_TYPE_STRING);
                        }
                        if (rowCount == 0) {
                            columnNamesList.add(cell.getStringCellValue().trim());
                        } else {
                            rowMap.put(columnNamesList.get(index),
                                    cell == null || cell.getStringCellValue() == null || cell.getStringCellValue().isEmpty() ? "NA" : cell.getStringCellValue());
                        }
                    } else if (rowCount != 0) {
                        rowMap.put(columnNamesList.get(index), "NA");
                    }
                }
                if (rowCount == 0) {
                    //Do nothing
                } else {
                    try {
                        Institute institute = null;
                        Stream stream = null;
                        Program program = null;
                        PassingYear passingYear = null;
                        Location location = null;
                        resourceDetails.setName(rowMap.get(columnNamesList.get(6)));
                        resourceDetails.setContactNumber(rowMap.get(columnNamesList.get(7)));
                        resourceDetails.setEmailId("NA".equals(rowMap.get(columnNamesList.get(8))) ? "" : rowMap.get(columnNamesList.get(8)));
                        if (instituteDao.findByName(rowMap.get(columnNamesList.get(9))) != null) {
                            resourceDetails.setInstitute(instituteDao.findByName(rowMap.get(columnNamesList.get(9))));
                        } else {
                            institute = new Institute();
                            institute.setInstituteName(rowMap.get(columnNamesList.get(9)).toUpperCase());
                            institute.setAddedBy("Excel Upload");
                            instituteDao.save(institute);
                            resourceDetails.setInstitute(institute);
                        }
                        if (streamDao.findByName(rowMap.get(columnNamesList.get(10))) != null) {
                            resourceDetails.setStream(streamDao.findByName(rowMap.get(columnNamesList.get(10))));
                        } else {
                            stream = new Stream();
                            stream.setStreamName(rowMap.get(columnNamesList.get(10)).toUpperCase());
                            stream.setAddedBy("Excel Upload");
                            streamDao.save(stream);
                            resourceDetails.setStream(stream);
                        }
                        if (programDao.findByName(rowMap.get(columnNamesList.get(11))) != null) {
                            resourceDetails.setProgram(programDao.findByName(rowMap.get(columnNamesList.get(11))));
                        } else {
                            program = new Program();
                            program.setProgramName(rowMap.get(columnNamesList.get(11)).toUpperCase());
                            program.setAddedBy("Excel Upload");
                            programDao.save(program);
                            resourceDetails.setProgram(program);
                        }
                        if (passingYearDao.findByName(rowMap.get(columnNamesList.get(12))) != null) {
                            resourceDetails.setPassingYear(passingYearDao.findByName(rowMap.get(columnNamesList.get(12))));
                        } else {
                            passingYear = new PassingYear();
                            passingYear.setPassingYear(rowMap.get(columnNamesList.get(12)).toUpperCase());
                            passingYear.setAddedBy("Excel Upload");
                            passingYearDao.save(passingYear);
                            resourceDetails.setPassingYear(passingYear);
                        }
                        resourceDetails.setCGPA(
                                "NA".equals(rowMap.get(columnNamesList.get(13))) ? new Double(0) : Double.parseDouble(rowMap.get(columnNamesList.get(13)))
                        );

                        try {
                            resourceDetails.setAirRank(
                                    "NA".equals(rowMap.get(columnNamesList.get(14))) ? new Integer(0) : Integer.parseInt(rowMap.get(columnNamesList.get(14)))
                            );
                        } catch (NumberFormatException nfe){
                            resourceDetails.setOtherRank(rowMap.get(columnNamesList.get(14)));
                        }

                        resourceDetails.setAreaOfExpertise(rowMap.get(columnNamesList.get(15)));
                        resourceDetails.setSkills(rowMap.get(columnNamesList.get(16)));
                        resourceDetails.setDesignation(rowMap.get(columnNamesList.get(17)));
                        resourceDetails.setCompany(rowMap.get(columnNamesList.get(18)));
                        if ("NA".equals(rowMap.get(columnNamesList.get(19)))) {
                            resourceDetails.setExperience(0);
                        } else {
                            String experience = rowMap.get(columnNamesList.get(19));
                            if(experience.indexOf("~") != -1) {
                                experience = experience.replaceAll("~", "");
                            }
                            if(experience.indexOf("+") != -1) {
                                experience = experience.substring(0, experience.indexOf("+"));
                            }
                            if(experience.indexOf(" ") != -1) {
                                experience = experience.substring(0, experience.indexOf(" "));
                            }
                            resourceDetails.setExperience(Integer.parseInt(experience.trim()));
                        }
                        resourceDetails.setFixedCTC(rowMap.get(columnNamesList.get(20)));
                        resourceDetails.setVariableCTC(rowMap.get(columnNamesList.get(21)));
                        if("NA".equals(rowMap.get(columnNamesList.get(22)))) {
                            resourceDetails.setNoticePeriod(0);
                        } else {
                            String noticePeriod = rowMap.get(columnNamesList.get(22));
                            noticePeriod = noticePeriod.toUpperCase().trim();
                            if(noticePeriod.indexOf(" ") != -1) {
                                noticePeriod = noticePeriod.substring(0, noticePeriod.indexOf(" "));
                            }
                            if(noticePeriod.indexOf("M") != -1) {
                                noticePeriod = noticePeriod.substring(0, noticePeriod.indexOf("M"));
                            }
                            resourceDetails.setNoticePeriod(Double.parseDouble(noticePeriod));
                        }


                        if (locationDao.findByName(rowMap.get(columnNamesList.get(23))) != null) {
                            resourceDetails.setCurrentLocation(locationDao.findByName(rowMap.get(columnNamesList.get(23))));
                        } else {
                            location = new Location();
                            location.setLocationName(rowMap.get(columnNamesList.get(23)).toUpperCase());
                            location.setAddedBy("Excel Upload");
                            locationDao.save(location);
                            resourceDetails.setCurrentLocation(location);
                        }

                        if (locationDao.findByName(rowMap.get(columnNamesList.get(24))) != null) {
                            resourceDetails.setPreferredLocation(locationDao.findByName(rowMap.get(columnNamesList.get(24))));
                        } else {
                            location = new Location();
                            location.setLocationName(rowMap.get(columnNamesList.get(24)).toUpperCase());
                            location.setAddedBy("Excel Upload");
                            locationDao.save(location);
                            resourceDetails.setPreferredLocation(location);
                        }

                        resourceDetails.setExpectedCTC(rowMap.get(columnNamesList.get(26)));

                        resourceDetails.setLinkedinProfile(rowMap.get(columnNamesList.get(27)));
                        resourceDetails.setAddedBy("Excel Upload");
                        resourceDetails.setAddedDate(new Date());
                        logger.info("Persisting resource to database {}", resourceDetails.toString());
                        resourceDao.save(resourceDetails);
                        noOfRecordsPersisted++;
                    } catch (Exception e) {
                        logger.error("Error while persisting Resource {}", resourceDetails.toString(), e);
                    }
                }
            }
        }
        return noOfRecordsPersisted;
    }
}
