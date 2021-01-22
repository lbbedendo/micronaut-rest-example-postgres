package diecast.collector.api.domain;

import diecast.collector.api.enums.ModelScale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "model")
public class Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    @NotEmpty
    private String name;

    @Column(name = "model_year")
    private Integer modelYear;

    @Column(name = "scale")
    @Enumerated(value = EnumType.STRING)
    private ModelScale scale;

    @Column(name = "color_rgba")
    private String colorRgba;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "automaker_id")
    private Automaker automaker;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "collection_id")
    private Collection collection;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    private Brand brand;

    public Model() {}

    public Model(Integer id) {
        this.id = id;
    }

    public Model(@NotNull String name,
                 Integer modelYear,
                 ModelScale scale,
                 String colorRgba,
                 Automaker automaker,
                 Collection collection,
                 Brand brand) {
        this.name = name;
        this.modelYear = modelYear;
        this.scale = scale;
        this.colorRgba = colorRgba;
        this.automaker = automaker;
        this.collection = collection;
        this.brand = brand;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getModelYear() {
        return modelYear;
    }

    public void setModelYear(Integer modelYear) {
        this.modelYear = modelYear;
    }

    public ModelScale getScale() {
        return scale;
    }

    public void setScale(ModelScale scale) {
        this.scale = scale;
    }

    public String getColorRgba() {
        return colorRgba;
    }

    public void setColorRgba(String colorRgba) {
        this.colorRgba = colorRgba;
    }

    public Automaker getAutomaker() {
        return automaker;
    }

    public void setAutomaker(Automaker automaker) {
        this.automaker = automaker;
    }

    public Collection getCollection() {
        return collection;
    }

    public void setCollection(Collection collection) {
        this.collection = collection;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }
}
