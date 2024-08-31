package com.application.javafxtest.model;

public class Lifestyles {
  private int id;
  private int creatorId;
  private String title;
  private String description;
  private String content;
  private boolean isPublic;
  private int downloads;
  private double averageRating;

  public Lifestyles(int id, int creatorId, String title, String description, String content,
                    boolean isPublic) {
    this.id = id;
    this.creatorId = creatorId;
    this.title = title;
    this.description = description;
    this.content = content;
    this.isPublic = isPublic;
  }

  public int getId() { return id; }
  public int getCreatorId() { return creatorId; }
  public String getTitle() { return title; }
  public String getDescription() { return description; } 
  public String getContent() {return content; }
  public boolean isPublic() { return isPublic; }
  public int getDownload() { return downloads; }
  public double getAverageRating() { return averageRating; }

  public void setId(int id) { this.id = id; }
  public void setCreatorId(int creatorId) { this.creatorId = creatorId; }
  public void setTitle(String title) { this.title = title; }
  public void setDescription(String description) { this.description = description; }
  public void setContent(String content) { this.content = content; }
  public void setIsPublic(boolean isPublic) { this.isPublic = isPublic; }
  public void setDownload(int downloads) { this.downloads = downloads; }
  public void setAverageRating(double averageRating) { this.averageRating = averageRating; }

  public void incrementDownloads() { this.downloads++; }
  public void updateAverateRating(double newRating) {
    //still need to figure out new logic for this
  }

}
