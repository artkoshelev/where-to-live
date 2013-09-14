package ru.yandex.hackaton.server.db.model;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: artkoshelev
 * Date: 14.09.13
 * Time: 17:25
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "population")
public class Population extends BaseModel<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "populationGen")
    @SequenceGenerator(name = "populationGen", sequenceName = "population_id")
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer total;

    @Column(nullable = false)
    private Integer men;

    @Column(nullable = false)
    private Integer woman;

    @Column
    private Integer districtid;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getMen() {
        return men;
    }

    public void setMen(Integer men) {
        this.men = men;
    }

    public Integer getWoman() {
        return woman;
    }

    public void setWoman(Integer woman) {
        this.woman = woman;
    }

    public Integer getDistrictid() {
        return districtid;
    }

    public void setDistrictid(Integer districtid) {
        this.districtid = districtid;
    }
}
