#version 320 es

in vec3 light_direction_cameraspace;
in vec3 vertex_normal_cameraspace;

out vec4 fragment_color;

void main() {
    float cosTheta = clamp(dot(light_direction_cameraspace, vertex_normal_cameraspace), 0.0, 1.0);

    fragment_color = cosTheta * vec4(1.0, 1.0, 1.0, 1.0);
}