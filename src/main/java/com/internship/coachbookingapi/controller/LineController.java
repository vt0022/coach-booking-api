package com.internship.coachbookingapi.controller;

import com.internship.coachbookingapi.entity.CoachType;
import com.internship.coachbookingapi.entity.Departure;
import com.internship.coachbookingapi.entity.Destination;
import com.internship.coachbookingapi.entity.Line;
import com.internship.coachbookingapi.model.LineModel;
import com.internship.coachbookingapi.model.ResponseModel;
import com.internship.coachbookingapi.service.*;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/lines")
public class LineController {
    private final ILineService lineService;
    private final IDepartureService departureService;
    private final IDestinationService destinationService;
    private final ICoachTypeService coachTypeService;
    private final ICoachService coachService;
    private final ModelMapper modelMapper;

    @Autowired
    public LineController(ILineService lineService, IDepartureService departureService, IDestinationService destinationService, ICoachService coachService, ICoachTypeService coachTypeService, ICoachService coachService1, ModelMapper modelMapper) {
        this.lineService = lineService;
        this.departureService = departureService;
        this.destinationService = destinationService;
        this.coachTypeService = coachTypeService;
        this.coachService = coachService1;
        this.modelMapper = modelMapper;
    }

    @Operation(summary = "Lấy danh sách ngày chứa chuyến theo điểm đi và điểm đến")
    @GetMapping()
    public ResponseEntity<?> getAvailableDates(@RequestParam UUID departureId,
                                               @RequestParam UUID destinationId) {
        Departure departure = departureService.findById(departureId).orElseThrow(() -> new RuntimeException("Departure not found!"));
        Destination destination = destinationService.findById(destinationId).orElseThrow(() -> new RuntimeException("Destination not found!"));
        List<Line> lines = lineService.findByStatusAndDepartureAndDestination(true, departure, destination);
        List<Date> lineDates = lines.stream()
                .map(Line::getDepartureDate)
                .filter(date -> date.after(new java.util.Date()))// Thay YourEntity bằng tên của entity của bạn
                .distinct()
                .collect(Collectors.toList());
        return ResponseEntity.ok(ResponseModel.builder()
                .status(200)
                .error(false)
                .message("Get all available dates containing lines from " + departure.getName() + " to " + destination.getName() + " successfully")
                .data(lineDates)
                .build());
    }

    @Operation(summary = "Lấy danh sách chuyến theo giờ của một ngày")
    @GetMapping("/date")
    public ResponseEntity<?> getAvailableLinesByDate(@RequestParam UUID departureId,
                                                     @RequestParam UUID destinationId,
                                                     @RequestParam Date departureDate,
                                                     @RequestParam UUID coachTypeId) {
        Departure departure = departureService.findById(departureId).orElseThrow(() -> new RuntimeException("Departure not found!"));
        Destination destination = destinationService.findById(destinationId).orElseThrow(() -> new RuntimeException("Destination not found!"));
        CoachType coachType = coachTypeService.findById(coachTypeId).orElseThrow(() -> new RuntimeException("Coach type not found!"));
        List<Line> lines = lineService.findByStatusAndDepartureAndDestinationAndDepartureDate(true, departure, destination, departureDate);
        // Find by type of coach
        lines = lines.stream().filter(line -> line.getCoach().getCoachType() == coachType).collect(Collectors.toList());
        List<LineModel> lineModels = lines.stream().map(line -> modelMapper.map(line, LineModel.class)).collect(Collectors.toList());
        return ResponseEntity.ok(ResponseModel.builder()
                .status(200)
                .error(false)
                .message("Get all available lines for date " + departureDate.toString() + " successfully")
                .data(lineModels)
                .build());
    }
}
