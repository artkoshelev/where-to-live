package ru.yandex.hackaton.server.resources;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: eoff
 * Date: 14.09.13
 * Time: 20:37
 * To change this template use File | Settings | File Templates.
 */
public class SearchParams {
    private Map<String, Double> params;

    public Map<String, Double> getParams() {
        return params;
    }

    public void setParams(Map<String, Double> params) {
        this.params = params;
    }
}