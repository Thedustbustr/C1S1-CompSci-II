package Errors;

/**
 * Debug logging
 */
public final class Logging {
  /** 
   * @param msg Message
   * @param thread Messages origin thread
   */
  public static void Log(String msg, Threads thread) {
    System.out.println("[INFO/" + thread + "] " + msg);
  }

  /** 
  * @param msg Warning message
  * @param thread Warning messages origin thread
  * @param e Optional exception that occured
  */
  public static void Warning(String msg, Threads thread, Exception... e) {
    System.out.println("[WARN/" + thread + "] " + msg + "\n" + e);
  }

  /** 
  * @param msg Error message
  * @param thread Error origin thread
  * @param e Required exception that occured
  */
  public static void Error(String msg, Threads thread, Exception e) {
    System.out.println("[FATAL/" + thread + "] " + msg + "\n" + e);
  }
}
