package com.soj.booksharing.data;

import com.soj.booksharing.entity.RentedBook;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class RentingIntervals {

    private Date startDate;
    private Date endDate;

    public RentingIntervals() {
    }

    public RentingIntervals(Date startDate, Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public static RentingIntervals getIntervalsForSpecificPeriod(int period){

        return switch (period){
            case 1 -> new RentingIntervals(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()),
                    Date.from(LocalDate.now().plusWeeks(1).atStartOfDay(ZoneId.systemDefault()).toInstant() ));
            case 2-> new RentingIntervals(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()),
                    Date.from(LocalDate.now().plusWeeks(2).atStartOfDay(ZoneId.systemDefault()).toInstant() ));
            case 3-> new RentingIntervals(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()),
                    Date.from(LocalDate.now().plusWeeks(3).atStartOfDay(ZoneId.systemDefault()).toInstant() ));
            case 4-> new RentingIntervals(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()),
                    Date.from(LocalDate.now().plusMonths(1).atStartOfDay(ZoneId.systemDefault()).toInstant() ));

            default -> throw new IllegalStateException("Unexpected value: " + period);
        };
    }
}
