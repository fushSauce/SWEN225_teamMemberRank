package org.fushsauce;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import static org.fushsauce.RankAnalysisUtils.*;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name = "GetRanks")
public class GetRanks implements Callable<Integer> {

    @Parameters
    List<Path> pathList;
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public Integer call() throws Exception {
        List<JsonObject> jsonObjects = getJsonObjects(pathList, gson);
        Map<String, Integer> finalRanks = getFinalRanks(jsonObjects);
        String json = gson.toJson(finalRanks);
        System.out.println(json);
        return 1;
    }


    public static void main(String[] args) {
        int execute = new CommandLine(new GetRanks()).execute(args);
        System.exit(execute);
    }
}

