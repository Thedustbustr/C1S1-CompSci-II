// package Server
// package Network

// import ServerPlayer._

// object ServerNetwork {
//   class ClientInstance {
//     var playerInstance: ServerPlayerInstance = _

//     def this() {
//       this()
//       generateNewPlayerInstance()
//     }

//     private def generateNewPlayerInstance(): Unit = {
//       val networkID = java.util.UUID.randomUUID()
//       val serverPlayerInstance = new ServerPlayerBuilder()
//         .setPosition(new Vector3(0, 0, 0))
//         .build()

//       this.playerInstance = serverPlayerInstance
//     }
//   }
// }
