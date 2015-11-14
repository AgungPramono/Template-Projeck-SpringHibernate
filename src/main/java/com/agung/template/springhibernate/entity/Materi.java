/*
 *  Copyright (c) 2015 Agung Pramono <agungpermadi13@gmail.com || www.github.com/agung pramono>.
 *  All rights reserved.
 * 
 * Silahkan digunakan dengan bebas / dimodifikasi
 * Dengan tetap mencantumkan nama @author dan Referensi / Source
 * Terima Kasih atas Kerjasamanya.
 * 
 *  Materi.java
 * 
 *  Created on Nov 14, 2015, 8:55:53 PM
 */
package com.agung.template.springhibernate.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author agung
 */

@Entity
@Table(name = "m_materi")
public class Materi {
    
    @Id
    @GeneratedValue(strategy =GenerationType.AUTO)
    private Integer id;
    @Column(length = 120, nullable = false,unique = true)
    private String kode;
    @Column(name = "nama_materi",length = 150, nullable = false)
    private String namaMateri;
    @Temporal(TemporalType.DATE)
    private Date tanggal;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getNamaMateri() {
        return namaMateri;
    }

    public void setNamaMateri(String namaMateri) {
        this.namaMateri = namaMateri;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }
}
