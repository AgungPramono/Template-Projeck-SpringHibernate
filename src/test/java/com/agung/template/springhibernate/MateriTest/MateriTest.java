/*
 *  Copyright (c) 2015 Agung Pramono <agungpermadi13@gmail.com || www.github.com/agung pramono>.
 *  All rights reserved.
 * 
 * Silahkan digunakan dengan bebas / dimodifikasi
 * Dengan tetap mencantumkan nama @author dan Referensi / Source
 * Terima Kasih atas Kerjasamanya.
 * 
 *  MateriTest.java
 * 
 *  Created on Nov 14, 2015, 10:03:50 PM
 */
package com.agung.template.springhibernate.MateriTest;

import com.agung.template.springhibernate.entity.Materi;
import com.agung.template.springhibernate.service.MateriService;

import java.io.File;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.dataset.CompositeDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;

import org.junit.Assert;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author agung
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:/config/ApplicationConfig.xml")
public class MateriTest {
    
    @Autowired private DataSource ds;
    @Autowired private MateriService materiService;
    
    @Before
    public void resetDatabase()throws Exception{
        
        IDataSet [] daftarDataSets = new IDataSet[]{
            new FlatXmlDataSetBuilder().build(new File("src/test/resources/data/sample-data.xml"))
        };
        
        Connection con = ds.getConnection();
        DatabaseOperation.CLEAN_INSERT
                .execute(new DatabaseConnection(con), 
                 new CompositeDataSet(daftarDataSets));
    }
    
    @Test
    public void testSimpan() throws SQLException{
        Materi m = new Materi();
        m.setKode("dm-004");
        m.setNamaMateri("Data Mining Technology");
        m.setTanggal(new Date());
        
        materiService.simpan(m);
        
        String sql = "select count(*) as jumlah from m_materi where kode='dm-004'";
        Connection con = ds.getConnection();
        ResultSet rs = con.createStatement().executeQuery(sql);
        
        Assert.assertTrue(rs.next());
        
        Integer jumlahRow = rs.getInt("jumlah");
        Assert.assertEquals(1L, jumlahRow.longValue());
        
        con.close();
    }
    
    public void testHapus(){
        //tidak perlu ditest krn method bawaan framework minus kesalahan
    }
    
    @Test
    public void testCariSemua(){
        List<Materi> result = materiService.getAll();
        Assert.assertNotNull(result);
        Assert.assertFalse(result.isEmpty());
        for(Materi m:result){
            assertNotNull(m.getId());
            assertNotNull(m.getNamaMateri());
            assertNotNull(m.getTanggal());
            assertNotNull(m.getKode());
        }
    }
    
    @Test
    public void testCariById() throws SQLException{
        List<Materi> result = materiService.getAll();
        Integer id = result.get(2).getId();
        
        Materi m = materiService.findById(id);
        Assert.assertNotNull(m);
        Assert.assertEquals("Java Fundamental", m.getNamaMateri());
    }
    
    @Test
    public void testCariByNama(){
        List<Materi> result = materiService.findByNama("java");
        Assert.assertNotNull(result);
        Assert.assertTrue(result.size()==2);
    }
    
    @Test
    public void testHitungByNama(){
        Long jumlah = materiService.countByNama("java");
        Assert.assertNotNull(jumlah);
        Assert.assertEquals(2L, jumlah.longValue());
        Assert.assertEquals(Long.valueOf(1), Long.valueOf(materiService.countByNama("android")));
        Assert.assertEquals(Long.valueOf(1), Long.valueOf(materiService.countByNama("ios")));
    }
    
    @Test
    public void testHitungSemua(){
        Long jumlah = materiService.countAll();
        Assert.assertNotNull(jumlah);
        Assert.assertEquals(4L, jumlah.longValue());
    }
    
}
