package com.betrybe.agrix.controllers;

import com.betrybe.agrix.dto.CropDto;
import com.betrybe.agrix.dto.FertilizerDto;
import com.betrybe.agrix.exceptions.NotFoundException;
import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.Fertilizer;
import com.betrybe.agrix.service.CropService;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * comment.
 */

@RestController
@RequestMapping("/crops")
public class CropController {
  private CropService cropService;

  @Autowired
  public CropController(CropService cropService) {
    this.cropService = cropService;
  }

  /**
   * comment.
   */

  @GetMapping
  public ResponseEntity<List<CropDto>> getAllCrops() {
    List<Crop> allCrops = cropService.getCrops();
    List<CropDto> response = allCrops.stream().map(CropDto::fromEntity).toList();
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  /**
   * comment.
   */

  @GetMapping("/{cropId}")
  public ResponseEntity<CropDto> getCropById(@PathVariable Long cropId) {
    Optional<Crop> crop = cropService.getCropById(cropId);
    if (crop.isPresent()) {
      return ResponseEntity.status(HttpStatus.OK).body(CropDto.fromEntity(crop.get()));
    }
    throw new NotFoundException("Plantação não encontrada!");
  }

  /**
   * comment.
   */
  @GetMapping("/search")
  public ResponseEntity<List<CropDto>> searchCrops(
      @RequestParam(name = "start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
      @RequestParam(name = "end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
    List<Crop> crops = cropService.searchCrops(start, end);
    List<CropDto> cropsDto = crops.stream().map(CropDto::fromEntity).toList();
    return ResponseEntity.status(HttpStatus.OK).body(cropsDto);
  }

  @PostMapping("/{cropId}/fertilizers/{fertilizerId}")
  public ResponseEntity<String> addFertilizer(
      @PathVariable Long cropId,
      @PathVariable Long fertilizerId) {
    String result = this.cropService.addFertilizers(cropId, fertilizerId);
    return ResponseEntity.status(HttpStatus.CREATED).body(result);
  }

  /**
   * comment.
   */

  @GetMapping("/{cropId}/fertilizers")
  public ResponseEntity<List<FertilizerDto>> getCropFertilizers(@PathVariable Long cropId) {
    List<Fertilizer> fertilizers = this.cropService.getCropFertilizers(cropId);
    List<FertilizerDto> fertilizersDto = fertilizers.stream()
        .map(FertilizerDto::fromFertilizer).toList();
    return ResponseEntity.ok(fertilizersDto);
  }
}