package eu.navispeed.raytracer
package tool

import org.apache.commons.math3.linear.{MatrixUtils, RealMatrix}

object Matrix3D {
  def identity() = MatrixUtils.createRealIdentityMatrix(3)

  def rotationMatrixFromEuler(rotation: Vector3D): RealMatrix = {
    val (roll, pitch, yaw) = rotation.toRadiansTuple

    val cosRoll = math.cos(roll)
    val sinRoll = math.sin(roll)
    val cosPitch = math.cos(pitch)
    val sinPitch = math.sin(pitch)
    val cosYaw = math.cos(yaw)
    val sinYaw = math.sin(yaw)

    val m00 = cosYaw * cosPitch
    val m01 = -sinYaw * cosRoll + cosYaw * sinPitch * sinRoll
    val m02 = sinYaw * sinRoll + cosYaw * sinPitch * cosRoll
    val m10 = sinYaw * cosPitch
    val m11 = cosYaw * cosRoll + sinYaw * sinPitch * sinRoll
    val m12 = -cosYaw * sinRoll + sinYaw * sinPitch * cosRoll
    val m20 = -sinPitch
    val m21 = cosPitch * sinRoll
    val m22 = cosPitch * cosRoll

    MatrixUtils.createRealMatrix(Array(
      Array(m00, m01, m02),
      Array(m10, m11, m12),
      Array(m20, m21, m22)
    ))
  }
}