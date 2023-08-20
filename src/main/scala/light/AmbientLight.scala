package eu.navispeed.raytracer
package light

import tool.Vector3D

case class AmbientLight(color: Color) extends Light(0) {
  def illuminate(hitPoint: Vector3D): Color = color
}
