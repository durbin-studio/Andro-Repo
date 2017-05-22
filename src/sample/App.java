package sample;

import com.google.googlejavaformat.java.Formatter;

/**
 * Created by arup3 on 5/16/2017.
 */
public class App {

    static App mInstance;
    Controller controller;
    FileUtils fileUtils;
    Formatter formatter;

    public static App getmInstance(){
        if(mInstance == null){
            mInstance = new App();
        }
        return mInstance;
    }

    public void setController(Controller _controller){
        controller = _controller;
    }

    public Controller getController(){
        return controller;
    }

    public FileUtils getFileUtils(){
        if(fileUtils == null){
            fileUtils = new FileUtils();
        }
        return fileUtils;
    }

    public void setFormatter(Formatter _formatter){
        this.formatter = _formatter;
    }

    public Formatter getFormatter(){
        return this.formatter;
    }
}
