#version 330

layout (location=0) in vec2 position;
layout (location=1) in vec3 color;

out vec3 outColor;

uniform ivec2 viewportSize;

void main()
{
    gl_Position = vec4((position / viewportSize), 0.0, 1.0);
    outColor = color;
}