package com.freedobjective.illuminare.framework;

import java.io.IOException;
import java.util.ArrayList;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL21.*;

public class Program {
	private ArrayList<Shader> shaders;
	private int programID;
	
	public Program(ArrayList<Shader> shaders, ArrayList<Program> includedPrograms) {
		this.shaders = shaders;
		for (Program p: includedPrograms) {
			includeProgram(p);
		}
	}
	
	public Program(ArrayList<Shader> shaders) {
		this(shaders, new ArrayList<Program>());
	}
	
	public void addShader(Shader s) {
		shaders.add(s);
	}
	
	public void removeShader(Shader s) {
		shaders.remove(s);
	}
	
	public ArrayList<Shader> getShaders() {
		return shaders;
	}
	
	public void setShaders(ArrayList<Shader> shaders) {
		this.shaders = shaders; 
	}
	
	public void includeProgram(Program p) {
		shaders.addAll(p.getShaders());
	}
	
	// TODO possibly add decompile method

	public int compileProgram() throws IOException {
		for (Shader shader: shaders) {
			shader.compileShader();
		}
		
		programID = glCreateProgram();

		for (Shader shader : shaders) {
			glAttachShader(programID, shader.getShaderID());
		}

		glLinkProgram(programID);

		int status = glGetProgram(programID, GL_LINK_STATUS);
		if (status == 0) {
			int infoLogLength = glGetProgram(programID, GL_INFO_LOG_LENGTH);

			String strInfoLog = null;
			strInfoLog = glGetProgramInfoLog(programID, infoLogLength);

			System.err.printf("Linker failure: %s\n", strInfoLog);
			System.exit(0);
		}

		for (Shader shader : shaders) {
			glDetachShader(programID, shader.getShaderID());
		}
		
		for (Shader shader: shaders) {
			shader.deleteShader();
		}
		
		return programID;
	}
	
	public void enable() {
		glUseProgram(programID);
	}
	
	public void disable() {
		glUseProgram(0);
	}
	
	public boolean isCompiled() {
		return programID != 0;
	}
	
	public void destroy() {
		glDeleteProgram(programID);
	}
}
