package com.example.prono_doctor_app;

public class patient_details {
    public String mob_no;
    public String Name;
    public int age;
    public String city;
    public String state;
    //int problem_id;
    public patient_details(String mob_no,String Name,int age,String city,String state)
    {
        this.mob_no=mob_no;
        this.Name=Name;
        this.age=age;
        this.city=city;
        this.state=state;
       // this.problem_id=problem_details_id;


    }
    public patient_details()
    {

    }
}
