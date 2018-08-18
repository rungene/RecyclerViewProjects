package com.rungenes.recyclerviewprojects;

public class ExampleItem {

    private  int mImageRescource;
    private String mText1;
    private String mText2;

    public ExampleItem(int mImageRescource, String mText1, String mText2) {
        this.mImageRescource = mImageRescource;
        this.mText1 = mText1;
        this.mText2 = mText2;
    }

    public int getmImageRescource() {
        return mImageRescource;
    }

    public String getmText1() {
        return mText1;
    }

    public String getmText2() {
        return mText2;
    }

    public void changeText1(String text){
        mText1 = text;

    }
}
