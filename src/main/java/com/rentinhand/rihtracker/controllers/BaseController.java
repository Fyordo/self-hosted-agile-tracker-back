package com.rentinhand.rihtracker.controllers;


import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "${lk.path}")
public abstract class BaseController {
    protected ModelMapper mapper = new ModelMapper();
}
