package com.internship.coachbookingapi.controller;

import com.internship.coachbookingapi.entity.CoachType;
import com.internship.coachbookingapi.model.CoachTypeModel;
import com.internship.coachbookingapi.model.ResponseModel;
import com.internship.coachbookingapi.service.ICoachTypeService;
import io.swagger.v3.oas.annotations.Operation;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.io.IOException;
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

    @Operation(summary = "Thêm danh sách loại xe/ghế")
    @PostMapping
    public ResponseEntity<?> createCoachTypes() throws IOException {
        FileInputStream file = new FileInputStream("./src/main/resources/Data.xlsx");
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheetAt(0);

        // Duyệt qua các hàng trong sheet
        for (Row row : sheet) {
            // Lấy giá trị từ cột 'name' (cột 0)
            Cell nameCell = row.getCell(0);

            if (nameCell != null) {
                String name = nameCell.getStringCellValue();
                CoachType coachType = new CoachType();
                coachType.setName(name);
                coachTypeService.save(coachType);
            }
        }

        workbook.close();
        file.close();

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
