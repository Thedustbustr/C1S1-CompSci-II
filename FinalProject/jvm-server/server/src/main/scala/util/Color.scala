package util

import io.circe.Encoder
import io.circe.generic.semiauto.*

final case class Color(
    r: Int,
    g: Int,
    b: Int,
    a: Int = 1
)

object Color {
  implicit val encoder: Encoder[Color] = deriveEncoder
}
