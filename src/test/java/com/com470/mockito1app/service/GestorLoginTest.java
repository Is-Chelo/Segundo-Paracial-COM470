/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.com470.mockito1app.service;

import com.com470.mockito1app.controller.ExcepcionCuentaEnUso;
import com.com470.mockito1app.controller.ExcepcionUsuarioDesconocido;
import com.com470.mockito1app.controller.ICuenta;
import com.com470.mockito1app.controller.IRepositorioCuentas;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.OngoingStubbing;

/**
 *
 * @author CHELO
 */
public class GestorLoginTest {
    
    @Mock
    ICuenta cuenta;
    
    @Mock
    IRepositorioCuentas repo;
    
    @InjectMocks
    GestorLogin login;
    
    public GestorLoginTest() {
    }
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test 
    public void testInicio(){
        Mockito.when(repo.buscar("pepe")).thenReturn(cuenta);
    }
    
    /**
     * Test of acceder method, of class GestorLogin.
     */
//    @Test
//    public void testAccesoAlaPrimera() {
//        Mockito.when(cuenta.claveCorrecta("1234")).thenReturn(true);
//        
//       
//    }
    
   
    
    @Test(expected = ExcepcionUsuarioDesconocido.class)
    public void testUsuarioIncorrecto() {
        
       when(repo.buscar("bartolo")).thenReturn(null);
       login.acceder("bartolo", "1234");
       
    }
    
    @Test(expected = ExcepcionCuentaEnUso.class)
    public void testUsuarioCuentaEnUso() {
        
       Mockito.when(repo.buscar("pepe")).thenReturn(cuenta);
       when(cuenta.estaEnUso()).thenReturn(true);
       login.acceder("pepe", "1234");
       
    }
    
    @Test
    public void testEstaCuentaEstaBloqueada(){
 
       Mockito.when(repo.buscar("pepe")).thenReturn(cuenta);
       when(cuenta.estaBloqueada()).thenReturn(false);
       login.acceder("pepe", "1234");
       
    }
    
    @Test
    public void testAccesoConsedidoALaprimera(){
 
       Mockito.when(repo.buscar("pepe")).thenReturn(cuenta);
       when(cuenta.claveCorrecta("1234")).thenReturn(true);
       login.acceder("pepe", "1234");
       
    }
    
    @Test
    public void testCuenta1intento(){
 
       Mockito.when(repo.buscar("pepe")).thenReturn(cuenta);
       
       when(cuenta.claveCorrecta("1234")).thenReturn(false);

       login.acceder("pepe", "1234"); 
       login.acceder("pepe", "1234"); 
       login.acceder("pepe", "1234"); 
       login.acceder("pepe", "1234"); 

       
    }
    
    
}
