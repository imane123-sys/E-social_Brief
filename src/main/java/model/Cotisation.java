package model;

import jakarta.persistence.*;

@Entity
@Table(name = "cotisation")
public class Cotisation {

    public static final double TAUX_SALARIAL = 0.0448;
    public static final double TAUX_PATRONAL = 0.2153;
    public static final double PLAFOND_COTISATION = 6000.0;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private double cotisationSalariale;

    @Column(nullable = false)
    private double cotisationPatronale;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assure_id", nullable = false)
    private  Assure assure;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "declaration_id", nullable = false)
    private Declaration declaration;


    public Cotisation() {}

    public Cotisation(Assure assure, Declaration declaration) {
        this.assure = assure;
        this.declaration = declaration;
        calculerCotisations(assure.getSalaireMensuel());
    }

    public void calculerCotisations(double salaire) {
        double salairePlafonne = Math.min(salaire, PLAFOND_COTISATION);
        this.cotisationSalariale = Math.round(salairePlafonne * TAUX_SALARIAL * 100.0) / 100.0;
        this.cotisationPatronale = Math.round(salairePlafonne * TAUX_PATRONAL * 100.0) / 100.0;
    }


    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public double getCotisationSalariale() { return cotisationSalariale; }
    public void setCotisationSalariale(double cotisationSalariale) { this.cotisationSalariale = cotisationSalariale; }

    public double getCotisationPatronale() { return cotisationPatronale; }
    public void setCotisationPatronale(double cotisationPatronale) { this.cotisationPatronale = cotisationPatronale; }

    public Assure getAssure() { return assure; }
    public void setAssure(Assure assure) { this.assure = assure; }

    public Declaration getDeclaration() { return declaration; }
    public void setDeclaration(Declaration declaration) { this.declaration = declaration; }

    public double getTotalCotisation() {
        return cotisationSalariale + cotisationPatronale;
    }
}