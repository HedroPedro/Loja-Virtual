package com.proj.loja.service;

import java.security.spec.InvalidKeySpecException;
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
     * @throws UsuarioNotFoundException if the the usuario is not found by the given id
     * @return the desired usuario or null
     */
    Usuario getUsuarioById(long id);

    /**
     * Insert an usuario in the database before adding, check if its cpf and email are invalid or already in use,
     * if the cpf or email are not valid or in use then the usuario is not added in the database and an exception is thrown, returning null
     * @param usuario the usuario to be inserted in the database
     * @throws InvalidUsuarioException only is thrown when the email is in use or invalid or the cpf is in use by another
     * entity in the databse or is invalid
     * @return the saved usuario in the database
     */
    Usuario addUsuario(Usuario usuario);

    /**
     * Deletes an usuario in the database by determined id, if the id doesn't exist the call to delete is ignored
     * @param id the id of the usuario to be deleted in the database
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

    public boolean isEmailInUse(String email);

    /**
     * Check if a given string is valid in the format of a email and if its already in the database
     * @param email The string to be check
     * @return true if the email is in the database or invalid, false if it's valid or not in the database
     */
    public boolean isEmailInvalid(String email);

    /**
     * Update an already existing usuario in the databse, checking if the email and cpf is valid and not in use by another usuario
     * if's not then returns null
     * @param usuario the usuario to be updated in the databse, it's only updated with a valid cpf and email
     * @throws InvalidUsuarioException only happens if the email is invalid or in use by another entity in the databse or if the cpf is 
     * invalid or being use by another entity in the database
     * @return the updated usuario in the databse or null if it's invallid
     */
    public Usuario updateUsuario(Usuario usuario);

    /**
     * Hashes given string and insert it in the usuario, only alters (if has any) the hash of the usuario and the password
     * @param usuario the usuario to have the password to be hashed, each time is given a unique hash to it
     */
    //To do: colocar um sistema melhor de hashing
    public void hashSenha(Usuario usuario);

    public boolean checkCredentials(String email, String password) throws InvalidKeySpecException;
}
