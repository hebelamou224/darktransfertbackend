package com.trustify.darktransfertdata.controller;

import com.trustify.darktransfertdata.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

@Controller
@AllArgsConstructor
@RestController
public class CustomerController {

    private CustomerService customerService;



}
