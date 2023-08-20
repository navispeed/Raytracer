package eu.navispeed.raytracer

import tool.Vector3D

import scala.math.{cos, sin}

object Angle {
  def toNormal(anglesInDegrees: Vector3D): Vector3D = {
    // Vecteur d'angles en degrés
    val anglesInDegrees = Vector3D(30, 45, 60)

    // Convertir les angles en radians
    val anglesInRadians = anglesInDegrees.mapDegreesToRadians

    // Calculer les composantes du vecteur normal
    val normal = Vector3D(
      cos(anglesInRadians.x) * sin(anglesInRadians.y),
      sin(anglesInRadians.x) * sin(anglesInRadians.y),
      cos(anglesInRadians.y)
    )

    // Normaliser le vecteur pour obtenir une normale unitaire
    normal.normalize()
  }
}
