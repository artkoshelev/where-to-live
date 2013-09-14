package ru.yandex.hackaton.server.db.model;

import javax.persistence.*;

/**
 * @author Sergey Polovko
 */
@Entity
@Table(name = "districts_summary")
public class DistrictsSummary extends BaseModel<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "districtsGen")
    @SequenceGenerator(name = "districtsGen", sequenceName = "districts_id")
    private Integer id;

    @Column(nullable = false)
    private Integer districtid;

    @Column(nullable = false)
    private Integer child_polyclinic;

    @Column(nullable = false)
    private Integer child_teeth_polyclinic;

    @Column(nullable = false)
    private Integer city_polyclinic;

    @Column(nullable = false)
    private Integer dispensary;

    @Column(nullable = false)
    private Integer drugs;

    @Column(nullable = false)
    private Integer elementaries;

    @Column(nullable = false)
    private Integer hospitals;

    @Column(nullable = false)
    private Integer night_schools;

    @Column(nullable = false)
    private Integer parks;

    @Column(nullable = false)
    private Integer pre_schools;

    @Column(nullable = false)
    private Integer fountains;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDistrictid() {
        return districtid;
    }

    public void setDistrictid(Integer districtid) {
        this.districtid = districtid;
    }

    public Integer getChild_polyclinic() {
        return child_polyclinic;
    }

    public void setChild_polyclinic(Integer child_polyclinic) {
        this.child_polyclinic = child_polyclinic;
    }

    public Integer getChild_teeth_polyclinic() {
        return child_teeth_polyclinic;
    }

    public void setChild_teeth_polyclinic(Integer child_teeth_polyclinic) {
        this.child_teeth_polyclinic = child_teeth_polyclinic;
    }

    public Integer getCity_polyclinic() {
        return city_polyclinic;
    }

    public void setCity_polyclinic(Integer city_polyclinic) {
        this.city_polyclinic = city_polyclinic;
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

    public Integer getNight_schools() {
        return night_schools;
    }

    public void setNight_schools(Integer night_schools) {
        this.night_schools = night_schools;
    }

    public Integer getParks() {
        return parks;
    }

    public void setParks(Integer parks) {
        this.parks = parks;
    }

    public Integer getPre_schools() {
        return pre_schools;
    }

    public void setPre_schools(Integer pre_schools) {
        this.pre_schools = pre_schools;
    }

    public Integer getFountains() {
        return fountains;
    }

    public void setFountains(Integer fountains) {
        this.fountains = fountains;
    }
}
