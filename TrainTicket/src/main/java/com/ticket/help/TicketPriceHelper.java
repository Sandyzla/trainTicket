package com.ticket.help;

import static java.lang.Math.*;

/**
 * 将角度转化为弧度
 */

public class TicketPriceHelper {
    public static double radians(double d) {
        return d * Math.PI / 180.0;
    }
    public static double getDistance(double lng1, double lat1, double lng2, double lat2){
        //将角度转化为弧度
        double radLng1 = radians(lng1);
        double radLat1 = radians(lat1);
        double radLng2 = radians(lng2);
        double radLat2 = radians(lat2);

        double a = radLat1 - radLat2;
        double b = radLng1 - radLng2;

        return 2 * asin(sqrt(sin(a / 2) * sin(a / 2) + cos(radLat1) * cos(radLat2) * sin(b / 2) * sin(b / 2))) * 6378.137;
    }

    public static int computePrice(double distance, int duration, String type){
        double basePrice = 0;
        double price = 0;
        if(distance <= 200){
            basePrice = distance * 0.05861;
        }else if(distance <= 500){
            basePrice = 11.722 + (distance-200) * 0.05275;
        }else if(distance <= 1000){
            basePrice = 27.547 + (distance-500) * 0.04689;
        }else if(distance <= 1500){
            basePrice = 50.992 + (distance-1000) * 0.04102;
        }else if(distance <= 2000){
            basePrice = 71.502 + (distance-1500) * 0.03517;
        }else{
            basePrice = 89.087 + (distance-2000) * 0.02931;
        }
        if(type.equals("二等座")){
            price = basePrice * 5.8;
        }else if (type.equals("一等座")){
            price = basePrice * 9.28;
        }else{
            price = basePrice * 17.4;
        }
        price += (double) 5000 / duration - 20;
        return (int) price;
    }
}
