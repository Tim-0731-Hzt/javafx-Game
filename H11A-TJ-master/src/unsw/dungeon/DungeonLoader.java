package unsw.dungeon;

import unsw.dungeon.Entities.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import javafx.scene.image.Image;
import unsw.dungeon.goals.Goal;

/**
 * Loads a dungeon from a .json file.
 *
 * By extending this class, a subclass can hook into entity creation. This is
 * useful for creating UI elements with corresponding entities.
 *
 * @author Robert Clifton-Everest
 *
 */
public abstract class DungeonLoader {

    private JSONObject json;
    private String filename;
    public DungeonLoader(String filename) throws FileNotFoundException {
        json = new JSONObject(new JSONTokener(new FileReader("dungeons/" + filename)));
        this.filename = filename;
    }

    /**
     * Parses the JSON to create a dungeon.
     * 
     * @return
     */
    public Dungeon load() {

        int width = json.getInt("width");
        int height = json.getInt("height");

        Dungeon dungeon = new Dungeon(width, height);
        dungeon.setOpenDoorImage(new Image((new File("images/open_door.png")).toURI().toString()));
        dungeon.setPlayerWithSword(new Image((new File("images/playerWithSword.png")).toURI().toString()));
        Goal goal = new Goal(json.getJSONObject("goal-condition"));
        dungeon.setGoal(goal);
        dungeon.setMapFilename(filename);

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
                onLoad(player);
                entity = player;
                break;
            case "wall":
                Wall wall = new Wall(x, y);
                onLoad(wall);
                entity = wall;
                break;
            case "enemy":
                Enemy enemy = new Enemy(x, y, dungeon.getPlayer());
                onLoad(enemy);
                entity = enemy;
                break;
            case "treasure":
                Treasure treasure = new Treasure(x, y);
                onLoad(treasure);
                entity = treasure;
                break;
            case "sword":
                Sword sword = new Sword(x, y);
                onLoad(sword);
                entity = sword;
                break;
            case "invincibility":
                Invincibility invincibility = new Invincibility(x, y);
                onLoad(invincibility);
                entity = invincibility;
                break;
            case "portal":
                int id = json.getInt("id");
                Portal portal = new Portal(x, y, id);
                onLoad(portal);
                entity = portal;
                break;
            case "door":
                id = json.getInt("id");
                Door door = new Door(x, y, id);
                onLoad(door);
                entity = door;
                break;
            case "key":
                id = json.getInt("id");
                Key key = new Key(x, y, id);
                onLoad(key);
                entity = key;
                break;
            case "boulder":
                Boulder boulder = new Boulder(x, y);
                boulder.setDungeon(dungeon);
                onLoad(boulder);
                entity = boulder;
                break;
            case "switch":
                Switch s = new Switch(x, y);
                onLoad(s);
                entity = s;
                break;
            case "exit":
                Exit exit = new Exit(x, y);
                onLoad(exit);
                entity = exit;
                break;
            case "hound":
                Hound hound = new Hound(x, y, dungeon.getPlayer());
                onLoad(hound);
                entity = hound;
                break;
            case "gnome":
                Gnome gnome = new Gnome(x, y);
                onLoad(gnome);
                entity = gnome;
                break;
            case "bubbly":
                Bubbly bubbly = new Bubbly(x, y, dungeon.getPlayer());
                onLoad(bubbly);
                entity = bubbly;
                break;
        }
        dungeon.addEntity(entity);
    }

    public abstract void onLoad(Entity player);

    public abstract void onLoad(Wall wall);

    public abstract void onLoad(Enemy enemy);

    public abstract void onLoad(Treasure treasure);

    public abstract void onLoad(Sword sword);

    public abstract void onLoad(Invincibility invincibility);

    public abstract void onLoad(Portal portal);

    public abstract void onLoad(Door door);

    public abstract void onLoad(Key key);

    // TODO Create additional abstract methods for the other entities
    public abstract void onLoad(Boulder boulder);

    public abstract void onLoad(Switch s);

    public abstract void onLoad(Exit exit);

    public abstract void onLoad(Hound hound);

    public abstract void onLoad(Gnome gnome);

    public abstract void onLoad(Bubbly bubbly);
}
