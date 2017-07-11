#version 320 es

precision mediump float;

in vec3 light_direction_cameraspace;
in vec3 vertex_normal_cameraspace;

out vec4 fragment_color;

void main() {
    vec3 fragment_light_direction = normalize(light_direction_cameraspace);
    vec3 fragment_normal = normalize(vertex_normal_cameraspace);
    float cosTheta = clamp(dot(fragment_light_direction, fragment_normal), 0.0, 1.0);

    fragment_color.xyz =  vec3(1.0, 1.0, 1.0) * cosTheta;
}

