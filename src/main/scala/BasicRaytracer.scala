package eu.navispeed.raytracer

import gameobject.{Box, Plane, Sphere}
import tool.{Intersection, Ray, Scene, Vector3D}

import com.sun.javafx.scene.AmbientLightHelper.AmbientLightAccessor
import eu.navispeed.raytracer.light.{AmbientLight, DirectionalLight, SpotLight}

import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

class BasicRaytracer(height: Int, width: Int, scene: Scene, camera: Camera, maxDepth: Int) {
  private val image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)
  private val graphics = image.createGraphics()
  private val EPSILON = 1e-5

  def render(): Unit = {
    for (y <- 0 until height) {
      for (x <- 0 until width) {
        val ray = calculateRayForPixel(x, y)
        val color = traceRay(ray, depth = 0)
        image.setRGB(x, y, color.toRGB)
      }
    }
  }

  def calculateRayForPixel(x: Int, y: Int): Ray = {
    val aspectRatio = width.toDouble / height.toDouble
    val viewPlaneWidth = 2.0 * math.tan(camera.fov / 2.0)
    val viewPlaneHeight = viewPlaneWidth / aspectRatio

    val normalizedX = (x.toDouble + 0.5) / width.toDouble
    val normalizedY = (y.toDouble + 0.5) / height.toDouble

    Ray(camera.position, camera.calculateRayDirection(normalizedX, normalizedY, viewPlaneWidth, viewPlaneHeight)
  }

  def calculateReflectedRay(incidentRay: Ray, intersection: Intersection): Ray = {
    val normal = intersection.normal.normalize()
    val reflectionDirection = incidentRay.direction - (normal * (2.0 * (incidentRay.direction dot normal)))

    Ray(intersection.position + reflectionDirection * EPSILON, reflectionDirection)
  }

  def traceRay(ray: Ray, depth: Int): Color = {
    if (depth >= maxDepth) {
      return Color.black // Limite de profondeur atteinte, renvoyer une couleur noire
    }

    val intersectionOpt = scene.intersect(ray)
    intersectionOpt match {
      case Some(hit) =>
        // Calculez la couleur éclairée en utilisant illuminate pour chaque source de lumière
        val illuminatedColor = scene.lights.foldLeft(Color.black) { (accColor, light) =>
          accColor + light.illuminate(hit.position)
        }

        // Calculez la couleur réfléchie en générant un rayon réfléchi et en le tracant
        val reflectedRay = calculateReflectedRay(ray, hit)
        val reflectedColor = traceRay(reflectedRay, depth + 1)

        // Combinez les couleurs éclairées et réfléchies pour obtenir la couleur finale
        illuminatedColor + reflectedColor

      case None =>
        Color.black // Aucune intersection, renvoyer une couleur noire
    }
  }

  def saveImage(filename: String): Unit = {
    val outputFile = new File(filename)
    ImageIO.write(image, "png", outputFile)
  }

}


object BasicRaytracerApp {
  def main(args: Array[String]): Unit = {
    val width = 800
    val height = 600

    val scene = new Scene()
      .add(new Sphere(Vector3D(-2, 0, -5), 1))
      .add(new Sphere(Vector3D(2, 0, -10), 1))
      .add(Box(Vector3D(5, 2, -10), Vector3D(0, 5, -10), Vector3D(90,0,0)))
      .add(AmbientLight(Color(255, 255, 255)))
      .add(DirectionalLight(Vector3D(2, 2, 0), 3, Color.blue))
//      .add(DirectionalLight(Vector3D(-2, -2, 0), 10, Color.red))
      .add(Plane(Vector3D(0, 0, -15), Vector3D(0, 0, 1)))

    val camera = new Camera(Vector3D(0, 0, 0), Vector3D(0, 0, -1), math.Pi / 2.0)

    val raytracer = new BasicRaytracer(width, height, scene, camera, 5)
    raytracer.render()
    raytracer.saveImage("output.png")
  }
}