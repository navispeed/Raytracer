package eu.navispeed.raytracer
package light

import tool.Vector3D

case class SpotLight(position: Vector3D, direction: Vector3D, angle: Double,
                     override val intensity: Double, color: Color) extends Light(intensity) {
  def illuminate(hitPoint: Vector3D): Color = {
    val lightDirection = (position - hitPoint).normalize()
    val cosTheta = lightDirection.dot(direction)

    if (cosTheta < math.cos(math.toRadians(angle / 2.0))) {
      // Le point n'est pas à l'intérieur de l'angle d'éclairage de la lumière spot.
      Color.black
    } else {
      val distance = (position - hitPoint).length
      val attenuation = 1.0 / (distance * distance)  // Atténuation quadratique

      val intensityFactor = cosTheta / (distance * distance)
      val finalIntensity = intensity * intensityFactor * attenuation

      // La couleur résultante est la couleur de la lumière multipliée par l'intensité.
      color * finalIntensity
    }
  }
}
