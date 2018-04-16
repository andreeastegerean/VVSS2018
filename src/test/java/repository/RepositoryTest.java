package repository;

import model.Patient;
import org.junit.Test;

import java.io.IOException;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class RepositoryTest {

    Repository rep;

    @org.junit.Before
    public void setUp() throws Exception {
        rep = new Repository("FilePatients.txt", "FileConsultations.txt");
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
        Repository repos = new Repository("not-exist.txt", "C:\\Users\\nicol\\Documents\\Custom Office Templates\\college\\3rd year, 2nd semester\\vvss\\pnie1983Lab3\\src\\FileConsultations.txt");

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

}
