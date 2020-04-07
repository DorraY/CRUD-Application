package Model;

public class Employee implements  java.io.Serializable {
    private int Matricule;
    private String Nom;
    private String Prénom;
    private int Département;

    public Employee() {

    }

    public int getMatricule() {
        return Matricule;
    }

    public String getNom() {
        return Nom;
    }

    public String getPrénom() {
        return Prénom;
    }

    public int getDépartement() {
        return Département;
    }

    public void setMatricule(int matricule) {
        Matricule = matricule;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public void setPrénom(String prénom) {
        Prénom = prénom;
    }

    public void setDépartement(int département) {
        Département = département;
    }

    @Override
    public String toString() {
        return "Employé [id= " + Matricule + " , nom= " + Nom + ",prénom " + Prénom + ",département " + Département + "]" ;
    }


}

