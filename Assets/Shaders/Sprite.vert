#version 330 core

layout (location = 0) in vec3 apos;
layout (location = 1) in vec3 ipos;
layout (location = 2) in vec3 iscale;
layout (location = 3) in float irot;
layout (location = 4) in vec4 iuv;
layout (location = 5) in vec3 icolor;

out vec2 fragtexel;
out vec4 fraguv;
out vec3 fragcolor;


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