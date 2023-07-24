package com.leads.hrapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import lombok.Data;

@Data
@Entity
@Table(name="Employee")
public class Employee {
	
	@Id
    @GeneratedValue (strategy = GenerationType.AUTO)
	@JacksonXmlProperty(isAttribute = true)
    private int id;

    @JacksonXmlProperty(localName = "firstname")
    private String firstname;

    @JacksonXmlProperty(localName = "lastname")
    private String lastname;

    @JacksonXmlProperty(localName = "title")
    private String title;

    @JacksonXmlProperty(localName = "division")
    private String division;

    @JacksonXmlProperty(localName = "building")
    private String building;

    @JacksonXmlProperty(localName = "room")
    private String room;

}
