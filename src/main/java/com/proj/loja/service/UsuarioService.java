package com.proj.loja.service;

import java.util.List;

import com.proj.loja.model.Usuario;

public interface UsuarioService {
    /**
     * Get all the usuario in the database
     * @return the list of usuario in the database
     */
    List<Usuario> getUsuarios();

    /**
     * Get a usuario by its id, if the id doesn't exist in the databse then null is returned
     * @param id the id of the usuario to be retrieved from the databse
     * @return the desired usuario or null
     */
    Usuario getUsuarioById(long id);

    /**
     * Insert an usuario in the database before adding, check if its cpf and email are invalid or already in use,
     * if the cpf or email are not valid or in use then the usuario is not added in the database, returning null
     * @param usuario the usuario to be inserted in the database
     * @return the saved usuario in the database
     */
    Usuario addUsuario(Usuario usuario);

    /**
     * Deletes an usuario in the database by determined id, if the id doesn't exist the call to delete is ignored
     * @param id the id of the usuario to be deleted in the databse
     */
    void deleteUsuario(Long id);

    /**
     * Checks if given string is valid by checking its lenght and if it is mathematically correct
     * @param cpf the cpf to be validated, if it's invalid return false
     * @return if true if is in the apropriate lenght
     */
    boolean isCPFCorreto(String cpf);

    /**
     * Check if a given string is in use in the cpf field from an usuario in the databse
     * @param cpf the string to be checked
     * @return true if is in the database, false if it's not
     */
    public boolean isCPFInUse(String cpf);

    /**
     * Check if a given string is valid in the format of a email and if its already in the database
     * @param email The string to be check
     * @return true if the email is in the database or invalid, false if it's valid or not in the database
     */
    public boolean isEmailInUseOrInvalid(String email);

    /**
     * Update an already existing usuario in the databse, checking if the email and cpf is valid
     * @param usuario
     * @return
     */
    public Usuario updateUsuario(Usuario usuario);

    public void hashSenha(Usuario usuario);
}
