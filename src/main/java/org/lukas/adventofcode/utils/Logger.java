package org.lukas.adventofcode.utils;

import static org.lukas.adventofcode.utils.Logger.LogLevel.DEBUG;
import static org.lukas.adventofcode.utils.Logger.LogLevel.INFO;

public class Logger {

  public static final Logger log = new Logger();
  private static LogLevel chosenLevel = DEBUG;

  public void setLevel(LogLevel level) {
    chosenLevel = level;
  }

  public void debug(String msg) {
    if (isAllowed(DEBUG)) {
      System.out.println(msg);
    }
  }

  public void info(String msg) {
    if (isAllowed(INFO)) {
      System.out.println(msg);
    }
  }

  private boolean isAllowed(LogLevel level) {
    return level.ordinal() >= chosenLevel.ordinal();
  }

  public enum LogLevel {DEBUG, INFO}
}
