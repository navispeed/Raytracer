package eu.navispeed.raytracer
package light

import tool.Vector3D

abstract class Light(val intensity: Double) {
  def illuminate(hitPoint: Vector3D): Color
}
