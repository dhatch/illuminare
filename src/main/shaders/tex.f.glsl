#version 150

smooth in vec2 texturePosition;
uniform sampler2D textureSampler;

out vec4 outputColor;
void main() {
	outputColor = texture(textureSampler, texturePosition);
}
