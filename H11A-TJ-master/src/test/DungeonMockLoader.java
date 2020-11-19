package test;

import org.json.JSONObject;
import unsw.dungeon.Entities.Dungeon;

public class DungeonMockLoader extends DungeonLoader {

  public DungeonMockLoader(JSONObject json) {
    super(json);
  }

  public Dungeon getDungeon() {
    return load();
  }
}