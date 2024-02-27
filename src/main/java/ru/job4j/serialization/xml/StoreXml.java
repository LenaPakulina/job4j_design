package ru.job4j.serialization.xml;

import javax.xml.bind.annotation.*;
import java.util.Arrays;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "storeXml")
@XmlAccessorType(XmlAccessType.FIELD)
public class StoreXml {

    @XmlAttribute
    private boolean isOpen;

    @XmlAttribute
    private int employeesCount;

    @XmlAttribute
    private String name;

    private StoreManagerXml storeManager;

    @XmlElementWrapper(name = "products")
    @XmlElement(name = "product")
    private String[] products;

    public StoreXml() {

    }

    public StoreXml(boolean isOpen, int employeesCount, String name,
                    StoreManagerXml storeManager, String[] products) {
        this.isOpen = isOpen;
        this.employeesCount = employeesCount;
        this.name = name;
        this.storeManager = storeManager;
        this.products = products;
    }

    @Override
    public String toString() {
        return "Store{"
                + "isOpen=" + isOpen
                + ", employeesCount=" + employeesCount
                + ", name='" + name + '\''
                + ", storeManager=" + storeManager
                + ", products=" + Arrays.toString(products)
                + '}';
    }
}
