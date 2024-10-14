package com.rincondeltaco.products_service.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product")
    private int codProd;

    @Column(name = "name_product")
    private String nomProd;

    @Column(name = "description_product")
    private String descProd;

    @Column(name = "price_product")
    private double precProd;

    @Column(name = "img_path")
    private String rutaImg;

    @Column(name = "id_category")
    private int codCatProd;

    @Column(name = "is_enabled")
    private boolean enabled;

    @ManyToOne
    @JoinColumn(name = "id_category", insertable = false, updatable = false)
    private Categoria ref_catProd;
}
