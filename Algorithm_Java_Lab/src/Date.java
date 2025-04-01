import java.time.*;

public class Date implements Comparable<Date>{

    private final int month;
    private final int day;
    private final int year;
    private boolean isVaild(int month, int day, int year){
        try{
            LocalDate.of(year, month, day);
            return true;
        } catch (DateTimeException e){
            return false;
        }
    }
    public Date(int month, int day, int year, int year1){
        if (!isVaild(month, day, year)) throw new IllegalArgumentException("Invalid date");
        this.year = year;
        this.month = month;
        this.day = day;
    }
    public int compareTo(Date that) {
        if (this.year < that.year) return -1;
        if (this.year > that.year) return +1;
        if (this.month < that.month) return -1;
        if (this.month > that.month) return +1;
        if (this.day < that.day) return -1;
        if (this.day > that.day) return +1;
        return 0;
    }
}
