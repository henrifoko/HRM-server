package com.frsummit.HRM.api.server.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

@SuppressWarnings( "serial" )
public class Attendance implements Serializable {

    private int       attendId;
    private String    userId;
    private Date      signTime;
    private Calendar  inTime;
    private Calendar  outTime;
    private String    status;
    private Date      lateBy;

    // Extra features
    private Date      workHourInDay;

    // Extra features
    private Date      overtime;

    // Extra features
    private String    shift;

    // Extra features
    private String    workDuration;
    private int       absent;

    // Extra features
    private double    performance;

    // Extra features
    private String    attendanceLink1;

    // Extra features
    private String    attendanceLink2;

    // Extra features
    private String    attendanceLink3;

    // Extra features
    private String    attendanceLink4;
    private String    attendanceRemark;

    private Set<User> user;

    public Attendance() {
    }

    public Attendance( String userId ) {
        this.userId = userId;
    }

    public int getAttendId() {
        return attendId;
    }

    public void setAttendId( int attendId ) {
        this.attendId = attendId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId( String userId ) {
        this.userId = userId;
    }

    public Date getSignTime() {
        return signTime;
    }

    public void setSignTime( Date signTime ) {
        this.signTime = signTime;
    }

    public Calendar getInTime() {
        return inTime;
    }

    public void setInTime( Calendar inTime ) {
        this.inTime = inTime;
    }

    public Calendar getOutTime() {
        return outTime;
    }

    public void setOutTime( Calendar outTime ) {
        this.outTime = outTime;
    }

    public Date getLateBy() {
        return lateBy;
    }

    public void setLateBy( Date lateBy ) {
        this.lateBy = lateBy;
    }

    public Date getWorkHourInDay() {
        return workHourInDay;
    }

    public void setWorkHourInDay( Date workHourInDay ) {
        this.workHourInDay = workHourInDay;
    }

    public Date getOvertime() {
        return overtime;
    }

    public void setOvertime( Date overtime ) {
        this.overtime = overtime;
    }

    public String getShift() {
        return shift;
    }

    public void setShift( String shift ) {
        this.shift = shift;
    }

    public String getWorkDuration() {
        return workDuration;
    }

    public void setWorkDuration( String workDuration ) {
        this.workDuration = workDuration;
    }

    public int getAbsent() {
        return absent;
    }

    public void setAbsent( int absent ) {
        this.absent = absent;
    }

    public double getPerformance() {
        return performance;
    }

    public void setPerformance( double performance ) {
        this.performance = performance;
    }

    public String getAttendanceLink1() {
        return attendanceLink1;
    }

    public void setAttendanceLink1( String attendanceLink1 ) {
        this.attendanceLink1 = attendanceLink1;
    }

    public String getAttendanceLink2() {
        return attendanceLink2;
    }

    public void setAttendanceLink2( String attendanceLink2 ) {
        this.attendanceLink2 = attendanceLink2;
    }

    public String getAttendanceLink3() {
        return attendanceLink3;
    }

    public void setAttendanceLink3( String attendanceLink3 ) {
        this.attendanceLink3 = attendanceLink3;
    }

    public String getAttendanceLink4() {
        return attendanceLink4;
    }

    public void setAttendanceLink4( String attendanceLink4 ) {
        this.attendanceLink4 = attendanceLink4;
    }

    public String getAttendanceRemark() {
        return attendanceRemark;
    }

    public void setAttendanceRemark( String attendanceRemark ) {
        this.attendanceRemark = attendanceRemark;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus( String status ) {
        this.status = status;
    }

    public Set<User> getUser() {
        TreeSet<User> userTreeSet = new TreeSet<User>();
        Iterator<User> it = user.iterator();
        while ( it.hasNext() ) {
            userTreeSet.add( it.next() );
        }
        return userTreeSet;
    }

    public void setUser( Set<User> user ) {
        TreeSet<User> userTreeSet = new TreeSet<User>();
        Iterator<User> it = user.iterator();
        while ( it.hasNext() ) {
            userTreeSet.add( it.next() );
        }
        this.user = userTreeSet;
    }

    /*
     *
     * Time Difference String time1 = "16:00:00"; String time2 = "19:00:00";
     *
     * SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss"); Date date1 =
     * format.parse(time1); Date date2 = format.parse(time2); long difference =
     * date2.getTime() - date1.getTime();
     *
     *********************************************************************************************
     *
     * Instant start = Instant.now(); Instant end = Instant.now(); Duration
     * timeElapsed = Duration.between(start, end);
     * System.out.println("Time taken: "+ timeElapsed.toMillis()
     * +" milliseconds");
     *
     *********************************************************************************************
     *
     * DateTimeFormatter formatter =
     * DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
     *
     * LocalDateTime dateTime1= LocalDateTime.parse("2014-11-25 19:00:00",
     * formatter); LocalDateTime dateTime2=
     * LocalDateTime.parse("2014-11-25 16:00:00", formatter);
     *
     * long diffInMilli = java.time.Duration.between(dateTime1,
     * dateTime2).toMillis(); long diffInSeconds =
     * java.time.Duration.between(dateTime1, dateTime2).getSeconds(); long
     * diffInMinutes = java.time.Duration.between(dateTime1,
     * dateTime2).toMinutes();
     *
     * *************************************************************************
     * *******************
     *
     *
     *
     */
}
