package networking
package codecs

import networking.events.*
import scodec.*
import scodec.codecs.*
import scodec.bits.hex
import networking.events.Event.PositionEvent
import scodec.bits.ByteVector
import fs2.interop.scodec.StreamDecoder
import fs2.text.hex

given eventStreamDecoder: StreamDecoder[Event] = StreamDecoder.many(eventDecoder)

given eventDecoder(using headerDC: Decoder[PayloadHeader]): Decoder[Event] = Decoder { vector =>
  headerDC
    .decode(vector)
    .flatMap(data => 
      decodeBytes(data.value.eventNumber, data.remainder.bytes)
    )
}

given eventEncoder: Encoder[Event] = Encoder { event =>
  ???
}

private def decodeBytes(eventNumber: EventNumber, data: ByteVector): Attempt[DecodeResult[Event]] = {
  import EventNumber.*

  def __decode[A](using cdc: Codec[A]): Attempt[DecodeResult[A]] = cdc.decode(data.bits)

  eventNumber match {
    case Position => __decode[PositionEvent]
    // case Position => summon[Codec[PositionEvent]].decode(data.bits).toEither.left.map(_.messageWithContext)
  }
}
