package com.example.firebase.Model;

public class Product {
    String title;
    String image;
    String content;
    String architecture;
    String codeone;
    String codetwo;
    String codethree;
    String codefour;

    public Product() {

    }

    public Product(String title, String image, String content) {
        this.title = title;
        this.image = image;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getArchitecture() {
        return architecture;
    }

    public void setArchitecture(String architecture) {
        this.architecture = architecture;
    }

    public String getCodeone() {
        return codeone;
    }

    public void setCodeone(String codeone) {
        this.codeone = codeone;
    }

    public String getCodetwo() {
        return codetwo;
    }

    public void setCodetwo(String codetwo) {
        this.codetwo = codetwo;
    }

    public String getCodethree() {
        return codethree;
    }

    public void setCodethree(String codethree) {
        this.codethree = codethree;
    }

    public String getCodefour() {
        return codefour;
    }

    public void setCodefour(String codefour) {
        this.codefour = codefour;
    }
}
