package test.rest.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import test.rest.api.model.ProductModel;
import test.rest.api.repository.ProductRepository;

@RestController
public class ProductController {
    @Autowired
    private ProductRepository repository;

    @GetMapping(path = "/api/product/{id}")
    public ResponseEntity read(@PathVariable("id") Integer id) {
        return repository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(path = "/api/tax/{id}/{first}/{installment}")
    public String getTax(
            @PathVariable("id") Integer id,
            @PathVariable("first") Integer first, //primeira parcela
            @PathVariable("installment") Integer installment //quantidade de parcelas
    ) {

        float price = repository.findById(id).get().price;
        float price_minus_first = price - first;

        String str = "";
        float pct_ = 1.5F; //porcentagem das parcelas
        float pct = pct_; //usado pra somar as porcentagens
        float total = 0; //pra somar o total

        for (int i = 1; i <= installment; i++) {
            //valor individual da parcela sem o %
            float inst = (price_minus_first / installment);
            //acréscimo mensal do %
            pct = pct + 1.5F;
            //soma geral com porcentagem
            float sum = (inst + (inst * pct / 100));
            //saída
            str += i + "º parcela: " + sum + "\n";
            total += sum;
        }

        return "Preço atual: " + price + "\n" +
                "Primeira parcela de " + first + "\n" +
                "Preço menos primeira parcela: " + price_minus_first + "\n" +
                "Porcentagem de acréscimo mensal: " + pct_ + "%\n" +
                str + "\n" +
                "TOTAL: " + total;
    }

    @PostMapping(path = "/api/product/save")
    public ProductModel save(@RequestBody ProductModel product) {
        return repository.save(product);
    }

}
