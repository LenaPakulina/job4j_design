package ru.job4j.serialization.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "storeManagerXml")
public class StoreManagerXml {

    @XmlAttribute
    private String name;

    @XmlAttribute
    private int experience;

    public StoreManagerXml() {
    }

    public StoreManagerXml(String name, int experience) {
        this.name = name;
        this.experience = experience;
    }

    @Override
    public String toString() {
        return "StoreManager{"
                + "name='" + name + '\''
                + ", experience=" + experience
                + '}';
    }
}

