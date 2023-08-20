package eu.navispeed.raytracer
package tool


case class Intersection(position: Vector3D, normal: Vector3D, t: Double, color: Color = Color.red)