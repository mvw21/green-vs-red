package project;

import project.controller.Controller;
import project.controller.ControllerImpl;
import project.controller.Engine;
import project.controller.EngineImpl;

public class Main {
    public static void main(String[] args) {

        Controller controller = new ControllerImpl();
        Engine engine = new EngineImpl(controller);
        engine.run();

    }
}
