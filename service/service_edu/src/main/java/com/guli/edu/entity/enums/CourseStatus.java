package com.guli.edu.entity.enums;




public enum CourseStatus {
     Draft("Draft"),
    Normal("Normal");

     private String value;
     CourseStatus(String value){
         this.value=value;
     }
     public String value(){
         return value;
     }

}

