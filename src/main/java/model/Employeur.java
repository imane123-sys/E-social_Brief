package model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;


//@SuppressWarnings("ALL")
@Entity
@Table(name = "employeur")
public class Employeur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String raisonSociale;

    @Column(nullable = false)
    private String secteurActivite;

    @OneToMany(mappedBy = "employeur", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Assure> assures = new ArrayList<>();

    @OneToMany(mappedBy = "employeur", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Declaration> declarations = new ArrayList<>();

    public Employeur() {}

    public Employeur(String raisonSociale, String secteurActivite) {
        this.raisonSociale = raisonSociale;
        this.secteurActivite = secteurActivite;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getRaisonSociale() { return raisonSociale; }
    public void setRaisonSociale(String raisonSociale) { this.raisonSociale = raisonSociale; }

    public String getSecteurActivite() { return secteurActivite; }
    public void setSecteurActivite(String secteurActivite) { this.secteurActivite = secteurActivite; }

    public List<Assure> getAssures() { return assures; }
    public void setAssures(List<Assure> assures) { this.assures = assures; }

    public List<Declaration> getDeclarations() { return declarations; }
    public void setDeclarations(List<Declaration> declarations) { this.declarations = declarations; }
