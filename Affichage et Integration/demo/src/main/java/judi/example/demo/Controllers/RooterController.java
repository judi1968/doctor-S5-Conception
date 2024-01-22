package judi.example.demo.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import judi.example.demo.Models.Objects.Main;
import judi.example.demo.Models.Objects.MedicamentPatientResultat;
import judi.example.demo.Models.Objects.Parametre;
import judi.example.demo.Models.Objects.Patient;
import judi.example.demo.Models.Objects.PatientMaladie;


@Controller
public class RooterController {
    @GetMapping("/")
    public String accueil(Model model){
        try {
            Parametre[] parametres = Parametre.getAllParametre(null);
            model.addAttribute("parametres",parametres);
            return "index";
            
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return "index";
    }
    @PostMapping("/add_patient")
    public String add_patient(@RequestParam String nom,@RequestParam int age,@RequestParam int[] parametres,@RequestParam int[] niveau,Model model){
        try {     
            Main.addNewPatient(nom,age,parametres,niveau);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return accueil(model);
    }
    
    @GetMapping("/List_patient")
    public String list_patient(Model model){
        try {
            Patient[] patients = Patient.getAllPatient(null);
            model.addAttribute("patients", patients);
            return "List_patient/List_patient";
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return "index";
        } 
    }

    @GetMapping("/predir_maladie")
    public String predir_maladie(@RequestParam int id_patient,Model model){
        try {
            Main main = new Main();
            PatientMaladie[] patientMaladies =  main.devineMaladieByIdClient(id_patient);
            model.addAttribute("id_patient", id_patient);
            model.addAttribute("patientMaladies", patientMaladies);

            return "resultat/List_maladie_patient";
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return "index";
        } 
    }

    @GetMapping("/resultMedicament")
    public String resultMedicament(@RequestParam int id_patient,@RequestParam int id_maladie,Model model){
        try {
            Main main = new Main();
            MedicamentPatientResultat[] medicament_patient = null;
            Patient p = Patient.getPatientById(id_patient, null);
            model.addAttribute("patient_name", p.getNomPatient());
            if (id_maladie==0) {
                medicament_patient = main.getAllMedicamentPatient(id_patient);
            }else{
                medicament_patient =  main.getMedicamentPatientByIdMaladie(id_patient,id_maladie);
            }
            model.addAttribute("medicament_patient", medicament_patient);
            double prixTotale = 0;
            for (MedicamentPatientResultat medicamentPatientResultat : medicament_patient) {
                prixTotale = prixTotale+medicamentPatientResultat.getPrixTotale();
            }
            model.addAttribute("prixTotale", prixTotale);
            
            return "resultat/list_medicament";
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return "index";
        } 
    }

    @GetMapping("/resultAllMedicament")
    public String resultAllMedicament(@RequestParam int id_patient,Model model){
        try {
            Main main = new Main();
            PatientMaladie[] patientMaladies =  main.devineMaladieByIdClient(id_patient);
            model.addAttribute("id_patient", id_patient);
            model.addAttribute("patientMaladies", patientMaladies);
        
            return "resultat/List_maladie_patient";
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return "index";
        } 
    }

//     @PostMapping("/modify_action")
//     public String result_manuel(@RequestParam int[] changer, @RequestParam int[] traiter, Model model) {
//     try {
//         System.out.println("Traiter");
//         for (int i : traiter) {
//             System.out.println(i);
//         }
//         System.out.println();
//         System.out.println("Changer");
//         for (int j : changer) {
//             System.out.println(j);
//         }
//         return "index";
//     } catch (Exception e) {
//         // TODO: handle exception
//         return "index";
//     }
// }
//     @PostMapping("/modify_alea")
//     public String modify_etat(@RequestParam String dent, @RequestParam String note, Model model) throws Exception {
//         System.out.println(dent+" "+note);
//         String[] dents = dent.split(";");
//         String[] notes = note.split(";");
//         // ;
//         if (dent.contains(";")) {
//             int i = 0;
//             for (String string : dents) {
//                 DentSituation.updateSituationPatient(Integer.parseInt(string), Integer.parseInt(notes[i]), null);
//                 i++;
//             }

//         } else if (dent.contains("-")) {
//             String[] dent2 = dent.split("-");
//             int id1 = Integer.parseInt(dent2[0]);
//             int id2 = Integer.parseInt(dent2[1]);
//             while (id1<=id2) {
//                 DentSituation.updateSituationPatient(id1 ,Integer.parseInt(note), null);
//                 id1++;
//             }
//         } else {
//             DentSituation.updateSituationPatient(Integer.parseInt(dent), Integer.parseInt(note), null);   
//         }
        
//         return "index";

//     }

    
}
