package com.model;

import java.sql.Date;

public class Employee {
    private int id;
    private String ename;
    private String city;
    private String dept;
    private String dest;
    private String address;
    private Date doj;
    private Date dob;
    private double salary;
    private byte[] photo; 

    // Constructor
    public Employee(int id, String ename, String city, String dept, String dest, String address, Date doj, Date dob, double salary, byte[] photo) {
        this.id = id;
        this.ename = ename;
        this.city = city;
        this.dept = dept;
        this.dest = dest;
        this.address = address;
        this.doj = doj;
        this.dob = dob;
        this.salary = salary;
        this.photo = photo;
    }

    // Getter and Setter methods
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getDest() {
        return dest;
    }

    public void setDest(String dest) {
        this.dest = dest;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDoj() {
        return doj;
    }

    public void setDoj(Date doj) {
        this.doj = doj;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "Employee [id=" + id + ", ename=" + ename + ", city=" + city + ", dept=" + dept + ", dest=" + dest
                + ", address=" + address + ", doj=" + doj + ", dob=" + dob + ", salary=" + salary + "]";
    }
}
