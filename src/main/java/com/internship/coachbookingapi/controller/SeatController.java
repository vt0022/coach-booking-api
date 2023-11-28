package com.internship.coachbookingapi.controller;

import com.internship.coachbookingapi.service.ICoachTypeService;
import com.internship.coachbookingapi.service.ISeatService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/seats")
public class SeatController {
    private final ISeatService seatService;
    private final ICoachTypeService coachTypeService;
    private final ModelMapper modelMapper;

    @Autowired
    public SeatController(ISeatService seatService, ICoachTypeService coachTypeService, ModelMapper modelMapper) {
        this.seatService = seatService;
        this.coachTypeService = coachTypeService;
        this.modelMapper = modelMapper;
    }
}
