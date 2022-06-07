uniform vec2 u_resolution;
uniform vec2 u_zoomCenter;
uniform float u_zoomSize;
uniform int u_maxIterations;

vec2 complexPow2(vec2 z) {
    return vec2(
        pow(z.x, 2.0) - pow(z.y, 2.0),
        2.0 * z.x * z.y
    );
}

vec2 calculateOneIteration(vec2 z, vec2 c) {
    return complexPow2(z) + c;
}

vec2 computeInitialFunctionValue() {
    vec2 uv = gl_FragCoord.xy / u_resolution;
    float aspectRatio = u_resolution.x / u_resolution.y;
    vec2 c = u_zoomCenter + (uv * 2.0 - vec2(1.0)) * u_zoomSize;
    c.y /= aspectRatio;
    return c;
}

int calculateLastIterationNumber(vec2 c) {
    vec2 z = vec2(0.0);
    int iterationNumber = 0;
    while(iterationNumber < u_maxIterations) {
        z = calculateOneIteration(z, c);
        if (length(z) > 2.0) {
            break;
        } else {
            iterationNumber++;
        }
    }
    return iterationNumber;
}

vec4 calculateColor(int lastIterationNumber) {
    const vec3 bias = vec3(0.02, 0.02, 0.03);
    const vec3 scale = vec3(0.1, 0.2, 0.3);
    const vec3 cosineOscillation = vec3(0.1, 0.6, 0.4);
    const vec3 phase = vec3(0.8, 0.8, 0.8);

    if (lastIterationNumber < u_maxIterations) {
        float paletteIndex = float(lastIterationNumber) / float(u_maxIterations);
        vec3 rgb = bias + scale * cos(6.28318 * (cosineOscillation * paletteIndex + phase));
        return vec4(rgb, 1.0);
    } else {
        return vec4(vec3(0.0), 1.0);
    }
}

void main() {
    vec2 c = computeInitialFunctionValue();
    int lastIterationNumber = calculateLastIterationNumber(c);
    gl_FragColor = calculateColor(lastIterationNumber);
}
