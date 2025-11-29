#version 330 core


out vec4 FragColor;
in vec2 fragtexel;

uniform sampler2D sample;


void main(){
    FragColor = texture(sample,fragtexel)*vec4(3.0f,1.0f,1.0f,1.0f);
}