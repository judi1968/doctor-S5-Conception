package judi.example.demo.Models.Objects;

public class MedicamentPatientResultat {
    String nomParametre;
    int noteParametre;
    String nomMedicament;
    int effetInitialeMedicament;
    int effetTotaleMedicament;
    int quantite;
    double prixTotale;
    double prixInitiale;

    public void setEffetInitialeMedicament(int effetInitialeMedicament) {
        this.effetInitialeMedicament = effetInitialeMedicament;
    }
    public void setEffetTotaleMedicament(int effetTotaleMedicament) {
        this.effetTotaleMedicament = effetTotaleMedicament;
    }
    public void setNomMedicament(String nomMedicament) {
        this.nomMedicament = nomMedicament;
    }
    public void setNomParametre(String nomParametre) {
        this.nomParametre = nomParametre;
    }
    public void setNoteParametre(int noteParametre) {
        this.noteParametre = noteParametre;
    }
    public void setPrixInitiale(double prixInitiale) {
        this.prixInitiale = prixInitiale;
    }
    public void setPrixTotale(double prixTotale) {
        this.prixTotale = prixTotale;
    }
    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public int getEffetInitialeMedicament() {
        return effetInitialeMedicament;
    }
    public int getEffetTotaleMedicament() {
        return effetTotaleMedicament;
    }
    public String getNomMedicament() {
        return nomMedicament;
    }
    public String getNomParametre() {
        return nomParametre;
    }
    public int getNoteParametre() {
        return noteParametre;
    }
    public double getPrixInitiale() {
        return prixInitiale;
    }
    public double getPrixTotale() {
        return prixTotale;
    }
    public int getQuantite() {
        return quantite;
    }

    public MedicamentPatientResultat(){}
}
