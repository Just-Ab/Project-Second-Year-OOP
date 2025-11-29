#version 330 core

layout (location=0) in vec3 apos;

out vec3 fragColor;

uniform mat4 model;
uniform mat4 view;
uniform mat4 projection;
uniform vec3 color;
void main(){
    gl_Position = projection*view*model*vec4(apos,1.0f);
    fragColor = color;
}