#version 150

uniform sampler2D DiffuseSampler;
uniform sampler2D depth;

uniform mat4 FlashLightInvProj;
uniform mat4 InvViewMat;
uniform float Brightness;
uniform float Linear;
uniform float Quadratic;
uniform vec3 Position;
uniform vec3 LightColor;


in vec2 texCoord;

out vec4 fragColor;

// Function to calculate world position from depth
vec3 toneMap(vec3 hdrColor, float intensity) {
    return hdrColor / (hdrColor + vec3(intensity));
}
vec3 WorldPosFromDepth(float depth) {
    // Convert depth from [0, 1] range to view-space z range [-1, 1]
    float z = depth * 2.0 - 1.0;

    // Convert from screen-space coordinates to clip space
    vec4 clipSpacePosition = vec4(texCoord * 2.0 - 1.0, z, 1.0);

    // Transform clip space to view space
    vec4 viewSpacePosition = FlashLightInvProj * clipSpacePosition;

    // Perspective divide to get normalized view space position
    viewSpacePosition /= viewSpacePosition.w;

    // Transform view space position to world space
    vec4 worldSpacePosition = InvViewMat * viewSpacePosition;

    return worldSpacePosition.xyz;
}

float calcDiffuse(vec3 lightPos, vec3 fragPos) {
    vec3 lightDir = normalize(lightPos - fragPos);  // Calculate direction from fragment to light
    return max(dot(lightDir, vec3(0.0, 1.0, 0.0)), 0.0);  // Use a fixed "up" direction for diffuse calculation
}

void main() {
    vec3 fragPos = WorldPosFromDepth(texture(depth,texCoord).r);

    float dist = length(fragPos - Position);

    float attenuation = 1.0 / (1 + Linear * dist +
  			     Quadratic * (dist * dist));

    vec4 pixelColor = texture(DiffuseSampler, texCoord);

    vec3 finalColor = pixelColor.rgb * (LightColor * attenuation * Brightness);
    finalColor = toneMap(finalColor , 2);
    fragColor = vec4(pixelColor + vec4(finalColor,1));
}