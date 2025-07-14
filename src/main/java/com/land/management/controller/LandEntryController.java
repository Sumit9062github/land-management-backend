// controller/LandEntryController.java
package com.land.management.controller;

import com.land.management.model.LandEntry;
import com.land.management.repository.LandEntryRepository;
import com.land.management.service.LandEntryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;

import java.util.List;

@RestController
@RequestMapping("/api/entries")
@CrossOrigin(origins = "*") // for frontend access
public class LandEntryController {

    @Autowired
    private LandEntryRepository repo;
    @Autowired
private LandEntryService landEntryService;


    @PostMapping
    public LandEntry saveEntry(@RequestBody LandEntry entry) {
        return repo.save(entry);
    }

    @GetMapping
    public List<LandEntry> getAllEntries() {
        return repo.findAll();
    }

    @DeleteMapping("/{id}")
    public void deleteEntry(@PathVariable Long id) {
        repo.deleteById(id);
    }

    
@GetMapping("/{id}/pdf")
public ResponseEntity<byte[]> downloadPdf(@PathVariable Long id) {
    byte[] pdfBytes = landEntryService.getPdfDataById(id);
    String pdfName = landEntryService.getPdfNameById(id);

    return ResponseEntity.ok()
        .contentType(MediaType.APPLICATION_PDF)
        .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + pdfName + "\"")
        .body(pdfBytes);
}

@PutMapping("/{id}")
public ResponseEntity<LandEntry> updateEntry(@PathVariable Long id, @RequestBody LandEntry updatedEntry) {
    try {
        LandEntry savedEntry = landEntryService.updateEntry(id, updatedEntry);
        return ResponseEntity.ok(savedEntry);
    } catch (RuntimeException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
}

}
