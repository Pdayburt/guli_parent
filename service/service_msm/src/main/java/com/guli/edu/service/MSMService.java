package com.guli.edu.service;

public interface MSMService {

    Boolean sendSMS(String phoneNumber, String verificationCode, Integer timeOut);
}
