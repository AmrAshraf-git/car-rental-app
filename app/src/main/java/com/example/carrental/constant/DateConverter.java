package com.example.carrental.constant;

public class DateConverter {
    private String dayOfWeek;
    private String monthOfYear;
    public DateConverter(int dayOfWeekAsNumber, int monthOfYearAsNumber) {

        switch (dayOfWeekAsNumber)
        {
            case 1: dayOfWeek="Sunday";
                break;

            case 2: dayOfWeek="Monday";
                break;

            case 3: dayOfWeek="Tuesday";
                break;

            case 4: dayOfWeek="Wednesday";
                break;

            case 5: dayOfWeek="Thursday";
                break;

            case 6: dayOfWeek="Friday";
                break;

            case 7: dayOfWeek="Saturday";
                break;

            default: dayOfWeek="Null";
                break;
        }

        switch (monthOfYearAsNumber)
        {
            case 0: monthOfYear="January";
                break;

            case 1: monthOfYear="February";
                break;

            case 2: monthOfYear="March";
                break;

            case 3: monthOfYear="April";
                break;

            case 4: monthOfYear="May";
                break;

            case 5: monthOfYear="June";
                break;

            case 6: monthOfYear="July";
                break;

            case 7: monthOfYear="August";
                break;

            case 8: monthOfYear="September";
                break;

            case 9: monthOfYear="October";
                break;

            case 10: monthOfYear="November";
                break;

            case 11: monthOfYear="December";
                break;

            default: monthOfYear="Null";
                break;
        }
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public String getMonthOfYear() {
        return monthOfYear;
    }
}
