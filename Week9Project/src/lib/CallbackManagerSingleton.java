package lib;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The callback manager manages any callback created in the program
 */
public class CallbackManagerSingleton {
  /**
   * The callback manager singleton
   */
  public static final CallbackManagerSingleton INSTANCE = new CallbackManagerSingleton();

  /** 
   * Returns instance of the callback manager singleton
   * @return CallbackManager
   */
  public static CallbackManagerSingleton getInstance() {
    return INSTANCE;
  }

  /**
  * Constructor
  */
  private CallbackManagerSingleton() {
    //
  }

  private Map<UUID, Callback<?>> activeCallbacks = new ConcurrentHashMap<>();

  /** 
   * Generates unique UUID
   * @return UUID
   */
  private UUID getUUID() {
    UUID id = UUID.randomUUID();

    if (activeCallbacks.containsKey(id)) {
      return getUUID();
    } else {
      return id;
    }
  }

  /** 
   * Registers a new callback to be tracked
   * @param callback The callback to be added
   * @param <T> The callbacks generic type
   * @return UUID
   */
  public <T> UUID registerCallback(Callback<T> callback) {
    UUID uuid = getUUID();
    activeCallbacks.put(uuid, callback);

    return uuid;
  }

  /** 
   * Removes callback from callback manager
   * @param uuid The UUID of the callback
   */
  public void removeCallback(UUID uuid) {
    activeCallbacks.remove(uuid);
  }

  /** 
   * Executes the callback
   * @param uuid The UUID of the callback
   * @param data The data to be passed to the callback
   * @param <T> The callbacks generic type
   */
  public <T> void executeCallback(UUID uuid, T data) {
    Callback<T> callback = (Callback<T>) activeCallbacks.get(uuid);
    if (callback != null) {
      callback.invoke(data);
      removeCallback(uuid);
    }
  }
}