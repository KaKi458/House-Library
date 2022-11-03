//package com.houselibrary.model;
//
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//
//@Entity
//public class Author {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private int id;
//    private String firstName;
//    private String lastName;
//
//    public Author(String firstName, String lastName) {
//        this.firstName = firstName;
//        this.lastName = lastName;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//
//    @Override
//    public String toString() {
//        return "Author{" +
//                "firstName='" + firstName + '\'' +
//                ", lastName='" + lastName + '\'' +
//                '}';
//    }
//}
