package lib;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import Main.Programmer;

/**
 * The callback manager manages any callback created in the program
 */
public class CallbackManager<T> {
  /**
  * Constructor
  */
  public CallbackManager() {
    //
  }

  /**
  * Deep copy constructor
  * @param activeCallbacks A thread safe map of registered callbacks
  */
  public CallbackManager(Map<UUID, Callback<T>> activeCallbacks) {
    this.activeCallbacks = new ConcurrentHashMap<>(activeCallbacks);
  }

  private Map<UUID, Callback<T>> activeCallbacks = new ConcurrentHashMap<>();

  /**
   * WOULD NOT RECOMMEND USING THIS, THIS IS LOCKED DOWN FOR THREAD SAFTEY, I'M ONLY DOING THIS FOR DEEP COPY REQUIREMENT IN ASSIGNMENT
   * @return Active callbacks map
   */
  public Map<UUID, Callback<T>> getActiveCallbacks() {
    return this.activeCallbacks;
  }

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
   * @return UUID
   */
  public UUID registerCallback(Callback<T> callback) {
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
   */
  public void executeCallback(UUID uuid, T data) {
    Callback<T> callback = (Callback<T>) activeCallbacks.get(uuid);
    if (callback != null) {
      callback.invoke(data);
      removeCallback(uuid);
    }
  }

  /**
  * Equals method
  * @param o Object to be compared
  * @return boolean
  */
  @Override
  public boolean equals(Object o) {
    if (o == this) { /* Check if the object is this class */
      return true;
    }

    if (!(o instanceof CallbackManager)) { /* Check if the object is type Assignment */
      return false;
    }

    /* Checking individual values */
    CallbackManager<T> CallbackManger = ((CallbackManager<T>) o);
    if (this.activeCallbacks.equals(CallbackManger.activeCallbacks)) {
      return true;
    }

    return false; /* Otherwise false */
  }
}