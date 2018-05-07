package controller;

import exceptions.ConsultationException;
import exceptions.PatientException;
import junit.framework.TestCase;
import model.Patient;
import org.junit.Test;
import repository.Repository;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;


public class DoctorControllerTest {
    DoctorController ctrl;
    Repository rep;

    @org.junit.Before
    public void setUp() throws Exception {
        rep = new Repository("FilePatients.txt", "FileConsultations.txt");
        ctrl = new DoctorController(rep);
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    @org.junit.Test
    public void addPatient() throws PatientException {
        String name = "andrada";
        String ssn = "2971106123456";
        String address = "braila";

        int nrBefore = rep.getPatientList().size();
        ctrl.addPatient(new Patient(name, ssn, address));
        int nrAfter = rep.getPatientList().size();
        assertEquals(nrBefore , nrAfter);
    }

    @Test
    public void checkIfPatientCNPHasProperLength() {
        String name = "radu";
        String ssn = "19611071456789";
        String address = "bacau";

        String thrown = "";

        try {
            ctrl.addPatient(new Patient(name, ssn, address));
        } catch (PatientException e) {
            thrown = e.getMessage();
        }

        assertEquals(thrown, "SSN has the length != 13");


    }

    @Test
    public void checkIfPatientCNPHasProperFormat() {
        String name = "radu";
        String ssn = "a196110714567";
        String address = "bacau";

        String thrown = "";

        try {
            ctrl.addPatient(new Patient(name, ssn, address));
        } catch (PatientException e) {
            thrown = e.getMessage();
        }
        assertEquals(thrown, "The \"ssn\" field has an invalid format!");
    }

    @Test
    public void checkIfPatientNameIsNotNull() {
        String name = "";
        String ssn = "1961107145678";
        String address = "bacau";

        String thrown = "";

        try {
            ctrl.addPatient(new Patient(name, ssn, address));
        } catch (PatientException e) {
            thrown = e.getMessage();
        }
        assertEquals(thrown, "One of the required fields is empty!");
    }

    @Test
    public void checkIfPatientAddressIsNotNull() {
        String name = "radu";
        String ssn = "1961107145678";
        String address = "";

        String thrown = "";

        try {
            ctrl.addPatient(new Patient(name, ssn, address));
        } catch (PatientException e) {
            thrown = e.getMessage();
        }
        assertEquals(thrown, "One of the required fields is empty!");
    }

    @Test
    public void addConsultationIf_3_C1_False() {
        String consID = null;
        String patientSSN = "2961107125782";
        String diag = "varicela";
        List<String> meds = new ArrayList<java.lang.String>();
        meds.add("antibiotic");
        meds.add("paracetamol");
        String date = "25.03.2018";

        String thrown = "";

        try {
            ctrl.addConsultation(consID, patientSSN, diag, meds, date);
        } catch (ConsultationException e) {
            thrown = e.getMessage();
        }

        assertEquals("invalid arguments", thrown);
    }

    @Test
    public void addConsultationIf_3_C1_True() {
        String consID = "10";
        String patientSSN = "2961107125782";
        String diag = "varicela";
        List<String> meds = new ArrayList<java.lang.String>();
        meds.add("antibiotic");
        meds.add("paracetamol");
        String date = "24.03.2018";

        String thrown = "";

        try {
            ctrl.addConsultation(consID, patientSSN, diag, meds, date);
        } catch (ConsultationException e) {
            thrown = e.getMessage();
        }

        assertEquals("", thrown);
    }

    @Test
    public void addConsultationIf_3_C2_False() {
        String consID = "11";
        String patientSSN = null;
        String diag = "varicela";
        List<String> meds = new ArrayList<java.lang.String>();
        meds.add("antibiotic");
        meds.add("paracetamol");
        String date = "24.03.2018";

        String thrown = "";

        try {
            ctrl.addConsultation(consID, patientSSN, diag, meds, date);
        } catch (ConsultationException e) {
            thrown = e.getMessage();
        }

        assertEquals("invalid arguments", thrown);
    }

    @Test
    public void addConsultationIf_3_C2_True() {
        String consID = "12";
        String patientSSN = "1961208123456";
        String diag = "varicela";
        List<String> meds = new ArrayList<java.lang.String>();
        meds.add("antibiotic");
        meds.add("paracetamol");
        String date = "24.03.2018";

        String thrown = "";

        try {
            ctrl.addConsultation(consID, patientSSN, diag, meds, date);
        } catch (ConsultationException e) {
            thrown = e.getMessage();
        }

        assertEquals("invalid arguments", thrown);
    }

    @Test
    public void addConsultationIf_3_C3_False() {
        String consID = "13";
        String patientSSN = "1961208123456";
        String diag = null;
        List<String> meds = new ArrayList<java.lang.String>();
        meds.add("antibiotic");
        meds.add("paracetamol");
        String date = "24.03.2018";

        String thrown = "";

        try {
            ctrl.addConsultation(consID, patientSSN, diag, meds, date);
        } catch (ConsultationException e) {
            thrown = e.getMessage();
        }

        assertEquals("invalid arguments", thrown);
    }

    @Test
    public void addConsultationIf_3_C3_True() {
        String consID = "14";
        String patientSSN = "2961107125782";
        String diag = "durere cap";
        List<String> meds = new ArrayList<String>();
        meds.add("antibiotic");
        meds.add("paracetamol");
        String date = "27.03.2018";

        String thrown = "";

        try {
            ctrl.addConsultation(consID, patientSSN, diag, meds, date);
        } catch (ConsultationException e) {
            thrown = e.getMessage();
        }

        assertEquals("", thrown);
    }

    @Test
    public void addConsultationIf_3_C4_False() {
        String consID = "15";
        String patientSSN = "1961208123456";
        String diag = "diafragma febra";
        List<String> meds = new ArrayList<java.lang.String>();
        String date = "29.03.2018";

        String thrown = "";

        try {
            ctrl.addConsultation(consID, patientSSN, diag, meds, date);
        } catch (ConsultationException e) {
            thrown = e.getMessage();
        }

        assertEquals("invalid arguments", thrown);
    }


    //aici
    @Test
    public void addConsultationIf_3_C4_True() {
        String consID = "15";
        String patientSSN = "2961107125782";
        String diag = "durere gat";
        List<String> meds = new ArrayList<java.lang.String>();
        meds.add("antibiotic");
        meds.add("paracetamol");
        String date = "27.03.2018";

        String thrown = "";

        try {
            ctrl.addConsultation(consID, patientSSN, diag, meds, date);
        } catch (ConsultationException e) {
            thrown = e.getMessage();
        }

        assertEquals("", thrown);
    }

    @Test
    public void addConsultationIf_3_C6_False() {
        String consID = "-1";
        String patientSSN = "1961208123456";
        String diag = null;
        List<String> meds = new ArrayList<java.lang.String>();
        meds.add("antibiotic");
        meds.add("paracetamol");
        String date = "24.03.2018";

        String thrown = "";

        try {
            ctrl.addConsultation(consID, patientSSN, diag, meds, date);
        } catch (ConsultationException e) {
            thrown = e.getMessage();
        }

        assertEquals("invalid arguments", thrown);
    }

    @Test
    public void addConsultationIf_3_C6_True() {
        String consID = "16";
        String patientSSN = "2961107125782";
        String diag = "durere cap";
        List<String> meds = new ArrayList<java.lang.String>();
        meds.add("antibiotic");
        meds.add("paracetamol");
        String date = "27.03.2018";

        String thrown = "";

        try {
            ctrl.addConsultation(consID, patientSSN, diag, meds, date);
        } catch (ConsultationException e) {
            thrown = e.getMessage();
        }

        assertEquals("", thrown);
    }

    @Test
    public void addConsultationIf_3_C5_False() {
        String consID = "17";
        String patientSSN = "1961208123450";
        String diag = null;
        List<String> meds = new ArrayList<java.lang.String>();
        meds.add("antibiotic");
        meds.add("paracetamol");
        String date = "24.03.2018";

        String thrown = "";

        try {
            ctrl.addConsultation(consID, patientSSN, diag, meds, date);
        } catch (ConsultationException e) {
            thrown = e.getMessage();
        }

        assertEquals("invalid arguments", thrown);
    }

    @Test
    public void addConsultationIf_3_C5_True() {
        String consID = "18";
        String patientSSN = "2961107125782";
        String diag = "durere cap";
        List<String> meds = new ArrayList<java.lang.String>();
        meds.add("antibiotic");
        meds.add("paracetamol");
        String date = "27.03.2018";

        String thrown = "";

        try {
            ctrl.addConsultation(consID, patientSSN, diag, meds, date);
        } catch (ConsultationException e) {
            thrown = e.getMessage();
        }

        assertEquals("", thrown);
    }

    @Test
    public void addConsultation_Path1()
    {
        String consID = "19";
        String patientSSN = "2961107125782";
        String diag = "durere cap";
        List<String> meds = null;
        String date = "27.03.2018";

        String thrown = "";

        try {
            ctrl.addConsultation(consID, patientSSN, diag, meds, date);
        } catch (ConsultationException e) {
            thrown = e.getMessage();
        }

        assertEquals("meds is null", thrown);
    }

    @Test
    public void addConsultation_Path2()
    {
        rep.setConsultationFileName("ddsad");
        String consID = "22";
        String patientSSN = "2961107125782";
        String diag = "durere cap + gat si asa";
        List<String> meds = new ArrayList<java.lang.String>();
        meds.add("antibiotic");
        meds.add("paracetamol");
        meds.add("aspirin plus c");
        meds.add("aspirin plus c forte");
        String date = "04.04.2018";

        String thrown = "";

        try {
            ctrl.addConsultation(consID, patientSSN, diag, meds, date);
        } catch (ConsultationException e) {
            thrown = e.getMessage();
        }

        assertEquals("", thrown);
    }

    @Test
    public void addConsultation_Path3()
    {
        rep.setConsultationFileName("FileConsultations.txt");
        String consID = "21";
        String patientSSN = "2961107125782";
        String diag = "durere cap + gat...";
        List<String> meds = new ArrayList<java.lang.String>();
        meds.add("antibiotic");
        meds.add("paracetamol");
        meds.add("aspirin plus c");
        String date = "02.04.2018";

        String thrown = "";

        try {
            ctrl.addConsultation(consID, patientSSN, diag, meds, date);
        } catch (ConsultationException e) {
            thrown = e.getMessage();
        }

        assertEquals("", thrown);
    }

    @Test
    public void testReqA() {
        String thrown0 = "";
        int nrBefore = rep.getPatientList().size();
        try {
            ctrl.addPatient(new Patient("diana", "2971106123456", "pitesti"));
            int nrAfter = rep.getPatientList().size();
            assertEquals(nrBefore , nrAfter);
        } catch (PatientException e) {
            thrown0 = e.getMessage();
            assertEquals(thrown0, "Null fields");
        }
    }

    @Test
    public void testReqAB() {
        String thrown0 = "";
        int nrBefore = rep.getPatientList().size();
        try {
            ctrl.addPatient(new Patient("diana", "2971106123456", "pitesti"));
            int nrAfter = rep.getPatientList().size();
            assertEquals(nrBefore , nrAfter);
        } catch (PatientException e) {
            thrown0 = e.getMessage();
            assertEquals(thrown0, "Null fields");
        }

        //B
        List<String> meds = new ArrayList<String>();
        int nrBefore2 = rep.getConsultationList().size();
        meds.add("antibiotic");
        meds.add("paracetamol");
        String thrown = "";
        try {
            ctrl.addConsultation("23", "2971106123456", "varicela", meds, "10.04.2018");
            int nrAfter2 = rep.getConsultationList().size()-1;

            assertEquals(nrBefore2,nrAfter2);
        } catch (ConsultationException exc) {
            thrown = exc.getMessage();
        }
    }

    @Test
    public void testReqABC() {

        //A
        String thrown0 = "";
        int nrBefore = rep.getPatientList().size();
        try {
            ctrl.addPatient(new Patient("diana", "2971106123456", "pitesti"));
            int nrAfter = rep.getPatientList().size();
            assertEquals(nrBefore , nrAfter);
        } catch (PatientException e) {
            thrown0 = e.getMessage();
        }

        //B
        List<String> meds = new ArrayList<String>();
        int nrBefore2 = rep.getConsultationList().size();
        meds.add("antibiotic");
        meds.add("paracetamol");
        String thrown = "";
        try {
            ctrl.addConsultation("23", "2971106123456", "varicela", meds, "10.04.2018");
            int nrAfter2 = rep.getConsultationList().size()-1;

            assertEquals(nrBefore2,nrAfter2);
        } catch (ConsultationException exc) {
            thrown = exc.getMessage();
        }


        //C
        String diseaseWanted = "varicela";
        String thrown2 = "";
        try {
            List<Patient> patientsWithDisease = ctrl.getPatientsWithDisease(diseaseWanted);
            assertEquals(patientsWithDisease.size(), 2);
        } catch (PatientException e) {
            thrown2 = e.getMessage();
            if(diseaseWanted != null)
            {
                assertEquals(thrown2, "Empty disease provided");
            }
            else {
                assertEquals(thrown2, "Null disease parameter provided");
            }
        }

    }
}