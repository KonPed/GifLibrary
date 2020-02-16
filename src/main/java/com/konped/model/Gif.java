package com.konped.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Gif {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @Column
  private String name;
  @Column
  private String description;
  @Column
  private int categoryId;
  @Column
  private LocalDate dateUploaded;
  @Column
  private String username;
  @Column
  private boolean favorite;

  public Gif() {

  }
  public Gif(String name, String description, int categoryId, LocalDate dateUploaded,
             String username, boolean favorite) {
    this.name = name;
    this.description = description;
    this.categoryId = categoryId;
    this.dateUploaded = dateUploaded;
    this.username = username;
    this.favorite = favorite;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(int categoryId) {
    this.categoryId = categoryId;
  }

  public LocalDate getDateUploaded() {
    return dateUploaded;
  }

  public void setDateUploaded(LocalDate dateUploaded) {
    this.dateUploaded = dateUploaded;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public boolean isFavorite() {
    return favorite;
  }

  public void setFavorite(boolean favorite) {
    this.favorite = favorite;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
