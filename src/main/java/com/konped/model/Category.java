package com.konped.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class Category {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @Column
  @NotNull
  @Size(min = 3, max = 12)
  private String name;
  @Column
  @NotNull
  private String color;

  public Category() {

  }
  public Category(int id, String name, String color) {
    this.id = id;
    this.name = name;
    this.color = color;
  }

  /* GETTERS AND SETTERS */
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public void setName(String name) {
    this.name = name;
  }
}
