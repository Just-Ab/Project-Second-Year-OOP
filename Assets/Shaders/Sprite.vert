#version 330 core

layout (location = 0) in vec3 apos;


out vec2 fragtexel;
out vec4 fraguv;
out vec3 fragcolor;

uniform vec3 ipos;
uniform vec3 iscale;
uniform float irot;
uniform vec3 icolor;
uniform vec4 iuv;


uniform mat4 projection;
uniform mat4 view;
void main(){
    vec3 finalPosition;
    finalPosition.x = apos.x * cos(irot) - apos.y * sin(irot);
    finalPosition.y = apos.x * sin(irot) + apos.y * cos(irot);
    finalPosition *= iscale;
    finalPosition += ipos;
    gl_Position = projection*view*vec4(finalPosition,1.0f);

    vec2 atexel = apos.xy+vec2(0.5f,0.5f);
    fragtexel = atexel;
    
    fraguv = iuv;
    fragcolor = icolor;
}