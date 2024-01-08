package com.proj.loja.service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        if(!isCPFCorreto(usuario.getCPF()) || isCPFInUse(usuario.getCPF())
        || !isEmailInUseOrInvalid(usuario.getEmail()))
            return null;
        byte salt[] = new byte[16];
        secureRandom.nextBytes(salt);
        md.update(salt);
        byte[] hashedSenha = md.digest(usuario.getSenha().getBytes(StandardCharsets.UTF_8));
        usuario.setSalt(new String(salt));
        usuario.setSenha(new String(hashedSenha));
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

    @Override
    public boolean isCPFInUse(String cpf){
        if(repository.existsByCPF(cpf))
            return true;
        return false;
    }

    @Override
    public boolean isEmailInUseOrInvalid(String email) {
        if(repository.existsByEmail(email) || 
        email.matches("[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}")) 
            return true;

        return false;
    }

    @Override
    public void deleteUsuario(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Usuario updateUsuario(Usuario usuario) {
        Usuario usuarioToBeUpdated = repository.findById(usuario.getId()).get();
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

    public void hashSenha(Usuario usuario){
        byte[] newSalt = new byte[16];
        secureRandom.nextBytes(newSalt);
        md.update(newSalt);
        byte[] hashedSenha = md.digest(usuario.getSenha().getBytes(StandardCharsets.UTF_8));
        usuario.setSalt(new String(newSalt));
        usuario.setSenha(new String(hashedSenha));
    }

}
