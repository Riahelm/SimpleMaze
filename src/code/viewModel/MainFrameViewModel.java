package code.viewModel;

import code.viewModel.observers.MainFrameObserver;

public class MainFrameViewModel {
    //Singleton design pattern
    private static MainFrameViewModel instance;
    private PageToShow mPageToShow;
    private MainFrameObserver mPageObserver;

    public static MainFrameViewModel getInstance(){
        if(instance == null){
            instance = new MainFrameViewModel();
        }
        return instance;
    }

    private MainFrameViewModel() {
        mPageToShow = PageToShow.MENU;
    }

    public void setMainFrameObserver(MainFrameObserver l) {
        mPageObserver = l;
        mPageObserver.onNewPage(mPageToShow);
    }

    public void onQuitPressed() {
        System.exit(0);
    }

    public void onMenuPressed(){
        mPageToShow = PageToShow.MENU;
        mPageObserver.onNewPage(mPageToShow);
    }

    public void onStartPressed() {
        mPageToShow = PageToShow.GAME;
        mPageObserver.onNewPage(mPageToShow);
    }

    public void onInstructionsPressed() {
        mPageToShow = PageToShow.INSTRUCTIONS;
        mPageObserver.onNewPage(mPageToShow);
    }
}

