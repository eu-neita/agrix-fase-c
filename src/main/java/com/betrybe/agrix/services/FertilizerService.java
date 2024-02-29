package com.betrybe.agrix.services;

import com.betrybe.agrix.exeption.FertilizerException;
import com.betrybe.agrix.models.entities.Fertilizer;
import com.betrybe.agrix.models.repositories.FertilizerRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * FertilizerService.
 */
@Service
public class FertilizerService {

  private final FertilizerRepository fertilizerRepository;

  @Autowired

  public FertilizerService(FertilizerRepository fertilizerRepository) {
    this.fertilizerRepository = fertilizerRepository;
  }

  /**
   * create fertilizer.
   */
  public Fertilizer create(Fertilizer fertilizer) {
    return fertilizerRepository.save(fertilizer);
  }

  /**
   * get all fertilizer.
   */
  public List<Fertilizer> getAll() {
    return fertilizerRepository.findAll();
  }

  /**
   * getById fertilizer.
   */
  public Fertilizer getById(Long id) {
    Optional<Fertilizer> fertilizerOp = fertilizerRepository.findById(id);
    if (fertilizerOp.isEmpty()) {
      throw new FertilizerException("Fertilizante não encontrado!");
    }
    return fertilizerOp.get();
  }

}
