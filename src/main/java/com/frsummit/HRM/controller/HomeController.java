package com.frsummit.HRM.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.frsummit.HRM.config.Config;
import com.frsummit.HRM.config.ConfigServe;
import com.frsummit.HRM.configuration.LeaveConfiguration;
import com.frsummit.HRM.configuration.MyAuthorization;
import com.frsummit.HRM.model.Role;
import com.frsummit.HRM.service.RoleService;
import com.frsummit.HRM.service.UserService;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private MyAuthorization myAuthorization;

    @Autowired
    private ConfigServe configServe;

    @RequestMapping(value="/home", method = RequestMethod.GET)
    public String home(Model model){

//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        User user = userService.findUserByEmail(auth.getName());
//        System.out.println(user.getMyRole());

        /*Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user1 = userService.findUserByEmail(auth.getName());
        User user2 = userService.findUserById(auth.getName());
        User user;
        if(user1 != null) user = user1;
        else user = user2;*/

//        Authentication/Access list for User and admin in common_layout
        model.addAttribute("myRole", myAuthorization.userFromEmailOrId().getMyRole());

        List<Role> roleList = roleService.findAllRole(myAuthorization.userFromEmailOrId().getMyRole());
        //List<Role> roleList = roleService.findAllRole(user.getMyRole());
        Role role = roleList.get(0);
        System.out.println(role.getId());
        System.out.println(role.getRole());
        System.out.println(role.getRoleChain());

        LeaveConfiguration leaveConfiguration = new LeaveConfiguration();
        leaveConfiguration.mapForRole(role.getRole(), role.getRoleChain());

//        Testing

        List<Config> confList = configServe.findAllConfigName();
        if(confList.size() == 0)
            return "configuration";
        return "home";
    }
}
