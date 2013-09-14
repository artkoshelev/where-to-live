package ru.yandex.hackaton.server.geocoder;

import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created with IntelliJ IDEA.
 * User: artkoshelev
 * Date: 14.09.13
 * Time: 19:05
 * To change this template use File | Settings | File Templates.
 */
public class TransLiterator {
    public static String translitRustoEng(String str) {
        SortedMap map = translitGetMap();
        str = str.toLowerCase();
        String[] rus = str.split("");
        String key = "";
        String result = "";
        for(int i = 0; i<rus.length; i++) {
            key = rus[i];
            if(map.containsKey(key)) {
                result += map.get(key);
            } else {
                result += key;
            }
        }
        return result;
    }

    private static SortedMap translitGetMap() {
        SortedMap<String, String> map = new TreeMap<String,String>();
        String[] rus = alphabet_rus;
        String[] eng = alphabet_eng;
        for(int i = 0; i<rus.length; i++) {
            map.put(rus[i], eng[i]);
        }
        return map;
    }

    public static final String[] alphabet_rus = new String[]{
            "а","б","в","г","д","е","ё","ж","з","и","й","к","л","м","н","о","п",
            "р","с","т","у","ф","х","ц","ч","ш","щ","ъ","ы","ь","э","ю","я"
    };

    public static final String[] alphabet_eng = new String[]{
            "a","b","v","g","d","e","e","zh","z","i","y","k","l","m","n","o","p",
            "r","s","t","u","f","h","c","ch","sh","sch","","y","","e","yu","ya"
    };
}
