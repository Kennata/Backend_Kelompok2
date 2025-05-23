package com.example.BackendTubes.Dokumen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "api/dokumen")
public class DokumenController {

    private final DokumenService dokumenService;

    @Autowired
    public DokumenController(DokumenService dokumenService) {
        this.dokumenService = dokumenService;
    }

    @GetMapping
    public List<Dokumen> getDokumen() {
        return dokumenService.getDokumen();
    }

    @PostMapping
    public void addNewDokumen(
            @RequestParam("jenis") String jenis,
            @RequestParam("namaFile") String namaFile,
            @RequestParam("tanggalUpload") LocalDate tanggalUpload,
            @RequestParam("penghuniId") Long penghuniId,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        DokumenRequest dR = new DokumenRequest(jenis,namaFile,penghuniId,file, tanggalUpload);
        dokumenService.addNewDokumen(dR);
    }
}
