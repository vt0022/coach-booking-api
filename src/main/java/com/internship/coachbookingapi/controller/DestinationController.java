package com.internship.coachbookingapi.controller;

import com.internship.coachbookingapi.entity.Departure;
import com.internship.coachbookingapi.entity.Destination;
import com.internship.coachbookingapi.model.DestinationModel;
import com.internship.coachbookingapi.model.ResponseModel;
import com.internship.coachbookingapi.service.IDepartureService;
import com.internship.coachbookingapi.service.IDestinationService;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/destinations")
public class DestinationController {
    private final IDestinationService destinationService;
    private final IDepartureService departureService;
    private final ModelMapper modelMapper;

    public DestinationController(IDestinationService destinationService, IDepartureService departureService, ModelMapper modelMapper) {
        this.destinationService = destinationService;
        this.departureService = departureService;
        this.modelMapper = modelMapper;
    }

    @Operation(summary = "Lấy danh sách nơi đến",
            description = "Trả về danh sách nơi đến")
    @GetMapping
    public ResponseEntity<?> getAllDestinations() {
        List<Destination> destinations = destinationService.findAll();
        List<DestinationModel> destinationModels = destinations
                .stream()
                .map(destination -> modelMapper.map(destination, DestinationModel.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(ResponseModel.builder()
                .error(false)
                .status(200)
                .message("Get all destinations successfully")
                .data(destinationModels)
                .build());
    }

    @Operation(summary = "Lấy điểm đến bằng slug")
    @GetMapping("/{slug}")
    public ResponseEntity<?> getDestinationBySlug(@PathVariable String slug) {
        Destination destination = destinationService.findBySlug(slug).orElseThrow(() -> new RuntimeException("Destination not found"));
        DestinationModel destinationModel = modelMapper.map(destination, DestinationModel.class);
        return ResponseEntity.ok(ResponseModel.builder()
                .status(200)
                .error(false)
                .message("Get destination successfully")
                .data(destinationModel)
                .build());
    }

    @Operation(summary = "Lấy danh sách nơi đến ứng với nơi đi",
            description = "Trả về danh sách nơi đến cho từng nơi đi")
    @GetMapping("/departures/{slug}")
    public ResponseEntity<?> getDestinationsByDeparture(@PathVariable String slug) {
        Departure departure = departureService.findBySlug(slug).orElseThrow(() -> new RuntimeException("Departure not found"));
        List<Destination> destinations = departure.getDestinations();
        List<DestinationModel> destinationModels = destinations
                .stream()
                .map(destination -> modelMapper.map(destination, DestinationModel.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(ResponseModel.builder()
                .error(false)
                .status(200)
                .message("Get all destinations from " + departure.getName() + " successfully")
                .data(destinationModels)
                .build());
    }
}
