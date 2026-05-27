#version 150

in vec3 Position;
in vec2 UV0;
in vec4 Color;
in vec3 Normal;

uniform mat4 ProjMat;
uniform mat4 ModelViewMat;

out vec2 texCoord;
out vec4 vertexColor;
out vec3 vNormal;

void main() {
    gl_Position = ProjMat * ModelViewMat * vec4(Position, 1.0);
    texCoord = UV0;
    vertexColor = Color;
    vNormal = Normal;
}
