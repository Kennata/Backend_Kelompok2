package com.example.BackendTubes.Dokumen;

import com.example.BackendTubes.Penghuni.Penghuni;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Arrays;

@Entity
@Table(name = "dokumen")
public class Dokumen {
    @Id
    @SequenceGenerator(
            name = "dokumen_sequence",
            sequenceName = "dokumen_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "dokumen_sequence"
    )
    private Long id;

    private String jenis;
    private String namaFile;
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private byte[] pdfFile;
    private LocalDate tanggalUpload;
    @ManyToOne
    @JoinColumn(name = "penghuniId", referencedColumnName = "id")
    private Penghuni penghuni;


    public Dokumen() {
    }

    public Dokumen(Long id, String jenis, String namaFile, byte[] pdfFile, LocalDate tanggalUpload, Penghuni penghuni) {
        this.id = id;
        this.jenis = jenis;
        this.namaFile = namaFile;
        this.pdfFile = pdfFile;
        this.tanggalUpload = tanggalUpload;
        this.penghuni = penghuni;
    }

    public Dokumen(String jenis, String namaFile, byte[] pdfFile, LocalDate tanggalUpload, Penghuni penghuni) {
        this.jenis = jenis;
        this.namaFile = namaFile;
        this.pdfFile = pdfFile;
        this.tanggalUpload = tanggalUpload;
        this.penghuni = penghuni;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getNamaFile() {
        return namaFile;
    }

    public void setNamaFile(String namaFile) {
        this.namaFile = namaFile;
    }

    public LocalDate getTanggalUpload() {
        return tanggalUpload;
    }

    public void setTanggalUpload(LocalDate tanggalUpload) {
        this.tanggalUpload = tanggalUpload;
    }

    public byte[] getPdfFile() {
        return pdfFile;
    }

    public void setPdfFile(byte[] pdfFile) {
        this.pdfFile = pdfFile;
    }

    public Penghuni getPenghuni() {
        return penghuni;
    }

    public void setPenghuni(Penghuni penghuni) {
        this.penghuni = penghuni;
    }

    @Override
    public String toString() {
        return "Dokumen{" +
                "id=" + id +
                ", jenis='" + jenis + '\'' +
                ", namaFile='" + namaFile + '\'' +
                ", pdfFile=" + Arrays.toString(pdfFile) +
                ", tanggalUpload=" + tanggalUpload +
                ", penghuni=" + penghuni +
                '}';
    }
}
