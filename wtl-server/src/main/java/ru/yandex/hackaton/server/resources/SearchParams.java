package ru.yandex.hackaton.server.resources;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: eoff
 * Date: 14.09.13
 * Time: 16:23
 * To change this template use File | Settings | File Templates.
 */
public class SearchParams {

    private Map<String, Integer> params;

    public Map<String, Integer> getParams() {
        return params;
    }

    public void setParams(Map<String, Integer> params) {
        this.params = params;
    }
}
