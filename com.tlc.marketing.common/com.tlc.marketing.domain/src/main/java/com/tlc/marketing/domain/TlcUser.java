package com.tlc.marketing.domain;

import java.sql.Timestamp;


/**
 * Description: 用户
 * Copyright (c) TLC.
 * All Rights Reserved.
 * @version 1.0 2016年8月1日 下午1:19:28 by chepeiqing (chepeiqing@icloud.com)
 */
public class TlcUser extends BaseCodeModel {
  /** 用户序号 **/
  private Integer userSeq;
  /** 用户id **/
  private String userId;
  /** 用户名 **/
  private String userName;
  /** 密码 **/
  private String password;
  /** 性别 **/
  private char sex;
  /** 年龄 **/
  private Integer age;
  /** 证件类型 **/
  private char idType;
  /** 证件号码 **/
  private char idNo;
  /** 手机 **/
  private char mobilePhone;
  /** 电话 **/
  private String phone;
  /** 渠道ID **/
  private String channelId;
  /** 地址 **/
  private String addr;
  /** 角色ID **/
  private Integer roleSeq;
  /** 创建时间 **/
  private Timestamp creteTime;


  public Timestamp getCreteTime() {
    return creteTime;
  }

  public void setCreteTime(Timestamp creteTime) {
    this.creteTime = creteTime;
  }

  public Integer getRoleSeq() {
    return roleSeq;
  }

  public void setRoleSeq(Integer roleSeq) {
    this.roleSeq = roleSeq;
  }

  public Integer getUserSeq() {
    return userSeq;
  }

  public void setUserSeq(Integer userSeq) {
    this.userSeq = userSeq;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public char getSex() {
    return sex;
  }

  public void setSex(char sex) {
    this.sex = sex;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public char getIdType() {
    return idType;
  }

  public void setIdType(char idType) {
    this.idType = idType;
  }

  public char getIdNo() {
    return idNo;
  }

  public void setIdNo(char idNo) {
    this.idNo = idNo;
  }

  public char getMobilePhone() {
    return mobilePhone;
  }

  public void setMobilePhone(char mobilePhone) {
    this.mobilePhone = mobilePhone;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getChannelId() {
    return channelId;
  }

  public void setChannelId(String channelId) {
    this.channelId = channelId;
  }

  public String getAddr() {
    return addr;
  }

  public void setAddr(String addr) {
    this.addr = addr;
  }
}
