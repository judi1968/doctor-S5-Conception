package judi.example.demo.Models.Objects;

import java.util.Vector;

/**
 * Main
 */
public class Main {

    public static void addNewPatient(String nomPatient,int age,int[] idParametres,int[] note) throws Exception{
        try {
        // deviner maladie
        // ---------------------- AJOUTER PATIENT ------------------------------------

        // // entrer le patient

        Patient patient = new Patient(nomPatient,age);
        patient.createNewPatient(null);

        // prendre le id du nouveau patient
            int idLastPatient = Patient.getLastIdPatient(null);
        // prendre le nouveau patient 
        patient = Patient.getPatientById(idLastPatient, null);
        // entrer le parametre du patient
        
        
        
        PatientParametre[] patientParametres = new PatientParametre[idParametres.length];
        int i = 0;
        for (int idP : idParametres) {
            Parametre parametre = Parametre.getParametreById(idP, null);
            patientParametres[i] = new PatientParametre(patient.getIdPatient(), patient.getNomPatient(), patient.getAge(), parametre, note[i]);
            patientParametres[i].addParametrePatient(null);
            i++;
        }

        // for (PatientParametre patientParametre : patientParametres) {
        //     System.out.println(patientParametre.getNomPatient()+" : "+patientParametre.getParametre().getNomParametre()+ " : "+ patientParametre.getNoteParametre());
        // }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
        // -------------------- DEVINER LA MALADIE -----------------------------------
    public PatientMaladie[] devineMaladieByIdClient(int idPatient){

        
        Vector<PatientMaladie> maladie_result = new Vector<>();
        PatientMaladie[] patient_Maladies_result = new PatientMaladie[0];
        try {
            Patient patient = Patient.getPatientById(idPatient, null);
            
            // maka ny aretina rehetra possible amin'ilay age aloha : Maladie[]
            Maladie[] maladiePossibleInAge = Maladie.getAllMaladieExistInAge(patient.getAge(), null);
            boolean maladieExist = true;
            int nombreParametreIntoAge = 0;
            int nombreParametreIntoAgeThePatient = 0;
            PatientMaladie parametrePatientInMaladie;
            for (Maladie maladie : maladiePossibleInAge) {
                parametrePatientInMaladie = new PatientMaladie();
                parametrePatientInMaladie.setIdMaladie(maladie.getIdMaladie());
                parametrePatientInMaladie.setNomMaladie(maladie.getNomMaladie());
                parametrePatientInMaladie.setPatient(patient);
                // System.out.println(maladie.getNomMaladie());
                // maka ny parametre rehetra isaky ny maladie izay entre an ilay age : MaladieParametre[]
                // System.out.println("  parametre existe dans l'age");
                for (MaladieParametre maladieParametre : MaladieParametre.getAllMaladieParametresInMaladie(maladie.getIdMaladie(), null)) {
                    if (maladieParametre.getAgeMinimal()<=patient.getAge() && maladieParametre.getAgeMaximal() >= patient.getAge()) {
                        nombreParametreIntoAge++;             
                    }
                }
                Vector<PatientParametre> patientParametresConcerne = new Vector<>();
                for (MaladieParametre maladieParametre : MaladieParametre.getAllMaladieParametresInMaladie(maladie.getIdMaladie(), null)) {
                    if (maladieParametre.getAgeMinimal()<=patient.getAge() && maladieParametre.getAgeMaximal() >= patient.getAge()) {
                        // isaky ny MaladieParametre dia jerena ilay note hoe entre an ilay noteMinimal sy noteMaximal an ilay MaladieParametre ve 
                        // System.out.println("    "+maladieParametre.getParametre().getNomParametre()+" : "+maladieParametre.getAgeMinimal()+" ans min "+maladieParametre.getAgeMaximal()+" ans max");
                        // System.out.println("      note compris entre "+maladieParametre.getNoteMinimal()+" et "+maladieParametre.getNoteMaximal());
                        
                        for (PatientParametre patientParametre : PatientParametre.getAllPatientParametreByIdPatient(idPatient, null)) {
                            if (patientParametre.getParametre().getIdParametre()==maladieParametre.getParametre().getIdParametre()) {  
                                // System.out.println("        "+patientParametre.getParametre().getNomParametre()+" : "+patientParametre.getNoteParametre());
                                // System.out.println("            min "+maladieParametre.getNoteMinimal()+"   max "+maladieParametre.getNoteMaximal()+"   note "+patientParametre.getNoteParametre());
                                if (patientParametre.getNoteParametre() >= maladieParametre.getNoteMinimal() && patientParametre.getNoteParametre() <= maladieParametre.getNoteMaximal()) {
                                    patientParametresConcerne.add(patientParametre);
                                    nombreParametreIntoAgeThePatient++;
                                }
                            }
                        }
                        
                    }
                }
                parametrePatientInMaladie.setPatientParametres(patientParametresConcerne);
                if (nombreParametreIntoAge==nombreParametreIntoAgeThePatient) {
                    // System.out.println(maladie.getNomMaladie());
                    maladie_result.add(parametrePatientInMaladie);
                }
                // System.out.println(maladie.getNomMaladie()+" : "+nombreParametreIntoAge+" "+nombreParametreIntoAgeThePatient);
                nombreParametreIntoAge=0;
                nombreParametreIntoAgeThePatient=0;
                maladieExist=true;
            }
        if (maladie_result.size()<=0) {

        }else{

            patient_Maladies_result = new PatientMaladie[maladie_result.size()];
            int a = 0;
            for (PatientMaladie maladie : maladie_result) {
                patient_Maladies_result[a] = maladie;
                a++;
            }
        }
        
        // System.out.println("Le maladie du patient sont : ");
        for (PatientMaladie maladie : patient_Maladies_result) {
            //System.out.println("    "+maladie.getNomMaladie());
            //System.out.println("        parametre du patient dans le maladie");
            for (PatientParametre parametre : maladie.getPatientParametres()) {
                // System.out.println(parametre.getParametre().getNomParametre()+" , note : "+parametre.getNoteParametre());
            }
        }
    } catch (Exception e) {
        // TODO: handle exception
        e.printStackTrace();
    }
    return patient_Maladies_result;
    }
    //     // ----------------------------- FANAFODY ----------------------------------
    public MedicamentPatientResultat[] getMedicamentPatientByIdMaladie(int idPatient,int id_maladie){

        // alaina daholo ny symptome ny aterina mahazo n ilay olona, alaina distinctement
        Vector<PatientMaladie> maladie_result = new Vector<>();
        PatientMaladie[] patient_Maladies_result = new PatientMaladie[0];
        MedicamentPatientResultat[] medicamentPatientResultats = null;
        try {
            Patient patient = Patient.getPatientById(idPatient, null);
            
            // maka ny aretina rehetra possible amin'ilay age aloha : Maladie[]
            Maladie[] maladiePossibleInAge = Maladie.getAllMaladieExistInAge(patient.getAge(), null);
            boolean maladieExist = true;
            int nombreParametreIntoAge = 0;
            int nombreParametreIntoAgeThePatient = 0;
            PatientMaladie parametrePatientInMaladie;
            for (Maladie maladie : maladiePossibleInAge) {
                parametrePatientInMaladie = new PatientMaladie();
                parametrePatientInMaladie.setIdMaladie(maladie.getIdMaladie());
                parametrePatientInMaladie.setNomMaladie(maladie.getNomMaladie());
                parametrePatientInMaladie.setPatient(patient);
                // System.out.println(maladie.getNomMaladie());
                // maka ny parametre rehetra isaky ny maladie izay entre an ilay age : MaladieParametre[]
                // System.out.println("  parametre existe dans l'age");
                for (MaladieParametre maladieParametre : MaladieParametre.getAllMaladieParametresInMaladie(maladie.getIdMaladie(), null)) {
                    if (maladieParametre.getAgeMinimal()<=patient.getAge() && maladieParametre.getAgeMaximal() >= patient.getAge()) {
                        nombreParametreIntoAge++;             
                    }
                }
                Vector<PatientParametre> patientParametresConcerne = new Vector<>();
                for (MaladieParametre maladieParametre : MaladieParametre.getAllMaladieParametresInMaladie(maladie.getIdMaladie(), null)) {
                    if (maladieParametre.getAgeMinimal()<=patient.getAge() && maladieParametre.getAgeMaximal() >= patient.getAge()) {
                        // isaky ny MaladieParametre dia jerena ilay note hoe entre an ilay noteMinimal sy noteMaximal an ilay MaladieParametre ve 
                        // System.out.println("    "+maladieParametre.getParametre().getNomParametre()+" : "+maladieParametre.getAgeMinimal()+" ans min "+maladieParametre.getAgeMaximal()+" ans max");
                        // System.out.println("      note compris entre "+maladieParametre.getNoteMinimal()+" et "+maladieParametre.getNoteMaximal());
                        
                        for (PatientParametre patientParametre : PatientParametre.getAllPatientParametreByIdPatient(idPatient, null)) {
                            if (patientParametre.getParametre().getIdParametre()==maladieParametre.getParametre().getIdParametre()) {  
                                // System.out.println("        "+patientParametre.getParametre().getNomParametre()+" : "+patientParametre.getNoteParametre());
                                // System.out.println("            min "+maladieParametre.getNoteMinimal()+"   max "+maladieParametre.getNoteMaximal()+"   note "+patientParametre.getNoteParametre());
                                if (patientParametre.getNoteParametre() >= maladieParametre.getNoteMinimal() && patientParametre.getNoteParametre() <= maladieParametre.getNoteMaximal()) {
                                    patientParametresConcerne.add(patientParametre);
                                    nombreParametreIntoAgeThePatient++;
                                }
                            }
                        }
                        
                    }
                }
                parametrePatientInMaladie.setPatientParametres(patientParametresConcerne);
                if (nombreParametreIntoAge==nombreParametreIntoAgeThePatient) {
                    // System.out.println(maladie.getNomMaladie());
                    maladie_result.add(parametrePatientInMaladie);
                }
                // System.out.println(maladie.getNomMaladie()+" : "+nombreParametreIntoAge+" "+nombreParametreIntoAgeThePatient);
                nombreParametreIntoAge=0;
                nombreParametreIntoAgeThePatient=0;
                maladieExist=true;
            }
        if (maladie_result.size()<=0) {

        }else{

            patient_Maladies_result = new PatientMaladie[maladie_result.size()];
            int a = 0;
            for (PatientMaladie maladie : maladie_result) {
                patient_Maladies_result[a] = maladie;
                a++;
            }
        }
        
        // System.out.println("Le maladie du patient sont : ");
        for (PatientMaladie maladie : patient_Maladies_result) {
            //System.out.println("    "+maladie.getNomMaladie());
            //System.out.println("        parametre du patient dans le maladie");
            for (PatientParametre parametre : maladie.getPatientParametres()) {
                // System.out.println(parametre.getParametre().getNomParametre()+" , note : "+parametre.getNoteParametre());
            }
        }

        // -------------------- FANAFODY -----------------------------------
        Vector<PatientParametre> patientParametres = new Vector<>();
        for (PatientMaladie patientMaladie : patient_Maladies_result) {
            if (patientMaladie.getIdMaladie()==id_maladie) {
                boolean parametreExist = true;
                for (PatientParametre patientParametre : patientMaladie.getPatientParametres()) {
                    for (PatientParametre patientParametre2 : patientParametres) {
                        if (patientParametre.getParametre().getIdParametre() == patientParametre2.getParametre().getIdParametre()) {
                            parametreExist = false;
                            break;
                        }
                    }
                    if (maladieExist) {
                        patientParametres.add(patientParametre);
                    }
                    parametreExist=true;
                }
            }
        }
        Vector<MedicamentPatient> medicamentPatients = new Vector<>();
        for (PatientParametre patientParametre : patientParametres) {
            // tediavina tsirairay ny fanafody par rapport amin'ny parametre tsirairay
            // System.out.println(patientParametre.getParametre().getNomParametre()+" : "+patientParametre.getNoteParametre());
            medicamentPatients.add(patientParametre.getAllMedicamentsByParametrePatient());
        }
        int i = 0;
        medicamentPatientResultats = new MedicamentPatientResultat[medicamentPatients.size()];
        for (MedicamentPatient medicamentPatient : medicamentPatients) {
            medicamentPatientResultats[i] = new MedicamentPatientResultat();
            medicamentPatientResultats[i].setEffetInitialeMedicament((medicamentPatient.getEffet()/medicamentPatient.getQuantite()));
            medicamentPatientResultats[i].setNomParametre(patientParametres.elementAt(i).getParametre().getNomParametre());
            medicamentPatientResultats[i].setNoteParametre(patientParametres.elementAt(i).getNoteParametre() );
            medicamentPatientResultats[i].setNomMedicament(medicamentPatient.getNomMedicament());
            medicamentPatientResultats[i].setEffetTotaleMedicament(medicamentPatient.getEffet());
            medicamentPatientResultats[i].setQuantite(medicamentPatient.getQuantite());
            medicamentPatientResultats[i].setPrixTotale(medicamentPatient.getPrix());
            medicamentPatientResultats[i].setPrixInitiale(medicamentPatient.getPrix()/(medicamentPatient.getQuantite()));
            i++;
        }
        } catch (Exception e) {
           // TODO: handle exception
            e.printStackTrace();
        }
        return medicamentPatientResultats;
    }

    public MedicamentPatientResultat[] getAllMedicamentPatient(int idPatient){

        // alaina daholo ny symptome ny aterina mahazo n ilay olona, alaina distinctement
        MedicamentPatientResultat[] medicamentPatientResultats = null;
        try {

        // -------------------- FANAFODY -----------------------------------
        Vector<PatientParametre> patientParametres = new Vector<>();
        PatientParametre[] patientParametresTab = PatientParametre.getAllPatientParametreByIdPatient(idPatient, null);
        for (PatientParametre patientParametre : patientParametresTab) {
            patientParametres.add(patientParametre);
        }
        
        Vector<MedicamentPatient> medicamentPatients = new Vector<>();
        for (PatientParametre patientParametre : patientParametres) {
            // tediavina tsirairay ny fanafody par rapport amin'ny parametre tsirairay
            // System.out.println(patientParametre.getParametre().getNomParametre()+" : "+patientParametre.getNoteParametre());
            medicamentPatients.add(patientParametre.getAllMedicamentsByParametrePatient());
        }
        int i = 0;
        medicamentPatientResultats = new MedicamentPatientResultat[medicamentPatients.size()];
        for (MedicamentPatient medicamentPatient : medicamentPatients) {
            medicamentPatientResultats[i] = new MedicamentPatientResultat();
            medicamentPatientResultats[i].setEffetInitialeMedicament((medicamentPatient.getEffet()/medicamentPatient.getQuantite()));
            medicamentPatientResultats[i].setNomParametre(patientParametres.elementAt(i).getParametre().getNomParametre());
            medicamentPatientResultats[i].setNoteParametre(patientParametres.elementAt(i).getNoteParametre() );
            medicamentPatientResultats[i].setNomMedicament(medicamentPatient.getNomMedicament());
            medicamentPatientResultats[i].setEffetTotaleMedicament(medicamentPatient.getEffet());
            medicamentPatientResultats[i].setQuantite(medicamentPatient.getQuantite());
            medicamentPatientResultats[i].setPrixTotale(medicamentPatient.getPrix());
            medicamentPatientResultats[i].setPrixInitiale(medicamentPatient.getPrix()/(medicamentPatient.getQuantite()));
            i++;
        }
        } catch (Exception e) {
           // TODO: handle exception
            e.printStackTrace();
        }
        return medicamentPatientResultats;
    }

}