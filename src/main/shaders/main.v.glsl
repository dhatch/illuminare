#version 150

uniform mat4 cameraToClipTransform;
uniform mat4 modelToCameraTransform;

layout(location = 0) in vec4 position;
void main()
{
    gl_Position = cameraToClipTransform * (modelToCameraTransform * position);
}
