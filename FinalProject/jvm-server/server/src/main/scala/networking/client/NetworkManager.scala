// package Server
// package Network

// import java.util.UUID
// import scala.collection.concurrent.TrieMap

// object NetworkManager {
//   private val usedNetworkIDs = TrieMap[UUID, Byte]()

//   def removeNetworkID(networkID: UUID): Unit =
//     if (!usedNetworkIDs.remove(networkID).isDefined)
//       Debug.Log(s"Cannot remove networkID, invalid networkID given: $networkID")

//   def getUniqueNetworkID: UUID = getID
  
//   def getID: UUID = {
//     val networkID = UUID.randomUUID()
//     if (!usedNetworkIDs.putIfAbsent(networkID, 0.toByte).isEmpty) getID
//     else networkID
//   }
// }
