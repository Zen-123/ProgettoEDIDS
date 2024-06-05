import UI.UI;
import UI.visibilityManager;
import  UI.choiceHandler;

public class MainGame {
    UI ui = new UI();
    visibilityManager manager = new visibilityManager(ui);
    choiceHandler handler = new choiceHandler(ui);

    public static void main(String[] args) throws Exception {
        new MainGame();
    }

    public MainGame(){
        ui.createUI();
        manager.showMenuScreen();
        handler.setLoad();
    }


}

