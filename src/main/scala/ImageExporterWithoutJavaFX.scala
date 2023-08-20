package eu.navispeed.raytracer

import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

object ImageExporterWithoutJavaFX {
  def exportImageToPNG(image: BufferedImage, outputPath: String): Unit = {
    val outputFile = new File(outputPath)
    ImageIO.write(image, "png", outputFile)
  }

  def main(args: Array[String]): Unit = {
    val imageWidth = 800
    val imageHeight = 600

    // Assume que vous avez déjà généré l'image dans un BufferedImage
    val bufferedImage = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB)
    // Remplissez le bufferedImage avec les données de votre image
    val graphics = bufferedImage.createGraphics()

    val squareSize = 100
    graphics.fillRect((imageWidth - squareSize) / 2, (imageHeight - squareSize) / 2, squareSize, squareSize)

    graphics.dispose()

    val outputPath = "output.png"
    exportImageToPNG(bufferedImage, outputPath)
  }
}
