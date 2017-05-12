package com.huntingCube.dataView.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by dgup27 on 1/8/2017.
 */
@Entity
@Table(name = "RESOURCE_DETAILS")
public class ResourceDetails extends BaseEntity implements Serializable {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RESOURCE_ID")
    private int id;

    @NotEmpty
    @Column(name = "NAME")
    private String name;

    @Column(name = "CONTACT_NUMBER")
    private String contactNumber;

    @Column(name = "EMAIL")
    private String emailId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "INSTITUTE_ID", insertable = false, updatable = false)
    private Institute institute;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "STREAM_ID", insertable = false, updatable = false)
    private Stream stream;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PROGRAM_ID", insertable = false, updatable = false)
    private Program program;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PASSING_YEAR_ID", insertable = false, updatable = false)
    private PassingYear passingYear;

    @Column(name = "CGPA")
    private double CGPA;

    @Column(name = "AIR_RANK")
    private int airRank;

    @Column(name = "OTHER_RANK")
    private String otherRank;

    @Column(name = "AREA_EXPERTISE")
    private String areaOfExpertise;

    @Column(name = "SKILLS")
    private String skills;

    @Column(name = "DESIGNATION")
    private String designation;

    @Column(name = "COMPANY")
    private String company;

    @Column(name = "EXPERIENCE")
    private int experience;

    @Column(name = "FIXED_CTC")
    private String fixedCTC;

    @Column(name = "VARIABLE_CTC")
    private String variableCTC;

    @Column(name = "EXPECTED_CTC")
    private String expectedCTC;

    @Column(name = "NOTICE_PERIOD")
    private double noticePeriod;

    @OneToOne
    @JoinColumn(name = "CURRENT_LOCATION_ID", referencedColumnName = "LOCATION_ID", insertable = false, updatable = false)
    private Location currentLocation;

    @OneToOne
    @JoinColumn(name = "PREFERRED_LOCATION_ID", referencedColumnName = "LOCATION_ID", insertable = false, updatable = false)
    private Location preferredLocation;

    @Column(name = "LINKDIN_PROFILE")
    private String linkedinProfile;

    @Column(name = "FACEBOOK_PROFILE")
    private String facebookProfile;

    @Column(name = "ADDED_DATE")
    private Date addedDate;

    public String getLinkedinProfile() {
        return linkedinProfile;
    }

    public void setLinkedinProfile(String linkedinProfile) {
        this.linkedinProfile = linkedinProfile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public Institute getInstitute() {
        return institute;
    }

    public void setInstitute(Institute institute) {
        this.institute = institute;
    }

    public double getCGPA() {
        return CGPA;
    }

    public void setCGPA(double CGPA) {
        this.CGPA = CGPA;
    }

    public int getAirRank() {
        return airRank;
    }

    public void setAirRank(int airRank) {
        this.airRank = airRank;
    }

    public String getAreaOfExpertise() {
        return areaOfExpertise;
    }

    public void setAreaOfExpertise(String areaOfExpertise) {
        this.areaOfExpertise = areaOfExpertise;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public String getFixedCTC() {
        return fixedCTC;
    }

    public void setFixedCTC(String fixedCTC) {
        this.fixedCTC = fixedCTC;
    }

    public String getVariableCTC() {
        return variableCTC;
    }

    public void setVariableCTC(String variableCTC) {
        this.variableCTC = variableCTC;
    }

    public String getExpectedCTC() {
        return expectedCTC;
    }

    public void setExpectedCTC(String expectedCTC) {
        this.expectedCTC = expectedCTC;
    }

    public double getNoticePeriod() {
        return noticePeriod;
    }

    public void setNoticePeriod(double noticePeriod) {
        this.noticePeriod = noticePeriod;
    }

    public String getFacebookProfile() {
        return facebookProfile;
    }

    public void setFacebookProfile(String facebookProfile) {
        this.facebookProfile = facebookProfile;
    }

    public Stream getStream() {
        return stream;
    }

    public void setStream(Stream stream) {
        this.stream = stream;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    public PassingYear getPassingYear() {
        return passingYear;
    }

    public void setPassingYear(PassingYear passingYear) {
        this.passingYear = passingYear;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }

    public Location getPreferredLocation() {
        return preferredLocation;
    }

    public void setPreferredLocation(Location preferredLocation) {
        this.preferredLocation = preferredLocation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    public String getOtherRank() {
        return otherRank;
    }

    public void setOtherRank(String otherRank) {
        this.otherRank = otherRank;
    }

    @Override
    public String toString() {
        return "ResourceDetails{" +
                "name='" + name + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", emailId='" + emailId + '\'' +
                ", institute=" + institute +
                ", stream=" + stream +
                ", program=" + program +
                ", passingYear=" + passingYear +
                ", CGPA=" + CGPA +
                ", airRank=" + airRank +
                ", otherRank='" + otherRank + '\'' +
                ", areaOfExpertise='" + areaOfExpertise + '\'' +
                ", skills='" + skills + '\'' +
                ", designation='" + designation + '\'' +
                ", company='" + company + '\'' +
                ", experience=" + experience +
                ", fixedCTC='" + fixedCTC + '\'' +
                ", variableCTC='" + variableCTC + '\'' +
                ", noticePeriod=" + noticePeriod +
                ", currentLocation=" + currentLocation +
                ", preferredLocation=" + preferredLocation +
                ", linkedinProfile='" + linkedinProfile + '\'' +
                ", facebookProfile='" + facebookProfile + '\'' +
                ", addedDate=" + addedDate +
                '}';
    }
}
