package test;

import org.json.JSONArray;
import org.json.JSONObject;
import unsw.dungeon.goals.Goal;
import unsw.dungeon.Entities.*;

public abstract class DungeonLoader {

    private JSONObject json;

    public DungeonLoader(JSONObject json) {
        this.json = json;
    }

    public Dungeon load() {

        int width = json.getInt("width");
        int height = json.getInt("height");

        Dungeon dungeon = new Dungeon(width, height);
        Goal goal = new Goal(json.getJSONObject("goal-condition"));
        dungeon.setGoal(goal);

        JSONArray jsonEntities = json.getJSONArray("entities");

        for (int i = 0; i < jsonEntities.length(); i++) {
            loadEntity(dungeon, jsonEntities.getJSONObject(i));
        }

        return dungeon;
    }

    private void loadEntity(Dungeon dungeon, JSONObject json) {
        String type = json.getString("type");
        int x = json.getInt("x");
        int y = json.getInt("y");

        Entity entity = null;

        switch (type) {
            case "player":
                Player player = new Player(dungeon, x, y);
                dungeon.setPlayer(player);
                entity = player;
                break;
            case "wall":
                Wall wall = new Wall(x, y);
                entity = wall;
                break;
            case "enemy":
                Enemy enemy = new Enemy(x, y, dungeon.getPlayer());
                entity = enemy;
                break;
            case "treasure":
                Treasure treasure = new Treasure(x, y);
                entity = treasure;
                break;
            case "sword":
                Sword sword = new Sword(x, y);
                entity = sword;
                break;
            case "invincibility":
                Invincibility invincibility = new Invincibility(x, y);
                entity = invincibility;
                break;
            case "portal":
                int id = json.getInt("id");
                Portal portal = new Portal(x, y, id);
                entity = portal;
                break;
            case "door":
                id = json.getInt("id");
                Door door = new Door(x, y, id);
                entity = door;
                break;
            case "key":
                id = json.getInt("id");
                Key key = new Key(x, y, id);
                entity = key;
                break;
            case "boulder":
                Boulder boulder = new Boulder(x, y);
                boulder.setDungeon(dungeon);
                entity = boulder;
                break;
            case "switch":
                Switch s = new Switch(x, y);
                entity = s;
                break;
            case "exit":
                Exit exit = new Exit(x, y);
                entity = exit;
                break;
        }
        dungeon.addEntity(entity);
    }
}