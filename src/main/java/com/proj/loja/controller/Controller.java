package com.proj.loja.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.proj.loja.model.Pedido;
import com.proj.loja.model.Produto;
import com.proj.loja.model.Usuario;
import com.proj.loja.service.PedidoServiceImpl;
import com.proj.loja.service.ProdutoServiceImpl;
import com.proj.loja.service.UsuarioServiceImpl;

@RestController
@CrossOrigin
public class Controller {
    @Autowired
    private UsuarioServiceImpl usuarioService;

    @Autowired
    private ProdutoServiceImpl produtoService;
    
    @Autowired
    private PedidoServiceImpl pedidoService;

    @GetMapping(path = "/usuario")
    public ResponseEntity<List<Usuario>> getUsuarios(){
        return ResponseEntity.ok(usuarioService.getUsuarios());
    }
    
    @PostMapping(path = "/login")
    public ResponseEntity<Boolean> checkLogin(@RequestParam String email, @RequestParam String password){
        return ResponseEntity.ok(usuarioService.checkCredentials(email, password));
    }

    @PostMapping(path = "/usuario")
    public ResponseEntity addUsuario(@RequestBody Usuario usuario){
        return ResponseEntity.ok(usuarioService.addUsuario(usuario));
    }

    @GetMapping(path = "/usuario/{id}")
    public ResponseEntity<Usuario> getUsuario(@PathVariable long id){
        return ResponseEntity.ok(usuarioService.getUsuarioById(id));
    }

    @GetMapping(path = "/usuario/{id}/pedidos")
    public ResponseEntity getUsuarioPedidos(@PathVariable long id){
        return ResponseEntity.ok(pedidoService.getPedidosByUsuarioId(id));
    }

    @DeleteMapping(path = "/usuario/{id}")
    public ResponseEntity deleteUsuario(@PathVariable Long id){
        usuarioService.deleteUsuario(id);
        return ResponseEntity.ok("Usuario deletado");
    }

    @PatchMapping(path = "/usuario")
    public ResponseEntity updateUsuario(@RequestBody Usuario usuario){
        return ResponseEntity.ok(usuarioService.updateUsuario(usuario));
    }

    @GetMapping(path = "/produto")
    public ResponseEntity<List<Produto>> getProdutos(){
        return ResponseEntity.ok(produtoService.getProdutos());
    }

    @GetMapping(path = "/produto/{id}")
    public ResponseEntity<Produto> getProduto(@PathVariable Long id){
        return ResponseEntity.ok(produtoService.getProdutoById(id));
    }

    @PostMapping(path = "/produto")
    public ResponseEntity<Produto> postProduto(@RequestBody Produto produto){
        return ResponseEntity.ok(produtoService.addProduto(produto));
    }

    @PatchMapping(path = "/produto")
    public ResponseEntity updateProduto(@RequestBody Produto produto){
        return ResponseEntity.ok(produtoService.updateProduto(produto));
    }

    @DeleteMapping(path = "/produto/{id}")
    public ResponseEntity deleteProduto(@PathVariable Long id){
        produtoService.deleteProduto(id);
        return ResponseEntity.ok(id + " deletado");
    }

    @GetMapping(path = "/pedido")
    public ResponseEntity<List<Pedido>> getPedidos(){
        return ResponseEntity.ok(pedidoService.getPedidos());
    }

    @GetMapping(path = "/pedido/{id}")
    public ResponseEntity getPedido(@PathVariable Long id){
        return ResponseEntity.ok(pedidoService.getPedidoById(id));
    }

    @PostMapping(path = "/pedido")
    public ResponseEntity postPedido(@RequestBody Pedido pedido, @RequestBody Long UsuarioId){
        pedido.setUsuario(usuarioService.getUsuarioById(UsuarioId));
        pedidoService.addPedido(pedido);
        pedido.getProdutos().forEach((Produto produto) -> produtoService.updateProdutoQuantidade(produto));
        return ResponseEntity.ok(pedido);
    }
}
