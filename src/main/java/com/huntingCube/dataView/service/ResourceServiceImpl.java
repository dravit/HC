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
                ResourceHistoryDetails resourceHistoryDetails = new ResourceHistoryDetails();
                ClientHistory clientHistory = new ClientHistory();
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
                        Position position = null;
                        ClientStatus clientStatus = null;
                        Client client = null;
                        Date addedDate = HuntingCubeUtility.convertToDBDate(
                                rowMap.get(columnNamesList.get(0)), rowMap.get(columnNamesList.get(1)), rowMap.get(columnNamesList.get(2)));
                        resourceDetails.setName(rowMap.get(columnNamesList.get(6)));
                        resourceDetails.setContactNumber(rowMap.get(columnNamesList.get(7)));
                        if ("NA".equals(rowMap.get(columnNamesList.get(8)))) {
                            continue;
                        }
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
                        resourceDetails.setCGPA(rowMap.get(columnNamesList.get(13)));

                        resourceDetails.setAirRank(rowMap.get(columnNamesList.get(14)));

                        resourceDetails.setAreaOfExpertise(rowMap.get(columnNamesList.get(15)));
                        resourceDetails.setSkills(rowMap.get(columnNamesList.get(16)));
                        resourceDetails.setDesignation(rowMap.get(columnNamesList.get(17)));
                        resourceDetails.setCompany(rowMap.get(columnNamesList.get(18)));
                        resourceDetails.setExperience(rowMap.get(columnNamesList.get(19)));
                        resourceDetails.setFixedCTC(rowMap.get(columnNamesList.get(20)));
                        resourceDetails.setVariableCTC(rowMap.get(columnNamesList.get(21)));
                        resourceDetails.setNoticePeriod(rowMap.get(columnNamesList.get(22)));

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
                        resourceDetails.setAddedBy("NA".equals(rowMap.get(columnNamesList.get(27))) ? "Excel Upload" : rowMap.get(columnNamesList.get(27)));
                        resourceDetails.setAddedDate(addedDate);
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
