package eu.navispeed.raytracer
package gameobject

import tool.{Intersection, Ray}

abstract class SceneObject() {

  def intersect(ray: Ray): Option[Intersection]
}
