#version 320 es

layout(location = 0) vec3 vertex_position;
layout(location = 1) vec3 vertex_normal;

uniform mat4 mvp;
uniform mat4 m;
uniform mat4 v;
uniform mat4 p;

uniform vec3 light_position;

out vec3 light_direction_cameraspace;
out vec3 vertex_normal_cameraspace;

void main() {
    gl_Position = mvp * vertex_position;

    vec3 vertex_position_cameraspace = (v * m * vec4(vertex_position, 1.0)).xyz;
    vec3 light_position_cameraspace = (v * vec4(light_position, 1.0)).xyz;

    light_direction_cameraspace = normalize(vertex_position_cameraspace - light_position_cameraspace);
    vertex_normal_cameraspace = (v * m * vec4(vertex_normal, 1.0)).xyz;
}