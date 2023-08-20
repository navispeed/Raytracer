package eu.navispeed.raytracer
package gameobject

import tool.{Intersection, Matrix3D, Ray, Vector3D}

case class Box(min: Vector3D, max: Vector3D, rotation: Vector3D) extends SceneObject {

  // TODO Rotation
  private val invRotation = rotation.mapDegreesToRadians.negate
  private val rotationMatrix = Matrix3D.rotationMatrixFromEuler(invRotation)


  override def intersect(ray: Ray): Option[Intersection] = {
    // Create a new transformed ray
    val t1 = (min.x - ray.origin.x) / ray.direction.x
    val t2 = (max.x - ray.origin.x) / ray.direction.x
    val t3 = (min.y - ray.origin.y) / ray.direction.y
    val t4 = (max.y - ray.origin.y) / ray.direction.y
    val t5 = (min.z - ray.origin.z) / ray.direction.z
    val t6 = (max.z - ray.origin.z) / ray.direction.z

    val tMin = math.max(math.max(math.min(t1, t2), math.min(t3, t4)), math.min(t5, t6))
    val tMax = math.min(math.min(math.max(t1, t2), math.max(t3, t4)), math.max(t5, t6))

    if (tMin > tMax) {
      None
    } else {
      val tHit = tMin
      val hitPoint = ray.origin + ray.direction * tHit
      Some(Intersection(hitPoint, Vector3D(0, 0, 0), tHit))
    }
  }
}