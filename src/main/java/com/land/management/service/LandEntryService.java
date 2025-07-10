package com.land.management.service;

import com.land.management.model.LandEntry;

import java.util.List;

public interface LandEntryService {
    LandEntry saveEntry(LandEntry landEntry);
    List<LandEntry> getAllEntries();
    void deleteEntry(Long id);
    byte[] getPdfDataById(Long id);
    String getPdfNameById(Long id);

}
