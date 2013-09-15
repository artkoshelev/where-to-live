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
@Table(name = "categories")
public class Category extends BaseModel<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "categoriesGen")
    @SequenceGenerator(name = "categoriesGen", sequenceName = "category_id")
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String searchparam;

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

    public String getSearchparam() {
        return searchparam;
    }

    public void setSearchparam(String searchparam) {
        this.searchparam = searchparam;
    }
}
