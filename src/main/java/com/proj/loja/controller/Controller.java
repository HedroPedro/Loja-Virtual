package com.proj.loja.controller;

import java.lang.reflect.Array;
import java.security.spec.InvalidKeySpecException;
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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@CrossOrigin
public class Controller {
    @Autowired
    private UsuarioServiceImpl usuarioService;

    @Autowired
    private ProdutoServiceImpl produtoService;
    
    @Autowired
    private PedidoServiceImpl pedidoService;

    @Operation(summary = "Entrega uma lista com todos os usuarios", responses = {
            @ApiResponse(responseCode = "200", description = "Lista com todos os usuarios existentes no banco de dados", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Array.class)))})
    @GetMapping(path = "/usuario")
    public ResponseEntity<List<Usuario>> getUsuarios(){
        return ResponseEntity.ok(usuarioService.getUsuarios());
    }

    @Operation(summary = "Pega o usuario pelo id", responses = {
        @ApiResponse(responseCode = "200", description = "Usuario com o id dado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class))),
        @ApiResponse(responseCode = "404", description = "Id não encontrado no banco de dados", content = @Content)
    })
    @GetMapping(path = "/usuario/{id}")
    public ResponseEntity<Usuario> getUsuario(@Parameter(description = "Id do usuario para ser encontrado dentro do banco de dados")
        @PathVariable long id){
        return ResponseEntity.ok(usuarioService.getUsuarioById(id));
    }

    @Operation(summary = "Pega os pedidos de um determinado usuario", responses = {@ApiResponse(responseCode = "200", description = "Pedidos com o usuario com o id dado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Array.class))),
    @ApiResponse(responseCode = "404", description = "Usuario com o id não econtrado", content = @Content)})
    @GetMapping(path = "/usuario/{id}/pedidos")
    public ResponseEntity<List<Pedido>> getUsuarioPedidos(@Parameter(description = "Id do usuario")
        @PathVariable long id){
        return ResponseEntity.ok(pedidoService.getPedidosByUsuarioId(id));
    }
    
    @Operation(summary = "Adiciona desejado usuario no banco de dados", responses = {
            @ApiResponse(responseCode = "200", description = "Usuario adicionado com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class))),
            @ApiResponse(responseCode = "400", description = "Usuario inválido com CPF e/ou email inválido", content = @Content)
        })
    @PostMapping(path = "/usuario")
    public ResponseEntity addUsuario(@RequestBody Usuario usuario){
        return ResponseEntity.ok(usuarioService.addUsuario(usuario));
    }

    @Operation(summary = "Deleta usuario com o id dado como caminho", responses = {
        @ApiResponse(responseCode = "200", description = "Usuario deletado", content = @Content)
    })
    @DeleteMapping(path = "/usuario/{id}")
    public ResponseEntity<Object> deleteUsuario(@Parameter(description = "Id do usuario para ser deletado")
        @PathVariable Long id){
        usuarioService.deleteUsuario(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Atualiza o usuario com o usuario dado em um json", responses = {
            @ApiResponse(responseCode = "200", description = "Usuario atualizado com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class))),
            @ApiResponse(responseCode = "400", description = "Usuario com CPF e/ou email inválido", content = @Content),
            @ApiResponse(responseCode = "404", description = "Usuario não econtrado com o id dado", content = @Content)
        })
    @PatchMapping(path = "/usuario")
    public ResponseEntity<Usuario> updateUsuario(@RequestBody Usuario usuario){
        return ResponseEntity.ok(usuarioService.updateUsuario(usuario));
    }

    @Operation(summary = "Pega a lista de todos os produtos", responses = {
        @ApiResponse(responseCode = "200", description = "Lista com todos os produtos", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Array.class)))
    })
    @GetMapping(path = "/produto")
    public ResponseEntity<List<Produto>> getProdutos(){
        return ResponseEntity.ok(produtoService.getProdutos());
    }

    @Operation(summary = "Pega o produto com um dado id", responses = {
        @ApiResponse(responseCode = "200", description = "Produto com o determinado id", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Produto.class))),
        @ApiResponse(responseCode = "404", description = "Produto com id que não pertence no banco de dados", content = @Content)
    })
    @GetMapping(path = "/produto/{id}")
    public ResponseEntity<Produto> getProduto(@Parameter(description = "id do produto para ser encontrado")
        @PathVariable Long id){
        return ResponseEntity.ok(produtoService.getProdutoById(id));
    }

    @Operation(summary = "Adiciona desejado produto no banco de dados", responses = {
        @ApiResponse(responseCode = "200", description = "Produto adicionado no banco de dados", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Produto.class))),
        @ApiResponse(responseCode = "400", description = "Produto com um usuario inválido", content = @Content()),
        @ApiResponse(responseCode = "404", description = "Usuario não encontrado", content = @Content())
    })
    @PostMapping(path = "/produto")
    public ResponseEntity<Produto> postProduto(@RequestBody Produto produto, @RequestBody Long id){
        return ResponseEntity.ok(produtoService.addProduto(produto, usuarioService.getUsuarioById(id)));
    }

    @Operation(summary = "Atualiza determinado produto", responses = {
        @ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Pedido.class))),
        @ApiResponse(responseCode = "400", description = "Produto com usuario inválido", content = @Content()),
        @ApiResponse(responseCode = "404", description = "Produto e/ou usuario não encontrado", content = @Content())
    })
    @PatchMapping(path = "/produto")
    public ResponseEntity<Produto> updateProduto(@RequestBody Produto produto, @RequestBody Long id){
        return ResponseEntity.ok(produtoService.updateProduto(produto, usuarioService.getUsuarioById(id).getTipoUsuario()));
    }

    @Operation(summary = "Deleta determinado produto pelo seu id", responses = {
        @ApiResponse(responseCode = "200", description = "Produto deletado com sucesso", content = @Content())})
    @DeleteMapping(path = "/produto/{id}")
    public ResponseEntity<Object> deleteProduto(@Parameter(description = "Id do produto para ser deletado")
        @PathVariable Long id){
        produtoService.deleteProduto(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Pega a lista com todos os pedidos", responses = {
        @ApiResponse(responseCode = "200", description = "Lista com todos os pedidos", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Pedido.class)))})
    @GetMapping(path = "/pedido")
    public ResponseEntity<List<Pedido>> getPedidos(){
        return ResponseEntity.ok(pedidoService.getPedidos());
    }

    @Operation(summary = "Pega um determinado pedido", responses = {
        @ApiResponse(responseCode = "200", description = "Pedido com o determinado id", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Pedido.class))),
        @ApiResponse(responseCode = "404", description = "Pedido não encontrado", content = @Content())
    })
    @GetMapping(path = "/pedido/{id}")
    public ResponseEntity getPedido(@Parameter(description = "Id do pedido a ser procurado")
        @PathVariable Long id){
        return ResponseEntity.ok(pedidoService.getPedidoById(id));
    }

    @Operation(summary = "Adiciona um pedido", responses = {   
        @ApiResponse(responseCode = "200", description = "Pedido adicionado no banco de dados", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Pedido.class))),
        @ApiResponse(responseCode = "400", description = "Pedido adicionado por um usuario inválido", content = @Content()),
        @ApiResponse(responseCode = "404", description = "Produto(s) e/ou usuario não encotrado", content = @Content())
    })
    @PostMapping(path = "/pedido")
    public ResponseEntity<Pedido> postPedido(@RequestBody Pedido pedido, @RequestBody Long UsuarioId){
        pedido.setUsuario(usuarioService.getUsuarioById(UsuarioId));
        pedidoService.addPedido(pedido);
        pedido.getProdutos().forEach((Produto produto) -> produtoService.updateProdutoQuantidade(produto));
        return ResponseEntity.ok(pedido);
    }

    @Operation(summary = "Checa o email e senha", responses = {
        @ApiResponse(responseCode = "200", description = "Retorna se a senha e o email são válidos", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Boolean.class))),
        @ApiResponse(responseCode = "400", description = "Usuario com email inválido", content = @Content()),
        @ApiResponse(responseCode = "404", description = "Usuario nao encontrado com o email", content = @Content())
    })
    @PostMapping(path = "/login")
    public ResponseEntity<Boolean> checkLogin(@RequestParam String email, @RequestParam String password) throws InvalidKeySpecException{
        return ResponseEntity.ok(usuarioService.checkCredentials(email, password));
    }
}
