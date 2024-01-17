package judi.example.demo.Models.Utils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

public class DateHeure {
	LocalDate date;
	LocalTime heure;
	
	// setters et getters normal
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public void setTime(LocalTime heure) {
		this.heure = heure;
	}
	public LocalDate getDate() {
		return date;
	}
	public LocalTime getHeure() {
		return heure;
	}
	
	// setters et getters en string
	public void setDate(String date) throws Exception {
		if(date.trim().isEmpty()) {throw new Exception("Le date a inserer est vide");}
		String dates[] = date.split("-");
		int anne = Integer.parseInt(dates[0]);
		int mois = Integer.parseInt(dates[1]);
		int jour = Integer.parseInt(dates[2]);
		this.date = LocalDate.of(anne,mois,jour);
	}
	public String getDateString() {
		return date.toString();
	}
	public void setTime(String time) throws Exception{
		if(time.trim().isEmpty()) {throw new Exception("L'heure a inserer est vide");}
		String times[] = time.split(":");
		int heure = Integer.parseInt(times[0]);
		int min = Integer.parseInt(times[1]);
		int sec = Integer.parseInt(times[2]);
		this.heure = LocalTime.of(heure,min,sec);
	}
	public String getTimeString() {
		String val = heure.toString();
		if (heure.toString().length()==5) {
			val = heure.toString()+":00";
		}
		return val;
	}
	public String getDateTimeString(){
		return getDateString()+" "+getTimeString();
	}
	
	// fonction comparer date
	public boolean isAfterTo(DateHeure date1) {
		if(date.isAfter(date1.getDate())) {
			return true;
		}else if(date1.getDate().isAfter(date)) {
			return false;
		}else {
			if(heure.isAfter(date1.getHeure())) {
				return true;
			}else if(date1.getHeure().isAfter(heure)) {
				return false;
			}else {
				return false;
			}
		}
	}

	// fonction pour connaitre le jour d'un date
	public int getTypeDay() {
		String dateStr = this.getDateString();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = sdf.parse(dateStr);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);

            // Calendar.DAY_OF_WEEK commence à 1 pour dimanche, nous voulons commencer à 1 pour lundi
            int numeroJour = (calendar.get(Calendar.DAY_OF_WEEK) + 5) % 7 + 1;

            return numeroJour;
        } catch (ParseException e) {
            e.printStackTrace();
            return -1; // Erreur de parsing de la date
        }
    }

	public String getTypeDayString(){
		int type = getTypeDay();
		if (type==1) {return "Lundi";}
		if (type==2) {return "Mardi";}
		if (type==3) {return "Mercredi";}
		if (type==4) {return "Jeudi";}
		if (type==5) {return "Vendredi";}
		if (type==6) {return "Samedi";}
		return "Dimanche";
	}

	public DateHeure addSeconds(double seconds) {
		int hoursToAdd = (int) (seconds / 3600);
		int minutesToAdd = (int) ((seconds % 3600) / 60);
		int secondsToAdd = (int) (seconds % 60);
	
		LocalTime newTime = this.heure.plusHours(hoursToAdd).plusMinutes(minutesToAdd).plusSeconds(secondsToAdd);
	
		// Check if there is a date change
		LocalDate newDate = this.date;
		if (newTime.isBefore(this.heure)) {
			newDate = this.date.plusDays(1);
		}
	
		DateHeure newDateTime = new DateHeure();
		newDateTime.setDate(newDate);
		newDateTime.setTime(newTime);
	
		return newDateTime;
	}
	public DateHeure addSeconds(int seconds) {
		int hoursToAdd = (int) (seconds / 3600);
		int minutesToAdd = (int) ((seconds % 3600) / 60);
		int secondsToAdd = (int) (seconds % 60);
	
		LocalTime newTime = this.heure.plusHours(hoursToAdd).plusMinutes(minutesToAdd).plusSeconds(secondsToAdd);
	
		// Check if there is a date change
		LocalDate newDate = this.date;
		if (newTime.isBefore(this.heure)) {
			newDate = this.date.plusDays(1);
		}
	
		DateHeure newDateTime = new DateHeure();
		newDateTime.setDate(newDate);
		newDateTime.setTime(newTime);
	
		return newDateTime;
	}
		

	// constructeurs
	public DateHeure(String date, String heure) throws Exception{
		setDate(date);
		setTime(heure);
	}
	public DateHeure(String dateHeure) throws Exception{
		if(dateHeure.length() >= 20) {
		dateHeure = (dateHeure.split("\\."))[0];}
		String[] dateHeures = dateHeure.split(" ");
		setDate(dateHeures[0]);
		setTime(dateHeures[1]);
	}
	public DateHeure(){}

}
