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


        Scanner in = new Scanner(System.in);
        System.out.println("Введите название файла и способ сортировки результата");
        String fileSort = in.nextLine();
        fileSort = fileSort.trim();                                                  //если присутствуют пробелы в начале и конце строки
        String[] params = fileSort.split(" ", 2);

        if(!params[0].contains(".xml")){                                         //не обязательно указывать формат файла
           params[0] = params[0]+".xml";
        }

        Document doc;                                                               //открываем документ
        try{
            doc = buildDocument(params[0]);
        } catch (Exception e){
            System.out.println("Open parsing error " + e.toString());
            return;
        }

        Node patientsNode = doc.getFirstChild();                                    //парсим
        NodeList patientsChild = patientsNode.getChildNodes();

        List<Patient> patientList = new ArrayList<>();
        for(int i = 0; i < patientsChild.getLength(); i++){
            if(patientsChild.item(i).getNodeType()!= Node.ELEMENT_NODE){
                continue;
            }
            NodeList patientChild = patientsChild.item(i).getChildNodes();

            String firstName ="";
            String middleName="";
            String lastName="";
            LocalDate birthday;
            int age = 0;
            String gender="";
            String phone="";

            for (int j = 0; j < patientChild.getLength(); j++){
                if(patientChild.item(j).getNodeType()!= Node.ELEMENT_NODE){
                    continue;
                }
                switch (patientChild.item(j).getNodeName()){
                    case "first_name":{ firstName = patientChild.item(j).getTextContent(); break;}
                    case "middle_name":{ middleName = patientChild.item(j).getTextContent();break;}
                    case "last_name":{ lastName = patientChild.item(j).getTextContent();break;}
                    case "birthday":{ birthday = LocalDate.parse(patientChild.item(j).getTextContent());
                        LocalDate currentDate = LocalDate.now();
                        age = Period.between(birthday, currentDate).getYears();
                        break;}
                    case "gender":{ gender = patientChild.item(j).getTextContent();break;}
                    case "phone":{ phone = patientChild.item(j).getTextContent();break;}
                }
            }
            Patient patient = new Patient(firstName, middleName, lastName, age, gender, phone);
            patientList.add(patient);
        }


        if(params.length == 1){                                         //проверяем входные параметры
            for(int i =0; i < patientList.size();i++){
                System.out.println(patientList.get(i));
            }
        } else if (params[1].trim().equals("name")) {
            Comparator<Patient> patientComparator = Comparator.comparing(Patient::getLastName);
            Collections.sort(patientList, patientComparator);
            for(int i =0; i < patientList.size();i++){
                System.out.println(patientList.get(i));
            }
        }
        else if (params[1].trim().equals("age")){
            Comparator<Patient> patientComparator = Comparator.comparing(Patient::getAge);
            Collections.sort(patientList, patientComparator);
            for(int i =0; i < patientList.size();i++){
                System.out.println(patientList.get(i));
            }

        }
        else {
            System.out.println("Указан неправильный параметр сортировки");
        }

    }




    private static Document buildDocument(String fileName) throws Exception{
        File file = new File(fileName);
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        return dbf.newDocumentBuilder().parse(file);
    }

}
