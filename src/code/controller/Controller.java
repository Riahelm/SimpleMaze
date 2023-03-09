package code.controller;

import code.view.OnNewPageListener;

public class Controller {
    private PageToShow mPageToShow;
    private OnNewPageListener mPageListener;
    public Controller() {
        mPageToShow = PageToShow.MENU;
    }

    public void setOnNewPage(OnNewPageListener l) {
        mPageListener = l;
        mPageListener.onNewPage(mPageToShow);
    }

    public void onQuitPressed() {
        System.exit(0);
    }

    public void onSettingsPressed() {
        mPageToShow = PageToShow.SETTINGS;
        mPageListener.onNewPage(mPageToShow);
    }

    public void onStartPressed() {
        mPageToShow = PageToShow.GAME;
        mPageListener.onNewPage(mPageToShow);
    }
}

