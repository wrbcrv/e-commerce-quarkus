package dev.application.form;

import org.jboss.resteasy.annotations.providers.multipart.PartType;

import jakarta.ws.rs.FormParam;

public class HardwareImageForm {

  @FormParam("id")
  private Long id;

  @FormParam("imageName")
  private String imageName;

  @FormParam("image")
  @PartType("application/octet-stream")
  private byte[] image;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getImageName() {
    return imageName;
  }

  public void setImageName(String imageName) {
    this.imageName = imageName;
  }

  public byte[] getImage() {
    return image;
  }

  public void setImage(byte[] image) {
    this.image = image;
  }
}