package com.betrybe.agrix.controllers;

import com.betrybe.agrix.dto.CropDto;
import com.betrybe.agrix.dto.FarmDto;
import com.betrybe.agrix.exceptions.NotFoundException;
import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.Farm;
import com.betrybe.agrix.service.CropService;
import com.betrybe.agrix.service.FarmService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * comment.
 */

@RestController
@RequestMapping("/farms")
public class FarmController {
  private final FarmService farmService;
  private final  CropService cropService;

  @Autowired
  public FarmController(FarmService farmService, CropService cropService) {
    this.farmService = farmService;
    this.cropService = cropService;
  }

  /**
   * comment.
   */

  @PostMapping
  public ResponseEntity<Farm> createFarm(@RequestBody FarmDto farmDto) {
    Farm newFarm = farmService.insertFarm(farmDto.toFarm());
    return ResponseEntity.status(HttpStatus.CREATED).body(newFarm);
  }

  /**
   * comment.
   */

  @GetMapping
  public ResponseEntity<List<Farm>> getAllFarms() {
    List<Farm> farms = farmService.getAllFarms();
    return ResponseEntity.ok().body(farms);
  }

  /**
   * comment.
   */

  @GetMapping("/{id}")
  public ResponseEntity<Farm> getFarmById(@PathVariable Long id) {
    Optional<Farm> farm = farmService.getFarmById(id);
    if (farm.isEmpty()) {
      throw new NotFoundException("Fazenda não encontrada!");
    }
    return ResponseEntity.ok().body(farm.get());
  }


  /**
   * comment.
   */

  @PostMapping("/{farmId}/crops")
  public ResponseEntity<CropDto> insertCrop(@PathVariable Long farmId,
      @RequestBody CropDto cropDto) {
    Optional<Farm> optionalFarm = farmService.getFarmById(farmId);
    if (optionalFarm.isPresent()) {
      Crop modifiedCropDto = cropDto.toEntity();
      modifiedCropDto.setFarmId(farmId);
      Crop newCrop = cropService.newCrop(modifiedCropDto);
      return ResponseEntity.status(HttpStatus.CREATED).body(CropDto.fromEntity(newCrop));
    }
    throw new NotFoundException("Fazenda não encontrada!");
  }

  /**
   * comment.
   */

  @GetMapping("/{farmId}/crops")
  public ResponseEntity<List<CropDto>> getAllCrops(@PathVariable Long farmId) {
    Optional<Farm> optionalFarm = farmService.getFarmById(farmId);
    List<Crop> allCrops = cropService.getCropsByFarmId(farmId);
    List<CropDto> response = allCrops.stream().map(CropDto::fromEntity).toList();
    if (optionalFarm.isEmpty()) {
      throw new NotFoundException("Fazenda não encontrada!");
    }
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }
}