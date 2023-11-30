package networking
package codecs

import scodec.codecs.*
import util.math.Vector3
import networking.events.Event.PositionEvent
import scodec.Codec
import networking.events.EventNumber
import scodec.Decoder
import scodec.Attempt
import scodec.Err

given Decoder[EventNumber] = short16L.emap(__decodeEventNumber)
given vector3Codec: Codec[Vector3] = (doubleL :: doubleL :: doubleL).xmap(Vector3.apply, v => (v.x, v.y, v.z))
given Codec[PositionEvent] = vector3Codec.xmap(PositionEvent.apply, _.position)

def __decodeEventNumber(num: Short): Attempt[EventNumber] =
  num match {
    case EventNumber.Position.num => Attempt.successful(EventNumber.Position)
    case _                        => Attempt.failure(Err(s"Unrecognized event number: $num"))
  }
