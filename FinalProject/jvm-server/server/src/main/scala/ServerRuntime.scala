// package Server

// class ServerRuntime { 
//   val Instance: ServerRuntime = new ServerRuntime()
  
//   private var serverTick: Long = _
//   private var maxServerTickRate: Int = _
//   private var realtimeSimulationSpeed: Float = _
//   private var simulationSpeed: Float = _
  
//   def Initialize(networkRuntimeInstance: NetworkRuntime, maxServerTickRate: Int, simulationSpeed: Int): Unit = {
//     this.simulationSpeed = simulationSpeed
//     this.maxServerTickRate = maxServerTickRate
//     this.realtimeSimulationSpeed = if (simulationSpeed < 0) 1f / maxServerTickRate else 1f / maxServerTickRate * simulationSpeed
    
//     this.networkRuntimeInstance = networkRuntimeInstance
    
//     ServerUniverseGeneration.StarSystemManager.GenerateStarSystem()
    
//     networkRuntimeInstance.NetworkManager.OnClientConnectedCallback += OnClientConnect
//   }
  
//   def OnClientConnect(conn: Long): Unit = {
//     Debug.Log("Client connection attepted with ID: {" + conn + "}. Requesting client information...")
    
//     networkRuntimeInstance.AwaitHandshakeClientRpc(networkRuntimeInstance.GenerateClientRPCParams(conn))
//   }
  
//   def ClientDataServerRpc(callbackID: String, serverRpcParams: ServerRpcParams = default): Unit = {
//     val id = ClientManager.GenerateNewClientInstance(serverRpcParams.Receive.SenderClientId)
//     if (id.isDefined) {
//       Debug.Log("[Info] Client response valid, new player added with UUID: " + id.get)
//     }
//   }
  
//   def InstantiateNewPlayerServerRpc(serverRpcParams: ServerRpcParams = default): Unit = {
//     Debug.Log("HERE IS YOUR NEW PLAYER")
//   }
  
//   def KickClient(connID: Long): Unit = {
//     networkRuntimeInstance.NetworkManager.DisconnectClient(connID)
//   }
// }
