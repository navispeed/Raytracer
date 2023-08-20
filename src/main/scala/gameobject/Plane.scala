package eu.navispeed.raytracer
package gameobject

import tool.{Intersection, Ray, Vector3D}

case class Plane(point: Vector3D, normal: Vector3D) extends SceneObject {
  private val EPSILON = 1e-6

  override def intersect(ray: Ray): Option[Intersection] = {
    val denominator = ray.direction dot normal
    if (denominator > EPSILON) {
      val t = (point - ray.origin) dot normal / denominator
      if (t >= 0) {
        val intersectionPoint = ray.origin + ray.direction * t
        Some(Intersection(intersectionPoint, normal, t))
      } else {
        None
      }
    } else {
      None
    }
  }
}