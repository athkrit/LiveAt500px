package com.example.liveat500px.DataType;

import android.os.Bundle;

public class MutableInteger {
    private int value;

    public MutableInteger(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
    public Bundle onSaveInstanceState(){
        Bundle bundle =new Bundle();
        bundle.putInt("Value",value);
        return bundle;
    }
    public void onRestoreInstanceState(Bundle savedInstanceState){
        value= savedInstanceState.getInt("Value");
    }
}
