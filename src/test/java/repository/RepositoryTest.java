package repository;

import controller.DoctorController;
import exceptions.ConsultationException;
import exceptions.PatientException;
import model.Patient;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class RepositoryTest {

    Repository rep;
    DoctorController ctrl;

    @org.junit.Before
    public void setUp() throws Exception {
        rep = new Repository("FilePatients.txt", "FileConsultations.txt");
        ctrl = new DoctorController(rep);
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }
    @Test
    public void savePatientToFile() throws IOException {
        String name="radu";
        String ssn="1961107145678";
        String address="bacau";

        int nrBefore = rep.getPatientList().size();
        rep.savePatientToFile(new Patient(name, ssn, address));
        int nrAfter = rep.getPatientList().size();

        assertEquals(nrBefore, nrAfter);
    }

    @Test
    public void savePatientInInexistingFile() {
        Repository repos = new Repository("not-exist.txt", "FileConsultations.txt");

        String name="radu";
        String ssn="1961107145678";
        String address="bacau";

        boolean thrown = false;

        try {
            repos.savePatientToFile(new Patient(name, ssn, address));
        } catch (IOException e) {
            thrown = true;
        }

        assertTrue(thrown);
    }

    @Test
    public void checkIfAddPatientWithSameSSNAsAnother() throws IOException {
        String name="radu";
        String ssn="1961107145678";
        String address="bacau";

        rep.savePatientToFile(new Patient(name, ssn, address));
        int nrBefore = rep.getPatientList().size();
        rep.savePatientToFile(new Patient(name, ssn, address));
        int nrAfter = rep.getPatientList().size();

        assertEquals(nrBefore, nrAfter);
    }

    @Test
    public void bigBangTest() {
        //A
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
        meds.add("antibiotic");
        meds.add("paracetamol");
        String thrown = "";
        try {
            ctrl.addConsultation("22", "2971106123456", "varicela", meds, "10.04.2018");
        } catch (ConsultationException exc) {
            thrown = exc.getMessage();
        }
        assertEquals("", thrown);

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
