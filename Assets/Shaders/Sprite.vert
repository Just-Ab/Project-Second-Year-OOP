#version 330 core

layout (location = 0) in vec3 apos;


out vec2 fragtexel;
out vec4 fraguv;
out vec3 fragcolor;
uniform vec4 uv;

uniform mat4 model;
uniform mat4 projection;
uniform mat4 view;
uniform vec3 color;
void main(){
    gl_Position = projection*view*model*vec4(apos,1.0f);
    vec2 atexel = apos.xy+vec2(0.5f,0.5f);
    fragtexel = atexel;
    fraguv = uv;
    fragcolor = color;
}