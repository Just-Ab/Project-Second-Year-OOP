#version 330 core


out vec4 FragColor;
in vec2 fragtexel;
in vec4 fraguv;
in vec3 fragcolor;
uniform sampler2D sample;

void main(){
    vec2 finaltexel = fraguv.xy + fragtexel*fraguv.zw;
    FragColor = texture(sample,finaltexel)*vec4(fragcolor,1.0f);
}


