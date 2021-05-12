package com.motivation.team3.motivateme.model;

public class Home
{
    int image;
    String functionName;
    String titleHome;

    public Home(){}

    public Home(int image) {
        this.image = image;
    }

    public Home(String titleHome, int image)
    {
        this.titleHome = titleHome;
        this.image = image;
    }

    public Home(int image, String functionName, String titleHome) {
        this.image = image;
        this.functionName = functionName;
        this.titleHome= titleHome;
    }

    public Home(String titleHome) {
        this.titleHome=titleHome;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public String getTitleHome() {
        return titleHome;
    }

    public void setTitleHome(String titleHome) {
        this.titleHome = titleHome;
    }
}
