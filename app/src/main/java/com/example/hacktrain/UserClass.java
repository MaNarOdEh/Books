package com.example.hacktrain;

public class UserClass {
    String first_name;
    String last_name;
    String user_name;
    String birth_date;
    public UserClass(){

    }
    public UserClass(String first_name,String last_name,String user_name,String birth_date){
        this.first_name=first_name;
        this.last_name=last_name;
        this.user_name=user_name;
        this.birth_date=birth_date;

    }
    public String getBirth_date() {
        return birth_date;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
}
