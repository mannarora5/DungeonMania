package dungeonmania.TestJSONExtract;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import java.lang.IllegalArgumentException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import dungeonmania.JSONExtract;




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
    @DisplayName("Test Correct goals extracted from dungeon")
    public void testCorrectGoals(){

    }
    @Test
    @DisplayName("Test Correct configs extracted from config file")
    public void testCorrectConfigs(){

        JSONObject test = JSONExtract.extractConfigJSON("c_battleTests_basicMercenaryMercenaryDies");

        assertEquals(22, test.length());
        assertEquals(1, test.get("bomb_radius"));
        assertEquals(1, test.get("enemy_goal"));
        assertEquals(5, test.get("spider_attack"));
    }




    
}
