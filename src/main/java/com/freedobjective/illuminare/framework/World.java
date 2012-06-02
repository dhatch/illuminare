package com.freedobjective.illuminare.framework;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.lwjgl.util.vector.Vector2f;

import com.freedobjective.illuminare.framework.coordinates.CoordinateSystem;

/**
 * @author dhatch
 * Contains all the RenderGroups which exist in the world.
 */
public class World {
	private Map<String, RenderGroup> groups;
	private Camera camera;
	private CoordinateSystem coordinateSystem;
	
	public World(ArrayList<RenderGroup> groups, Vector2f cameraDimensions) {
		this.groups = new HashMap<String, RenderGroup>();
		setGroups(groups);
		coordinateSystem = CoordinateSystem.createCoordinateSystem();
		camera = new Camera(this, cameraDimensions);
	}

	public ArrayList<RenderGroup> getGroups() {
		return new ArrayList<RenderGroup>(groups.values());
	}

	public void setGroups(ArrayList<RenderGroup> groups) {
		for (RenderGroup g: groups) {
			this.groups.put(g.getGroupID(), g);
			g.setWorld(this);
		}
	}
	
	public void addGroup(RenderGroup group) {
		groups.put(group.getGroupID(), group);
		group.setWorld(this);
	}
	
	public void removeGroup(RenderGroup group) {
		groups.remove(group.getGroupID());
		group.setWorld(null);
	}
	
	public RenderGroup getGroup(String groupID) {
		return groups.get(groupID);
	}
	
	public Camera getCamera() {
		return camera;
	}

	public void setCamera(Camera camera) {
		this.camera = camera;
	}

	public CoordinateSystem getCoordinateSystem() {
		return coordinateSystem;
	}

	public void setCoordinateSystem(CoordinateSystem coordinateSystem) {
		this.coordinateSystem = coordinateSystem;
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
