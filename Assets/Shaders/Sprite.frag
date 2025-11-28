#version 330 core


out vec4 FragColor;
in vec2 fragtexel;
in vec4 fraguv;

uniform sampler2D sample;

void main(){
    vec2 finaltexel = fraguv.xy + fragtexel*fraguv.zw;
    FragColor = texture(sample,finaltexel);
}


