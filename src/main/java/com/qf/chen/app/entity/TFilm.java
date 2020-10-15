package com.qf.chen.app.entity;

import com.qf.chen.app.utils.ClassTableName;
import com.qf.chen.app.utils.FieldColName;

/*通过注解配置实体类和对应的数据库表名
可以通过properties外置配置*/
@ClassTableName(value = "t_film")
public class TFilm {
  private String id;
  private String actor;
  @FieldColName(value = "cate_log_name")
  private String cateLogName;
  @FieldColName(value = "cate_log_id")
  private String cateLogId;
  private double evaluation;
  private String image;
  private long isUse;
  @FieldColName(value = "log_name")
  private String locName;
  @FieldColName(value = "log_id")
  private String locId;
  private String name;
  @FieldColName(value = "on_decade")
  private String onDecade;
  private String plot;
  private String resolution;
  private String status;
  @FieldColName(value = "sub_class_name")
  private String subClassName;
  @FieldColName(value = "sub_class_id")
  private String subClassId;
  @FieldColName(value = "type_name")
  private String typeName;
  @FieldColName(value = "type_id")
  private String typeId;
  @FieldColName(value = "update_time")
  private String updateTime;
  @FieldColName(value = "is_vip")
  private long isVip;


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public String getActor() {
    return actor;
  }

  public void setActor(String actor) {
    this.actor = actor;
  }


  public String getCateLogName() {
    return cateLogName;
  }

  public void setCateLogName(String cateLogName) {
    this.cateLogName = cateLogName;
  }


  public String getCateLogId() {
    return cateLogId;
  }

  public void setCateLogId(String cateLogId) {
    this.cateLogId = cateLogId;
  }


  public double getEvaluation() {
    return evaluation;
  }

  public void setEvaluation(double evaluation) {
    this.evaluation = evaluation;
  }


  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }


  public long getIsUse() {
    return isUse;
  }

  public void setIsUse(long isUse) {
    this.isUse = isUse;
  }


  public String getLocName() {
    return locName;
  }

  public void setLocName(String locName) {
    this.locName = locName;
  }


  public String getLocId() {
    return locId;
  }

  public void setLocId(String locId) {
    this.locId = locId;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getOnDecade() {
    return onDecade;
  }

  public void setOnDecade(String onDecade) {
    this.onDecade = onDecade;
  }


  public String getPlot() {
    return plot;
  }

  public void setPlot(String plot) {
    this.plot = plot;
  }


  public String getResolution() {
    return resolution;
  }

  public void setResolution(String resolution) {
    this.resolution = resolution;
  }


  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }


  public String getSubClassName() {
    return subClassName;
  }

  public void setSubClassName(String subClassName) {
    this.subClassName = subClassName;
  }


  public String getSubClassId() {
    return subClassId;
  }

  public void setSubClassId(String subClassId) {
    this.subClassId = subClassId;
  }


  public String getTypeName() {
    return typeName;
  }

  public void setTypeName(String typeName) {
    this.typeName = typeName;
  }


  public String getTypeId() {
    return typeId;
  }

  public void setTypeId(String typeId) {
    this.typeId = typeId;
  }


  public String getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(String updateTime) {
    this.updateTime = updateTime;
  }


  public long getIsVip() {
    return isVip;
  }

  public void setIsVip(long isVip) {
    this.isVip = isVip;
  }

}
