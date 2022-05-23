package com.example.afinal.viewmodels;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

public class MainViewModel {
    private final MainViewModel model;

    public MainViewModel(MainViewModel model) {
        this.model = model;
    }

    public void refresh(){

    }
}
