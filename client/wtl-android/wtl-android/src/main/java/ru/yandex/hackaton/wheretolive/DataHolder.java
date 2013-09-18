package ru.yandex.hackaton.wheretolive;

import java.util.ArrayList;
import java.util.List;

import ru.yandex.hackaton.wheretolive.server.responses.RatingResponse;

/**
 * Created by rustamgaifullin on 9/15/13.
 */
public class DataHolder {
    private static List<RatingResponse> mDistricts = new ArrayList<RatingResponse>();

    public static List<RatingResponse> getDistricts() {
        return mDistricts;
    }
}
