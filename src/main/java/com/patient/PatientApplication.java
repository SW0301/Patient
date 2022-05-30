package com.patient;

import com.patient.model.Patient;


import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;


public class PatientApplication {
    public static void main(String[] args) {
        if (args != null && args.length != 0){
            Parse.parse(args);
        }
        else {
            System.out.println("Введите параметры");
        }


    }
}
