package com.internship.coachbookingapi.controller;

import com.internship.coachbookingapi.entity.Line;
import com.internship.coachbookingapi.model.LineSeatModel;
import com.internship.coachbookingapi.model.ResponseModel;
import com.internship.coachbookingapi.service.ILineSeatService;
import com.internship.coachbookingapi.service.ILineService;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/seats")
public class LineSeatController {
    private final ILineSeatService lineSeatService;
    private final ILineService lineService;
    private final ModelMapper modelMapper;

    @Autowired
    public LineSeatController(ILineSeatService lineSeatService, ILineService lineService, ModelMapper modelMapper) {
        this.lineSeatService = lineSeatService;
        this.lineService = lineService;
        this.modelMapper = modelMapper;
    }

    @Operation(summary = "Lấy danh sách ghế/giường của một chuyến")
    @GetMapping("/lines/{lineId}")
    public ResponseEntity<?> getSeatsByLine(@PathVariable UUID lineId) {
        Line line = lineService.findById(lineId).orElseThrow(() -> new RuntimeException("Line not found"));
        List<LineSeatModel> lineSeatModels = line.getLineSeats()
                .stream()
                .map(lineSeat -> {
                    LineSeatModel lineSeatModel = modelMapper.map(lineSeat, LineSeatModel.class);
                    return lineSeatModel;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(ResponseModel
                .builder()
                .status(200)
                .error(false)
                .message("Get seats by line successfully")
                .data(lineSeatModels)
                .build());
    }
}
