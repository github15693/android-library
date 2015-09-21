package com.innoria.khanhduong.library.Systems.Contacts;

import java.util.List;

/**
 * Created by KhanhD Duong on 9/6/2015.
 * Email: khanhduong@innoria.com
 */
public class Contact {
    private int id;
    private String name;
    private List<PhoneContact> phoneContacts;
    private List<EmailContact> emailContacts;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PhoneContact> getPhoneContacts() {
        return phoneContacts;
    }

    public void setPhoneContacts(List<PhoneContact> phoneContacts) {
        this.phoneContacts = phoneContacts;
    }

    public List<EmailContact> getEmailContacts() {
        return emailContacts;
    }

    public void setEmailContacts(List<EmailContact> emailContacts) {
        this.emailContacts = emailContacts;
    }

}
