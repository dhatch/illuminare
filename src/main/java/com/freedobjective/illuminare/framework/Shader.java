package com.freedobjective.illuminare.framework;

import java.io.*;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL21.*;

public class Shader {
	private int shaderType;
	private String shaderLocation;
	private String shaderContents;
	private int shaderID;
	
	public Shader(String shaderInfo, int shaderType, boolean fromFile) {
		this.shaderType = shaderType;
		if (fromFile) {
			this.shaderLocation = shaderInfo;
		} else {
			this.shaderContents = shaderInfo;
		}
	}
	
	public Shader(String shaderLocation, int shaderType) {
		this(shaderLocation, shaderType, true);
	}
	
	public String getShaderContents() throws IOException {
		if (shaderContents == null) {
			BufferedReader reader = new BufferedReader(new FileReader(shaderLocation));
			shaderContents = "";
			while (reader.ready()) {
				shaderContents += reader.readLine()+"\n";
			}
			
		}
		return shaderContents;
	}
	
	public void setShaderContents(String shader) {
		shaderContents = shader;
	}
	
	public int compileShader() throws IOException {
		shaderID = glCreateShader(shaderType);
		glShaderSource(shaderID, getShaderContents());
		
		glCompileShader(shaderID);
		
		int compileStatus = glGetShader(shaderID, GL_COMPILE_STATUS);
		
		if (compileStatus == 0) {
    		int infoLogLength = glGetShader(shaderID, GL_INFO_LOG_LENGTH);

    		String strInfoLog = glGetShaderInfoLog(shaderID, infoLogLength);

    		String strShaderType = null;
			switch (shaderType) {
			case GL_VERTEX_SHADER:
				strShaderType = "vertex";
				break;
//			case GL_GEOMETRY_SHADER:
//				strShaderType = "geometry";
//				break;
			case GL_FRAGMENT_SHADER:
				strShaderType = "fragment";
				break;
			}
			// TODO make an exception for this
    		System.err.printf("Compile failure in %s shader:\n%s\n", strShaderType, strInfoLog);
    		System.exit(0);
		}
		
		return shaderID;
	}
	
	public void deleteShader() {
		glDeleteShader(shaderID);
		shaderID = 0;
	}
	
	public int getShaderID() {
		return shaderID;
	}
	
	public static String getShaderVersion() {
		return glGetString(GL_SHADING_LANGUAGE_VERSION);
	}
}
