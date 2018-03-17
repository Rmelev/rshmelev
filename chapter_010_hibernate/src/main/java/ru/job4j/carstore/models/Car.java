package ru.job4j.carstore.models;

import java.util.Objects;

/**
 * car.
 */
public class Car {
    /**
     * id.
     */
    private int id;
    /**
     * color.
     */
    private String color;
    /**
     * model.
     */
    private Model model;
    /**
     * body.
     */
    private Body body;
    /**
     * transmission.
     */
    private Transmission transmission;
    /**
     * engine.
     */
    private Engine engine;
    /**
     * default constructor.
     */
    public Car() {
    }

    /**
     * @return - id.
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id - id.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return - color.
     */
    public String getColor() {
        return color;
    }

    /**
     *
     * @param color - color.
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     *
     * @return - model.
     */
    public Model getModel() {
        return model;
    }

    /**
     *
     * @param model - model.
     */
    public void setModel(Model model) {
        this.model = model;
    }

    /**
     *
     * @return - body.
     */
    public Body getBody() {
        return body;
    }

    /**
     *
     * @param body - body.
     */
    public void setBody(Body body) {
        this.body = body;
    }

    /**
     *
     * @return - transmission.
     */
    public Transmission getTransmission() {
        return transmission;
    }

    /**
     *
     * @param transmission - transmission.
     */
    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    /**
     *
     * @return - engine.
     */
    public Engine getEngine() {
        return engine;
    }

    /**
     *
     * @param engine - engine.
     */
    public void setEngine(Engine engine) {
        this.engine = engine;
    }
    /**
     * @param o - obj.
     * @return - true, if equals
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Car car = (Car) o;
        return id == car.id
                && Objects.equals(color, car.color)
                && Objects.equals(model, car.model)
                && Objects.equals(body, car.body)
                && Objects.equals(transmission, car.transmission)
                && Objects.equals(engine, car.engine);
    }
    /**
     * @return - hashcode.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, color, model, body, transmission, engine);
    }
    /**
     * @return - string representation.
     */
    @Override
    public String toString() {
        return "Car{"
                + "id=" + id
                + ", color='" + color + '\''
                + ", model=" + model
                + ", body=" + body
                + ", transmission=" + transmission
                + ", engine=" + engine
                + '}';
    }
}
