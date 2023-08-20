package eu.navispeed.raytracer

import tool.Vector3D

class Camera(val position: Vector3D, val direction: Vector3D, val fov: Double) {
  val right: Vector3D = Vector3D(1, 0, 0)
  val up: Vector3D = Vector3D(0, 1, 0)

  // Calcul de la direction du rayon à partir des coordonnées normalisées du pixel sur l'écran
  def calculateRayDirection(normalizedX: Double, normalizedY: Double, viewPlaneWidth: Double, viewPlaneHeight: Double): Vector3D = {
    val viewPlaneX = (normalizedX - 0.5f) * viewPlaneWidth
    val viewPlaneY = (0.5f - normalizedY) * viewPlaneHeight

    val viewPlanePoint = position + direction + (right * viewPlaneX) + (up * viewPlaneY)
    (viewPlanePoint - position).normalize()
  }
}