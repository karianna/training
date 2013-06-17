/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jclarity.advjava.ex2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author boxcat
 */
public class UpdateTest {
    
    
    @Test
    public void testHashMap() {
        Map<Author, List<Update>> hm = new HashMap<>();
        Author ben = new Author("Ben");
        List<Update> l = new ArrayList<>();
        Update.Builder b = new Update.Builder();
        
        l.add(b.author(ben).updateText("Hello World!").build());
        l.add(b.updateText("Goodnight Moon!").build());
        hm.put(ben, l);
        
        ben.name = "Bill";
        assertNotNull(hm.get(ben));
        assertEquals(2, hm.get(ben).size());
        ben.name = "CGO";
        assertNull(hm.get(ben));
    }

    @Test
    public void testSimpleHashMap() {
        SimpleHashMap hm = new SimpleHashMap();
        Author ben = new Author("Ben");
        List<Update> l = new ArrayList<>();
        Update.Builder b = new Update.Builder();
        
        l.add(b.author(ben).updateText("Hello World!").build());
        l.add(b.updateText("Goodnight Moon!").build());
        hm.put(ben, l);
        
        ben.name = "Bill";
        assertNotNull(hm.get(ben));
        assertEquals(2, ((List<Update>)hm.get(ben)).size());
        ben.name = "CGO";
        assertNull(hm.get(ben));
    }

    
}
