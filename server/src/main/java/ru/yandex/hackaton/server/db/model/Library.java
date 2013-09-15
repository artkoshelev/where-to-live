package ru.yandex.hackaton.server.db.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Sergey Polovko
 */
@Entity
@Table(name = "libraries")
public class Library extends CategoryInfo {
}
