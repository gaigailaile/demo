package com.gai.designpattern.proxy_pattern;

public class RealImage implements Image {

    private String fileName;

    public RealImage(String fileName){
        this.fileName = fileName;
        loadFromDisk();
    }

    @Override
    public void display() {
        System.out.println("Display: " + fileName);
    }

    private void loadFromDisk(){
        System.out.println("Loading: " + fileName);
    }
}
