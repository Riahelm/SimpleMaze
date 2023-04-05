package code.controller;

import code.view.listener.OnNewPageListener;

public class Controller {
    //Singleton design pattern
    private static Controller instance;
    private PageToShow mPageToShow;
    private OnNewPageListener mPageListener;

    public static Controller getInstance(){
        if(instance == null){
            instance = new Controller();
        }
        return instance;
    }

    private Controller() {
        mPageToShow = PageToShow.MENU;
    }

    public void setOnNewPage(OnNewPageListener l) {
        mPageListener = l;
        mPageListener.onNewPage(mPageToShow);
    }

    public void onQuitPressed() {
        System.exit(0);
    }

    public void onMenuPressed(){
        mPageToShow = PageToShow.MENU;
        mPageListener.onNewPage(mPageToShow);
    }

    public void onStartPressed() {
        mPageToShow = PageToShow.GAME;
        mPageListener.onNewPage(mPageToShow);
    }

    public void onInstructionsPressed() {
        mPageToShow = PageToShow.INSTRUCTIONS;
        mPageListener.onNewPage(mPageToShow);
    }
}

