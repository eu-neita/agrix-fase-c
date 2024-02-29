package com.betrybe.agrix.models.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entity class representing a farm.
 * This class is used to model farm data in the application.
 */
@Entity
@Table(name = "farm")
public class Farm {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String name;
  private double size;

  public Farm() {
  }

  /**
   * Constructs a new Farm instance.
   */
  public Farm(Long id, String name, double size) {
    this.id = id;
    this.name = name;
    this.size = size;
  }

  public Farm(Long id, String name, double platedArea, Long farmId) {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getSize() {
    return size;
  }

  public void setSize(double size) {
    this.size = size;
  }

}
