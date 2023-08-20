package eu.navispeed.raytracer


case class Color(r: Int, g: Int, b: Int) {
  def toRGB: Int = (clamp(r, 0, 255) << 16) | (clamp(g, 0, 255) << 8) | clamp(b, 0, 255)

  def +(other: Color): Color = Color(r + other.r, g + other.g, b + other.b)

  def -(other: Color): Color = Color(r - other.r, g - other.g, b - other.b)

  def *(scalar: Double): Color = Color((r * scalar).toInt, (g * scalar).toInt, (b * scalar).toInt)

  private def clamp(value: Int, min: Int, max: Int): Int = {
    if (value < min) min
    else if (value > max) max
    else value
  }

}

object Color {
  def apply(r: Double, g: Double, b: Double): Color = Color(r.toInt, g.toInt, b.toInt)

  def black: Color = Color(0, 0, 0)

  def white: Color = Color(255, 255, 255)

  def red: Color = Color(255, 0, 0)

  def blue: Color = Color(0, 0, 255)
}