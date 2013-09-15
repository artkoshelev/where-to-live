package ru.yandex.hackaton.server.db.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Sergey Polovko
 */
@Entity
@Table(name = "districtssummary")
public class DistrictsSummary extends BaseModel<Integer> {

    @Id
    @Column(name = "districtid")
    private Integer id;

    @Column
    private String districtname;

    @Column
    private Integer childpolyclinic;

    @Column
    private Integer childteethpolyclinic;

    @Column
    private Integer citypolyclinic;

    @Column
    private Integer dispensary;

    @Column
    private Integer drugs;

    @Column
    private Integer elementaries;

    @Column
    private Integer hospitals;

    @Column
    private Integer nightschools;

    @Column
    private Integer parks;

    @Column
    private Integer preschools;

    @Column
    private Integer fountains;

    @Column
    private Integer wifi;

    @Column
    private Integer shops;

    @Column
    private Integer highschools;

    @Column
    private Integer piknik;

    @Column
    private Integer busstops;

    @Column
    private Integer libraries;

    @Column
    private BigDecimal summ;

    @Column
    private Integer size;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDistrictid() {
        return id;
    }

    public void setDistrictid(Integer id) {
        this.id = id;
    }

    public String getDistrictname() {
        return districtname;
    }

    public void setDistrictname(String districtname) {
        this.districtname = districtname;
    }

    public Integer getChildpolyclinic() {
        return childpolyclinic;
    }

    public void setChildpolyclinic(Integer childpolyclinic) {
        this.childpolyclinic = childpolyclinic;
    }

    public Integer getChildteethpolyclinic() {
        return childteethpolyclinic;
    }

    public void setChildteethpolyclinic(Integer childteethpolyclinic) {
        this.childteethpolyclinic = childteethpolyclinic;
    }

    public Integer getCitypolyclinic() {
        return citypolyclinic;
    }

    public void setCitypolyclinic(Integer citypolyclinic) {
        this.citypolyclinic = citypolyclinic;
    }

    public Integer getDispensary() {
        return dispensary;
    }

    public void setDispensary(Integer dispensary) {
        this.dispensary = dispensary;
    }

    public Integer getDrugs() {
        return drugs;
    }

    public void setDrugs(Integer drugs) {
        this.drugs = drugs;
    }

    public Integer getElementaries() {
        return elementaries;
    }

    public void setElementaries(Integer elementaries) {
        this.elementaries = elementaries;
    }

    public Integer getHospitals() {
        return hospitals;
    }

    public void setHospitals(Integer hospitals) {
        this.hospitals = hospitals;
    }

    public Integer getNightschools() {
        return nightschools;
    }

    public void setNightschools(Integer nightschools) {
        this.nightschools = nightschools;
    }

    public Integer getParks() {
        return parks;
    }

    public void setParks(Integer parks) {
        this.parks = parks;
    }

    public Integer getPreschools() {
        return preschools;
    }

    public void setPreschools(Integer preschools) {
        this.preschools = preschools;
    }

    public Integer getFountains() {
        return fountains;
    }

    public void setFountains(Integer fountains) {
        this.fountains = fountains;
    }

    public Integer getWifi() {
        return wifi;
    }

    public void setWifi(Integer wifi) {
        this.wifi = wifi;
    }

    public Integer getShops() {
        return shops;
    }

    public void setShops(Integer shops) {
        this.shops = shops;
    }

    public Integer getHighschools() {
        return highschools;
    }

    public void setHighschools(Integer highschools) {
        this.highschools = highschools;
    }

    public Integer getPiknik() {
        return piknik;
    }

    public void setPiknik(Integer piknik) {
        this.piknik = piknik;
    }

    public Integer getBusstops() {
        return busstops;
    }

    public void setBusstops(Integer busstops) {
        this.busstops = busstops;
    }

    public Integer getLibraries() {
        return libraries;
    }

    public void setLibraries(Integer libraries) {
        this.libraries = libraries;
    }

    public BigDecimal getSumm() {
        return summ;
    }

    public void setSumm(BigDecimal summ) {
        this.summ = summ;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

}
