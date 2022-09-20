#version 330

layout (location=0) in vec2 position;
layout (location=1) in vec3 color;

out vec3 outColor;

uniform vec2 viewportSize;
uniform vec2 transform;

void main()
{
    gl_Position = vec4((position + transform) / viewportSize, 0.0, 1.0);
    outColor = color;
}