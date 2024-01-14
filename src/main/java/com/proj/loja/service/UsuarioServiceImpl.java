package com.proj.loja.service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proj.loja.exceptions.UsuarioNotFoundException;
import com.proj.loja.model.Usuario;
import com.proj.loja.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService{
    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private SecureRandom secureRandom;

    @Autowired
    private MessageDigest md;

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Override
    public List<Usuario> getUsuarios(){
        return repository.findAll();
    }

    @Override
    public Usuario getUsuarioById(long id) {
        return repository.findById(id).orElseThrow(() -> new UsuarioNotFoundException("Usuario de id " + id + " não encontrado"));
    }

    @Override
    public Usuario addUsuario(Usuario usuario) {
        if(!isCPFCorreto(usuario.getCPF()) || isCPFInUse(usuario.getCPF())
        || isEmailInUseOrInvalid(usuario.getEmail()))
            return null;
        hashSenha(usuario);
        Usuario usuarioSaved = repository.save(usuario);
        return usuarioSaved;
    }

    @Override
    public boolean isCPFCorreto(String CPF){
        String CPFRecortado[] = CPF.split("\\D");
        String CPFFormatado = "";
        for(String s : CPFRecortado){
            CPFFormatado = CPFFormatado + s;
        }
        LOGGER.info(CPFFormatado);
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

        if(((sum*10) % 11) != lastFirstNumber){
            if((sum*10 % 11) != 10)
                return false;
        }

        sum = 0;
        modifer = 11;
        for(int i = 0; i < CPFFormatado.length()-1; i++){
            sum += modifer * Integer.valueOf(String.valueOf(CPFFormatado.charAt(i)));
            modifer--;
        }

        if(((sum*10) % 11) != lastSecondNumber){
            if((sum*10 % 11) != 10)
                return false;
        }
        return true;
    }

    @Override
    public boolean isCPFInUse(String cpf){
        if(repository.existsByCPF(cpf))
            return true;
        return false;
    }

    @Override
    public boolean isEmailInUseOrInvalid(String email) {
        if(repository.existsByEmail(email) || 
        !email.matches("[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}")) 
            return true;
        return false;
    }

    @Override
    public void deleteUsuario(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Usuario updateUsuario(Usuario usuario) {
        Usuario usuarioToBeUpdated = repository.findById(usuario.getId()).orElseThrow(() -> new UsuarioNotFoundException("Usuario de id " + usuario.getId() + " não encontrado"));
        if(!usuarioToBeUpdated.getCPF().equals(usuario.getCPF())){
            if(!isCPFCorreto(usuario.getCPF()) || isCPFInUse(usuario.getCPF()))
                return null;
        }

        if(!usuarioToBeUpdated.getEmail().equals(usuario.getEmail())){
            if(!isEmailInUseOrInvalid(usuario.getEmail()))
                return null;
        }

        hashSenha(usuarioToBeUpdated);
        usuarioToBeUpdated.setName(usuario.getName());
        usuarioToBeUpdated.setEmail(usuario.getEmail());
        usuarioToBeUpdated.setTipoUsuario(usuario.getTipoUsuario());
        usuarioToBeUpdated.setCPF(usuario.getCPF());
        repository.save(usuarioToBeUpdated);
        return usuarioToBeUpdated;
    }

    @Override
    //todo: Mudar para um método mais seguro 
    public void hashSenha(Usuario usuario){
        byte[] newSalt = new byte[16];
        secureRandom.nextBytes(newSalt);
        md.update(newSalt);
        byte[] hashedSenha = md.digest(usuario.getSenha().getBytes(StandardCharsets.UTF_8));
        usuario.setSalt(new String(newSalt));
        usuario.setSenha(new String(hashedSenha));
    }

}
