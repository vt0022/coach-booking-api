package com.internship.coachbookingapi.controller;

import com.internship.coachbookingapi.entity.Departure;
import com.internship.coachbookingapi.entity.PickUp;
import com.internship.coachbookingapi.entity.PickUpType;
import com.internship.coachbookingapi.model.PickUpModel;
import com.internship.coachbookingapi.model.ResponseModel;
import com.internship.coachbookingapi.service.IDepartureService;
import com.internship.coachbookingapi.service.IDestinationService;
import com.internship.coachbookingapi.service.IPickUpService;
import com.internship.coachbookingapi.service.IPickUpTypeService;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/pickups")
public class PickUpController {
    private final IPickUpService pickUpService;
    private final IDepartureService departureService;
    private final IDestinationService destinationService;
    private final IPickUpTypeService pickUpTypeService;
    private final ModelMapper modelMapper;

    public PickUpController(IPickUpService pickUpService, IDepartureService departureService, IDestinationService destinationService, IPickUpTypeService pickUpTypeService, ModelMapper modelMapper) {
        this.pickUpService = pickUpService;
        this.departureService = departureService;
        this.destinationService = destinationService;
        this.pickUpTypeService = pickUpTypeService;
        this.modelMapper = modelMapper;
    }

    @Operation(summary = "Lấy danh sách điểm đón theo nơi đi và phương thức đón")
    @GetMapping()
    public ResponseEntity<?> getPickUpByDepartureAndPickUpType(@RequestParam UUID departureId,
                                                               @RequestParam UUID pickUpTypeId) {
        Departure departure = departureService.findById(departureId).orElseThrow(() -> new RuntimeException("Departure not found"));
        PickUpType pickUpType = pickUpTypeService.findById(pickUpTypeId).orElseThrow(() -> new RuntimeException("Pick up type not found"));
        List<PickUp> pickUps = pickUpService.findByDepartureAndPickUpType(departure, pickUpType);
        List<PickUpModel> pickUpModels = pickUps.stream().map(pickUp -> modelMapper.map(pickUp, PickUpModel.class)).collect(Collectors.toList());
        return ResponseEntity.ok(ResponseModel
                .builder()
                .status(200)
                .error(false)
                .message("List pick ups successfully")
                .data(pickUpModels)
                .build());
    }
}
