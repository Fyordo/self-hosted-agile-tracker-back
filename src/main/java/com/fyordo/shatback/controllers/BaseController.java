package com.fyordo.shatback.controllers;


import com.fyordo.shatback.services.auth.SecurityWorkspace;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "${lk.path}")
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseController {
    protected ModelMapper mapper = new ModelMapper();
    protected SecurityWorkspace securityWorkspace;
}
