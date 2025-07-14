package com.land.management.service;

import com.land.management.model.LandEntry;
import com.land.management.repository.LandEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Base64;

import java.util.List;

@Service
public class LandEntryServiceImpl implements LandEntryService {

    @Autowired
    private LandEntryRepository landEntryRepository;

    @Override
    public LandEntry saveEntry(LandEntry landEntry) {
        return landEntryRepository.save(landEntry);
    }

    @Override
    public List<LandEntry> getAllEntries() {
        return landEntryRepository.findAll();
    }

    @Override
    public void deleteEntry(Long id) {
        landEntryRepository.deleteById(id);
    }

    
@Override
public byte[] getPdfDataById(Long id) {
    LandEntry entry = landEntryRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Entry not found with ID: " + id));

    if (entry.getPdfData() == null || entry.getPdfData().equals("null")) {
        throw new RuntimeException("No PDF found for ID: " + id);
    }

    return Base64.getDecoder().decode(entry.getPdfData());
}

@Override
public String getPdfNameById(Long id) {
    return landEntryRepository.findById(id)
        .map(LandEntry::getPdfName)
        .orElse("document.pdf");
}

@Override
public LandEntry updateEntry(Long id, LandEntry updatedEntry) {
    LandEntry existing = landEntryRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Entry not found with ID: " + id));

    // Update basic fields
    existing.setPurpose(updatedEntry.getPurpose());
    existing.setProposal(updatedEntry.getProposal());
    existing.setArea(updatedEntry.getArea());
    existing.setValuatedArea(updatedEntry.getValuatedArea());
    existing.setPurchasedArea(updatedEntry.getPurchasedArea());
    existing.setLocation(updatedEntry.getLocation());
    existing.setRemarks(updatedEntry.getRemarks());
    existing.setTabType(updatedEntry.getTabType());
   // existing.set(updatedEntry.getTabType());

    // Optional: Only update PDF if a new one is provided
    if (updatedEntry.getPdfName() != null && updatedEntry.getPdfData() != null) {
        existing.setPdfName(updatedEntry.getPdfName());
        existing.setPdfData(updatedEntry.getPdfData());
    }

    return landEntryRepository.save(existing);
}

}
