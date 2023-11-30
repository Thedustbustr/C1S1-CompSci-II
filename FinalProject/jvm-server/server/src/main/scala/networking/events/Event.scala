package networking.events

import util.math.Vector3
import scodec.Attempt

sealed trait Event(val eventNumber: EventNumber)

object Event {
  final case class PositionEvent(position: Vector3) extends Event(EventNumber.Position)
}

enum EventNumber(val num: Short) {
    case Position extends EventNumber(1)
}