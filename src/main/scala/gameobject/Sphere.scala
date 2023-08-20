package eu.navispeed.raytracer
package gameobject

import tool.{Intersection, Ray, Vector3D}

class Sphere(val center: Vector3D, val radius: Double) extends SceneObject {
  // check https://en.wikipedia.org/wiki/Line%E2%80%93sphere_intersection
  override def intersect(ray: Ray): Option[Intersection] = {
    val oc = ray.origin - center
    val a = ray.direction.dot(ray.direction)
    val b = 2.0 * oc.dot(ray.direction)
    val c = oc.dot(oc) - radius * radius
    val discriminant = b * b - 4 * a * c
    if (discriminant > 0) {
      val t = (-b - math.sqrt(discriminant)) / (2.0 * a)
      if (t > 0) {
        val hitPoint = ray.origin + ray.direction * t
        val normal = (hitPoint - center).normalize()
        Some(Intersection(hitPoint, normal, t))
      } else None
    } else None
  }
}
