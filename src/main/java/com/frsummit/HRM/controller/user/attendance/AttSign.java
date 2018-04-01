package com.frsummit.HRM.controller.user.attendance;

import com.frsummit.HRM.configuration.MyAuthorization;
import com.frsummit.HRM.model.Attendance;
import com.frsummit.HRM.model.User;
import com.frsummit.HRM.service.AttendanceService;
import com.frsummit.HRM.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
public class AttSign {

    @Autowired
    protected AttendanceService attendanceService;

    @Autowired
    protected UserService userService;

    @Autowired
    private MyAuthorization myAuthorization;

    @RequestMapping(value = "/user/attendance-user-punch", method = RequestMethod.GET)
    public String attSignForm(Model model){
        model.addAttribute("myRole", myAuthorization.userFromEmailOrId().getMyRole());
        return "attendance_user_punch";
    }

    @RequestMapping(value = "/user/attendance-sign", method = RequestMethod.POST)
    public String attSign(
            Model model,
            @RequestParam(value = "username") String username){

        /*Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user1 = userService.findUserByEmail(auth.getName());
        User user2 = userService.findUserById(auth.getName());
        User user;
        if(user1 != null) user = user1;
        else user = user2;*/

        //Attendance attendance = new Attendance(user.getId());
        Attendance attendance = new Attendance(myAuthorization.userFromEmailOrId().getId());
        attendanceService.saveAttendance(attendance);

//        List<Attendance> attendanceList = attendanceService.myAllSignList();
//        Attendance attendance1 = attendanceList.get(attendanceList.size() - 1);

//        Calendar calendar = Calendar.getInstance();
//        Date d = calendar.getTime();
//        System.out.println(d);
        model.addAttribute("myRole", myAuthorization.userFromEmailOrId().getMyRole());
        return "home";
    }
}
