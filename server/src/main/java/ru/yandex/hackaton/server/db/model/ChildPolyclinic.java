package ru.yandex.hackaton.server.db.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Sergey Polovko
 */
@Entity
@Table(name = "child_polyclinic")
public class ChildPolyclinic extends DistrictInfo {
}
