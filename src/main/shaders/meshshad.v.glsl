#version 150

uniform mat4 cameraToClipTransform;
uniform mat4 modelToCameraTransform;

layout(location = 0) in vec4 position;
layout(location = 1) in vec4 color;

smooth out vec4 fragColor;

void main()
{
    gl_Position = cameraToClipTransform * (modelToCameraTransform * position);
    fragColor = color;
}
