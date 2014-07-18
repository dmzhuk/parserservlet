package com.epam.phones.entity;

/**
 * Created by Dmitry on 09.07.2014.
 */
public class Phone {

    public enum SDCard { microSD, microSDHC, microSDXC, miniSD, no };
    private String name;
    private double size;
    private String manuf;
    private String os;
    private String display;
    private SDCard sd;
    private int manufDate;
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append( "name: ").append( name ).append(", size: ").append( size ).append(" manufacturer: ")
                .append( manuf ).append( ", OS: ").append( os ).append(", display type: ").append( display )
                .append( ", SD card: ").append( sd ).append( ", manufacture date: ").append( manufDate ).append("\r\n");
        return sb.toString();
    }
    public int getManufDate() {
        return manufDate;
    }

    public void setManufDate(int manufDate) {
        this.manufDate = manufDate;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public String getManuf() {
        return manuf;
    }

    public void setManuf(String manuf) {
        this.manuf = manuf;
    }

    public String getOs() {
        return os;
    }

    public String getDisplay() {
        return display;
    }

    public SDCard getSd() {
        return sd;
    }

    public void setSd( SDCard sd) {
        this.sd = sd;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public void setOs(String os) {
        this.os = os;
    }
}
