#version 150

uniform sampler2D Sampler0;
uniform vec3 LightDirection;
uniform vec3 SkyColor;
uniform vec4 ColorModulator;

in vec2 texCoord;
in vec4 vertexColor;
in vec3 vNormal;

out vec4 fragColor;

void main() {
    vec4 texColor = texture(Sampler0, texCoord);
    vec3 n = normalize(vNormal);
    float ndotl = max(dot(n, -normalize(LightDirection)), 0.0);
    float lighting = clamp(0.15 + ndotl, 0.0, 1.0);
    vec3 lit = texColor.rgb * lighting + SkyColor * (1.0 - lighting) * 0.3;
    fragColor = vec4(lit, texColor.a) * vertexColor * ColorModulator;
}
