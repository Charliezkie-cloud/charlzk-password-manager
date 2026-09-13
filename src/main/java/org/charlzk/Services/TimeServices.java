package org.charlzk.Services;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class TimeServices {
  public static String formatTimeMillis(long timeMillis) {
    return Instant.ofEpochMilli(timeMillis)
            .atZone(ZoneId.systemDefault())
            .format(DateTimeFormatter.ofPattern("MMMM d, yyyy"));
  }
}
