package com.example.BackendTubes.Dokumen;

import com.example.BackendTubes.Pembayaran.Pembayaran;
import com.example.BackendTubes.Penghuni.Penghuni;
import com.example.BackendTubes.Penghuni.PenghuniRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class DokumenService {

    private final DokumenRepository dokumenRepository;
    private final PenghuniRepository penghuniRepository;

    @Autowired
    public DokumenService(DokumenRepository dokumenRepository, PenghuniRepository penghuniRepository) {
        this.dokumenRepository = dokumenRepository;
        this.penghuniRepository = penghuniRepository;
    }

    public List<Dokumen> getDokumen() {
        return dokumenRepository.findAll();
    }

    public void addNewDokumen(DokumenRequest dokumen) throws IOException {
        Penghuni penghuni = penghuniRepository.findById(dokumen.getPenghuniId())
                .orElseThrow(() -> new RuntimeException("Penghuni not found"));

        Dokumen newDokumen = new Dokumen();
        newDokumen.setJenis(dokumen.getJenis());
        newDokumen.setPenghuni(penghuni);
        newDokumen.setNamaFile(dokumen.getNamaFile());
        newDokumen.setPdfFile(dokumen.getFile().getBytes());
        newDokumen.setJenis(dokumen.getJenis());
        newDokumen.setTanggalUpload(dokumen.getTanggalUpload());

        dokumenRepository.save(newDokumen);

    }
}
