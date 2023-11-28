package com.internship.coachbookingapi.controller;

import com.internship.coachbookingapi.entity.CoachType;
import com.internship.coachbookingapi.model.CoachTypeModel;
import com.internship.coachbookingapi.model.ResponseModel;
import com.internship.coachbookingapi.service.ICoachTypeService;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/coachtypes")
public class CoachTypeController {
    private final ICoachTypeService coachTypeService;
    private final ModelMapper modelMapper;

    @Autowired
    public CoachTypeController(ICoachTypeService coachTypeService, ModelMapper modelMapper) {
        this.coachTypeService = coachTypeService;
        this.modelMapper = modelMapper;
    }

    @Operation(summary = "Liệt kê danh sách loại xe/ghế")
    @GetMapping
    public ResponseEntity<?> getAllCoachTypes() {
        List<CoachType> coachTypes = coachTypeService.findAll();
        List<CoachTypeModel> coachTypeModels = coachTypes.stream().map(coachType -> modelMapper.map(coachType, CoachTypeModel.class)).collect(Collectors.toList());
        return ResponseEntity.ok(ResponseModel.builder()
                .status(200)
                .error(false)
                .message("Get all types of coach successfully")
                .data(coachTypeModels)
                .build());
    }
}
