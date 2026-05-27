#version 150

in vec4 vertexColor;
in vec3 vNormal;

uniform vec3 LightDirection;
uniform vec3 SkyColor;
uniform vec4 ColorModulator;

out vec4 fragColor;

void main() {
    vec3 n = normalize(vNormal);
    float ndotl = max(dot(n, -normalize(LightDirection)), 0.0);

    ndotl = 1.0 - 1.0 / (ndotl * ndotl * 5.0 + 1.0);

    float ambient = 0.1;
    float lighting = clamp(ambient + ndotl, 0.0, 1.0);

    vec3 litColor = vertexColor.rgb * lighting;
    litColor += SkyColor * clamp(1.0 - lighting, 0.0, 1.0);

    fragColor = vec4(litColor * ColorModulator.rgb, vertexColor.a);
    gl_FragDepth = 1.0;
}
