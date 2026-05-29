#version 150

uniform sampler2D DiffuseSampler;
uniform sampler2D depth;

uniform mat4 FlashLightInvProj;
uniform mat4 InvViewMat;
uniform float Brightness;
uniform vec3 Position;
uniform vec3 Direction;
uniform vec3 LightColor;
uniform float Range;
uniform float Radius;
uniform float RadiusOuter;

vec3 toneMap(vec3 hdrColor, float intensity) {
    return hdrColor / (hdrColor + vec3(intensity));
}

vec3 lerp(vec3 a, vec3 b, float t) {
    return a + t * (b - a);
}
in vec2 texCoord;

out vec4 fragColor;

// Function to calculate world position from depth
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


void main() {
    vec3 fragPos = WorldPosFromDepth(texture(depth,texCoord).r);

vec3 lightDir = Position - fragPos;
float theta = dot(normalize(lightDir), normalize(-Direction));
if(theta > RadiusOuter)
{
float distanceToLight = length(lightDir);
float epsilon   = Radius - RadiusOuter;
float intensity = clamp(pow((theta - RadiusOuter) / epsilon, 3.0), 0.0, 1.0);

//float attenuation = 1.0 / (distanceToLight * distanceToLight);

float distanceFactor = 1.0 - clamp(distanceToLight / Range, 0.0, 1.0);
    vec4 pixelColor = texture(DiffuseSampler, texCoord);

    vec3 finalColor = pixelColor.rgb * (LightColor * intensity * distanceFactor  * Brightness);


    finalColor = toneMap(finalColor , 2);

    fragColor = vec4(pixelColor + vec4(finalColor,1));
    } else {
        fragColor = texture(DiffuseSampler, texCoord);
        //fragColor = vec4(1,1,1,1);
    }
}