package org.fm.ydummyremote;

import com.pi4j.io.gpio.*;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import spark.template.velocity.*;

import spark.*;

import static spark.Spark.*;


public class Main {

    private final static String EmptyBody = "";

    private final static ArrayList AvailableGpios = new ArrayList() {{
        add(new GpioModel("1", "Chrome Cast"));
        add(new GpioModel("2", "Lights"));
        add(new GpioModel("3", "Speakers"));
        add(new GpioModel("4", "TV"));
    }};

    private final static Logger logger = LoggerFactory.getLogger(Main.class);

    private static GpioController GpioController = GpioFactory.getInstance();

    private static final HashMap<String, GpioPinDigitalOutput> IndexToGpioOut = new HashMap<>();


    /************************************
     * Main class
     * @param args [not used]
     ***********************************/

    public static void main(String[] args) {

        staticFiles.location("/public");

        before((req, res) -> logger.info("Starting elaboration for: " + req.requestMethod() + " " + req.pathInfo() + " [" + req.params() + "]"));
        exception(Exception.class, (exc, req, res) -> logger.error("Exception elaborating: " + req.url() + " [" + req.params() + "]", exc));

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("availableGpios", AvailableGpios);

            return renderTemplate("velocity/index.vm", model);
        });

        put("/gpio/:index/on", (ICRoute) (req) -> {
            String index = req.params("index");

            final GpioPinDigitalOutput pin = getGpioOutByIndex(index);
            pin.setShutdownOptions(true, PinState.LOW);

            pin.low();
        });

        put("/gpio/:index/off", (ICRoute) (req) -> {
            String index = req.params("index");

            final GpioPinDigitalOutput pin = getGpioOutByIndex(index);
            pin.setShutdownOptions(true, PinState.HIGH);

            pin.toggle();
        });
    }


    /************************************
     * Utility methods
     ***********************************/

    private static GpioPinDigitalOutput getGpioOutByIndex(String index) {
        GpioPinDigitalOutput out;

        if(IndexToGpioOut.containsKey(index)) {
            out = IndexToGpioOut.get(index);
        }
        else {
            switch (index) {
                case "0" :
                    out = GpioController.provisionDigitalOutputPin(RaspiPin.GPIO_00, "Gpio_00", PinState.HIGH);
                    break;
                case "1" :
                    out = GpioController.provisionDigitalOutputPin(RaspiPin.GPIO_01, "Gpio_01", PinState.HIGH);
                    break;
                case "2" :
                    out = GpioController.provisionDigitalOutputPin(RaspiPin.GPIO_02, "Gpio_02", PinState.HIGH);
                    break;
                case "3" :
                    out = GpioController.provisionDigitalOutputPin(RaspiPin.GPIO_03, "Gpio_03", PinState.HIGH);
                    break;
                case "4" :
                    out = GpioController.provisionDigitalOutputPin(RaspiPin.GPIO_04, "Gpio_04", PinState.HIGH);
                    break;
                case "5" :
                    out = GpioController.provisionDigitalOutputPin(RaspiPin.GPIO_05, "Gpio_05", PinState.HIGH);
                    break;
                case "6" :
                    out = GpioController.provisionDigitalOutputPin(RaspiPin.GPIO_06, "Gpio_06", PinState.LOW);
                    break;
                case "7" :
                    out = GpioController.provisionDigitalOutputPin(RaspiPin.GPIO_07, "Gpio_07", PinState.LOW);
                    break;
                case "8" :
                    out = GpioController.provisionDigitalOutputPin(RaspiPin.GPIO_08, "Gpio_08", PinState.LOW);
                    break;
                case "9" :
                    out = GpioController.provisionDigitalOutputPin(RaspiPin.GPIO_09, "Gpio_09", PinState.LOW);
                    break;
                case "10":
                    out = GpioController.provisionDigitalOutputPin(RaspiPin.GPIO_10, "gpio_10", PinState.LOW);
                    break;
                case "11":
                    out = GpioController.provisionDigitalOutputPin(RaspiPin.GPIO_11, "gpio_11", PinState.LOW);
                    break;
                case "12":
                    out = GpioController.provisionDigitalOutputPin(RaspiPin.GPIO_12, "gpio_12", PinState.LOW);
                    break;
                case "13":
                    out = GpioController.provisionDigitalOutputPin(RaspiPin.GPIO_13, "gpio_13", PinState.LOW);
                    break;
                case "14":
                    out = GpioController.provisionDigitalOutputPin(RaspiPin.GPIO_14, "gpio_14", PinState.LOW);
                    break;
                case "15":
                    out = GpioController.provisionDigitalOutputPin(RaspiPin.GPIO_15, "gpio_15", PinState.LOW);
                    break;
                case "16":
                    out = GpioController.provisionDigitalOutputPin(RaspiPin.GPIO_16, "gpio_16", PinState.LOW);
                    break;
                case "17":
                    out = GpioController.provisionDigitalOutputPin(RaspiPin.GPIO_17, "gpio_17", PinState.LOW);
                    break;
                case "18":
                    out = GpioController.provisionDigitalOutputPin(RaspiPin.GPIO_18, "gpio_18", PinState.LOW);
                    break;
                case "19":
                    out = GpioController.provisionDigitalOutputPin(RaspiPin.GPIO_19, "gpio_19", PinState.LOW);
                    break;
                case "20":
                    out = GpioController.provisionDigitalOutputPin(RaspiPin.GPIO_20, "gpio_20", PinState.LOW);
                    break;
                case "21":
                    out = GpioController.provisionDigitalOutputPin(RaspiPin.GPIO_21, "gpio_21", PinState.LOW);
                    break;
                case "22":
                    out = GpioController.provisionDigitalOutputPin(RaspiPin.GPIO_22, "gpio_22", PinState.LOW);
                    break;
                case "23":
                    out = GpioController.provisionDigitalOutputPin(RaspiPin.GPIO_23, "gpio_23", PinState.LOW);
                    break;
                case "24":
                    out = GpioController.provisionDigitalOutputPin(RaspiPin.GPIO_24, "gpio_24", PinState.LOW);
                    break;
                case "25":
                    out = GpioController.provisionDigitalOutputPin(RaspiPin.GPIO_25, "gpio_25", PinState.LOW);
                    break;
                case "26":
                    out = GpioController.provisionDigitalOutputPin(RaspiPin.GPIO_26, "gpio_26", PinState.LOW);
                    break;
                case "27":
                    out = GpioController.provisionDigitalOutputPin(RaspiPin.GPIO_27, "gpio_27", PinState.LOW);
                    break;
                case "28":
                    out = GpioController.provisionDigitalOutputPin(RaspiPin.GPIO_28, "gpio_28", PinState.LOW);
                    break;
                case "29":
                    out = GpioController.provisionDigitalOutputPin(RaspiPin.GPIO_29, "gpio_29", PinState.LOW);
                    break;
                case "30":
                    out = GpioController.provisionDigitalOutputPin(RaspiPin.GPIO_30, "gpio_30", PinState.LOW);
                    break;
                case "31":
                    out = GpioController.provisionDigitalOutputPin(RaspiPin.GPIO_31, "gpio_31", PinState.LOW);
                    break;
                default:
                    throw new IllegalArgumentException("DigitalOutputPin not found for index " + index);
            }

            IndexToGpioOut.put(index, out);
        }

        return out;
    }

    private static String renderTemplate(String template, Map model) {
        return new VelocityTemplateEngine().render(new ModelAndView(model, template));
    }


    /************************************
     * Utility Interfaces and Classes
     ***********************************/

    @FunctionalInterface
    private interface ICRoute extends Route {
        default Object handle(Request request, Response response) throws Exception {
            handle(request);

            response.status(201);

            return EmptyBody;
        }

        void handle(Request request) throws Exception;
    }

    @Data
    public static class GpioModel {
        String index;
        String label;

        public GpioModel(String index, String label) {
            this.index = index;
            this.label = label;
        }
    }
}