package com.frsummit.HRM.controller.admin.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.frsummit.HRM.service.UserService;

@Controller
public class UserList {

    @Autowired
    private UserService userService;

	// @RequestMapping("/admin/users-list")
    @RequestMapping("/admin/profile-user-list")
    public String getUserList(Model model) {
        model.addAttribute("userList",userService.findAllUsers());
        return "profile_user_list";
    }

	@RequestMapping(value = "/admin/list-user-edit", method = RequestMethod.GET)
    public String getUserListEditAction(@RequestParam(value = "checkBox") String checkBox) {
        System.out.println(checkBox.toString());

        return "profile_user_list";
    }

    @RequestMapping(value = "/check-box", method = RequestMethod.GET)
    public String getUserFromCheckMark(@RequestParam(value = "check") String checkBox) {
        System.out.println(checkBox);

        return "profile_user_list";
    }

    @RequestMapping(value = "/admin/registration", method = RequestMethod.GET)
    public String deleteUserByCheckBoxSElection(@RequestParam(value = "check") String checkBox, Model model) {
        System.out.println(checkBox);
        String[] parts = checkBox.split(",");
        System.out.println(parts.length);
        for(int i=0; i< parts.length-1; i++){
            userService.deleteUser(parts[i]);
        }

        model.addAttribute("userList",userService.findAllUsers());

        return "profile_user_list";
    }

	/*
	 * @RequestMapping(value = "/admin/user-edit-list", method = RequestMethod.GET)
	 * public String userListBtnFromTable(Model model, @RequestParam(value =
	 * "buttonId") String buttonId) { System.out.println("Edit / Delete Clicked " +
	 * buttonId); model.addAttribute("userList",userService.findAllUsers());
	 * 
	 * return "profile_user_list"; }
	 */
}
