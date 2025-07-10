package com.land.management.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "land_entries")
@Data
public class LandEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("entryType")
    private String tabType; 

    private String purpose;
    private String proposal;
    private Double area;
    private Double valuatedArea;
    private Double purchasedArea;
    private String location;
    private String remarks;

    private String pdfName;

    @Lob
    private String pdfData;

    @JsonProperty("pdfUrl")
    public void setPdfUrl(String base64WithPrefix) {
        if (base64WithPrefix != null && base64WithPrefix.contains(",")) {
            // remove "data:application/pdf;base64," prefix
            this.pdfData = base64WithPrefix.split(",")[1];
        } else {
            this.pdfData = base64WithPrefix; // already clean
        }
    }

    @JsonProperty("pdfUrl")
    public String getPdfUrl() {
        return "data:application/pdf;base64," + this.pdfData;
    }

}
