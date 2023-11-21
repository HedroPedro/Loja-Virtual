package com.proj.loja.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proj.loja.model.Usuario;
import com.proj.loja.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService{
    @Autowired
    private UsuarioRepository repository;

    @Override
    public List<Usuario> getUsuarios(){
        return repository.findAll();
    }

    @Override
    public Usuario getUsuarioById(long id) {
        return repository.findById(id).get();
    }

    @Override
    public boolean addUsuario(Usuario usuario) {
        if(!isCPFCorreto(usuario.getCPF()))
            return false;

        return true;
    }

    @Override
    public boolean isCPFCorreto(String CPF){
        if(CPF.length() < 12){
            return false;
        }

        String CPFRecortado[] = CPF.split("\\D");
        String CPFFormatado = CPFRecortado[0] + CPFRecortado[1] + CPFRecortado[2];
        
        int firstNumber = Integer.valueOf(String.valueOf(CPFFormatado.charAt(0)));
        int lastFirstNumber = Integer.valueOf(String.valueOf(CPFRecortado[2].charAt(0)));
        int lastSecondNumber = Integer.valueOf(String.valueOf(CPFRecortado[2].charAt(1)));
        int sum = 0;
        for(int i = 0; i < CPFFormatado.length(); i++)
            sum += Integer.valueOf(String.valueOf(CPFFormatado.charAt(i)));
        if(sum == (firstNumber*11)){
            return false;
        }
        
        sum = 0; 
        for(int i = 0; i < CPFFormatado.length()-2; i++){
            sum += Integer.valueOf(String.valueOf(CPFFormatado.charAt(i))) * i+1;           
        }
        if((sum % 11) != lastFirstNumber){

            return false;
        }

        sum = 0;
        for(int i = 0; i < CPFFormatado.length()-1; i++){
            sum += Integer.valueOf(String.valueOf(CPFFormatado.charAt(i))) * i;           
        }
        if((sum % 11) != lastSecondNumber){
            return false;
        }

        return true;
    }

}
