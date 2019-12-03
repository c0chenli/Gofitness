package com.flagcamp.gofitness.controller;

import com.flagcamp.gofitness.model.Schedule;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {
    public static void main(String[] args) throws ParseException {
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmm");
        String startTime = "201912301130";
        String endTime = "201912301600";
        long start = sf.parse(startTime).getTime();
        long end = sf.parse(endTime).getTime();
        Date date = new Date(start);
       // System.out.println(date);
        Date after = new Date(end);
        //System.out.println(date.compareTo(after));
        System.out.println(startTime.compareTo("201912301900"));
    }
}
