package eu.navispeed.raytracer
package tool

import gameobject.SceneObject
import light.Light

class Scene(val objectList: List[SceneObject] = List(), val lights: List[Light] = List()) {

  def add(gameObject: SceneObject): Scene = {
    new Scene(gameObject :: objectList, lights)
  }
  def add(light: Light): Scene = {
    new Scene(objectList, light :: lights)
  }

  def intersect(ray: Ray): Option[Intersection] = {
    objectList.flatMap(_.intersect(ray)) // Vérifie l'intersection pour chaque objet
      .minByOption(_.t) // Sélectionne la plus proche intersection
  }

}
