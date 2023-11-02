import static org.junit.jupiter.api.Assertions.assertEquals;

import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import static org.fushsauce.RankAnalysisUtils.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestRankAnalysisUtils {

    @Test
    void testGetMedian() {
        List<Integer> givenRanks = new ArrayList<>(List.of(1,2,3));
        int median = getMedian(givenRanks);
        assertEquals(2,median);
    }

    @Test
    @DisplayName("Test that given id1 gets full range of ranks 1-5, meaning median rank for them is 3, we should see this in final ranks")
    void testGetFinalRanks() {
        List<JsonObject> jsonObjects = generateStudentRankings();
        Map<String, Integer> finalRanks = getFinalRanks(jsonObjects);
        assertEquals(3,finalRanks.get("id1"));
    }

    /**
     * Generate json objects representing student rankings where
     * id1 has been given full range of values (1-5) so median = 3,
     * everyone else has been given 5.
     * @return
     */
    private List<JsonObject> generateStudentRankings() {
        List<JsonObject> jsonObjectArrayList = new ArrayList<JsonObject>();
        for (int i = 1; i <= 5; i++) {
            JsonObject jsonObject = new JsonObject();
            for (int j = 1; j <= 5; j++) {
                jsonObject.addProperty("id"+j,5);
            }
            jsonObjectArrayList.add(jsonObject);
        }
        int index = 1;
        for (JsonObject jsonObject : jsonObjectArrayList) {
            jsonObject.addProperty("id1",index);
            index++;
        }
        return jsonObjectArrayList;
    }
}
