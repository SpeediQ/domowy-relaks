package pl.domowyrelaks.domowyrelaks.model;

import jakarta.persistence.*;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "DESCRIPTION")
    private String desc;
    @OneToOne
    @JoinColumn(name = "COMPONENT_ID")
    private ProductComponent component;
    private String title;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "VISIT_ID")
    private Visit visit;
    private double value;
    private int preparationTime;

    public Product() {
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Product(ProductComponent component) {
        copyFieldsFromProductComponent(component);
    }

    private void copyFieldsFromProductComponent(ProductComponent component) {
        setDesc(component.getDesc());
        setTitle(component.getTitle());
        setValue(component.getValue());
        setPreparationTime(component.getPreparationTime());
        setComponent(component);
    }

    public Visit getVisit() {
        return visit;
    }

    public void setVisit(Visit visit) {
        this.visit = visit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public ProductComponent getComponent() {
        return component;
    }

    public int getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(int preparationTime) {
        this.preparationTime = preparationTime;
    }

    public void setComponent(ProductComponent component) {
        this.component = component;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
