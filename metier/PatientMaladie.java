package metier;

import java.util.Vector;

public class PatientMaladie extends Maladie{
    Patient patient;
    Vector<PatientParametre> patientParametres;
    public void setPatient(Patient patient) {
        this.patient = patient;
    }
    public void setPatientParametres(Vector<PatientParametre> patientParametres) {
        this.patientParametres = patientParametres;
    }
    public Patient getPatient() {
        return patient;
    }
    public Vector<PatientParametre> getPatientParametres() {
        return patientParametres;
    }

    public PatientMaladie(){}

    public PatientMaladie(PatientMaladie[] patientMaladies,Patient patient){
        setPatientParametres(patientParametres);
        setPatient(patient);
    }

    public PatientMaladie(int id,String nom,PatientMaladie[] patientMaladies,Patient patient){
        setIdMaladie(id);
        setNomMaladie(nom);
        setPatientParametres(patientParametres);
        setPatient(patient);
    }
}
