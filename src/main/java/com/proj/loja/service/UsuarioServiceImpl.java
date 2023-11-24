package com.proj.loja.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proj.loja.model.Usuario;
import com.proj.loja.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService{
    @Autowired
    private UsuarioRepository repository;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public List<Usuario> getUsuarios(){
        return repository.findAll();
    }

    @Override
    public Usuario getUsuarioById(long id) {
        return repository.findById(id).get();
    }

    @Override
    public Usuario addUsuario(Usuario usuario) {
        if(!isCPFCorreto(usuario.getCPF()))
            return null;

        Usuario usuarioSaved = repository.save(usuario);
        return usuarioSaved;
    }

    @Override
    public boolean isCPFCorreto(String CPF){
        if(CPF.length() < 12){
            return false;
        }

        String CPFRecortado[] = CPF.split("\\D");
        String CPFFormatado = "";
        for(String s : CPFRecortado){
            CPFFormatado = CPFFormatado + s;
        }

        int firstNumber = Integer.valueOf(String.valueOf(CPFFormatado.charAt(0)));
        int lastFirstNumber = Integer.parseInt(String.valueOf(CPFFormatado.charAt(CPFFormatado.length()-2)));
        int lastSecondNumber = Integer.parseInt(String.valueOf(CPFFormatado.charAt(CPFFormatado.length()-1)));
        int sum = 0, modifer = 10;
        
        for(int i = 0; i < CPFFormatado.length(); i++)
            sum += Integer.valueOf(String.valueOf(CPFFormatado.charAt(i)));
        
        if(sum == (firstNumber*11)){
            return false;
        }
        
        sum = 0; 
        for(int i = 0; i < CPFFormatado.length()-2; i++){
            sum += modifer * Integer.valueOf(String.valueOf(CPFFormatado.charAt(i)));           
            modifer--;
        }

        logger.info(String.valueOf(CPFFormatado.charAt(CPFFormatado.length()-2)));
        if(((sum*10) % 11) != lastFirstNumber){
            if((sum*10 % 11) != 10){
                return false;
            }
        }

        sum = 0;
        modifer = 11;
        for(int i = 0; i < CPFFormatado.length()-1; i++){
            sum += Integer.valueOf(String.valueOf(CPFFormatado.charAt(i))) * i;
            modifer--;           
        }

        if(((sum*10) % 11) != lastSecondNumber){
            if((sum*10 % 11) != 10){
                return false;
            }
        }

        return true;
    }

}
