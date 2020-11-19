package unsw.dungeon.goals;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import unsw.dungeon.Entities.Dungeon;

/**
 * a goal class
 * composite pattern
 */
public class Goal {
	private GoalStrategy goal;

	public boolean achieved(Dungeon dungeon) {
		if (dungeon.isPlayerAlive()) return goal.achieved(dungeon);
		else {
			return false;
		}
	}

	public Goal(JSONObject json) {
		this.goal = getGoal(json);
	}

	/**
	 * load goal from a JSONObject
	 * @param json the src
	 * @return the goal of this game
	 */
	public GoalStrategy getGoal(JSONObject json) {
		String goal = json.getString("goal");

		GoalStrategy g = null;
		switch (goal) {
			case "AND":
				g = new GoalAnd();
				break;
			case "OR":
				g = new GoalOr();
				break;
			case "exit":
				g = new GoalExit();
				break;
			case "enemies":
				g = new GoalEnemy();
				break;
			case "boulders":
				g = new GoalBoulder();
				break;
			case "treasure":
				g = new GoalTreasure();
				break;
		}

		try {
			JSONArray subgoals = json.getJSONArray("subgoals");
			if (subgoals != null) {
				addSubgoal(g, subgoals);
			}
		} catch (JSONException e) {
			// e.printStackTrace();
		}

		return g;
	}

	public void addSubgoal(GoalStrategy g, JSONArray json) {
		for (Object sub : json) {
			g.addGoal(getGoal((JSONObject) sub));
		}
	}

	@Override
	public String toString() {
		return this.goal.toString();
	}

}