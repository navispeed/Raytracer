package eu.navispeed.raytracer
package light

import tool.Vector3D

case class DirectionalLight(direction: Vector3D, override val intensity: Double, color: Color) extends Light(intensity) {
  def illuminate(hitPoint: Vector3D): Color = {
    val lightDirection = direction.normalize()
    val lightIntensity = intensity * color.toRGB

    val dotProduct = lightDirection dot hitPoint.normalize()
    val contribution = dotProduct * lightIntensity

    Color(contribution, contribution, contribution)
  }
}
