package edu.neu.my12306.entity;

public class Account {
    private static Account account;
    private String username;
    private String name;
    private String certificateType;
    private String certificateNumber;
    private String passengerType;
    private String phoneNumber;
    private String password;

    public Account() {
    }

    public Account(String username, String name, String certificateType, String certificateNumber, String passengerType, String phoneNumber) {
        this.username = username;
        this.name = name;
        this.certificateType = certificateType;
        this.certificateNumber = certificateNumber;
        this.passengerType = passengerType;
        this.phoneNumber = phoneNumber;
    }

    public static Account getAccount(){
        if(account==null){
            account = new Account();
        }
        return  account;
    }

    public static void setAccount(Account ac) {
        if(account==null){
            account = new Account();
        }
        account.setUsername(ac.getUsername());
        account.setName(ac.getName());
        account.setCertificateType(ac.getCertificateType());
        account.setCertificateNumber(ac.getCertificateNumber());
        account.setPassengerType(ac.getPassengerType());
        account.setPhoneNumber(ac.getPhoneNumber());
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCertificateType() {
        return certificateType;
    }

    public void setCertificateType(String certificateType) {
        this.certificateType = certificateType;
    }

    public String getCertificateNumber() {
        return certificateNumber;
    }

    public void setCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber;
    }

    public String getPassengerType() {
        return passengerType;
    }

    public void setPassengerType(String passengerType) {
        this.passengerType = passengerType;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
