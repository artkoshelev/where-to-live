package ru.yandex.hackaton.server.db.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Sergey Polovko
 */
@Entity
@Table(name = "parks")
public class Parks extends CategoryInfo {
}
