#version 150

uniform mat4 cameraToClipTransform;
uniform mat4 modelToCameraTransform;

layout(location = 0) in vec4 position;
layout(location = 1) in vec2 texturePos;

smooth out vec2 texturePosition;

void main()
{
    gl_Position = cameraToClipTransform * (modelToCameraTransform * position);
    texturePosition = texturePos;
}
