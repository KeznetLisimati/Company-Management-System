/*
 * Copyright 2014 Edward Zengeni.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.company.business.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Edward Zengeni
 */
public class DateUtil {

    private static final Integer startDate = getMonthStartDate();
    private static final Integer startMonth = 0;
    private static final Integer endDate = getMonthEndDate();
    private static final Integer endMonth = 11;
    public static final Integer INITIAL_YEAR = 1980;
    public static final Integer CURRENT_YEAR = getCurrentYear();
    public static final Integer CURRENT_MONTH = getCurrentMonth();
    public static final Integer CURRENT_DATE = getCurrentDate();
    public static final List<Integer> YEAR_RANGE = getInitialDateRange(INITIAL_YEAR);

    private DateUtil() {
        throw new IllegalStateException("Class not intended to be instantiated");
    }
    
    private static Integer getMonthEndDate() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
    }
    
    public static Integer getMonthStartDate() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        return cal.getActualMinimum(Calendar.DAY_OF_MONTH);
    }

    private static Integer getCurrentYear() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        return cal.get(Calendar.YEAR);
    }

    private static Integer getCurrentMonth() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        return cal.get(Calendar.MONTH);
    }

    private static Integer getCurrentDate() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        return cal.get(Calendar.DATE);
    }

    public static List<Integer> getInitialDateRange(Integer initialYear) {
        List<Integer> items = new ArrayList<>();
        for (int x = initialYear; x <= CURRENT_YEAR + 1; x++) {
            if (x <= CURRENT_YEAR) {
                items.add(x);
            } else {
                /**
                 * TODO make comparizon month editable
                 *
                 * @param comparizon month
                 */
                if (CURRENT_MONTH >= 8) {
                    items.add(x);
                }

            }
        }
        return items;
    }

    public static Date getDate(Integer year, Boolean start) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, start ? startMonth : endMonth, start ? startDate : endDate);
        return cal.getTime();
    }

    public static Integer getYearFromDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.YEAR);
    }

    public static Integer getMonthFromDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.MONTH);
    }

    public static String getFriendlyFileName(String name) {
        String new_name = "";
        String [] arr = name.split(" ");
        for(String n : arr){
            new_name += n+"_";
        }        
        return new_name + "_" + getCurrentDate() + "_" + getCurrentMonth() + "_" + getCurrentYear();
    }

    public static String getPrintName(String name) {
        final String searchExp = "_";
        StringBuilder builder = new StringBuilder();
        String[] args = name.split(searchExp);
        int pos = 0;
        for (String s : args) {
            if (pos < args.length - 1) {
                builder.append(s);
                builder.append(" ");
                pos++;
            }
        }
        return builder.toString();
    }
    
    public static String getFooterText(String name){
        final String searchExp = "_";
        String[] args = name.split(searchExp);
        return args[args.length -1];
    }

    public static Integer[] hex2Rgb(String colorStr) {
        return new Integer[]{Integer.valueOf(colorStr.substring(1, 3), 16),
            Integer.valueOf(colorStr.substring(3, 5), 16),
            Integer.valueOf(colorStr.substring(5, 7), 16)};
    }
    
    public static Date getStartOfYear(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) -1);
        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.DATE, 1);
        return calendar.getTime();
    }
    
    public static Date getEndOfYear(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) -1);
        calendar.set(Calendar.MONTH, 11);
        calendar.set(Calendar.DATE, 31);
        return calendar.getTime();
    }
    
    public static Date getDateFromAge(Integer age){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.YEAR, -age);
        return cal.getTime();
    }
    
    public static Date getEarliestEmployableDate(Integer age){
        age = age - 16;
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.YEAR, -age);
        return cal.getTime();
    }
}
