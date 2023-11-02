# GetRanks - Automated Team member ranking process

## Use case

- Students submit their rankings in the form of a `json` file with the filename being their student id + json file extension.
- Markers can then download these to single directory and run `java -jar getRanks.jar <dirWithRankings>/*.json`
  which will output `json` of median ranks of team members to `stdout` 

## Demo

- Given a directory with students (`id1.json`,...,`id5.json`) that gave student `id1` a full range of ranks (1-5) with everyone else receiving 5,
we'd expect to see final ranks showing id1 having a median rank of 3 with everyone else having a final rank of 5.
- This can be seen [TestRankAnalysisUtils.java](./src/test/java/TestRankAnalysisUtils.java)'s `testGetFinalRanks()` method.
- Or can be manually tested using `GetRanks` picocli interface, using demo files in [](./src/test/resources)
which would look like `java -jar ./target/getRanks.jar ./src/test/resources/*.json` and would output 
```json
{
  "id1": 3,
  "id2": 5,
  "id3": 5,
  "id4": 5,
  "id5": 5
}
```
to `stdout`.
Output could easily be changed to other formats like xlsx, csv, or others by either parsing output or editing src and recompiling.






