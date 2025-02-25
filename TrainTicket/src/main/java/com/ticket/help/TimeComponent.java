package com.ticket.help;

import org.springframework.stereotype.Component;

import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Component
public class TimeComponent {
   public TimeComponent() {
   }


   public Time getCurrentSqlTime() {
      // 获取当前时间
      Date date = new Date();
      return new Time(date.getTime());

   }

   public static String convertMinutesToHoursAndMinutes(int duration) {
      int hours = duration / 60;
      int minutes = duration % 60;
      return hours + "小时" + minutes + "分钟";
   }
}
