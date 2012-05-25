package com.freedobjective.illuminare.framework;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 * @author dhatch
 * Contains all the RenderGroups which exist in the world.
 */
public class World {
	private Map<String, RenderGroup> groups;
	
	public World(ArrayList<RenderGroup> groups) {
		setGroups(groups);
	}

	public ArrayList<RenderGroup> getGroups() {
		return new ArrayList<RenderGroup>(groups.values());
	}

	public void setGroups(ArrayList<RenderGroup> groups) {
		for (RenderGroup g: groups) {
			this.groups.put(g.getGroupID(), g);
		}
	}
	
	public void addGroup(RenderGroup group) {
		groups.put(group.getGroupID(), group);
	}
	
	public void removeGroup(RenderGroup group) {
		groups.remove(group.getGroupID());
	}
	
	public RenderGroup getGroup(String groupID) {
		return groups.get(groupID);
	}
	
	public void init() throws IOException {
		for (RenderGroup g: groups.values()) {
			g.init();
		}
	}
	
	public void update(int delta) {
		for (RenderGroup g: groups.values()) {
			g.update(delta);
		}
	}
	
	public void render() {
		for (RenderGroup g: groups.values()) {
			g.render();
		}
	}
	
	public void destroy() {
		for (RenderGroup g: groups.values()) {
			g.destroy();
		}
	}
}
