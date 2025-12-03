#version 330 core

layout (location=0) in vec3 apos;

out vec3 fragColor;

uniform vec3 ipos;
uniform vec3 iscale;
uniform float irot;
uniform vec3 icolor;


uniform mat4 view;
uniform mat4 projection;
void main(){
    vec3 finalPosition;
    finalPosition.x = apos.x * cos(irot) - apos.y * sin(irot);
    finalPosition.y = apos.x * sin(irot) + apos.y * cos(irot);
    finalPosition *= iscale;
    finalPosition += ipos;
    gl_Position = projection*view*vec4(finalPosition,1.0f);    
    fragColor = icolor;
}