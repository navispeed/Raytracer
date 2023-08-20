package eu.navispeed.raytracer
package tool

case class Vector3D(x: Double, y: Double, z: Double) {
  def +(other: Vector3D): Vector3D = Vector3D(x + other.x, y + other.y, z + other.z)

  def -(other: Vector3D): Vector3D = Vector3D(x - other.x, y - other.y, z - other.z)

  def *(scalar: Double): Vector3D = Vector3D(x * scalar, y * scalar, z * scalar)

  def /(scalar: Double): Vector3D = Vector3D(x / scalar, y / scalar, z / scalar)

  def dot(other: Vector3D): Double = x * other.x + y * other.y + z * other.z

  def cross(other: Vector3D): Vector3D = Vector3D(
    y * other.z - z * other.y,
    z * other.x - x * other.z,
    x * other.y - y * other.x
  )

  def length: Double = math.sqrt(x * x + y * y + z * z)

  def normalize(): Vector3D = {
    val len = length
    if (len > 0) {
      Vector3D(x / len, y / len, z / len)
    } else {
      this
    }
  }

  def toRadiansTuple: (Double, Double, Double) = {
    (math.toRadians(x), math.toRadians(y), math.toRadians(z))
  }

  def mapDegreesToRadians: Vector3D = {
    Vector3D(math.toRadians(x), math.toRadians(y), math.toRadians(z))
  }

  def negate: Vector3D = {
    Vector3D(-x, -y, -z)
  }
}
