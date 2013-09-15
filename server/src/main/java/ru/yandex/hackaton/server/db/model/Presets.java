package ru.yandex.hackaton.server.db.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * @author Sergey Polovko
 */
@Entity
@Table(name = "presets")
public class Presets extends BaseModel<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "districtsGen")
    @SequenceGenerator(name = "districtsGen", sequenceName = "districts_id")
    @Column(nullable = false)
    private Integer presetid;

    @Column(nullable = false)
    private String presetname;

    @Column(nullable = false)
    private Integer childpolyclinic;

    @Column(nullable = false)
    private Integer childteethpolyclinic;

    @Column(nullable = false)
    private Integer citypolyclinic;

    @Column(nullable = false)
    private Integer dispensary;

    @Column(nullable = false)
    private Integer drugs;

    @Column(nullable = false)
    private Integer elementaries;

    @Column(nullable = false)
    private Integer hospitals;

    @Column(nullable = false)
    private Integer nightschools;

    @Column(nullable = false)
    private Integer parks;

    @Column(nullable = false)
    private Integer preschools;

    @Column(nullable = false)
    private Integer fountains;

    @Column(nullable = false)
    private Integer wifi;

    @Column(nullable = false)
    private Integer shops;

    @Column(nullable = false)
    private Integer highschools;

    @Column(nullable = false)
    private Integer piknik;

    @Column(nullable = false)
    private Integer libraries;

    @Column(nullable = false)
    private Integer busstops;

    public Integer getPresetid() {
        return presetid;
    }

    public void setPresetid(Integer presetid) {
        this.presetid = presetid;
    }

    public String getPresetname() {
        return presetname;
    }

    public void setPresetname(String presetname) {
        this.presetname = presetname;
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

    public Integer getLibraries() {
        return libraries;
    }

    public void setLibraries(Integer libraries) {
        this.libraries = libraries;
    }

    public Integer getBusstops() {
        return busstops;
    }

    public void setBusstops(Integer busstops) {
        this.busstops = busstops;
    }

    @Override
    @JsonIgnore
    public Integer getId() {
        return presetid;
    }

    @Override
    @JsonIgnore
    public void setId(Integer integer) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
