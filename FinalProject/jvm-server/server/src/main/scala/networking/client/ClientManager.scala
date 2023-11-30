// package Server
// package Network

// import java.util.UUID
// import scala.collection.mutable.Map

// object ClientManager {
//   private val clientInstances = Map[UUID, ClientInstance]()

//   def generateNewClientInstance(connID: Long): Option[UUID] = {
//     if (checkClientIsValid(connID) == false) return None

//     val id = NetworkManger.getUniqueNetworkID()
//     clientInstances += (id -> new ClientInstance())

//     Some(id)
//   }

//   private def checkClientIsValid(connID: Long): Boolean = {
//     if (false) {
//       // ServerRuntime.Instance.KickClient(connID);
//       // return false;
//     }

//     true
//   }

//   def getClientInstance(id: UUID): ClientInstance = {
//     clientInstances(id)
//   }

//   def removeClientInstance(id: UUID): Unit = {
//     clientInstances -= id
//   }
// }
