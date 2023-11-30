package networking.events

import scodec.*
import scodec.codecs.*
import scodec.bits.hex
import networking.events.Event.PositionEvent
import scodec.bits.ByteVector
import fs2.interop.scodec.StreamDecoder
import fs2.text.hex
import networking.codecs.{given, *}
import scodec.bits.BitVector

final case class PayloadHeader(eventNumber: EventNumber)

object PayloadHeader {

  private def handleConsumeError(err: String) = Attempt.failure(Err(err))
  private def decodeData(arr: BitVector, data: BitVector)(using eventNumberDecoder: Decoder[EventNumber]) = {
    if (arr.take(16).bytes != hex"FFFE" || arr.takeRight(16).bytes != hex"FEFF") { Attempt.failure(Err("Invalid header bytes")) }
    else {
      eventNumberDecoder
        .decode(arr.drop(16).take(16) ++ data)
        .map(_.map(PayloadHeader.apply))
    }
  }

  given (using Decoder[EventNumber]): Decoder[PayloadHeader] = Decoder { vector =>
    vector.consumeThen(48)(handleConsumeError, decodeData)
  }

  given eventEncoder: Encoder[PayloadHeader] = Encoder { event =>
    ???
  }
}
