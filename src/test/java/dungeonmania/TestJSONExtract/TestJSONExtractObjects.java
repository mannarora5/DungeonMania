package dungeonmania.TestJSONExtract;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import java.lang.IllegalArgumentException;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import dungeonmania.JSONExtract;
import dungeonmania.Goals.GoalComponent;




// Class tests the first three functions in JSONExtract Class where they
// extract the .json files given into java JSONObject


public class TestJSONExtractObjects {

    @Test
    @DisplayName("Test Illegal dungeon name given")
    public void testIllegalDungeon(){

        String dungeonName = "hello";

       assertThrowsExactly(IllegalArgumentException.class, () -> {JSONExtract.extractEntitiesJSON(dungeonName);});

       assertThrowsExactly(IllegalArgumentException.class, () -> {JSONExtract.extractGoalsJSON(dungeonName);});
               
    }

    @Test
    @DisplayName("Test Illegal config name given")
    public void testIllegalConfig(){

        String config = "hello";
        assertThrowsExactly(IllegalArgumentException.class, () -> {JSONExtract.extractConfigJSON(config);});

    }


    @Test
    @DisplayName("Test Correct entities extracted from dungeon")
    public void testCorrectEntities(){

        JSONArray test = JSONExtract.extractEntitiesJSON("d_bombTest_placeBombRadius2");

        assertEquals("switch", test.getJSONObject(2).get("type"));
        assertEquals(4, test.getJSONObject(2).get("x"));
        assertEquals(2, test.getJSONObject(2).get("y"));


        assertEquals("treasure", test.getJSONObject(7).get("type"));
        assertEquals(5, test.getJSONObject(7).get("x"));
        assertEquals(3, test.getJSONObject(7).get("y"));

        assertEquals("treasure", test.getJSONObject(8).get("type"));
        assertEquals(4, test.getJSONObject(8).get("x"));
        assertEquals(5, test.getJSONObject(8).get("y"));

    }

    @Test
    @DisplayName("Test Correct configs extracted from config file")
    public void testCorrectConfigs(){

        JSONObject test = JSONExtract.extractConfigJSON("c_battleTests_basicMercenaryMercenaryDies");

        assertEquals(37, test.length());
        assertEquals(1, test.get("bomb_radius"));
        assertEquals(1, test.get("enemy_goal"));
        assertEquals(5, test.get("spider_attack"));
    }



    @Test
    @DisplayName("Test goal list for JSON extract")
    public void testSimpleGoal() {
        JSONObject testgoals = JSONExtract.extractGoalsJSON("d_battleTest_basicMercenary");
        List<GoalComponent> goals = JSONExtract.createGoalClasses(testgoals);
        assertEquals(1,goals.size());
        assertEquals(":exit", goals.get(0).toString());
        
    }

    @Test
    @DisplayName("Test Complex goal from JSON extract")
    public void testComplexGoal() {
        JSONObject testgoals = JSONExtract.extractGoalsJSON("d_complexGoalsTest_andAll");
        List<GoalComponent>goals = JSONExtract.createGoalClasses(testgoals);
        assertEquals(1, goals.size());
        assertEquals(":exit && :treasure && :boulders && :enemies", goals.get(0).toString());
    }

    @Test
    @DisplayName("Test Complex goal OR from JSON extract")
    public void testComplexGoalOR() {
        JSONObject testgoals = JSONExtract.extractGoalsJSON("d_complexGoalsTest_orAll");
        List<GoalComponent>goals = JSONExtract.createGoalClasses(testgoals);
        assertEquals(1, goals.size());
        assertEquals("((:exit || :treasure) || (:boulders || :enemies))", goals.get(0).toString());
    }

    @Test
    @DisplayName("SIngle AND goal")
    public void singleANDGoal() {
        JSONObject testgoals = JSONExtract.extractGoalsJSON("d_singleandgoal");
        List<GoalComponent>goals = JSONExtract.createGoalClasses(testgoals);
        assertEquals(1, goals.size());
        System.out.println(goals);
        assertEquals(":enemies && :treasure", goals.get(0).toString());
    }
    
}
