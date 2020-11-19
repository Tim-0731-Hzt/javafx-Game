package test;

import org.json.JSONArray;
import org.json.JSONObject;

public class GetJsonObject {
  public static JSONObject getAdvanced() {
    JSONObject jsonString = new JSONObject();
    JSONArray entities = getEntitiesAdvanced();
    JSONObject goal = getGoal();
    jsonString.put("width", 18);
    jsonString.put("height", 16);
    jsonString.put("entities", entities);
    jsonString.put("goal-condition", goal);
    return jsonString;
  }

  private static JSONArray getEntitiesAdvanced() {
    JSONArray entities = new JSONArray();
    JSONObject jsonString = new JSONObject();
    jsonString.put("x", 0);
    jsonString.put("y", 0);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 1);
    jsonString.put("y", 0);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 2);
    jsonString.put("y", 0);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 3);
    jsonString.put("y", 0);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 4);
    jsonString.put("y", 0);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 5);
    jsonString.put("y", 0);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 5);
    jsonString.put("y", 0);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 6);
    jsonString.put("y", 0);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 7);
    jsonString.put("y", 0);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 8);
    jsonString.put("y", 0);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 9);
    jsonString.put("y", 0);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 10);
    jsonString.put("y", 0);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 11);
    jsonString.put("y", 0);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 12);
    jsonString.put("y", 0);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 13);
    jsonString.put("y", 0);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 14);
    jsonString.put("y", 0);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 15);
    jsonString.put("y", 0);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 16);
    jsonString.put("y", 0);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 17);
    jsonString.put("y", 0);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 0);
    jsonString.put("y", 1);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 1);
    jsonString.put("y", 1);
    jsonString.put("type", "player");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 1);
    jsonString.put("y", 6);
    jsonString.put("id", 0);
    jsonString.put("type", "portal");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 2);
    jsonString.put("y", 7);
    jsonString.put("id", 0);
    jsonString.put("type", "key");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 7);
    jsonString.put("y", 8);
    jsonString.put("id", 0);
    jsonString.put("type", "door");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 13);
    jsonString.put("y", 10);
    jsonString.put("id", 0);
    jsonString.put("type", "portal");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 13);
    jsonString.put("y", 11);
    jsonString.put("type", "treasure");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 6);
    jsonString.put("y", 1);
    jsonString.put("type", "sword");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 17);
    jsonString.put("y", 1);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 0);
    jsonString.put("y", 2);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 3);
    jsonString.put("y", 2);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 6);
    jsonString.put("y", 2);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 7);
    jsonString.put("y", 2);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 8);
    jsonString.put("y", 2);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 9);
    jsonString.put("y", 2);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 12);
    jsonString.put("y", 2);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 13);
    jsonString.put("y", 2);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 14);
    jsonString.put("y", 2);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 15);
    jsonString.put("y", 2);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 17);
    jsonString.put("y", 2);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 0);
    jsonString.put("y", 3);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 3);
    jsonString.put("y", 3);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 4);
    jsonString.put("y", 3);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 15);
    jsonString.put("y", 3);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 17);
    jsonString.put("y", 3);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 0);
    jsonString.put("y", 4);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 8);
    jsonString.put("y", 4);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 17);
    jsonString.put("y", 4);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 0);
    jsonString.put("y", 5);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 3);
    jsonString.put("y", 5);
    jsonString.put("type", "enemy");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 8);
    jsonString.put("y", 5);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 17);
    jsonString.put("y", 5);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 0);
    jsonString.put("y", 6);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 6);
    jsonString.put("y", 6);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 8);
    jsonString.put("y", 6);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 9);
    jsonString.put("y", 6);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 13);
    jsonString.put("y", 6);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 17);
    jsonString.put("y", 6);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 0);
    jsonString.put("y", 7);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 6);
    jsonString.put("y", 7);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 12);
    jsonString.put("y", 7);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 13);
    jsonString.put("y", 7);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 17);
    jsonString.put("y", 7);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 0);
    jsonString.put("y", 8);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 3);
    jsonString.put("y", 8);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 4);
    jsonString.put("y", 8);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 5);
    jsonString.put("y", 8);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 6);
    jsonString.put("y", 8);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 8);
    jsonString.put("y", 8);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 9);
    jsonString.put("y", 8);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 10);
    jsonString.put("y", 8);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 13);
    jsonString.put("y", 8);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 17);
    jsonString.put("y", 8);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 0);
    jsonString.put("y", 9);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 9);
    jsonString.put("y", 9);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 13);
    jsonString.put("y", 9);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 14);
    jsonString.put("y", 9);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 17);
    jsonString.put("y", 9);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 0);
    jsonString.put("y", 10);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 3);
    jsonString.put("y", 10);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 5);
    jsonString.put("y", 10);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 6);
    jsonString.put("y", 10);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 7);
    jsonString.put("y", 10);
    jsonString.put("type", "treasure");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 11);
    jsonString.put("y", 10);
    jsonString.put("type", "invincibility");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 17);
    jsonString.put("y", 10);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 0);
    jsonString.put("y", 11);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 3);
    jsonString.put("y", 11);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 6);
    jsonString.put("y", 11);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 8);
    jsonString.put("y", 11);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 9);
    jsonString.put("y", 11);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 10);
    jsonString.put("y", 11);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 12);
    jsonString.put("y", 11);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 17);
    jsonString.put("y", 11);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 0);
    jsonString.put("y", 12);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 3);
    jsonString.put("y", 12);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 9);
    jsonString.put("y", 12);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 12);
    jsonString.put("y", 12);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 17);
    jsonString.put("y", 12);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 0);
    jsonString.put("y", 13);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 3);
    jsonString.put("y", 13);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 4);
    jsonString.put("y", 13);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 5);
    jsonString.put("y", 13);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 12);
    jsonString.put("y", 13);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 13);
    jsonString.put("y", 13);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 14);
    jsonString.put("y", 13);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 17);
    jsonString.put("y", 13);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 0);
    jsonString.put("y", 14);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 17);
    jsonString.put("y", 14);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 0);
    jsonString.put("y", 15);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 1);
    jsonString.put("y", 15);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 2);
    jsonString.put("y", 15);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 3);
    jsonString.put("y", 15);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 4);
    jsonString.put("y", 15);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 5);
    jsonString.put("y", 15);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 6);
    jsonString.put("y", 15);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 7);
    jsonString.put("y", 15);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 8);
    jsonString.put("y", 15);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 9);
    jsonString.put("y", 15);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 10);
    jsonString.put("y", 15);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 11);
    jsonString.put("y", 15);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 12);
    jsonString.put("y", 15);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 13);
    jsonString.put("y", 15);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 14);
    jsonString.put("y", 15);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 15);
    jsonString.put("y", 15);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 16);
    jsonString.put("y", 15);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    jsonString = new JSONObject();
    jsonString.put("x", 17);
    jsonString.put("y", 15);
    jsonString.put("type", "wall");
    entities.put(jsonString);
    return entities;
  }

  static JSONObject getGoal() {
    JSONObject jsonString = new JSONObject();
    JSONArray subgoals = getSubgoals();
    jsonString.put("goal", "AND");
    jsonString.put("subgoals", subgoals);
    return jsonString;
  }

  private static JSONArray getSubgoals() {
    JSONArray subgoals = new JSONArray();
    JSONObject jsonString1 = new JSONObject();
    jsonString1.put("goal", "enemies");
    JSONObject jsonString2 = new JSONObject();
    jsonString2.put("goal", "treasure");
    subgoals.put(jsonString1);
    subgoals.put(jsonString2);
    return subgoals;
  }

  static JSONObject getComplexGoal() {
    JSONObject jsonString = new JSONObject();
    JSONArray subgoals = new JSONArray();
    JSONObject jsonString1 = new JSONObject();
    jsonString1.put("goal", "treasure");
    JSONObject jsonString2 = new JSONObject();
    jsonString2.put("goal", "boulders");
    subgoals.put(jsonString1);
    subgoals.put(jsonString2);

    jsonString.put("goal", "OR");
    jsonString.put("subgoals", subgoals);

    JSONObject jsonString3 = new JSONObject();
    JSONArray subgoals2 = new JSONArray();
    JSONObject jsonString4 = new JSONObject();
    jsonString4.put("goal", "exit");
    subgoals2.put(jsonString);
    subgoals2.put(jsonString4);

    jsonString3.put("goal", "AND");
    jsonString3.put("subgoals", subgoals2);
    return jsonString3;
  }

  static JSONObject getExitGoal() {
    JSONObject json = new JSONObject();
    json.put("goal", "exit");
    return json;
  }

  static JSONObject getBoulderGoal() {
    JSONObject json = new JSONObject();
    json.put("goal", "boulders");
    return json;
  }

  static JSONObject getTreasureGoal() {
    JSONObject json = new JSONObject();
    json.put("goal", "treasure");
    return json;
  }

  static JSONObject getEnemyGoal() {
    JSONObject json = new JSONObject();
    json.put("goal", "enemies");
    return json;
  }

}