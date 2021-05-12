package com.motivation.team3.motivateme.model;

public class Quote
{
    String quote,autor;
    public Quote(){}

    public Quote(String quote, String autor)
    {
        this.quote = quote;
        this.autor = autor;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
}
