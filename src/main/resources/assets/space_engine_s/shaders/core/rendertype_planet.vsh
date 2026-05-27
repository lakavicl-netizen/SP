#version 150

in vec3 Position;
in vec4 Color;
in vec3 Normal;

out vec4 vertexColor;
out vec3 vNormal;

uniform mat4 ProjMat;
uniform mat4 ModelViewMat;
uniform vec3 LightDirection;

void main() {
    gl_Position = ProjMat * ModelViewMat * vec4(Position, 1.0);
    vertexColor = Color;
    vNormal = Normal;
}
