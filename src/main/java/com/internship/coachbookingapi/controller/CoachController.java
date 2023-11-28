package com.internship.coachbookingapi.controller;

import com.internship.coachbookingapi.service.ICoachService;
import com.internship.coachbookingapi.service.ICoachTypeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/coaches")
public class CoachController {
    private final ICoachService coachService;
    private final ICoachTypeService coachTypeService;
    private final ModelMapper modelMapper;

    @Autowired
    public CoachController(ICoachService coachService, ICoachTypeService coachTypeService, ModelMapper modelMapper) {
        this.coachService = coachService;
        this.coachTypeService = coachTypeService;
        this.modelMapper = modelMapper;
    }
}
