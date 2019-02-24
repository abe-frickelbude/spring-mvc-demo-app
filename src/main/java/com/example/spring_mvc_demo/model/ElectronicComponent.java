package com.example.spring_mvc_demo.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

/**
 * Base class for all electronic component representations, provides some common attributes, as well as an common
 * interface for the attributes essential to the indexer.
 * 
 * Most of the methods in this class are declared final, since overriding them may cause potential inconsistencies in
 * the essential attributes of a ElectronicComponent.
 * 
 * @author Ibragim Kuliev
 *
 */
@JsonPropertyOrder({
    "id", "type", "description", "manufacturer", "createDate"
})
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type")
@JsonSubTypes({
    @Type(name = Resistor.TYPE, value = Resistor.class),
    @Type(name = Capacitor.TYPE, value = Capacitor.class),
    @Type(name = Inductor.TYPE, value = Inductor.class),
    @Type(name = Diode.TYPE, value = Diode.class),
    @Type(name = BipolarTransistor.TYPE, value = BipolarTransistor.class),
    @Type(name = MosfetTransistor.TYPE, value = MosfetTransistor.class)
})
public abstract class ElectronicComponent {

    private Long id;

    private final String type;
    private String description;
    private Manufacturer manufacturer;

    private final OffsetDateTime createDate;

    public ElectronicComponent(final String type) {
        this.type = type;
        createDate = OffsetDateTime.now(ZoneOffset.UTC);
    }

    public final Long getId() {
        return id;
    }

    public final void setId(final Long id) {
        this.id = id;
    }

    /**
     * ElectronicComponent type, e.g. "bjt". This is used to determine the type attribute for the JSON data (for automatic
     * serialization/deserialization of any type in the {@linkplain ElectronicComponent} class hierarchy).
     */
    public final String getType() {
        return this.type;
    }

    public final String getDescription() {
        return description;
    }

    public final void setDescription(final String description) {
        this.description = description;
    }

    public final Manufacturer getManufacturer() {
        return manufacturer;
    }

    public final void setManufacturer(final Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public final OffsetDateTime getCreateDate() {
        return createDate;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        builder.append("\n\ttype: ");
        builder.append(getType());
        builder.append("\n\t");
        builder.append(ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE));
        builder.append("\n}");
        return builder.toString();
    }
}
