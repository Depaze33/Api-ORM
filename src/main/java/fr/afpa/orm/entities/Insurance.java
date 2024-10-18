package fr.afpa.orm.entities;

import jakarta.persistence.*;

public class Insurance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @JoinTable(name = "subscribe")
    @ManyToMany(targetEntity = Client.class)
    private Client client;

    public Insurance(Long id, String name, Client client) {
        this.id = id;
        this.name = name;
        this.client = client;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
