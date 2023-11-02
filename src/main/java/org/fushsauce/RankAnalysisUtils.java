package org.fushsauce;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class RankAnalysisUtils {

    public static List<JsonObject> getJsonObjects(List<Path> paths, Gson gson)
        throws FileNotFoundException {
        List<JsonObject> jsonObjects = new ArrayList<>();
        for (Path path : paths) {
            JsonObject rankings = gson.fromJson(new FileReader(path.toFile()), JsonObject.class);
            jsonObjects.add(rankings);
        }
        return jsonObjects;
    }

    public static Map<String, Integer> getFinalRanks(List<JsonObject> jsonObjects) {
        Map<String, Integer> finalRanks = new TreeMap<>();
        for (String id : jsonObjects.get(0).keySet()) {
            List<Integer> givenRanks = new ArrayList<>(
                jsonObjects.stream().map(jo -> jo.get(id).getAsInt()).toList());
            int medianInt = getMedian(givenRanks);
            finalRanks.put(id, medianInt);
        }
        return finalRanks;
    }

    public static int getMedian(List<Integer> givenRanks) {
        Collections.sort(givenRanks);
        int n = givenRanks.size();
        if (n % 2 == 0) { // even
            // get two middle values
            int lhs = givenRanks.get((n / 2) - 1);
            int rhs = givenRanks.get((n / 2) + 1);
            return (lhs + rhs) / 2;
        } else { // odd
            return givenRanks.get(n / 2);
        }
    }
}
