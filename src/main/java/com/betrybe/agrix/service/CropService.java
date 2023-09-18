package com.betrybe.agrix.service;

import com.betrybe.agrix.exceptions.NotFoundException;
import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.Fertilizer;
import com.betrybe.agrix.models.repositories.CropRepository;
import com.betrybe.agrix.models.repositories.FertilizerRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * comment.
 */

@Service
public class CropService {
  private CropRepository cropRepository;
  private FertilizerRepository fertilizerRepository;

  @Autowired
  public CropService(CropRepository cropRepository, FertilizerRepository fertilizerRepository) {
    this.cropRepository = cropRepository;
    this.fertilizerRepository = fertilizerRepository;
  }

  public Crop newCrop(Crop crop) {
    return cropRepository.save(crop);
  }

  public List<Crop> getCropsByFarmId(Long farmId) {
    List<Crop> allCrops = getCrops();
    return allCrops.stream().filter(a -> a.getFarmId().equals(farmId)).toList();
  }

  public List<Crop> getCrops() {
    return cropRepository.findAll();
  }

  public Optional<Crop> getCropById(Long id) {
    return cropRepository.findById(id);
  }

  public List<Crop> searchCrops(LocalDate start, LocalDate end) {
    List<Crop> crops = this.cropRepository.findByHarvestDateBetween(start, end);
    return crops;
  }

  /**
   * comment.
   */

  public String addFertilizers(Long cropId, Long fertilizerId) {
    Optional<Crop> cropOptional = this.cropRepository.findById(cropId);
    if (cropOptional.isEmpty()) {
      throw new NotFoundException("Plantação não encontrada!");
    }
    Optional<Fertilizer> fertilizerOptional = this.fertilizerRepository.findById(fertilizerId);
    if (fertilizerOptional.isEmpty()) {
      throw new NotFoundException("Fertilizante não encontrado!");
    }
    Crop crop = cropOptional.get();
    Fertilizer fertilizer = fertilizerOptional.get();
    crop.getFertilizers().add(fertilizer);
    fertilizer.getCrops().add(crop);
    this.fertilizerRepository.save(fertilizer);
    return "Fertilizante e plantação associados com sucesso!";
  }

  /**
   * comment.
   */

  public List<Fertilizer> getCropFertilizers(Long id) {
    Optional<Crop> cropOptional = this.cropRepository.findById(id);
    if (cropOptional.isEmpty()) {
      throw new NotFoundException("Plantação não encontrada!");
    }
    Crop crop = cropOptional.get();
    List<Fertilizer> fertilizers = crop.getFertilizers();
    return fertilizers;
  }
}