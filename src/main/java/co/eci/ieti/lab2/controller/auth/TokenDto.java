package co.eci.ieti.lab2.controller.auth;

import java.util.Date;

public class TokenDto {

    String token;
    Date expirationDate;

    public TokenDto(String token, Date expirationDate){
        this.token = token;
        this.expirationDate = expirationDate;
    }

    public String getToken(){
        return token;
    }

    public Date getExpirationDate(){
        return expirationDate;
    }

    public void setToken(String token){
        this.token = token;
    }

    public void setExpirationDate(Date expirationDate){
        this.expirationDate = expirationDate;
    }
    
}
