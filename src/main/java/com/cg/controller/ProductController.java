package com.cg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/products")
public class ProductController {

    @GetMapping()
    public ModelAndView showAdmin() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/admin/listProduct");
        return modelAndView;
    }


//    @Autowired
//    private IUserService userService;
//
//
//
//    private String getPrincipal() {
//        String username;
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//        if (principal instanceof UserDetails) {
//            username = ((UserDetails) principal).getUsername();
//        } else {
//            username = "";
//        }
//        return username;
//}
//
//    private UserDTO getUserDTO(String userName) {
//        User user = userService.findByUsername(userName).get();
//        Role role = user.getRole();
//        UserDTO userDTO = user.toUserDTO();
//        userDTO.setRole(role.toRoleDTO());
//        return userDTO;
//    }
//
//    @GetMapping("/products")
//    public ModelAndView showListProduct() {
//        ModelAndView modelAndView = new ModelAndView();
//        if (!getPrincipal().equals("")) {
//            UserDTO userDTO = getUserDTO(getPrincipal());
//            modelAndView.addObject("user", userDTO);
//            modelAndView.addObject("role", userDTO.getRole());
//            if (userDTO.getRole().getCode().equalsIgnoreCase("admin")) {
//                modelAndView.setViewName("/admin/listProduct");
//                return modelAndView;
//            }
//        } else {
//            modelAndView.addObject("user", null);
//            modelAndView.addObject("role", null);
//        }
////        modelAndView.setViewName("/user/list");
//        return modelAndView;
//    }
//
//    @GetMapping("/products/admin")
//    public ModelAndView showListProductAdmin() {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("/admin/listProduct");
//        if (!getPrincipal().equals("")) {
//            modelAndView.addObject("user", getUserDTO(getPrincipal()));
//            modelAndView.addObject("role", getUserDTO(getPrincipal()).getRole());
//        } else {
//            modelAndView.addObject("user", null);
//            modelAndView.addObject("role", null);
//        }
//        return modelAndView;
//    }
}
